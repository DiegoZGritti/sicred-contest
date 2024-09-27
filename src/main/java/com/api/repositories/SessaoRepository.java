package com.api.repositories;

import com.api.gateway.enitities.SessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SessaoRepository extends JpaRepository<SessaoEntity, UUID> {
    List<SessaoEntity> findByPautaIdAndFechamentoAfter(UUID pautaId, LocalDateTime agora);
    List<SessaoEntity> findByFechamentoAfter(LocalDateTime now);
    List<SessaoEntity> findByFechamentoBefore(LocalDateTime now);
}
