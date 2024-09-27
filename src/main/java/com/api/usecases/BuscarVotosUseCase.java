package com.api.usecases;

import com.api.gateway.VotoGateway;
import com.api.gateway.enitities.VotoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
@Slf4j
@Component
public class BuscarVotosUseCase {

    private final VotoGateway votoGateway;

  public BuscarVotosUseCase(VotoGateway votoGateway) {
        this.votoGateway = votoGateway;
  }

    public List<VotoEntity> execute(UUID sessaoId) {
        return votoGateway.findBySessaoId(sessaoId);
    }
}