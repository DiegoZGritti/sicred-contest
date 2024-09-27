package com.api.usecases;

import com.api.exceptions.VotoDuplicadoException;
import com.api.gateway.SessaoGateway;
import com.api.gateway.VotoGateway;
import com.api.gateway.enitities.SessaoEntity;
import com.api.gateway.enitities.VotoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrarVotoUseCase {

    private final VotoGateway votoGateway;
    private final SessaoGateway sessaoGateway;

    public VotoEntity execute(VotoEntity votoEntity, UUID sessaoId) {
        SessaoEntity sessaoEntity = sessaoGateway.findById(sessaoId)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

        if (sessaoEntity.getFechamento().isBefore(java.time.LocalDateTime.now())) {
            throw new IllegalStateException("Sessão já foi encerrada.");
        }

        if (votoGateway.existsByAssociadoCpfAndSessaoId(votoEntity.getAssociadoCpf(), sessaoId)) {
            throw new VotoDuplicadoException("Este associado já votou nesta sessão.");
        }

        votoEntity.setSessao(sessaoEntity);

        try {
            return votoGateway.save(votoEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new VotoDuplicadoException("Este associado já votou nesta sessão.");
        }
    }
}
