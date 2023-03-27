package ru.itstamag.application.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itstamag.application.dao.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {
}
