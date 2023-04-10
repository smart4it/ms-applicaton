package ru.itstamag.application.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.itstamag.application.dao.entity.VacanciesSearchParam;

import java.util.UUID;

public interface VacanciesSearchParamRepository extends JpaRepository<VacanciesSearchParam, UUID> {

}