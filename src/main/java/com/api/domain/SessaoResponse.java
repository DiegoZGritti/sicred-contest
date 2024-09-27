package com.api.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record SessaoResponse(

        UUID id,

        PautaResponse pauta,

        String titulo,

        String status,

        LocalDateTime abertura,

        LocalDateTime fechamento) {
}
