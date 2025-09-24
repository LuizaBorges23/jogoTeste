package service;

import model.Criatura;
import model.TipoElemental;

public class CalculadoraElemental {
	    
	  
	    public double calcularMultiplicadorDano(TipoElemental atacante, TipoElemental defensor) {
	        if (atacante == null || defensor == null) {
	            return 1.0;
	        }
	        
	        
	        switch (atacante) {
	            case FOGO:
	                return calcularVantagemFogo(defensor);
	            case AGUA:
	                return calcularVantagemAgua(defensor);
	            case TERRA:
	                return calcularVantagemTerra(defensor);
	            case AR:
	                return calcularVantagemAr(defensor);
	            case LUZ:
	                return calcularVantagemLuz(defensor);
	            case TREVAS:
	                return calcularVantagemTrevas(defensor);
	            default:
	                return 1.0;
	        }
	    }
	    
	    private double calcularVantagemFogo(TipoElemental defensor) {
	        switch (defensor) {
	            case TERRA: return 2.0;  
	            case AGUA: return 0.5;   
	            case FOGO: return 0.5;   
	            default: return 1.0;     
	        }
	    }
	    
	    private double calcularVantagemAgua(TipoElemental defensor) {
	        switch (defensor) {
	            case FOGO: return 2.0;   
	            case TERRA: return 0.5;  
	            case AGUA: return 0.5;   
	            default: return 1.0;
	        }
	    }
	    
	    private double calcularVantagemTerra(TipoElemental defensor) {
	        switch (defensor) {
	            case AGUA: return 2.0;   // Terra > Água
	            case FOGO: return 0.5;   // Terra < Fogo
	            case TERRA: return 0.5;  // Mesmo tipo
	            case AR: return 2.0;     // Terra > Ar
	            default: return 1.0;
	        }
	    }
	    
	    private double calcularVantagemAr(TipoElemental defensor) {
	        switch (defensor) {
	            case TERRA: return 2.0;  // Ar > Terra
	            case FOGO: return 0.5;   // Ar < Fogo
	            case AGUA: return 0.5;   // Ar < Água
	            case AR: return 0.5;     // Mesmo tipo
	            default: return 1.0;
	        }
	    }
	    
	    private double calcularVantagemLuz(TipoElemental defensor) {
	        switch (defensor) {
	            case TREVAS: return 2.0; // Luz > Trevas
	            case LUZ: return 0.5;    // Mesmo tipo
	            default: return 1.0;
	        }
	    }
	    
	    private double calcularVantagemTrevas(TipoElemental defensor) {
	        switch (defensor) {
	            case LUZ: return 2.0;    // Trevas > Luz
	            case TREVAS: return 0.5; // Mesmo tipo
	            default: return 1.0;
	        }
	    }
	    
	    
	    public boolean isSuperEfetivo(TipoElemental atacante, TipoElemental defensor) {
	        return calcularMultiplicadorDano(atacante, defensor) > 1.0;
	    }
	    
	    
	    public boolean isNaoEfetivo(TipoElemental atacante, TipoElemental defensor) {
	        return calcularMultiplicadorDano(atacante, defensor) < 1.0;
	    }
	    
	    
	    public String getDescricaoVantagem(TipoElemental atacante, TipoElemental defensor) {
	        double multiplicador = calcularMultiplicadorDano(atacante, defensor);
	        
	        if (multiplicador > 1.0) {
	            return "Super efetivo!";
	        } else if (multiplicador < 1.0) {
	            return "Não é muito efetivo...";
	        } else {
	            return "Efetivo normal.";
	        }
	    }
	}

