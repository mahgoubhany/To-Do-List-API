package com.example.ToDo.dto;

import com.example.ToDo.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class updateTaskRequest {
    private UUID id;
    private String title;
    private String description;
    private TaskStatus status;
}
