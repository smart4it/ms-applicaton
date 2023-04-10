package ru.itstamag.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itstamag.application.dao.entity.VacanciesSearchParam;
import ru.itstamag.application.dao.repository.VacanciesSearchParamRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VacanciesSearchParamsService {

    private final VacanciesSearchParamRepository repository;

    public List<VacanciesSearchParam> parameters() {
        return repository.findAll();
    }

    public void save(VacanciesSearchParam parameter) {
        repository.save(parameter);
    }

    @Transactional
    public void update(UUID id) {
        repository.findById(id).ifPresent(i -> {
            i.setDisabled(true);
            repository.save(i);
        });
    }
}

