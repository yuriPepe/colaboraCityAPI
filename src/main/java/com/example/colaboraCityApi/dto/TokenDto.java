package com.example.colaboraCityApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class TokenDto {
    private String token;
    private String refreshToken;
    private String email;
}