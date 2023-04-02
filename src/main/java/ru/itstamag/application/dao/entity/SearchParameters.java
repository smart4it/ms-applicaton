package ru.itstamag.application.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "search_parameters")
public class SearchParameters {

    @Id
    private UUID id;

    @Column(length = 25)
    private String text;

    @ManyToOne
    @JoinColumn(name = "experience_id")
    Experience experience;

    @ManyToOne
    @JoinColumn(name = "employment_id")
    Employment employment;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    Schedule schedule;

    private LocalDate created;

    private LocalTime updated;
}
