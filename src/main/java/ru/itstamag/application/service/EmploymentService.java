package ru.itstamag.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itstamag.application.dao.entity.Employment;
import ru.itstamag.application.dao.repository.EmploymentRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmploymentService {
    private final EmploymentRepository employmentRepository;

    public List<Employment> findAll() {
        return employmentRepository.findAll();
    }
}