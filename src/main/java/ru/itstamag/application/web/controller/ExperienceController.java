package ru.itstamag.application.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itstamag.application.dao.entity.Experience;
import ru.itstamag.application.service.ExperienceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/experience")
public class ExperienceController {

    private final ExperienceService service;

    @GetMapping
    public List<Experience> getAllExperiences() {
        return service.findAll();
    }
}