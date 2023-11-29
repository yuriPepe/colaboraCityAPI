package com.example.colaboraCityApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class ErrorDto {
    
    private String field;
    private String error;

}
