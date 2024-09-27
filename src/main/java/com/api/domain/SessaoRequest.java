package com.api.domain;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record SessaoRequest(

        @NotBlank(message = "Id da Pauta Obrigatorio")
        UUID pautaId,

        @NotBlank(message = "Titulo da Sessão Obrigatorio")
        String titulo,

        Integer minutosDuracao
) {
}
