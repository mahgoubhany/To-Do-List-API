package com.example.ToDo.services;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.ToDo.dto.CreateTaskRequest;
import com.example.ToDo.dto.updateTaskRequest;
import com.example.ToDo.model.Task;
import com.example.ToDo.model.TaskStatus;
import com.example.ToDo.model.User;
import com.example.ToDo.repository.TaskRepository;
import com.example.ToDo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceIMPL implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    @Override
    public Task createtask(CreateTaskRequest createTaskRequest) {
         Task newtask=new Task();
        User user = getCurrentUser();

        newtask.setTitle(createTaskRequest.getTitle());
         newtask.setDescription(createTaskRequest.getDescription());
         newtask.setStatus(createTaskRequest.getStatus());
         newtask.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
        newtask.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));
        taskRepository.save(newtask);
        newtask.setUser(user);

        return taskRepository.save(newtask);
    }

    @Override
    public List<Task> getAllTasks() {
        User user = getCurrentUser();
        return taskRepository.findByUser(user);
        //return taskRepository.findAll();
    }

//    @Override
//    public List<Task> getTasksFilter(TaskStatus taskStatus, String taskTitle) {
//        if(taskStatus!=null && taskTitle!=null)
//        {
//               return taskRepository.findByStatusAndTitle(taskStatus,taskTitle);
//        }
//        else if (taskTitle!=null) {
//            return taskRepository.findByTitle(taskTitle);
//        }
//        else if(taskStatus!=null)
//            return taskRepository.findByStatus(taskStatus);
//        else return getAllTasks();
//    }
    @Override
    public List<Task> getTasksFilter(TaskStatus taskStatus, String taskTitle) {

        User user = getCurrentUser();

        if(taskStatus != null && taskTitle != null) {
            return taskRepository.findByStatusAndTitleAndUser(taskStatus, taskTitle, user);
        }

        else if(taskTitle != null) {
            return taskRepository.findByTitleAndUser(taskTitle, user);
        }

        else if(taskStatus != null) {
            return taskRepository.findByStatusAndUser(taskStatus, user);
        }

        else {
            return taskRepository.findByUser(user);
        }
    }

    @Override
    public Task getTaskById(UUID id) {
        User user = getCurrentUser();
        return taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
    @Override
    public void deleteTask(UUID id) {
        User user = getCurrentUser();
        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);
    }
    @Override
    public Task updateTask(UUID id, updateTaskRequest request) {

        User user = getCurrentUser();

        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }

        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }

        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }

        task.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));

        return taskRepository.save(task);
    }

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
