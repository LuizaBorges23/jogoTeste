package service;

import model.Congelado;
import model.EfeitoStatus;
import model.Envenenado;
import model.Queimado;

public class GerenciadorEfeitos {


    public GerenciadorEfeitos() {
    }


    public EfeitoStatus criarEfeitoQueimado(int duracao) {
        return new Queimado(duracao);
    }


    public EfeitoStatus criarEfeitoEnvenenado(int duracao) {
        return new Envenenado(duracao);
    }


    public EfeitoStatus criarEfeitoCongelado(int duracao) {
        return new Congelado(duracao);
    }
}