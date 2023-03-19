package ru.itstamag.application.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itstamag.application.dao.entity.Employment;

public interface EmploymentRepository extends JpaRepository<Employment, String> {
}