package controllers;


import com.api.controllers.PautaController;
import com.api.domain.PautaRequest;
import com.api.domain.PautaResponse;
import com.api.gateway.enitities.PautaEntity;
import com.api.usecases.BuscarPautaUseCase;
import com.api.usecases.CadastrarPautaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PautaControllerTest {

    @InjectMocks
    private PautaController pautaController;

    @Mock
    private CadastrarPautaUseCase cadastrarPautaUseCase;

    @Mock
    private BuscarPautaUseCase buscarPautaUseCase;

    private PautaRequest pautaRequest;
    private PautaEntity pautaEntity;
    private PautaResponse pautaResponse;

    @BeforeEach
    void setUp() {
        pautaRequest = new PautaRequest("Titulo", "Descricao");
        pautaEntity = PautaEntity.builder()
                .id(UUID.randomUUID())
                .titulo("Titulo")
                .descricao("Descricao")
                .build();
        pautaResponse = new PautaResponse(pautaEntity.getId(), "Titulo", "Descricao");
    }

    @Test
    void testCriarPauta_Success() {
        // Arrange
        when(cadastrarPautaUseCase.execute(any(PautaEntity.class))).thenReturn(pautaEntity);

        // Act
        ResponseEntity<PautaResponse> response = pautaController.cadastrarPauta(pautaRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pautaResponse, response.getBody());

        // Verify interactions
        verify(cadastrarPautaUseCase, times(1)).execute(any(PautaEntity.class));
    }

    @Test
    void testListarPautas_Success() {
        // Arrange
        when(buscarPautaUseCase.execute(any(UUID.class))).thenReturn(pautaEntity);

        // Act
        ResponseEntity<PautaResponse> response = pautaController.buscarPautaPorId(pautaEntity.getId());

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pautaResponse, response.getBody());

        // Verify interactions
        verify(buscarPautaUseCase, times(1)).execute(pautaEntity.getId());
    }
}