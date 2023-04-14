package ru.itstamag.application.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.support.TransactionTemplate;
import ru.itstamag.application.IntegrationTestBase;
import ru.itstamag.application.dao.entity.vacancy.Status;
import ru.itstamag.application.dao.entity.vacancy.VacancySubtask;
import ru.itstamag.application.dao.entity.vacancy.VacancyTask;
import ru.itstamag.application.dao.repository.vacancy.VacancyRepository;
import ru.itstamag.application.dao.repository.vacancy.VacancySnapshotRepository;
import ru.itstamag.application.dao.repository.vacancy.VacancySubtaskRepository;
import ru.itstamag.application.dao.repository.vacancy.VacancyTaskRepository;
import ru.itstamag.application.web.client.hh.HhClient;

import java.util.List;
import java.util.stream.Collectors;


@Sql({
        "classpath:sql/vacancy_service.sql"
})
class VacancyServiceTest extends IntegrationTestBase {

    @Autowired
    private VacancyRepository vacancyRepository;
    @Autowired
    private VacancyTaskRepository vacancyTaskRepository;
    @Autowired
    private VacancySubtaskRepository vacancySubtaskRepository;
    @Autowired
    private VacancySnapshotRepository vacancySnapshotRepository;
    @Autowired
    private @Qualifier("fakeHhClient") HhClient hhClient;
    @Autowired
    private VacanciesSearchParamsService vacanciesSearchParamsService;
    @Autowired
    private TransactionTemplate transactionTemplate;

    private  VacancyService vacancyService;

    @BeforeEach
    void initVacancyService() {
        vacancyService = new VacancyService(vacancyRepository, vacancyTaskRepository, vacancySubtaskRepository,
                vacancySnapshotRepository, hhClient, vacanciesSearchParamsService, transactionTemplate);
    }

    @Test
    void createTask() {
        vacancyService.createTaskToSaveVacancies();

        List<VacancyTask> tasks = vacancyTaskRepository.findAll();
        VacancyTask task = tasks.get(0);
        List<VacancySubtask> subtask = vacancySubtaskRepository.findAllByVacancyTaskId(task.getId());

        Assertions.assertEquals(1, tasks.size());
        Assertions.assertNotNull(task);
        Assertions.assertEquals(Status.IN_PROGRESS, task.getStatus());
        Assertions.assertEquals(20, subtask.size());
    }

}