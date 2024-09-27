package com.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SessaoJaAbertaException.class)
    public ResponseEntity<String> handleSessaoJaAbertaException(SessaoJaAbertaException ex) {
        // Retorna um 400 com uma mensagem de erro customizada
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
