package ru.itstamag.application.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itstamag.application.dao.entity.HhCounterEntity;
import ru.itstamag.application.dao.repository.HhCounterRepository;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final HhCounterRepository hhCounterRepository;

    @CrossOrigin
    @GetMapping("/info")
    public Page<HhCounterEntity> getInfo(@PageableDefault Pageable pageable) {
        log.info("ApplicationController.getInfo started: pageable = {}", pageable);
        Page<HhCounterEntity> page = hhCounterRepository.findAll(pageable);
        log.info("ApplicationController.getInfo completed: total = {}", page.getTotalElements());
        return page;
    }
}
