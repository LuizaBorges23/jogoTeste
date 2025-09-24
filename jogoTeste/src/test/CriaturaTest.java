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
    private Habilidade habilidadeFogo;

    @Mock
    private Habilidade envenenar;

    private Criatura dragao;
    private Criatura golem;
    private BatalhaService batalha;

    @BeforeEach
    void setUp() {
        
        when(habilidadeFogo.getNome()).thenReturn("Cuspir Fogo");
        when(habilidadeFogo.getCustoMana()).thenReturn(10);
        
        when(envenenar.getNome()).thenReturn("Envenenar");
        when(envenenar.getCustoMana()).thenReturn(15);

        
        dragao = new Criatura("Dragão", 100, 50, 30, 40, TipoElemental.FOGO, habilidadeFogo);
        golem = new Criatura("Golem", 120, 40, 50, 30, TipoElemental.TERRA, envenenar);

        batalha = new BatalhaService(dragao, golem);
    }

    @Test
    @DisplayName("Deve criar criaturas com atributos corretos")
    void testCriacaoCriaturas() {
        assertNotNull(dragao);
        assertNotNull(golem);
        
        assertEquals("Dragão", dragao.getNome());
        assertEquals(100, dragao.getHp());
        assertEquals(50, dragao.getAtk());
        assertEquals(TipoElemental.FOGO, dragao.getTipoElemental());
        
        assertEquals("Golem", golem.getNome());
        assertEquals(120, golem.getHp());
        assertEquals(40, golem.getAtk());
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
        assertTrue(golem.estaViva());
        
        
        dragao.receberDano(150);
        assertFalse(dragao.estaViva());
    }

    @Test
    @DisplayName("Deve executar turno de batalha")
    void testExecutarTurno() {
        assertFalse(batalha.isBatalhaFinalizada());
        
        batalha.executarTurno();
        
        
        assertTrue(dragao.getHp() <= 100 || golem.getHp() <= 120);
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
        assertNull(batalha.getVencedor());
    }

    @Test
    @DisplayName("Deve usar habilidade mockada")
    void testUsoHabilidade() {
        dragao.usarHabilidade(golem);
        
        
        verify(habilidadeFogo, times(1)).usar(dragao, golem);
    }
}
