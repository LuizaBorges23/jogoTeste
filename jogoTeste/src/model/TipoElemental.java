package model;

public enum TipoElemental {
	    FOGO, AGUA, TERRA, AR, LUZ, TREVAS;

	    public double getMultiplicadorDano(TipoElemental alvo) {
	        
	        switch (this) {
	            case FOGO:
	                return (alvo == TERRA) ? 2.0 : (alvo == AGUA || alvo == FOGO) ? 0.5 : 1.0;
	            case AGUA:
	                return (alvo == FOGO) ? 2.0 : (alvo == TERRA || alvo == AGUA) ? 0.5 : 1.0;
	            case TERRA:
	                return (alvo == AGUA) ? 2.0 : (alvo == FOGO || alvo == TERRA) ? 0.5 : 1.0;
	            case AR:
	                return (alvo == TERRA) ? 2.0 : (alvo == AR) ? 0.5 : 1.0;
	            case LUZ:
	                return (alvo == TREVAS) ? 2.0 : (alvo == LUZ) ? 0.5 : 1.0;
	            case TREVAS:
	                return (alvo == LUZ) ? 2.0 : (alvo == TREVAS) ? 0.5 : 1.0;
	            default:
	                return 1.0;
	        }
	    }
	}