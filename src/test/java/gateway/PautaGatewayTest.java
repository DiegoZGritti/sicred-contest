package gateway;

import com.api.gateway.PautaGateway;
import com.api.gateway.enitities.PautaEntity;
import com.api.repositories.PautaRepository;
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
class PautaGatewayTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaGateway pautaGateway;

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
    void testFindAll() {
        // Arrange
        List<PautaEntity> pautas = List.of(pautaEntity1, pautaEntity2);
        when(pautaRepository.findAll()).thenReturn(pautas);

        // Act
        List<PautaEntity> result = pautaGateway.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(pautaEntity1, result.get(0));
        assertEquals(pautaEntity2, result.get(1));
    }

    @Test
    void testFindById() {
        // Arrange
        UUID pautaId = pautaEntity1.getId();
        when(pautaRepository.findById(pautaId)).thenReturn(Optional.of(pautaEntity1));

        // Act
        Optional<PautaEntity> result = pautaGateway.findById(pautaId);

        // Assert
        assertNotNull(result);
        assertEquals(pautaEntity1, result.orElse(null));
    }

    @Test
    void testSave() {
        // Arrange
        when(pautaRepository.save(pautaEntity1)).thenReturn(pautaEntity1);

        // Act
        PautaEntity result = pautaGateway.save(pautaEntity1);

        // Assert
        assertNotNull(result);
        assertEquals(pautaEntity1, result);
    }
}