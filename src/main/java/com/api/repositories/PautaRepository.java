package com.api.repositories;

import com.api.gateway.enitities.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity, UUID> {

}
