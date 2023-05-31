package it.uniroma3.diadia.Comandi;

import it.uniroma3.diadia.Partita.*;

public class ComandoGuarda extends AbstractComando {
	private final static String NOME_CMD = "Comando Guarda";

	public ComandoGuarda(IO console) {
		super(console);
	}

	@Override
	public void esegui(Partita partita) {
		super.getConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione() + "\nCfu residui: " + partita.getCfu() 
		+ "\n" + partita.getGiocatore().getBorsa().toString());
	}

	@Override
	public String getNome() {
		return NOME_CMD;
	}
}
