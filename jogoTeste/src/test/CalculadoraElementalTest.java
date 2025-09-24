package test;


import static org.junit.Assert.*;

import org.junit.Test;

import model.TipoElemental;
import service.CalculadoraElemental;

public class CalculadoraElementalTest {    
	    private CalculadoraElemental calculadora = new CalculadoraElemental();
	    
	    @Test
	    public void testFogoContraTerra() {
	        double multiplicador = calculadora.calcularMultiplicadorDano(TipoElemental.FOGO, TipoElemental.TERRA);
	        assertEquals("Fogo deve ser super efetivo contra Terra", 2.0, multiplicador, 0.01);
	        assertTrue(calculadora.isSuperEfetivo(TipoElemental.FOGO, TipoElemental.TERRA));
	    }
	    
	    @Test
	    public void testFogoContraAgua() {
	        double multiplicador = calculadora.calcularMultiplicadorDano(TipoElemental.FOGO, TipoElemental.AGUA);
	        assertEquals("Fogo deve ser não efetivo contra Água", 0.5, multiplicador, 0.01);
	        assertTrue(calculadora.isNaoEfetivo(TipoElemental.FOGO, TipoElemental.AGUA));
	    }
	    
	    @Test
	    public void testAguaContraFogo() {
	        double multiplicador = calculadora.calcularMultiplicadorDano(TipoElemental.AGUA, TipoElemental.FOGO);
	        assertEquals("Água deve ser super efetivo contra Fogo", 2.0, multiplicador, 0.01);
	    }
	    
	    @Test
	    public void testAguaContraTerra() {
	        double multiplicador = calculadora.calcularMultiplicadorDano(TipoElemental.AGUA, TipoElemental.TERRA);
	        assertEquals("Água deve ser não efetivo contra Terra", 0.5, multiplicador, 0.01);
	    }
	    
	    @Test
	    public void testTerraContraAgua() {
	        double multiplicador = calculadora.calcularMultiplicadorDano(TipoElemental.TERRA, TipoElemental.AGUA);
	        assertEquals("Terra deve ser super efetivo contra Água", 2.0, multiplicador, 0.01);
	    }
	    
	    @Test
	    public void testTerraContraAr() {
	        double multiplicador = calculadora.calcularMultiplicadorDano(TipoElemental.TERRA, TipoElemental.AR);
	        assertEquals("Terra deve ser super efetivo contra Ar", 2.0, multiplicador, 0.01);
	    }
	    
	    @Test
	    public void testArContraTerra() {
	        double multiplicador = calculadora.calcularMultiplicadorDano(TipoElemental.AR, TipoElemental.TERRA);
	        assertEquals("Ar deve ser super efetivo contra Terra", 2.0, multiplicador, 0.01);
	    }
	    
	    @Test
	    public void testLuzContraTrevas() {
	        double multiplicador = calculadora.calcularMultiplicadorDano(TipoElemental.LUZ, TipoElemental.TREVAS);
	        assertEquals("Luz deve ser super efetivo contra Trevas", 2.0, multiplicador, 0.01);
	    }
	    
	    @Test
	    public void testTrevasContraLuz() {
	        double multiplicador = calculadora.calcularMultiplicadorDano(TipoElemental.TREVAS, TipoElemental.LUZ);
	        assertEquals("Trevas deve ser super efetivo contra Luz", 2.0, multiplicador, 0.01);
	    }
	    
	    @Test
	    public void testMesmoTipo() {
	        
	        for (TipoElemental tipo : TipoElemental.values()) {
	            double multiplicador = calculadora.calcularMultiplicadorDano(tipo, tipo);
	            assertEquals(tipo + " contra " + tipo + " deve ser 0.5", 0.5, multiplicador, 0.01);
	            assertTrue(calculadora.isNaoEfetivo(tipo, tipo));
	        }
	    }
	    
	    @Test
	    public void testTiposNeutros() {
	       
	        assertEquals(1.0, calculadora.calcularMultiplicadorDano(TipoElemental.FOGO, TipoElemental.LUZ), 0.01);
	        assertEquals(1.0, calculadora.calcularMultiplicadorDano(TipoElemental.FOGO, TipoElemental.TREVAS), 0.01);
	        assertEquals(1.0, calculadora.calcularMultiplicadorDano(TipoElemental.AGUA, TipoElemental.LUZ), 0.01);
	        assertEquals(1.0, calculadora.calcularMultiplicadorDano(TipoElemental.AR, TipoElemental.LUZ), 0.01);
	    }
	    
	    @Test
	    public void testTiposNulos() {
	       
	        assertEquals(1.0, calculadora.calcularMultiplicadorDano(null, TipoElemental.FOGO), 0.01);
	        assertEquals(1.0, calculadora.calcularMultiplicadorDano(TipoElemental.AGUA, null), 0.01);
	        assertEquals(1.0, calculadora.calcularMultiplicadorDano(null, null), 0.01);
	    }
	    
	   @Test
	    public void testDescricaoVantagem() {
	        assertEquals("Super efetivo!", calculadora.getDescricaoVantagem(TipoElemental.FOGO, TipoElemental.TERRA));
	        assertEquals("Não é muito efetivo...", calculadora.getDescricaoVantagem(TipoElemental.FOGO, TipoElemental.AGUA));
	        assertEquals("Efetivo normal.", calculadora.getDescricaoVantagem(TipoElemental.FOGO, TipoElemental.LUZ));
	    }
	    
	    @Test
	    public void testTodasCombinacoes() {
	        
	        for (TipoElemental atacante : TipoElemental.values()) {
	            for (TipoElemental defensor : TipoElemental.values()) {
	                double multiplicador = calculadora.calcularMultiplicadorDano(atacante, defensor);
	                
	                
	                assertTrue("Multiplicador deve ser 0.5, 1.0 ou 2.0", 
	                    multiplicador == 0.5 || multiplicador == 1.0 || multiplicador == 2.0);
	                
	                
	                if (multiplicador > 1.0) {
	                    assertTrue(calculadora.isSuperEfetivo(atacante, defensor));
	               
	                } else if (multiplicador < 1.0) {
	                    assertTrue(calculadora.isNaoEfetivo(atacante, defensor));
	               
	                }
	            }
	        }
	    }
}
