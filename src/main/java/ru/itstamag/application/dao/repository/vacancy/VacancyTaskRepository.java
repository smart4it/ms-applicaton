package ru.itstamag.application.dao.repository.vacancy;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itstamag.application.dao.entity.vacancy.Status;
import ru.itstamag.application.dao.entity.vacancy.VacancyTask;

import java.util.Optional;
import java.util.UUID;

public interface VacancyTaskRepository extends JpaRepository<VacancyTask, UUID> {

    Optional<VacancyTask> findFirstByStatus(Status status);
}
