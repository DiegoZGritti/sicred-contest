package com.api.gateway;

import com.api.gateway.enitities.PautaEntity;
import com.api.repositories.PautaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class PautaGateway {

    private final PautaRepository pautaRepository;

    public PautaGateway(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public PautaEntity save(PautaEntity pautaEntity) {
        log.info("[save-pauta: {}] saving new pauta", pautaEntity.getTitulo() + "id:" + pautaEntity.getId());
        pautaRepository.save(pautaEntity);
        return pautaEntity;
    }

    public Optional<PautaEntity> findById(UUID id) {
        return pautaRepository.findById(id);
    }

    public List<PautaEntity> findAll() {
        log.info("Fetching all pautas from database");
        return pautaRepository.findAll();
    }
}
