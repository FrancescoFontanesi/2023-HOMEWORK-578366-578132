package it.uniroma3.diadia.Personaggi;
import it.uniroma3.diadia.Attrezzo.Attrezzo;
import it.uniroma3.diadia.Partita.*;

public class FakeAbstractPersonaggio extends AbstractPersonaggio {
	
	public FakeAbstractPersonaggio(String nome, String presentazione) {
		super(nome,presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		return "done";
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "ricevuto";
	}
	
	@Override
	public String desc4Stanza() {
		return "un fakeAbstractPersonaggio";
	}
}
