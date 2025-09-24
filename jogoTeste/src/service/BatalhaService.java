package service;

import model.Criatura;
import model.TipoElemental;

public class BatalhaService {
    private Criatura criatura1;
    private Criatura criatura2;
    private CalculadoraElemental calculadoraElemental;

    
    public BatalhaService(Criatura criatura1, Criatura criatura2) {
        this.criatura1 = criatura1;
        this.criatura2 = criatura2;
        this.calculadoraElemental = new CalculadoraElemental();
    }


    public void executarTurno() {

        if (isBatalhaFinalizada()) {
            System.out.println("A batalha já terminou!");
            return;
        }

        Criatura atacante, defensor;

        
        if (criatura1.getSpd() >= criatura2.getSpd()) {
            atacante = criatura1;
            defensor = criatura2;
        } else {
            atacante = criatura2;
            defensor = criatura1;
        }

        System.out.println("--- " + atacante.getNome() + " ataca primeiro ---");

        
        int dano = calcularDano(atacante, defensor);
        System.out.println(atacante.getNome() + " ataca " + defensor.getNome() + " causando " + dano + " de dano!");
        defensor.receberDano(dano);

     
        if (defensor.estaMorto()) {
            System.out.println(defensor.getNome() + " foi derrotado!");
        }

       
        if (defensor.estaViva()) {
            dano = calcularDano(defensor, atacante);
            System.out.println(defensor.getNome() + " contra-ataca " + atacante.getNome() + " causando " + dano + " de dano!");
            atacante.receberDano(dano);

            if (atacante.estaMorto()) {
                System.out.println(atacante.getNome() + " foi derrotado!");
            }
        }

       
        aplicarEfeitosStatus();
    }

    public int calcularDano(Criatura atacante, Criatura defensor) {
        int atk = atacante.getAtk();
        int def = defensor.getDef();
        
        
        double multiplicador = calculadoraElemental.calcularMultiplicadorDano(
            atacante.getTipoElemental(), 
            defensor.getTipoElemental()
        );
        
        int danoBase = Math.max(1, atk - def); 
        int danoFinal = (int) (danoBase * multiplicador);
        
        
        String descricao = calculadoraElemental.getDescricaoVantagem(
            atacante.getTipoElemental(), 
            defensor.getTipoElemental()
        );
        System.out.println(descricao + " Multiplicador: " + multiplicador + "x");
        
        return danoFinal;
    }

  
    private void aplicarEfeitosStatus() {
        System.out.println("Aplicando efeitos de status...");
        
        if (criatura1.getEfeitoStatus() != null) {
            criatura1.aplicarEfeitoTurno();
        }
        
        if (criatura2.getEfeitoStatus() != null) {
            criatura2.aplicarEfeitoTurno();
        }
    }

    public boolean isBatalhaFinalizada() {
        return !criatura1.estaViva() || !criatura2.estaViva();
    }

    public Criatura getVencedor() {
        if (!criatura1.estaViva() && !criatura2.estaViva()) {
            return null; 
        } else if (!criatura1.estaViva()) {
            return criatura2;
        } else {
            return criatura1;
        }
    }

   
    public String getStatusBatalha() {
        return String.format("%s: %d HP | %s: %d HP", 
                criatura1.getNome(), criatura1.getHp(),
                criatura2.getNome(), criatura2.getHp());
    }
    

    public boolean isEmpate() {
        return !criatura1.estaViva() && !criatura2.estaViva();
    }
}