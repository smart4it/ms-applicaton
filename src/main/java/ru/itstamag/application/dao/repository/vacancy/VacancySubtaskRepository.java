package ru.itstamag.application.dao.repository.vacancy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itstamag.application.dao.entity.vacancy.VacancySubtask;

import java.util.UUID;

public interface VacancySubtaskRepository extends JpaRepository<VacancySubtask, UUID> {


    @EntityGraph(attributePaths = {"vacancyTask"})
    Page<VacancySubtask> findAllBy(Pageable pageable);

    void deleteAllByVacancyTaskId(UUID vacancyTaskId);

}
