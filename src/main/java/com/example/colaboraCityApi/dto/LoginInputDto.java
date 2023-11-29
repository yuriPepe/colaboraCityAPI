package com.example.colaboraCityApi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class LoginInputDto {
    @NotBlank
    private String login;
    @NotBlank
    private String password;

    // @NotBlank
    // private String email;
}