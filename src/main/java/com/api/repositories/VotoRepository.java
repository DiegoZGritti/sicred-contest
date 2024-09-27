package com.api.repositories;

import com.api.gateway.enitities.VotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VotoRepository extends JpaRepository<VotoEntity, UUID> {

    List<VotoEntity> findBySessaoId(UUID sessaoId);

    boolean existsByAssociadoCpfAndSessaoId(String cpf, UUID sessaoId);
}
