package it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Comandi.*;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final protected String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	
	private IO console;

	public DiaDia(IO console) {
		this.partita = new Partita();
		this.console = console;
	}

	public void gioca() {
		String istruzione; 

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	public boolean processaIstruzione(String istruzione) {
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
		Comando comando = factory.costruisciComando(istruzione, this.console);
		
		comando.esegui(this.partita);
		if(this.partita.vinta())
			this.console.mostraMessaggio("Hai vinto!");
		else
			if(!this.partita.giocatoreIsVivo())
				this.console.mostraMessaggio("Hai perso! Cfu esauriti...");
		return this.partita.isFinita();
	}

	public static void main(String[] argc) {
		IO console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}
