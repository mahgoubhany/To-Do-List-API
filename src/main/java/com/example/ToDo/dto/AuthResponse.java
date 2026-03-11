package com.example.ToDo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class AuthResponse {
    String token;
    //@JsonProperty("username")
    long expiresIn;
}
