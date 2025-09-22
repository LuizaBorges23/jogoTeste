package model;
public interface EfeitoStatus {
	void aplicarEfeito(Criatura criatura);
    void reduzirDuracao();
    boolean estaAtivo();
    String getNome();
}
