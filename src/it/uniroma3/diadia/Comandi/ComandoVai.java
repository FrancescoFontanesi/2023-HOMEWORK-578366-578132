package it.uniroma3.diadia.Comandi;
import it.uniroma3.diadia.Ambienti.Stanza;
import it.uniroma3.diadia.Partita.*;

public class ComandoVai extends AbstractComando{
	private final static String NOME_CMD = "Comando Vai";

	public ComandoVai(IO console) {
		super(console);
	}

	@Override
	public void esegui(Partita partita) {
		if(super.getParametro() == null)
			super.getConsole().mostraMessaggio("Dove vuoi andare? Non ho capito la direzione");
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(super.getParametro());
			if (prossimaStanza == null)
				super.getConsole().mostraMessaggio("Direzione inesistente");
			else {
				partita.setStanzaCorrente(prossimaStanza);
				int cfu = partita.getCfu();
				partita.setCfu(--cfu);
			}
			super.getConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		}
	}

	@Override
	public String getNome() {
		return NOME_CMD;
	}
}
