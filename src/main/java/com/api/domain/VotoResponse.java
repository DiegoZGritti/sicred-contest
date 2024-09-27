package com.api.domain;

import java.util.UUID;

public record VotoResponse(
        UUID id,
        UUID sessaoId,
        String cpf,
        boolean voto
) {}

