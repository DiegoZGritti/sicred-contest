package com.api.controllers;

import com.api.gateway.mapper.SessaoGatewayMapper;
import com.api.usecases.AbrirSessaoUseCase;
import com.api.usecases.BuscarSessoesUseCase;
import com.api.domain.SessaoRequest;
import com.api.domain.SessaoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoController {

    private final AbrirSessaoUseCase abrirSessaoUseCase;
    private final BuscarSessoesUseCase  buscarSessoesUseCase;

    public SessaoController(AbrirSessaoUseCase abrirSessaoUseCase, BuscarSessoesUseCase buscarSessoesUseCase){
        this.abrirSessaoUseCase = abrirSessaoUseCase;
        this.buscarSessoesUseCase = buscarSessoesUseCase;
    }


    @Operation(summary = "Abrir uma sessão", description = "Abre uma sessão de votação por um tempo definido em minutos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sessão aberta com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessaoResponse abrirSessao(@Valid @RequestBody SessaoRequest sessaoRequest) {
        return SessaoGatewayMapper.toDTO(abrirSessaoUseCase.execute(
                SessaoGatewayMapper.toEntity(sessaoRequest),
                sessaoRequest.minutosDuracao()));
    }

    @Operation(summary = "Listar sessões", description = "Lista todas as sessões, filtradas por status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessões retornadas com sucesso"),
    })
    @GetMapping
    public ResponseEntity<List<SessaoResponse>> listarSessoes(@RequestParam(required = false) String status) {
        return ResponseEntity.ok(buscarSessoesUseCase.execute(status).stream()
                .map(SessaoGatewayMapper::toDTO)
                .collect(Collectors.toList()));
    }

}