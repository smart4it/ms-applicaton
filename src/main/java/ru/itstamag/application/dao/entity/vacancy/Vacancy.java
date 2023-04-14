package ru.itstamag.application.dao.entity.vacancy;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "vacancy")
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {

    @Id
    private UUID id;
    @Column(name = "vacancy_id", length = 25)
    private String vacancyId;
    @Type(JsonType.class)
    private Map<String, Object>  text = new HashMap<>();
    @CreationTimestamp
    private LocalDateTime created;
}