package model;

public class Queimado implements EfeitoStatus{
	private int duracao;
	private static final String NOME = "queimado";

	public Queimado(int duração) {
		this.duracao = duração;
	}

	public void aplicarEfeito(Criatura criatura) {
		int dano = (int) (criatura.getHp() * 0.1);
		criatura.receberDano(dano);
		reduzirDuracao();
	}

	public void reduzirDuracao() {
		duracao--;
	}

	public boolean estaAtivo() {

		return duracao > 0;
	}

	public String getNome() {

		return NOME;
	}

}

