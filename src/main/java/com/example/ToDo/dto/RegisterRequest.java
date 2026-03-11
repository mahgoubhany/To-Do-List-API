package com.example.ToDo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
}
