package gateway;


import com.api.gateway.SessaoGateway;
import com.api.gateway.enitities.SessaoEntity;
import com.api.repositories.SessaoRepository;
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
class SessaoGatewayTest {

    @Mock
    private SessaoRepository sessaoRepository;

    @InjectMocks
    private SessaoGateway sessaoGateway;

    private SessaoEntity sessaoEntity1;
    private SessaoEntity sessaoEntity2;

    @BeforeEach
    void setUp() {
        sessaoEntity1 = SessaoEntity.builder()
                .id(UUID.randomUUID())
                .build();
        sessaoEntity2 = SessaoEntity.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Test
    void testFindAll() {
        // Arrange
        List<SessaoEntity> sessoes = List.of(sessaoEntity1, sessaoEntity2);
        when(sessaoRepository.findAll()).thenReturn(sessoes);

        // Act
        List<SessaoEntity> result = sessaoGateway.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(sessaoEntity1, result.get(0));
        assertEquals(sessaoEntity2, result.get(1));
    }

}
