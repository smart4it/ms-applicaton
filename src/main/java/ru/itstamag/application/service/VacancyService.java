package ru.itstamag.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.itstamag.application.dao.entity.vacancy.*;
import ru.itstamag.application.dao.repository.vacancy.VacancyRepository;
import ru.itstamag.application.dao.repository.vacancy.VacancySnapshotRepository;
import ru.itstamag.application.dao.repository.vacancy.VacancySubtaskRepository;
import ru.itstamag.application.dao.repository.vacancy.VacancyTaskRepository;
import ru.itstamag.application.web.client.hh.HhClient;
import ru.itstamag.application.web.client.hh.dto.FoundDto;
import ru.itstamag.application.web.client.hh.dto.VacanciesDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final VacancyTaskRepository vacancyTaskRepository;
    private final VacancySubtaskRepository vacancySubtaskRepository;
    private final VacancySnapshotRepository vacancySnapshotRepository;
    private final HhClient hhClient;
    private final VacanciesSearchParamsService vacanciesSearchParamsService;

    public void createTaskToSaveVacancies() {
        LocalDateTime created = LocalDateTime.now();
        createTask(created);
    }

    private void createTask(LocalDateTime created) {
        for (var parameter : vacanciesSearchParamsService.parameters()) {
            FoundDto foundDto = hhClient.vacanciesSize(parameter.getText());
            VacancyTask task = new VacancyTask(UUID.randomUUID(), parameter.getText(), created, foundDto.found().intValue(),
                    Status.IN_PROGRESS);
            vacancyTaskRepository.save(task);
            createSubTask(task);
        }
    }
    private void createSubTask(VacancyTask task) {
        int page = 0;
        while (page < 20) {
            vacancySubtaskRepository.save(new VacancySubtask(UUID.randomUUID(), page++, false, task));
        }
        saveVacancy();
    }

    //взять задачу, если она есть в прогресе то выбрать подзадачу. должен заблокировать подзадачу что бы никто другой её не взял
    // после того как выполнил поставил done. Работа с подзадачей и сохранение вакансий должна выполнятся в транзакции.
    @Scheduled(cron = "0 1 * * * ?")
    private void saveVacancy() {
        PageRequest pageable = PageRequest.of(0, 1, Sort.by("page"));
        var page = vacancySubtaskRepository.findAllBy(pageable);
        while (page.hasNext()) {
            List<VacancySubtask> subtasks = page.getContent().stream().filter(Predicate.not(VacancySubtask::isDone)).toList();
            for (var subtask : subtasks) {
                VacanciesDto json = hhClient.vacancies(subtask.getVacancyTask().getSearchParams(), subtask.getPage());
                List<Vacancy> vacancies = vacanciesByVacanciesDto(json, subtask.getVacancyTask().getStart());
                vacancyRepository.saveAll(vacancies);
                saveVacancySnapshot(vacancies, subtask.getVacancyTask());
                subtask.setDone(true);
                vacancySubtaskRepository.save(subtask);
            }
            executionControl();
            page = vacancySubtaskRepository.findAllBy(page.nextPageable());
        }
    }

    private List<Vacancy> vacanciesByVacanciesDto(VacanciesDto vacanciesDto, LocalDateTime created) {
        List<Vacancy> vacancies = new ArrayList<>();
        for (var json : vacanciesDto.items()) {
            String vacancyId = json.get("id").toString();
            Vacancy vacancy = new Vacancy(UUID.randomUUID(), vacancyId, json, created);
            vacancies.add(vacancy);
        }
        return vacancies;
    }

    private void saveVacancySnapshot(List<Vacancy> vacancies, VacancyTask vacancyTask) {
        for (var vacancy : vacancies) {
            VacancySnapshot snapshot = VacancySnapshot.builder()
                    .vacancy(vacancy)
                    .vacancyTask(vacancyTask)
                    .date(vacancy.getCreated())
                    .build();
            vacancySnapshotRepository.save(snapshot);
        }
    }

    private void executionControl() {
        LocalDateTime now = LocalDateTime.now();

        PageRequest pageable = PageRequest.of(0, 20, Sort.by("vacancyTask"));
        var page = vacancySubtaskRepository.findAllBy(pageable);
        while (!page.isEmpty()) {
            VacancyTask task = page.getContent().get(0).getVacancyTask();
            boolean isCompleted = page.getContent()
                    .stream()
                    .filter(VacancySubtask::isDone)
                    .filter(i -> i.getVacancyTask().getStart().isAfter(now.minusHours(1)))
                    .count() == page.getTotalElements();

            task.setStatus(isCompleted ? Status.COMPLETED : Status.ERROR);
            deleteTaskAndSubTask(task);
            vacancyTaskRepository.save(task);
        }
    }

    private void deleteTaskAndSubTask(VacancyTask vacancyTask) {
        if (vacancyTask.getStatus().equals(Status.ERROR)) {
            vacancySubtaskRepository.deleteAllByVacancyTaskId(vacancyTask.getId());
            List<Vacancy> vacancies = vacancySnapshotRepository.findAllByVacancyTaskId(vacancyTask.getId())
                    .stream()
                    .map(VacancySnapshot::getVacancy)
                    .toList();
            vacancyRepository.deleteAll(vacancies);
            vacancySnapshotRepository.deleteAllByVacancyTaskId(vacancyTask.getId());
        }
    }
}