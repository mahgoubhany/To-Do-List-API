package com.example.ToDo.controller;
import com.example.ToDo.GlobalExceptionHandler;

import com.example.ToDo.dto.CreateTaskRequest;
import com.example.ToDo.dto.updateTaskRequest;
import com.example.ToDo.model.Task;
import com.example.ToDo.model.TaskStatus;
import com.example.ToDo.repository.TaskRepository;
import com.example.ToDo.services.TaskService;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    //private final TaskRepository taskRepository;
    private final TaskService taskService;
    @Transactional
    @PostMapping()
    public ResponseEntity<Task>createTask(@RequestBody CreateTaskRequest createTaskRequest){
         Task savedTask=taskService.createtask(createTaskRequest);
         return ResponseEntity.ok(savedTask);
    }
    @GetMapping()
    public ResponseEntity<List<Task>>getAllTasks()
    {
        List<Task>tasks=taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Task>> getTasksByFilter(
            @RequestParam(required = false) TaskStatus taskStatus,
            @RequestParam(required = false) String title) {

        List<String> statuses = Arrays.asList(
                "PENDING",
                "IN_PROGRESS",
                "COMPLETED",
                "CANCELLED"
        );
        if (taskStatus != null) {
            String taskString = taskStatus.toString().toUpperCase();
            if (!statuses.contains(taskString)) {
                throw new RuntimeException("Not valid status: " + taskString);
            }
        }

        List<Task> tasks = taskService.getTasksFilter(taskStatus, title);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task>getTask(@PathVariable UUID id)
    {
         Task task=taskService.getTaskById(id);
         return ResponseEntity.ok(task);
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable UUID id,
            @RequestBody updateTaskRequest request) {
        Task updated = taskService.updateTask(id, request);
        return ResponseEntity.ok(updated);
    }


}
