package ru.itstamag.application.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hh_counter")
public class HhCounterEntity {

    @Id
    private UUID id;

    private Integer count;

    private String text;

    private LocalDate date;

    private LocalTime time;
}
