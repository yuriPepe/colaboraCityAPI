package com.example.colaboraCityApi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CitizenInputDto {
    
    private Boolean admin;


    private String name;

    private String adress;

    private String phone;

    @Email
    private String email;

    @NotBlank
    private String senha;

    private Long id;


}
