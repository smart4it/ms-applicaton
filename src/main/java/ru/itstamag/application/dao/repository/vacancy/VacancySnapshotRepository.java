package ru.itstamag.application.dao.repository.vacancy;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itstamag.application.dao.entity.vacancy.VacancySnapshot;

import java.util.List;
import java.util.UUID;

public interface VacancySnapshotRepository extends JpaRepository<VacancySnapshot, Integer> {

    void deleteAllByVacancyTaskId(UUID vacancyTaskId);


    @EntityGraph(attributePaths = {"vacancy"})
    List<VacancySnapshot> findAllByVacancyTaskId(UUID vacancyTaskId);
}
