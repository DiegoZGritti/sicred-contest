package com.api.usecases;



import com.api.gateway.enitities.PautaEntity;
import com.api.gateway.PautaGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CadastrarPautaUseCase {

    private final PautaGateway pautaGateway;

    public CadastrarPautaUseCase(PautaGateway pautaGateway) {
        this.pautaGateway = pautaGateway;
    }

    public PautaEntity execute(final PautaEntity pautaEntity) {
        return pautaGateway.save(pautaEntity);
    }
}