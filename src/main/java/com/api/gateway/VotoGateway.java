package com.api.gateway;

import com.api.gateway.enitities.VotoEntity;
import com.api.repositories.VotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class VotoGateway {

    private final VotoRepository votoRepository;

    public VotoGateway(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public VotoEntity save(VotoEntity votoEntity) {
        return votoRepository.save(votoEntity);
    }

    public List<VotoEntity> findBySessaoId(UUID sessaoId) {
        return votoRepository.findBySessaoId(sessaoId);
    }

    public boolean existsByAssociadoCpfAndSessaoId(String cpf, UUID sessaoId) {
        return votoRepository.existsByAssociadoCpfAndSessaoId(cpf, sessaoId);
    }
}
