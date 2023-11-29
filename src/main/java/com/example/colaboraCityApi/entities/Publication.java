package com.example.colaboraCityApi.entities;


import java.time.LocalDateTime;

import com.example.colaboraCityApi.dto.PublicationInputDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="publicacao")
@Data @NoArgsConstructor @AllArgsConstructor
public class Publication {

    public Publication(PublicationInputDto dto){
        this.textoPublicacao = dto.getTextoPublicacao();
        this.dataPublicacao = dto.getDataPublicacao();
    } 


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isActive = true;

    private String textoPublicacao;
    private LocalDateTime dataPublicacao;

    @ManyToOne
    private Citizen cidadao;

     
}
