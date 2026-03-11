package com.example.ToDo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username",nullable = false)
    String username;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password_hash")
    private String password_hash;
    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
    @JsonIgnore
    private List<Task> tasks;
}
