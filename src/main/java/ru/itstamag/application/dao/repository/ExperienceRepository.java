package ru.itstamag.application.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itstamag.application.dao.entity.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, String> {
}