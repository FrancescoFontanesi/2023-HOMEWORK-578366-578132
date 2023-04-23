package it.uniroma3.diadia.Comandi;
import it.uniroma3.diadia.Partita.*;

public class ComandoFine implements Comando {
	private IO console;
	public final static String MSG_FINE = "Grazie di aver giocato!";
	private final static String NOME_CMD = "Comando Fine";
	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		this.console.mostraMessaggio(MSG_FINE);
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
