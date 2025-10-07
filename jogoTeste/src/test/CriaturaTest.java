package test;

import model.Criatura;
import model.Habilidade;
import model.TipoElemental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.BatalhaService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para Criatura e BatalhaService")
public class CriaturaTest {

    
    @Mock
    private Habilidade habilidadeVoar;
    @Mock
    private Habilidade habilidadePsiquico;
    
    @Mock
    private Habilidade habilidadesombrio;

    
    private Criatura dragao;
    private Criatura golem;
    private BatalhaService batalha;

    @BeforeEach
    void setUp() {
    
        dragao = new Criatura("Dragão", 100, 50, 30, 40, TipoElemental.FOGO, habilidadeVoar);
        golem = new Criatura("Golem", 120, 40, 50, 30, TipoElemental.TERRA, habilidadePsiquico);

    
        batalha = new BatalhaService(dragao, golem);
    }

    @Test
    @DisplayName("Deve criar criaturas com atributos corretos")
    void testCriacaoCriaturas() {
        assertNotNull(dragao);
        assertEquals("Dragão", dragao.getNome());
        assertEquals(100, dragao.getHp());
        assertEquals(TipoElemental.FOGO, dragao.getTipoElemental());

        assertNotNull(golem);
        assertEquals("Golem", golem.getNome());
        assertEquals(120, golem.getHp());
    }

    @Test
    @DisplayName("Deve calcular dano corretamente")
    void testCalculoDano() {
    
        int dano = batalha.calcularDano(dragao, golem);
        assertTrue(dano >= 0, "Dano não pode ser negativo");
    }

    @Test
    @DisplayName("Deve verificar se criatura está viva")
    void testEstaViva() {
        assertTrue(dragao.estaViva());
        dragao.receberDano(150); 
        assertFalse(dragao.estaViva());
    }

    @Test
    @DisplayName("Deve executar turno de batalha")
    void testExecutarTurno() {
        int golemHpAntes = golem.getHp();
        batalha.executarTurno();
        
        assertTrue(golem.getHp() < golemHpAntes);
    }

    @Test
    @DisplayName("Deve identificar vencedor corretamente")
    void testVencedor() {
        
        golem.receberDano(200); 

        assertTrue(batalha.isBatalhaFinalizada());
        Criatura vencedor = batalha.getVencedor();
        
        assertNotNull(vencedor);
        assertEquals("Dragão", vencedor.getNome());
    }

    @Test
    @DisplayName("Deve verificar empate")
    void testEmpate() {
        dragao.receberDano(200);
        golem.receberDano(200);

        assertTrue(batalha.isBatalhaFinalizada());
        assertNull(batalha.getVencedor(), "O resultado deveria ser um empate (vencedor nulo)");
    }

    @Test
    @DisplayName("Deve usar habilidade e verificar a interação com o mock")
    void testUsoHabilidade() {
        
        dragao.getHabilidade();
        
        
        verify(habilidadeVoar, times(1)).usar(dragao, golem);
    }
}
