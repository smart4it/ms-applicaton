package ru.itstamag.application.fake;

import org.springframework.stereotype.Component;
import ru.itstamag.application.web.client.hh.HhClient;
import ru.itstamag.application.web.client.hh.dto.DictionariesDto;
import ru.itstamag.application.web.client.hh.dto.FoundDto;
import ru.itstamag.application.web.client.hh.dto.SchedulesDto;
import ru.itstamag.application.web.client.hh.dto.VacanciesDto;

import java.util.List;
import java.util.Map;

@Component
public class FakeHhClient implements HhClient {
    @Override
    public FoundDto countVacancies(Map<String, String> params) {
        return new FoundDto(1L);
    }

    @Override
    public DictionariesDto dictionaries() {
        return new DictionariesDto(
                List.of(new DictionariesDto.IdNameDto("id", "experience")),
                List.of(new DictionariesDto.IdNameDto("id", "employment")),
                List.of(new SchedulesDto("id", "schedule")));
    }

    @Override
    public VacanciesDto vacancies(String text, int page) {
        return new VacanciesDto(
                List.of(Map.of("id", "1")),
                1,
                0
        );
    }

    @Override
    public FoundDto vacanciesSize(String text) {
        return new FoundDto(1L);
    }
}
