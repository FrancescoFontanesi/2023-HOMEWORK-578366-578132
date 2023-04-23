package it.uniroma3.diadia.Comandi;
import it.uniroma3.diadia.Partita.*;

public class ComandoAiuto implements Comando{
	private final String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa","guarda"};
	private final static String NOME_CMD = "Comando Aiuto";
	private IO console;

	public ComandoAiuto() {
		this.console = new IOConsole();
	}

	@Override
	public void esegui(Partita partita) {
		StringBuilder s = new StringBuilder();
		for(String comando : this.elencoComandi) {
			s.append(comando + " ");
		}
		this.console.mostraMessaggio(s.toString());
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
