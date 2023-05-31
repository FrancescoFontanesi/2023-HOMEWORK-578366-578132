package it.uniroma3.diadia.Comandi;
import it.uniroma3.diadia.Partita.*;
import it.uniroma3.diadia.Personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	private final static String NOME_CMD = "Comando Interagisci";
	private String messaggio;
	
	public ComandoInteragisci(IO console) {
		super(console);
	}
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.agisci(partita);
			super.getConsole().mostraMessaggio(this.messaggio);

		} else super.getConsole().mostraMessaggio(MESSAGGIO_CON_CHI);
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
	
	@Override
	public String getNome() {
		return NOME_CMD;
	}
}
