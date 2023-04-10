package ru.itstamag.application.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itstamag.application.dao.entity.VacanciesSearchParam;
import ru.itstamag.application.service.VacanciesSearchParamsService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vacancies")
public class VacanciesSearchParamsController {

    private final VacanciesSearchParamsService vacanciesSearchParamsService;

    @CrossOrigin
    @GetMapping("/search-parameters")
    public List<VacanciesSearchParam> vacanciesSearchParamList() {
        return vacanciesSearchParamsService.parameters();
    }

    @CrossOrigin
    @PostMapping("/search-parameters")
    public void save(@RequestBody VacanciesSearchParam parameters) {
        vacanciesSearchParamsService.save(parameters);
    }

    @CrossOrigin
    @PatchMapping("/search-parameters")
    public void update(@RequestParam UUID id) {
        vacanciesSearchParamsService.update(id);
    }
}