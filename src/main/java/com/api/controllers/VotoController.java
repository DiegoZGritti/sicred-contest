package com.api.controllers;

import com.api.domain.VotacaoResultadoResponse;
import com.api.domain.VotoRequest;
import com.api.domain.VotoResponse;
import com.api.gateway.mapper.VotoGatewayMapper;
import com.api.usecases.BuscarResultadoVotacaoUseCase;
import com.api.usecases.BuscarVotosUseCase;
import com.api.usecases.RegistrarVotoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/votos")
public class VotoController {

    private final RegistrarVotoUseCase registrarVotoUseCase;
    public final BuscarVotosUseCase buscarVotosUseCase;
    private final BuscarResultadoVotacaoUseCase buscarResultadoVotacaoUseCase;

    public VotoController(RegistrarVotoUseCase registrarVotoUseCase, BuscarVotosUseCase buscarVotosUseCase, BuscarResultadoVotacaoUseCase buscarResultadoVotacaoUseCase) {
        this.registrarVotoUseCase = registrarVotoUseCase;
        this.buscarVotosUseCase = buscarVotosUseCase;
        this.buscarResultadoVotacaoUseCase = buscarResultadoVotacaoUseCase;
    }

    @Operation(summary = "Registrar um voto em uma sessão", description = "Registra um voto em uma sessão de votação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Voto registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Sessão já foi encerrada ou o associado já votou")
    })

    @PostMapping
    public ResponseEntity<VotoResponse> registrarVoto(@Valid @RequestBody VotoRequest votoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(VotoGatewayMapper.toDTO(registrarVotoUseCase.execute(
                VotoGatewayMapper.toEntity(votoRequest),
                votoRequest.sessaoId())));
    }


    @Operation(summary = "Buscar votos de uma sessão", description = "Retorna todos os votos de uma sessão de votação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Votos retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sessão não encontrada")
    })
    @GetMapping("/sessao/{sessaoId}")
    public ResponseEntity<List<VotoResponse>> buscarVotosPorSessao(@PathVariable UUID sessaoId) {
        return ResponseEntity.ok(buscarVotosUseCase.execute(sessaoId).stream()
                .map(VotoGatewayMapper::toDTO)
                .collect(Collectors.toList()));
    }


    @Operation(summary = "Buscar resultado da votação", description = "Retorna o resultado da votação de uma sessão encerrada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado retornado com sucesso"),
            @ApiResponse(responseCode = "400", description = "A sessão ainda está aberta")
    })
    @GetMapping("/sessao/{sessaoId}/resultado")
    public ResponseEntity<VotacaoResultadoResponse> buscarResultadoVotacao(@PathVariable UUID sessaoId) {
        return ResponseEntity.ok(buscarResultadoVotacaoUseCase.execute(sessaoId));
    }
}