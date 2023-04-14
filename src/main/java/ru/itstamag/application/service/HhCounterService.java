package ru.itstamag.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itstamag.application.dao.entity.*;
import ru.itstamag.application.dao.repository.HhCounterRepository;
import ru.itstamag.application.service.params.RequestParams;
import ru.itstamag.application.web.client.hh.HhClient;
import ru.itstamag.application.web.client.hh.dto.FoundDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class HhCounterService {

    private final HhCounterRepository repository;

    private final HhClient hhClient;

    public void saveHhCounterBySearchParameters() {
        SearchParameters parameters = searchParameters();
        FoundDto vacancies = hhClient.countVacancies(params(parameters));

        HhCounterEntity hhCounterEntity = new HhCounterEntity(UUID.randomUUID(), vacancies.found().intValue(), parameters.getText(),
                parameters, LocalDate.now(), LocalTime.now());
        repository.save(hhCounterEntity);

    }

    private SearchParameters searchParameters() {

        return new SearchParameters(UUID.randomUUID(), "java", null, null, null, LocalDate.now(), LocalTime.now());
    }

    private Map<String, String> params(SearchParameters parameters) {
        Map<String, String> params = new HashMap<>();

        Optional.ofNullable(parameters.getText())
                .ifPresent(v -> params.put(RequestParams.TEXT.value(), parameters.getText()));
        Optional.ofNullable(parameters.getExperience())
                .ifPresent(v -> params.put(RequestParams.EXPERIENCE.value(), parameters.getExperience().getName()));
        Optional.ofNullable(parameters.getEmployment())
                .ifPresent(v -> params.put(RequestParams.EMPLOYMENT.value(), parameters.getEmployment().getName()));
        Optional.ofNullable(parameters.getSchedule())
                .ifPresent(v -> params.put(RequestParams.SCHEDULE.value(), parameters.getSchedule().getName()));
        return params;
    }
}
