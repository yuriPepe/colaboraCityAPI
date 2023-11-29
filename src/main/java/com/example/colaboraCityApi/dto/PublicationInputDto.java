package com.example.colaboraCityApi.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PublicationInputDto{
    
    @NotBlank
    private String textoPublicacao;
    
    private LocalDateTime dataPublicacao;

    private Long id;
}
