package com.example.colaboraCityApi.dto;

import com.example.colaboraCityApi.entities.Citizen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CitizenOutputDto {

    private Boolean admin;
    private String name;
    private String adress;
    private String phone;
    private String email;
    private Long id;

    

    
}
