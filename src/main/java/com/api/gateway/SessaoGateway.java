package com.api.gateway;

import com.api.gateway.enitities.SessaoEntity;
import com.api.repositories.SessaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class SessaoGateway {

    private final SessaoRepository sessaoRepository;

    public SessaoGateway(SessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    public SessaoEntity save(SessaoEntity sessaoEntity) {
        log.info("Saving new sessao");
        return sessaoRepository.save(sessaoEntity);
    }

    public List<SessaoEntity> findAll() {
        return sessaoRepository.findAll();
    }

    public Optional<SessaoEntity> findById(UUID id) {
        return sessaoRepository.findById(id);
    }
    public List<SessaoEntity> findSessoesAbertasPorPauta(UUID pautaId) {
        LocalDateTime agora = LocalDateTime.now();
        return sessaoRepository.findByPautaIdAndFechamentoAfter(pautaId, agora);
    }

    public List<SessaoEntity> findSessoesAbertas() {
        return sessaoRepository.findByFechamentoAfter(java.time.LocalDateTime.now());
    }


    public List<SessaoEntity> findSessoesFechadas() {
        return sessaoRepository.findByFechamentoBefore(java.time.LocalDateTime.now());
    }



}