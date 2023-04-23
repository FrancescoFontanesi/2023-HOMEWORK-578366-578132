package it.uniroma3.diadia.Comandi;

import it.uniroma3.diadia.Partita.*;

public class ComandoNonValido implements Comando {
	private IO console;
	private final static String NOME_CMD = "Comando NonValido";

	public ComandoNonValido() {
		this.console = new IOConsole();
	}
	
	@Override
	public void esegui(Partita partita) {
		this.console.mostraMessaggio("Comando sconosciuto...");
	}

	@Override
	public void setParametro(String parametro) {}
	
	
	@Override
	public String getParametro() {
		return null;
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
