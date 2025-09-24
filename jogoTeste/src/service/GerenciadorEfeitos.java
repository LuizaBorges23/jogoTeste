package service;

import model.Criatura;
import model.EfeitoStatus;

public class GerenciadorEfeitos implements EfeitoStatus {

	public int queimado() {
		return 10;
	}

	public int envenenado() {
		return 50;
	}

	public int congelado() {
		return 20;
	}

	public void aplicarEfeito(Criatura criatura) {

	}

	public void reduzirDuracao() {

	}

	public boolean estaAtivo() {

		return false;
	}

	public String getNome() {

		return null;
	}
}
