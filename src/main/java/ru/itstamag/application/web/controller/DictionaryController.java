package ru.itstamag.application.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itstamag.application.dao.entity.Employment;
import ru.itstamag.application.dao.entity.Experience;
import ru.itstamag.application.dao.entity.Schedule;
import ru.itstamag.application.service.EmploymentService;
import ru.itstamag.application.service.ExperienceService;
import ru.itstamag.application.service.ScheduleService;
import ru.itstamag.application.web.client.hh.dto.SchedulesDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dictionary")
public class DictionaryController {

    private final ExperienceService experienceService;
    private final EmploymentService employmentService;

    private final ScheduleService scheduleService;

    @CrossOrigin
    @GetMapping("/experience")
    public List<Experience> getAllExperiences() {
        return experienceService.findAll();
    }

    @CrossOrigin
    @GetMapping("/employment")
    public List<Employment> getAllEmployment() {
        return employmentService.findAll();
    }

    @CrossOrigin
    @GetMapping("/schedules")
    public List<SchedulesDto> schedules() {
        return scheduleService.schedules();
    }

}