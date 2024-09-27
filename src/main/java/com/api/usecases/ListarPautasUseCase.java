package com.api.usecases;

import com.api.gateway.enitities.PautaEntity;
import com.api.gateway.PautaGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ListarPautasUseCase {

    private final PautaGateway pautaGateway;

    public ListarPautasUseCase(PautaGateway pautaGateway) {
        this.pautaGateway = pautaGateway;
    }

    public List<PautaEntity> execute() {
        return pautaGateway.findAll();
    }
}