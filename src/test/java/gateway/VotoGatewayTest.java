package gateway;

import com.api.gateway.VotoGateway;
import com.api.gateway.enitities.SessaoEntity;
import com.api.gateway.enitities.VotoEntity;
import com.api.repositories.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotoGatewayTest {

    @Mock
    private VotoRepository votoRepository;

    @InjectMocks
    private VotoGateway votoGateway;

    private VotoEntity votoEntity1;
    private VotoEntity votoEntity2;

    @BeforeEach
    void setUp() {
        votoEntity1 = VotoEntity.builder()
                .id(UUID.randomUUID())
                .sessao(SessaoEntity.builder().id(UUID.randomUUID()).build())
                .build();
        votoEntity2 = VotoEntity.builder()
                .id(UUID.randomUUID())
                .sessao(SessaoEntity.builder().id(UUID.randomUUID()).build())
                .build();
    }


    @Test
    void testFindBySessaoId() {
        // Arrange
        UUID sessaoId = UUID.randomUUID();
        List<VotoEntity> votos = List.of(votoEntity1);
        when(votoRepository.findBySessaoId(sessaoId)).thenReturn(votos);

        // Act
        List<VotoEntity> result = votoGateway.findBySessaoId(sessaoId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(votoEntity1, result.get(0));
    }

    @Test
    void testSave() {
        // Arrange
        when(votoRepository.save(votoEntity1)).thenReturn(votoEntity1);

        // Act
        VotoEntity result = votoGateway.save(votoEntity1);

        // Assert
        assertNotNull(result);
        assertEquals(votoEntity1, result);
    }
}