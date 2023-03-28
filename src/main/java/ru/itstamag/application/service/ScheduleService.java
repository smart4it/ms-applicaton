package ru.itstamag.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itstamag.application.dao.entity.Schedule;
import ru.itstamag.application.dao.repository.ScheduleRepository;
import ru.itstamag.application.web.client.hh.dto.DictionariesDto;
import ru.itstamag.application.web.client.hh.dto.SchedulesDto;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository repository;

    public void saveSchedulesByDictionariesAndSave(DictionariesDto dictionariesDto) {
        List<Schedule> schedulesByDictionaries = createSchedule(dictionariesDto);
        repository.saveAll(schedulesByDictionaries);
    }
    private List<Schedule> createSchedule(DictionariesDto dictionaries) {
        return dictionaries.schedule().stream()
                .map(dto -> new Schedule(dto.id(), dto.name()))
                .toList();
    }

    public List<SchedulesDto> schedules() {
        return repository.findAll().stream().map(i -> new SchedulesDto(i.getId(), i.getName())).toList();
    }
}
