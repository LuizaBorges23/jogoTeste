package model;

public class Criatura {
	 private String nome;
	    private int hp;
	    private int atk;
	    private int def;
	    private int spd;
	    private EfeitoStatus efeitoStatus;
	    private TipoElemental tipoElemental;
	    private Habilidade habilidade;

	    public Criatura(String nome, int hp, int atk, int def, int spd, 
	                   TipoElemental tipoElemental, Habilidade habilidade) {
	        this.nome = nome;
	        this.hp = hp;
	        this.atk = atk;
	        this.def = def;
	        this.spd = spd;
	        this.tipoElemental = tipoElemental;
	        this.habilidade = habilidade;
	    }

	    
	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public int getHp() {
	        return hp;
	    }

	    public void setHp(int hp) {
	        this.hp = Math.max(0, hp); 
	    }

	    public int getAtk() {
	        return atk;
	    }

	    public void setAtk(int atk) {
	        this.atk = atk;
	    }

	    public int getDef() {
	        return def;
	    }

	    public void setDef(int def) {
	        this.def = def;
	    }

	    public int getSpd() {
	        return spd;
	    }

	    public void setSpd(int spd) {
	        this.spd = spd;
	    }

	    public TipoElemental getTipoElemental() {
	        return tipoElemental;
	    }

	    public void setTipoElemental(TipoElemental tipoElemental) {
	        this.tipoElemental = tipoElemental;
	    }

	    public Habilidade getHabilidade() {
	        return habilidade;
	    }

	    public void setHabilidade(Habilidade habilidade) {
	        this.habilidade = habilidade;
	    }

	    public EfeitoStatus getEfeitoStatus() {
	        return efeitoStatus;
	    }

	    public void setEfeitoStatus(EfeitoStatus efeitoStatus) {
	        this.efeitoStatus = efeitoStatus;
	    }

	    public void receberDano(int dano) {
	        this.hp = Math.max(0, this.hp - dano); 
	    }
	    
	    public boolean estaViva() {
	        return this.hp > 0;
	    }

	    public void usarHabilidade() {
	        if (habilidade != null) {
	            habilidade.congelar();
	        }
	    }

	    public void aplicarEfeitoTurno() {
	        if (efeitoStatus != null && efeitoStatus.estaAtivo()) {
	            efeitoStatus.aplicarEfeito(this);
	        } else {
	            efeitoStatus = null; 
	        }
	    }

}
