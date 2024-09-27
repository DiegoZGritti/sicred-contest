package com.api.gateway.mapper;

import com.api.gateway.enitities.PautaEntity;
import com.api.domain.PautaRequest;
import com.api.domain.PautaResponse;

public class PautaGatewayMapper {

    public static PautaResponse toDTO(PautaEntity pautaEntity) {
        return new PautaResponse(pautaEntity.getId(), pautaEntity.getTitulo(), pautaEntity.getDescricao());
    }

    public static PautaEntity toEntity(PautaRequest pautaRequest) {
        return PautaEntity.builder()
                .titulo(pautaRequest.titulo())
                .descricao(pautaRequest.descricao())
                .build();
    }

}