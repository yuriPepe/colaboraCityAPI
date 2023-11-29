package com.example.colaboraCityApi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data @NoArgsConstructor
public class RefreshTokenDto {
   @NotBlank
   private String refreshToken;
}