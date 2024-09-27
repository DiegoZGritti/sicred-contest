package com.api.controllers;

import com.api.gateway.mapper.PautaGatewayMapper;
import com.api.usecases.BuscarPautaUseCase;
import com.api.usecases.CadastrarPautaUseCase;
import com.api.usecases.ListarPautasUseCase;
import com.api.domain.PautaRequest;
import com.api.domain.PautaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pautas")
@Tag(name = "PautaResponse", description = "Endpoints para gerenciar pautas")
public class PautaController {

    private final CadastrarPautaUseCase cadastrarPautaUseCase;
    private final BuscarPautaUseCase buscarPautaUseCase;
    private final ListarPautasUseCase listarPautasUseCase;

    public PautaController(CadastrarPautaUseCase cadastrarPautaUseCase, BuscarPautaUseCase buscarPautaUseCase, ListarPautasUseCase listarPautasUseCase) {
        this.cadastrarPautaUseCase = cadastrarPautaUseCase;
        this.buscarPautaUseCase = buscarPautaUseCase;
        this.listarPautasUseCase = listarPautasUseCase;
    }

    @Operation(summary = "Cadastra uma nova pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PautaResponse cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<PautaResponse> cadastrarPauta(@Valid @RequestBody PautaRequest pautaRequest) {
        var pautaSalva = cadastrarPautaUseCase.execute(PautaGatewayMapper.toEntity(pautaRequest));
        return ResponseEntity.ok(PautaGatewayMapper.toDTO(pautaSalva));
    }

    @Operation(summary = "Busca uma pauta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PautaResponse encontrada"),
            @ApiResponse(responseCode = "404", description = "PautaResponse não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PautaResponse> buscarPautaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(PautaGatewayMapper.toDTO(buscarPautaUseCase.execute(id)));
    }

    @Operation(summary = "Listar todas as pautas", description = "Retorna uma lista de todas as pautas cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pautas retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<PautaResponse>> listarPautas() {
        return ResponseEntity.ok(listarPautasUseCase.execute().stream()
                .map(PautaGatewayMapper::toDTO)
                .collect(Collectors.toList()));
    }

}
