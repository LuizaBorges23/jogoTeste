package service;

import model.Criatura;

import model.Item;
import model.TipoElemental;

public class BatalhaService {
	Criatura atacante, defensor;
	private Criatura criatura1;
	private Criatura criatura2;
	private Criatura atacanteAtual;
	private CalculadoraElemental calculadoraElemental;

	public BatalhaService(Criatura criatura1, Criatura criatura2) {
		this.criatura1 = criatura1;
		this.criatura2 = criatura2;
		this.calculadoraElemental = new CalculadoraElemental();

		if (criatura1.getSpd() >= criatura2.getSpd()) {
			this.atacanteAtual = criatura1;
		} else {
			this.atacanteAtual = criatura2;
		}

		System.out.println(">> A batalha começa! " + this.atacanteAtual.getNome() + " tem a iniciativa.");
	}

	private void passarTurno() {

		if (isBatalhaFinalizada()) {
			System.out.println(">> A batalha terminou!");
			return;
		}
		if (atacanteAtual.equals(criatura1)) {
			this.atacanteAtual = criatura2;
		} else {
			this.atacanteAtual = criatura1;
		}
		System.out.println("--- Agora é o turno de " + this.atacanteAtual.getNome() + " ---");
	}

	public void executarTurno() {

		if (isBatalhaFinalizada()) {
			System.out.println("A batalha já terminou!");
			return;
		}
		Criatura defensor = atacanteAtual.equals(criatura1) ? criatura2 : criatura1;

		System.out.println(atacanteAtual.getNome() + " ataca " + defensor.getNome() + "!");

		if (criatura1.getSpd() >= criatura2.getSpd()) {
			atacante = criatura1;
			defensor = criatura2;
		} else {
			atacante = criatura2;
			defensor = criatura1;
		}

		System.out.println("--- " + atacante.getNome() + " ataca primeiro ---");

		int dano = calcularDano(atacanteAtual, defensor);
		defensor.receberDano(dano);

		passarTurno();

		if (defensor.estaMorto()) {
			System.out.println(defensor.getNome() + " foi derrotado!");
		}

		if (defensor.estaViva()) {
			dano = calcularDano(defensor, atacante);
			System.out.println(
					defensor.getNome() + " contra-ataca " + atacante.getNome() + " causando " + dano + " de dano!");
			atacante.receberDano(dano);

			if (atacante.estaMorto()) {
				System.out.println(atacante.getNome() + " foi derrotado!");
			}
		}

		aplicarEfeitosStatus();
	}

	public void executarTurnoUsandoItem(Item item) {
		if (isBatalhaFinalizada())
			return;
		System.out.println(atacanteAtual.getNome() + " decide usar um item.");
		atacanteAtual.usarItem(item, atacanteAtual);
		passarTurno();
	}

	public int calcularDano(Criatura atacante, Criatura defensor) {
		int atk = atacante.getAtk();
		int def = defensor.getDef();

		double multiplicador = calculadoraElemental.calcularMultiplicadorDano(atacante.getTipoElemental(),
				defensor.getTipoElemental());

		int danoBase = Math.max(1, atk - def);
		int danoFinal = (int) (danoBase * multiplicador);

		String descricao = calculadoraElemental.getDescricaoVantagem(atacante.getTipoElemental(),
				defensor.getTipoElemental());
		System.out.println(descricao + " Multiplicador: " + multiplicador + "x");

		return atacante.getAtk();
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
	    boolean criatura1EstaViva = criatura1.estaViva();
	    boolean criatura2EstaViva = criatura2.estaViva();

	    if (criatura1EstaViva && !criatura2EstaViva) {
	        return criatura1; 
	    } else if (!criatura1EstaViva && criatura2EstaViva) {
	        return criatura2; 
	    } else {
	        return null; 
	    }
	}

	public String getStatusBatalha() {
		return String.format("%s: %d HP | %s: %d HP", criatura1.getNome(), criatura1.getHp(), criatura2.getNome(),
				criatura2.getHp());
	}

	public boolean isEmpate() {
		return !criatura1.estaViva() && !criatura2.estaViva();
	}
}