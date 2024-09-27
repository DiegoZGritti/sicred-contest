package com.api.usecases;

import com.api.gateway.enitities.PautaEntity;
import com.api.gateway.PautaGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Slf4j
@Component
public class BuscarPautaUseCase {

    private final PautaGateway pautaGateway;

    public BuscarPautaUseCase(PautaGateway pautaGateway) {
        this.pautaGateway = pautaGateway;
    }

    public PautaEntity execute(UUID id) {
        return pautaGateway.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrada"));
    }
}
