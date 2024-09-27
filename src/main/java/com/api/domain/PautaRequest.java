package com.api.domain;

import jakarta.validation.constraints.NotBlank;

public record PautaRequest(

        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao
){}
