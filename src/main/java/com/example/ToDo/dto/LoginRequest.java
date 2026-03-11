package com.example.ToDo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class LoginRequest {
    private String username;
    private String password;
}
