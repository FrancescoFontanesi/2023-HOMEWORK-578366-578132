package it.uniroma3.diadia.Comandi;

import it.uniroma3.diadia.Partita.*;

public class ComandoNonValido extends AbstractComando {
	private final static String NOME_CMD = "Comando NonValido";

	public ComandoNonValido(IO console) {
		super(console);
	}
	
	@Override
	public void esegui(Partita partita) {
		super.getConsole().mostraMessaggio("Comando sconosciuto...");
	}
	
	@Override
	public String getNome() {
		return NOME_CMD;
	}
}
