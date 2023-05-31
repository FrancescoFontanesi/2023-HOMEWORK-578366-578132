package it.uniroma3.diadia.Personaggi;
import it.uniroma3.diadia.Attrezzo.*;
import it.uniroma3.diadia.Partita.*;

public class Mago extends AbstractPersonaggio {
	protected static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	protected static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	protected static final String MESSAGGIO_REGALO = "Sei gentile... Ma non posso accettare...Anzi,tieni!";
	private Attrezzo attrezzo;
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	
	@Override
	public String agisci(Partita partita) {
		if (this.attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			return MESSAGGIO_DONO;
		}
		else 
			return MESSAGGIO_SCUSE;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo(attrezzo.getNome(),attrezzo.getPeso()/2));
		return MESSAGGIO_REGALO;
	}
	
	@Override
	public String toString() {
		return "Mago " + super.getNome(); 
	}
	
	@Override
	public String desc4Stanza() {
		return "un mago ";
	}


}
