package com.api.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VotoDuplicadoException extends RuntimeException {
    public VotoDuplicadoException(String mensagem) {
        super(mensagem);
    }
}