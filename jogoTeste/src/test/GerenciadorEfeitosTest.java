package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.EfeitoStatus;
import service.GerenciadorEfeitos;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para o Gerenciador de Efeitos")
public class GerenciadorEfeitosTest {

    private GerenciadorEfeitos gerenciadorEfeitos;

    @BeforeEach
    void setUp() {
        
        gerenciadorEfeitos = new GerenciadorEfeitos();
    }

    @Test
    @DisplayName("Deve retornar o valor correto para o efeito Queimado")
    void deveRetornarValorCorretoParaQueimado() {
        int valorEsperado = 10;
        EfeitoStatus valorRetornado = gerenciadorEfeitos.criarEfeitoQueimado(10);
        assertEquals(valorEsperado, valorRetornado, "O método queimado() deve retornar 10.");
    }

    @Test
    @DisplayName("Deve retornar o valor correto para o efeito Envenenado")
    void deveRetornarValorCorretoParaEnvenenado() {
        int valorEsperado = 50;
        EfeitoStatus valorRetornado = gerenciadorEfeitos.criarEfeitoEnvenenado(50);
        assertEquals(valorEsperado, valorRetornado, "O método envenenado() deve retornar 50.");
    }

    @Test
    @DisplayName("Deve retornar o valor correto para o efeito Congelado")
    void deveRetornarValorCorretoParaCongelado() {
        int valorEsperado = 20;
        EfeitoStatus valorRetornado = gerenciadorEfeitos.criarEfeitoCongelado(20);
        assertEquals(valorEsperado, valorRetornado, "O método congelado() deve retornar 20.");
    }
}