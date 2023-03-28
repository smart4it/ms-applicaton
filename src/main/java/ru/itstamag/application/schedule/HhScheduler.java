package ru.itstamag.application.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.itstamag.application.dao.entity.Employment;
import ru.itstamag.application.dao.entity.Experience;
import ru.itstamag.application.dao.entity.HhCounterEntity;
import ru.itstamag.application.dao.repository.EmploymentRepository;
import ru.itstamag.application.dao.repository.ExperienceRepository;
import ru.itstamag.application.dao.repository.HhCounterRepository;
import ru.itstamag.application.service.ScheduleService;
import ru.itstamag.application.web.client.hh.HhClient;
import ru.itstamag.application.web.client.hh.dto.DictionariesDto;
import ru.itstamag.application.web.client.hh.dto.FoundDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class HhScheduler {

    private final HhClient hhClient;
    private final HhCounterRepository hhCounterRepository;
    private final ExperienceRepository experienceRepository;
    private final EmploymentRepository employmentRepository;

    private final ScheduleService scheduleService;

    @Scheduled(cron = "0 0 0/6 * * ?")
    public void logInfo() {
        log.info("HhScheduler.logInfo() started");
        FoundDto vacancies = hhClient.vacancies();
        HhCounterEntity hhCounterEntity = new HhCounterEntity(UUID.randomUUID(), vacancies.found().intValue(), "java",
                                                              LocalDate.now(), LocalTime.now());
        hhCounterRepository.save(hhCounterEntity);
        log.info("HhScheduler.logInfo() completed: counter = {}", hhCounterEntity);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron = "0 0 1 * * ?")
    public void updateDictionaries() {
        log.info("HhScheduler.updateDictionaries() started");
        DictionariesDto dictionaries = hhClient.dictionaries();
        List<Experience> experiences = dictionaries.experience()
                                                   .stream()
                                                   .map(dto -> new Experience(dto.id(), dto.name()))
                                                   .toList();
        List<Employment> employments = dictionaries.employment()
                                                   .stream()
                                                   .map(dto -> new Employment(dto.id(), dto.name()))
                                                   .toList();
        experienceRepository.saveAll(experiences);
        employmentRepository.saveAll(employments);
        log.info("HhScheduler.updateDictionaries() completed: data = {}", dictionaries);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron = "0 10 1 * * ?")
    public void updateScheduled() {
        log.info("HhScheduler.updateScheduled() started");
        DictionariesDto dictionaries = hhClient.dictionaries();
        scheduleService.saveSchedulesByDictionariesAndSave(dictionaries);
        log.info("HhScheduler.updateScheduled() completed: data = {}", dictionaries);
    }
}