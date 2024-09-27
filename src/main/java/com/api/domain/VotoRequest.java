package com.api.domain;

import java.util.UUID;

public record VotoRequest(
        UUID sessaoId,
        String cpf,
        boolean voto
) {}