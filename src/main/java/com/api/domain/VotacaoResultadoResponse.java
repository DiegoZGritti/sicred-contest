package com.api.domain;

public record VotacaoResultadoResponse(
        Integer totalVotos,
        Integer votosSim,
        Integer votosNao,
        String resultadoFinal
) {}
