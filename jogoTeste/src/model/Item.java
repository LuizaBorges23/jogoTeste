package model;
public class Item {
	private String nome;
    private String descricao;
    private EfeitoItem efeito;
    
    public Item(String nome, String descricao, EfeitoItem efeito) {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
    }
    
    public void usar(Criatura alvo) {
        efeito.aplicar(alvo);
    }
    
    
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
}

