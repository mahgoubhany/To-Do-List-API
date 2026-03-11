package com.example.ToDo.dto;

import com.example.ToDo.model.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Getter @Setter
public class CreateTaskRequest {
    private String title;
    private String description;
    private TaskStatus status;
}
