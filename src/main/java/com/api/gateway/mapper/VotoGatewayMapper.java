package com.api.gateway.mapper;

import com.api.domain.VotacaoResultadoResponse;
import com.api.domain.VotoRequest;
import com.api.domain.VotoResponse;
import com.api.gateway.enitities.SessaoEntity;
import com.api.gateway.enitities.VotoEntity;

public class VotoGatewayMapper {

    public static VotacaoResultadoResponse toDTO(int totalVotos, int votosSim, int votosNao, String resultadoFinal) {
        return new VotacaoResultadoResponse(
                totalVotos,
                votosSim,
                votosNao,
                resultadoFinal
        );
    }

    public static VotoEntity toEntity(VotoRequest votoRequest) {
        return VotoEntity.builder()
                .sessao(SessaoEntity.builder().id(votoRequest.sessaoId()).build())
                .associadoCpf(votoRequest.cpf())
                .voto(votoRequest.voto())
                .build();
    }

    public static VotoResponse toDTO(VotoEntity entity) {
        return new VotoResponse(
                entity.getId(),
                entity.getSessao().getId(),
                entity.getAssociadoCpf(),
                entity.isVoto()
        );
    }
}
