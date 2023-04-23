package it.uniroma3.diadia.Comandi;
import it.uniroma3.diadia.Partita.*;

public interface FabbricaDiComandi {
	public Comando costruisciComando(String istruzione, IO console);
}
