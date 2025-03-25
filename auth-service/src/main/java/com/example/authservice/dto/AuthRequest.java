package com.example.authservice.dto;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthRequest {
    private String username;
    private String password;

}


