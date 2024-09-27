package com.api.gateway.mapper;

import com.api.gateway.enitities.PautaEntity;
import com.api.gateway.enitities.SessaoEntity;
import com.api.domain.SessaoRequest;
import com.api.domain.SessaoResponse;

import java.time.LocalDateTime;

public class SessaoGatewayMapper {

    public static SessaoResponse toDTO(SessaoEntity sessaoEntity) {
        return new SessaoResponse(
                sessaoEntity.getId(),
                PautaGatewayMapper.toDTO(sessaoEntity.getPauta()),
                sessaoEntity.getTitulo(),
                obterStatus(sessaoEntity),
                sessaoEntity.getAbertura(),
                sessaoEntity.getFechamento()
        );
    }

    private static String obterStatus(SessaoEntity sessaoEntity) {
        LocalDateTime agora = LocalDateTime.now();
         if (agora.isAfter(sessaoEntity.getFechamento())) {
            return "Encerrada";
        } else {
            return "Aberta";
        }
    }

    public static SessaoEntity toEntity(SessaoRequest sessaoRequest) {
        return SessaoEntity.builder()
                .pauta(PautaEntity.builder().id(sessaoRequest.pautaId()).build())
                .titulo(sessaoRequest.titulo())
                .build();
    }


}
