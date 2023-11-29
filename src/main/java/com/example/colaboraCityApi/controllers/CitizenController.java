package com.example.colaboraCityApi.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.colaboraCityApi.dto.CitizenInputDto;
import com.example.colaboraCityApi.dto.CitizenOutputDto;
import com.example.colaboraCityApi.entities.Citizen;
import com.example.colaboraCityApi.services.CitizenService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/cidadao")
public class CitizenController {
    
    @Autowired
    private CitizenService service;

    @PostMapping
    public ResponseEntity<CitizenOutputDto> post(@RequestBody @Valid CitizenInputDto cidadao){
        CitizenOutputDto cidadaoSaida = service.create(cidadao);
        return new ResponseEntity<CitizenOutputDto>(cidadaoSaida, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CitizenOutputDto> put(@RequestBody CitizenInputDto cidadao){
        CitizenOutputDto cidadaoAtualizado = service.update(cidadao);
        return ResponseEntity.ok(cidadaoAtualizado);
    }

    @DeleteMapping
    public ResponseEntity delete(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            var usuario = (Citizen) auth.getPrincipal();
        service.delete(usuario.getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/photo/{id}")
    public ResponseEntity<?> uploadFile(@PathVariable Long id, @RequestParam MultipartFile file) {
      String message = "";
      try {
          service.upload(id, file);
        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(message);
      } catch (Exception e) {
        message =
          "Could not upload the file: " +
          file.getOriginalFilename() +
          ". Error: " +
          e.getMessage();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
      }
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<?> getFile(@PathVariable Long id) {
      Resource file = service.getFoto(id);
      
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }



    @GetMapping
    public ResponseEntity<List<CitizenOutputDto>> getList(Pageable page){
        
        List<CitizenOutputDto> lista = service.list(page);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitizenOutputDto> getRead(@PathVariable Long id){
        CitizenOutputDto cidadaoEncontrado = service.read(id);
        return ResponseEntity.ok(cidadaoEncontrado);
    }
}

