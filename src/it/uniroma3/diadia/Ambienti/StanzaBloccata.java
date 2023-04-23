package it.uniroma3.diadia.Ambienti;

public class StanzaBloccata extends Stanza {
	private String perSbloccare, dirBloccata;
	
	public StanzaBloccata(String nome, String perSbloccare, String dirBloccata) {
		super(nome);
		this.perSbloccare = perSbloccare;
		this.dirBloccata = dirBloccata;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(!this.dirBloccata.equals(direzione))
			return super.getStanzaAdiacente(direzione);
		else
			if(!this.hasAttrezzo(this.perSbloccare))
				return this;
			else
				return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		return super.getDescrizione() + "\nSembra che l'uscita a " + this.dirBloccata
				+ " sia bloccata... Forse qualcosa potrebbe aprirla...";
	}
	

}
