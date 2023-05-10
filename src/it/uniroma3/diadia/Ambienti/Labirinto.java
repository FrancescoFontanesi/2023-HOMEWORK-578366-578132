package it.uniroma3.diadia.Ambienti;

public class Labirinto {
	private Stanza stanzaCorrente, stanzaVincente;
	
    public Stanza getStanzaCorrente() {
    	return this.stanzaCorrente;
    }
    public Stanza getStanzaVincente() {
    	return this.stanzaVincente;
    }
    
    public void setStanzaCorrente(Stanza stanzaCorrente) {
    	this.stanzaCorrente = stanzaCorrente;
    }
    public void setStanzaVincente(Stanza stanzaVincente) {
    	this.stanzaVincente = stanzaVincente;
    }  
}
