package service;

import model.Criatura;
import model.TipoElemental;

public class BatalhaService {

	public Criatura criatura1;
	public Criatura criatura2;
	public TipoElemental Fogo;

	public void Turno(Criatura criatura1, Criatura criatura2) {
		Criatura atacante, defensor;

        if (criatura1.getSpd() >= criatura2.getSpd()) {
            atacante = criatura1;
            defensor = criatura2;
        } else {
            atacante = criatura2;
            defensor = criatura1;
        }
        int dano = calcularDano(atacante, defensor);
        defensor.receberDano(dano);
        
        if (defensor.estaViva()) {
            dano = calcularDano(defensor, atacante);
            atacante.receberDano(dano);
            
        }
     criatura1.aplicarEfeitoTurno();
     criatura2.aplicarEfeitoTurno();
    }

	

	   public int calcularDano(Criatura atacante, Criatura defensor) {
	        int atk = atacante.getAtk();
	        int def = defensor.getDef();
	        double multiplicador = atacante.getTipoElemental().getMultiplicadorDano(defensor.getTipoElemental());
	        boolean superEfetivo = (multiplicador > 1.0);
	        int danoBase = Math.max(0, atk - def);
	        int danoFinal = (int) (danoBase * multiplicador);
	        if (superEfetivo) {
	            System.out.println("O ataque foi super efetivo!");
	        }
	        return danoFinal;
	    }

	    public boolean isBatalhaFinalizada() {
	        return !criatura1.estaViva() || !criatura2.estaViva();
	    }

	    public Criatura getVencedor() {
	        if (!criatura1.estaViva()) {
	            return criatura2;
	        } else if (!criatura2.estaViva()) {
	            return criatura1;
	        } else {
	            return null;
	        }
	    }


}
