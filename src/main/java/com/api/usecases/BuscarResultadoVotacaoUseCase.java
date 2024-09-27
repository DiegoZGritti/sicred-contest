package com.api.usecases;

import com.api.domain.VotacaoResultadoResponse;
import com.api.exceptions.SessaoAbertaException;
import com.api.gateway.SessaoGateway;
import com.api.gateway.VotoGateway;
import com.api.gateway.enitities.SessaoEntity;
import com.api.gateway.mapper.VotoGatewayMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BuscarResultadoVotacaoUseCase {

    private final SessaoGateway sessaoGateway;
    private final VotoGateway votoGateway;


    public BuscarResultadoVotacaoUseCase(SessaoGateway sessaoGateway, VotoGateway votoGateway) {
        this.sessaoGateway = sessaoGateway;
        this.votoGateway = votoGateway;
    }

    public VotacaoResultadoResponse execute(UUID sessaoId) {

        SessaoEntity sessaoEntity = sessaoGateway.findById(sessaoId)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

        if (sessaoEntity.getFechamento().isAfter(java.time.LocalDateTime.now())) {
            throw new SessaoAbertaException("A sessão ainda está aberta.");
        }


        var votos = votoGateway.findBySessaoId(sessaoId);


        var contagemVotos = votos.stream()
                .collect(Collectors.groupingBy(
                        voto -> voto.isVoto() ? "SIM" : "NÃO", // Usamos o campo booleano para determinar SIM ou NÃO
                        Collectors.counting()
                ));


        int votosSim = contagemVotos.getOrDefault("SIM", 0L).intValue();
        int votosNao = contagemVotos.getOrDefault("NÃO", 0L).intValue();

        String resultadoFinal = (votosSim > votosNao) ? "Aprovado" : "Reprovado";

        return VotoGatewayMapper.toDTO(votos.size(), votosSim, votosNao, resultadoFinal);
    }
}
