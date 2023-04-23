package it.uniroma3.diadia.Comandi;
import it.uniroma3.diadia.Ambienti.Stanza;
import it.uniroma3.diadia.Partita.*;

public class ComandoVai implements Comando{
	private String direzione;
	private final static String NOME_CMD = "Comando Vai";
	private IO console;

	public ComandoVai() {
		this.console = new IOConsole();
	}

	@Override
	public void esegui(Partita partita) {
		if(this.direzione == null)
			this.console.mostraMessaggio("Dove vuoi andare? Non ho capito la direzione");
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null)
				this.console.mostraMessaggio("Direzione inesistente");
			else {
				partita.setStanzaCorrente(prossimaStanza);
				int cfu = partita.getCfu();
				partita.setCfu(--cfu);
			}
			this.console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}
	
	@Override
	public void setIO(IO console) {
		this.console = console;
	}

	@Override
	public String getNome() {
		return NOME_CMD;
	}
}
