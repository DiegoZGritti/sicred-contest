package com.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SessaoAbertaException extends RuntimeException {
    public SessaoAbertaException(String mensagem) {
        super(mensagem);
    }
}