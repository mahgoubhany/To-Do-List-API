package com.example.ToDo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.engine.internal.Cascade;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "description",nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
