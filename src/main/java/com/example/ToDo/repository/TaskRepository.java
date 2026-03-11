package com.example.ToDo.repository;

import com.example.ToDo.model.Task;
import com.example.ToDo.model.TaskStatus;
import com.example.ToDo.model.User;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByTitle(String title);
/// name attributes based on the entity field name
    List<Task> findByStatus(TaskStatus status);

    List<Task> findByStatusAndTitle(TaskStatus status, String title);

    List<Task> findByUser(User user);

    Optional<Task> findByIdAndUser(UUID id, User user);

    List<Task> findByStatusAndUser(TaskStatus status, User user);

    List<Task> findByTitleAndUser(String title, User user);

    List<Task> findByStatusAndTitleAndUser(TaskStatus status, String title, User user);
}
