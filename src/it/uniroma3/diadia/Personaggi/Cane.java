package it.uniroma3.diadia.Personaggi;
import it.uniroma3.diadia.Partita.*;
import it.uniroma3.diadia.Attrezzo.*;

public class Cane extends AbstractPersonaggio {
	protected final static String MSG_MORSO = "WOOF WOOF!!";
	protected final static String MSG_REGALO = "WOOF";
	private Attrezzo daRegalare, prefCibo;

	public Cane(String nome, String presentazione, Attrezzo prefCibo, Attrezzo daRegalare) {
		super(nome,presentazione);
		this.daRegalare = daRegalare;
		this.prefCibo = prefCibo;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		return MSG_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(!this.prefCibo.equals(attrezzo))
			return agisci(partita);
		partita.getStanzaCorrente().addAttrezzo(this.daRegalare);
		return MSG_REGALO;
	}
	
	@Override
	public String desc4Stanza() {
		return "un cane ";
	}


}
