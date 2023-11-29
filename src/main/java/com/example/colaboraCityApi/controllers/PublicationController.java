package com.example.colaboraCityApi.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.colaboraCityApi.dto.PublicationInputDto;
import com.example.colaboraCityApi.dto.PublicationOutputDto;
import com.example.colaboraCityApi.entities.Citizen;
import com.example.colaboraCityApi.entities.Publication;
import com.example.colaboraCityApi.services.PublicationService;

import com.example.colaboraCityApi.advice.HandlerAdvice;

@RestController
@RequestMapping("/publicacao")
public class PublicationController {
    
    @Autowired
    private PublicationService service;

    @PostMapping
    public ResponseEntity<PublicationOutputDto> post(@RequestBody PublicationInputDto publicacao){
        if(publicacao.getTextoPublicacao() == null || publicacao.getTextoPublicacao().isEmpty()){
             
            return ResponseEntity.badRequest().build();
        }
        else{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            var usuario = (Citizen) auth.getPrincipal();

        PublicationOutputDto publicacaoSaida = service.create(publicacao, usuario);
        return new ResponseEntity<PublicationOutputDto>(publicacaoSaida, HttpStatus.CREATED);
        }
    }

    @PutMapping
    public ResponseEntity<PublicationOutputDto> put(@RequestBody PublicationInputDto publicacao){
        PublicationOutputDto publicacaoEditada = service.update(publicacao);
        return ResponseEntity.ok(publicacaoEditada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<PublicationOutputDto>> getList(Pageable page){
        List<PublicationOutputDto> lista = service.list(page);
        return ResponseEntity.ok(lista);  

    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationOutputDto> getRead(@PathVariable Long id){
        PublicationOutputDto publicacaoEncontrada = service.read(id);
        // PublicationOutputDto publicacaoSaida = service.converteEntidadeParaDto(publicacaoEncontrada);
        return ResponseEntity.ok(publicacaoEncontrada);

    }
}

