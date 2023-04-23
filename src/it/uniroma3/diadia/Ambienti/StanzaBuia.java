package it.uniroma3.diadia.Ambienti;

public class StanzaBuia extends Stanza {
	private String perSbloccare;
	private final static String MSG_BUIO = "Qui c'è buio pesto...";
	
	public StanzaBuia(String nome, String perSbloccare) {
		super(nome);
		this.perSbloccare = perSbloccare;
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(this.perSbloccare))
			return super.getDescrizione();
		else
			return MSG_BUIO;	
	}

}
