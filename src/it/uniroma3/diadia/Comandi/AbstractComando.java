package it.uniroma3.diadia.Comandi;

import it.uniroma3.diadia.Partita.*;

public abstract class AbstractComando {
	private IO console;
	private String parametro;
	
	public AbstractComando(IO console) {
		this.console = console;
	}
	
	public abstract void esegui(Partita partita);
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public abstract String getNome();
	
	public String getParametro() {
		return this.parametro;
	}
	
	public IO getConsole() {
		return this.console;
	}
	
	public void setIO(IO console) {
		this.console = console;
	}
}
