package com.example.colaboraCityApi.advice;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.colaboraCityApi.dto.ErrorDto;

public class HandlerAdvice {
    
    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity notFound(){

        return new ResponseEntity<>("Objeto não encontrado 乁( ⁰͡ Ĺ̯ ⁰͡ ) ㄏ", HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity badRequest(MethodArgumentNotValidException exception){
        List<FieldError> errors = exception.getFieldErrors();
        var dto = errors.stream().map(e -> converteEntidadeParaDTO(e)).toList();
        return new ResponseEntity<>("não pode ser nulo", HttpStatus.BAD_REQUEST);
    }

    private ErrorDto converteEntidadeParaDTO(FieldError error){
        return new ErrorDto(error.getField(), error.getDefaultMessage());
    }
}
