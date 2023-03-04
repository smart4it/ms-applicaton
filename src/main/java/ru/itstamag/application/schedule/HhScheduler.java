package ru.itstamag.application.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.itstamag.application.repository.HhCounterEntity;
import ru.itstamag.application.repository.HhCounterRepository;
import ru.itstamag.application.web.client.hh.HhClient;
import ru.itstamag.application.web.client.hh.dto.FoundDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class HhScheduler {

    private final HhClient hhClient;
    private final HhCounterRepository hhCounterRepository;

    @Scheduled(cron = "0 0 0/6 * * ?")
    public void logInfo() {
        log.info("HhScheduler.logInfo() started");
        FoundDto vacancies = hhClient.vacancies();
        HhCounterEntity hhCounterEntity = new HhCounterEntity(UUID.randomUUID(), vacancies.found().intValue(), "java",
                                                              LocalDate.now(), LocalTime.now());
        hhCounterRepository.save(hhCounterEntity);
        log.info("HhScheduler.logInfo() completed: counter = {}", hhCounterEntity);
    }
}