package model;
public class Habilidade {
    private String nome;
    private int custoMana;
    private String tipo; 
    private int valor;
    
    public Habilidade(String nome, int custoMana, String tipo, int valor) {
        this.nome = nome;
        this.custoMana = custoMana;
        this.tipo = tipo;
        this.valor = valor;
    }
    
    public void usar(Criatura fonte, Criatura alvo) {
        switch(tipo) {
            case "ataque":
                int dano = fonte.getAtk() - alvo.getDef();
                if (dano < 1) dano = 1;
                alvo.receberDano(dano);
                System.out.println(fonte.getNome() + " atacou " + alvo.getNome() + " causando " + dano + " de dano!");
                break;
                
            case "Psiquico":
                int danoElemental = valor + (fonte.getAtk() / 2);
                alvo.receberDano(danoElemental);
                System.out.println(fonte.getNome() + " usou " + nome + " em " + alvo.getNome() + " causando " + danoElemental + " Psiquico!");
                break;
                
            case "voar":
                alvo.setHp(alvo.getHp() + valor);
                System.out.println(fonte.getNome() + " voar " + alvo.getNome() + " em " + valor + " VELOCIDADE!");
                break;
                
            case "status":
                System.out.println(fonte.getNome() + " aplicou " + nome + " em " + alvo.getNome() + "!");
                break;
        }
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCustoMana() {
		return custoMana;
	}

	public void setCustoMana(int custoMana) {
		this.custoMana = custoMana;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
    
}
