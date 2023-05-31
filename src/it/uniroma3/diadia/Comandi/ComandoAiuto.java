package it.uniroma3.diadia.Comandi;
import it.uniroma3.diadia.Partita.*;

public class ComandoAiuto extends AbstractComando{
	private final String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa",
													"guarda","regala","interagisci","saluta"};
	
	private final static String NOME_CMD = "Comando Aiuto";

	public ComandoAiuto(IO console) {
		super(console);
	}

	@Override
	public void esegui(Partita partita) {
		StringBuilder s = new StringBuilder();
		for(String comando : this.elencoComandi) {
			s.append(comando + " ");
		}
		super.getConsole().mostraMessaggio(s.toString());
	}

	@Override
	public String getNome() {
		return NOME_CMD;
	}
}
