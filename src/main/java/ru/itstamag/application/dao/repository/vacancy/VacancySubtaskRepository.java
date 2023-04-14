package ru.itstamag.application.dao.repository.vacancy;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import ru.itstamag.application.dao.entity.vacancy.VacancySubtask;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VacancySubtaskRepository extends JpaRepository<VacancySubtask, UUID> {



    List<VacancySubtask> findAllByDoneIsTrueAndVacancyTaskId(UUID vacancyTaskId);
    void deleteAllByVacancyTaskId(UUID vacancyTaskId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<VacancySubtask> findFirstByDoneIsFalseAndVacancyTaskId(UUID id);

    List<VacancySubtask> findAllByVacancyTaskId(UUID vacancyTaskId);
}