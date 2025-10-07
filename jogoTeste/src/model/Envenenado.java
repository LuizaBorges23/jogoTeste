package model;

public class Envenenado implements EfeitoStatus{
	private int duracao;
	private static final String NOME = "envenenado";

	public Envenenado(int duracao) {
		this.duracao = duracao;
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
