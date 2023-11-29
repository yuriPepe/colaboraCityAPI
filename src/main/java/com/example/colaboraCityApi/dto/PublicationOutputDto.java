package com.example.colaboraCityApi.dto;

//import java.sql.Date;
import java.time.LocalDateTime;

import com.example.colaboraCityApi.entities.Publication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PublicationOutputDto {
    
    private Long Id;
    private String textoPublicacao;
    private LocalDateTime dataPublicacao;

    private String citizen;
}
