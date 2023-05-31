package it.uniroma3.diadia.Comandi;

import it.uniroma3.diadia.Partita.IO;
import it.uniroma3.diadia.Partita.Partita;

public class ComandoRegala extends AbstractComando {
	private final static String NOME_CMD = "Comando Regala";
	private final static String MSG_ASSENTE = "Non ho un oggetto così in Borsa...";
	private final static String MSG_NOPNG = "Non c'è nessuno a cui dare il regalo...";
	
	public ComandoRegala(IO console) {
		super(console);
	}
	
	@Override
	public String getNome() {
		return NOME_CMD;
	}
	
	@Override
	public void esegui(Partita partita) {
		if(!partita.getStanzaCorrente().hasPersonaggio())
			super.getConsole().mostraMessaggio(MSG_NOPNG);
		else if(!partita.getGiocatore().getBorsa().hasAttrezzo(super.getParametro()))
			super.getConsole().mostraMessaggio(MSG_ASSENTE);
		else
			super.getConsole().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio()
					.riceviRegalo(partita.getGiocatore().getBorsa().
							getAttrezzo(super.getParametro()),partita));
	}
}
