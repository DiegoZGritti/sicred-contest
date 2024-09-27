package com.api.gateway.enitities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "votos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sessao_id", nullable = false)
    private SessaoEntity sessao;

    private String associadoCpf;

    @Column(nullable = false)
    private boolean voto;
}