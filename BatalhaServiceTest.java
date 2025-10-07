package test;

import model.Criatura;

import model.Item;
import model.PocaoCura;
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
@DisplayName("Testes para o BatalhaService")
public class BatalhaServiceTest {

    @Mock
    private Criatura dragao;
    @Mock
    private Criatura golem;

    private BatalhaService batalhaService;

    @BeforeEach
    void setUp() {
       
        when(dragao.getNome()).thenReturn("Dragão");
        when(golem.getNome()).thenReturn("Golem");

       
        batalhaService = new BatalhaService(dragao, golem);
    }

    @Test
    @DisplayName("Deve executar um turno de item corretamente")
    void testarExecutarTurnoItem() {
       
        when(dragao.estaViva()).thenReturn(true);
        when(golem.estaViva()).thenReturn(true);
        
       
        when(dragao.getSpd()).thenReturn(50);
        when(golem.getSpd()).thenReturn(30);
       
        batalhaService = new BatalhaService(dragao, golem);

        Item pocao = new PocaoCura();

       
        batalhaService.executarTurnoUsandoItem(pocao);

       
        verify(dragao, times(1)).usarItem(pocao, dragao);
        verify(golem, never()).usarItem(any(Item.class), any(Criatura.class));
    }

    @Test
    @DisplayName("Deve finalizar a batalha quando uma criatura não está viva")
    void testarIsBatalhaFinalizada() {
        
        when(dragao.estaViva()).thenReturn(true);
        when(golem.estaViva()).thenReturn(true);
        assertFalse(batalhaService.isBatalhaFinalizada(), "A batalha não deveria terminar se ambos estão vivos.");

        
        when(dragao.estaViva()).thenReturn(false); 
        when(golem.estaViva()).thenReturn(true);
        assertTrue(batalhaService.isBatalhaFinalizada(), "A batalha deveria terminar se uma criatura não está viva.");
    }

    @Test
    @DisplayName("Deve retornar o vencedor correto")
    void testargetVencedor() {
       
       
        when(dragao.estaViva()).thenReturn(false);
        when(golem.estaViva()).thenReturn(true);
        assertEquals(golem, batalhaService.getVencedor());

       
        when(dragao.estaViva()).thenReturn(true);
        when(golem.estaViva()).thenReturn(false);
        assertEquals(dragao, batalhaService.getVencedor());
       
    }
}