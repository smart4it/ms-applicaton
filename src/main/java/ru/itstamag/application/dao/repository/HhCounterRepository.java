package ru.itstamag.application.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itstamag.application.dao.entity.HhCounterEntity;

import java.util.UUID;

public interface HhCounterRepository extends JpaRepository<HhCounterEntity, UUID> {

}