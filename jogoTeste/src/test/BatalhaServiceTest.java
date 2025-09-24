package test;

import model.Criatura;
import model.TipoElemental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.BatalhaService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BatalhaServiceTest {
	 @Mock
	    private Criatura dragaoMock;

	    @Mock
	    private Criatura golemMock;

	    private BatalhaService batalhaService;

	    @BeforeEach
	    void setUp() {
	        batalhaService = new BatalhaService(dragaoMock, golemMock);
	    }

	    @Test
	    void testarExecutarTurno() {
	        
	        when(dragaoMock.getSpd()).thenReturn(40);
	        when(golemMock.getSpd()).thenReturn(30);
	        when(dragaoMock.estaViva()).thenReturn(true);
	        when(golemMock.estaViva()).thenReturn(true);

	       
	        batalhaService.executarTurno();

	        
	        verify(dragaoMock, atLeastOnce()).receberDano(anyInt());
	        verify(golemMock, atLeastOnce()).receberDano(anyInt());
	    }

	    @Test
	    void testarIsBatalhaFinalizada() {
	        when(dragaoMock.estaViva()).thenReturn(true);
	        when(golemMock.estaViva()).thenReturn(true);
	        assertFalse(batalhaService.isBatalhaFinalizada());

	        when(dragaoMock.estaViva()).thenReturn(false);
	        assertTrue(batalhaService.isBatalhaFinalizada());
	    }

	    @Test
	    void testarGetVencedor() {
	        when(dragaoMock.estaViva()).thenReturn(false);
	        when(golemMock.estaViva()).thenReturn(true);
	        assertEquals(golemMock, batalhaService.getVencedor());

	        when(dragaoMock.estaViva()).thenReturn(true);
	        when(golemMock.estaViva()).thenReturn(false);
	        assertEquals(dragaoMock, batalhaService.getVencedor());

	        when(dragaoMock.estaViva()).thenReturn(false);
	        when(golemMock.estaViva()).thenReturn(false);
	        assertNull(batalhaService.getVencedor());
	    }
	}

