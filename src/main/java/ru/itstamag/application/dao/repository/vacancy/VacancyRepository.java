package ru.itstamag.application.dao.repository.vacancy;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itstamag.application.dao.entity.vacancy.Vacancy;

import java.util.UUID;

public interface VacancyRepository extends JpaRepository<Vacancy, UUID> {
}
