package com.api.domain;

import java.util.UUID;

public record PautaResponse(

        UUID id,

        String titulo,

        String descricao

){}
