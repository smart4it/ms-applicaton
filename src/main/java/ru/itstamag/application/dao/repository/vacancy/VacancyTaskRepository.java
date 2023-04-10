package ru.itstamag.application.dao.repository.vacancy;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itstamag.application.dao.entity.vacancy.VacancyTask;

import java.util.UUID;

public interface VacancyTaskRepository extends JpaRepository<VacancyTask, UUID> {
}
