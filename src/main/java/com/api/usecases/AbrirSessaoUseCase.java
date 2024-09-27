package com.api.usecases;

import com.api.exceptions.SessaoJaAbertaException;
import com.api.gateway.enitities.SessaoEntity;
import com.api.gateway.SessaoGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class AbrirSessaoUseCase {

    private static final int TEMPO_PADRAO_MINUTOS = 1;

    private final SessaoGateway sessaoGateway;

    public AbrirSessaoUseCase(SessaoGateway sessaoGateway) {
        this.sessaoGateway = sessaoGateway;
    }

    public SessaoEntity execute(SessaoEntity sessaoEntity, Integer minutos) {
        verificarSessaoAberta(sessaoEntity.getPauta().getId());

        int duracao = (minutos != null && minutos > 0) ? minutos : TEMPO_PADRAO_MINUTOS;
        sessaoEntity.setAbertura(LocalDateTime.now());
        sessaoEntity.setFechamento(LocalDateTime.now().plusMinutes(duracao));

        return sessaoGateway.save(sessaoEntity);
    }

    private void verificarSessaoAberta(UUID pautaId) {
        List<SessaoEntity> sessoesAbertas = sessaoGateway.findSessoesAbertasPorPauta(pautaId);
        if (!sessoesAbertas.isEmpty()) {
            throw new SessaoJaAbertaException("Já existe uma sessão aberta para esta pauta.");
        }
    }
}
