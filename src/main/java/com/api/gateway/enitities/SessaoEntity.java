package com.api.gateway.enitities;


import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sessoes")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pauta_id", nullable = false)
    private PautaEntity pauta;

    private String titulo;

    @Column(nullable = false)
    private LocalDateTime abertura;

    @Column(nullable = false)
    private LocalDateTime fechamento;


}
