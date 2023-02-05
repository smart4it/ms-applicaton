package ru.itstamag.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HhCounterRepository extends JpaRepository<HhCounterEntity, UUID> {
}