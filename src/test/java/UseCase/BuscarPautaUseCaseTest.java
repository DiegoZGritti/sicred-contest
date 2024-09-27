package UseCase;


import com.api.gateway.PautaGateway;
import com.api.gateway.enitities.PautaEntity;
import com.api.usecases.BuscarPautaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarPautaUseCaseTest {

    @Mock
    private PautaGateway pautaGateway;

    @InjectMocks
    private BuscarPautaUseCase buscarPautaUseCase;

    private PautaEntity pautaEntity1;
    private PautaEntity pautaEntity2;

    @BeforeEach
    void setUp() {
        pautaEntity1 = PautaEntity.builder()
                .id(UUID.randomUUID())
                .titulo("Pauta 1")
                .descricao("Descrição 1")
                .build();
        pautaEntity2 = PautaEntity.builder()
                .id(UUID.randomUUID())
                .titulo("Pauta 2")
                .descricao("Descrição 2")
                .build();
    }

    @Test
    void testExecute_FindById_Success() {
        // Arrange
        UUID pautaId = pautaEntity1.getId();
        when(pautaGateway.findById(pautaId)).thenReturn(Optional.of(pautaEntity1));

        // Act
        PautaEntity result = buscarPautaUseCase.execute(pautaId);

        // Assert
        assertNotNull(result);
        assertEquals(pautaEntity1, result);
    }

}