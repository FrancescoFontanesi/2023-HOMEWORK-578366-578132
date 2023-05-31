
package it.uniroma3.diadia.Comandi;
import it.uniroma3.diadia.Partita.*;

public class ComandoFine extends AbstractComando {
	public final static String MSG_FINE = "Grazie di aver giocato!";
	private final static String NOME_CMD = "Comando Fine";
	
	public ComandoFine(IO console) {
		super(console);
	}
	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		super.getConsole().mostraMessaggio(MSG_FINE);
	}
	
	@Override
	public String getNome() {
		return NOME_CMD;
	}
}
