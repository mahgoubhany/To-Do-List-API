package com.example.ToDo.services;

import com.example.ToDo.dto.CreateTaskRequest;
import com.example.ToDo.dto.updateTaskRequest;
import com.example.ToDo.model.Task;
import com.example.ToDo.model.TaskStatus;
import com.example.ToDo.model.User;

import java.util.List;
import java.util.UUID;


public interface TaskService {
   public Task createtask(CreateTaskRequest createTaskRequest);
   public List<Task> getAllTasks();
   public List<Task> getTasksFilter(TaskStatus taskStatus, String taskTitle);
   public Task getTaskById(UUID id);
   public void deleteTask(UUID id);
   public Task updateTask(UUID id, updateTaskRequest request);
   public User getCurrentUser();
}
