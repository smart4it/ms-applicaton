package ru.itstamag.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itstamag.application.dao.entity.Experience;
import ru.itstamag.application.dao.repository.ExperienceRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExperienceService {
    private final ExperienceRepository experienceRepository;

    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }
}