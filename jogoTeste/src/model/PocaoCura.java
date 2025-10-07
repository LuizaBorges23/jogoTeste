package model;

public class PocaoCura implements Item{
	
	private static final String NOME = "Poção de Cura";
    private final int pontosDeCura = 10;

	
	public String getNome() {
		
		return NOME;
	}

	
	public void usar(Criatura alvo) {
		 System.out.println(">> " + alvo.getNome() + " usou uma " + NOME + "!");
	      alvo.receberCura(pontosDeCura);
		
	}

}
