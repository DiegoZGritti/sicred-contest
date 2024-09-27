package com.api.usecases;


import com.api.gateway.enitities.SessaoEntity;
import com.api.gateway.SessaoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BuscarSessoesUseCase {

    private final SessaoGateway sessaoGateway;

    public List<SessaoEntity> execute(String status) {
        // Se o status for "abertas", busca somente sessões abertas
        if ("abertas".equalsIgnoreCase(status)) {
            return sessaoGateway.findSessoesAbertas();
        }


        if ("fechadas".equalsIgnoreCase(status)) {
            return sessaoGateway.findSessoesFechadas();
        }


        return sessaoGateway.findAll();
    }
}
