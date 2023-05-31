package it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Comandi.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Scanner;

import it.uniroma3.diadia.Ambienti.*;
import it.uniroma3.diadia.Ambienti.CaricatoreLabirinto.FormatoFileNonValidoException;

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

	private Partita partita;

	private IO console;

	public static Properties prop = new Properties(); 

	private Integer livelloGioco;

	/* Conservo il costruttore originale per non "rompere" la logica dei test di IOSimulator*/
	public DiaDia(IO console, Labirinto labirinto) {
		this.partita = new Partita(labirinto);
		this.console = console;
		this.livelloGioco = 1;
	}

	public DiaDia(Scanner scanner) {
		this.partita = new Partita();
		this.console = new IOConsole(scanner);
		this.livelloGioco = 1;
	}

	public int getLivello() {
		return this.livelloGioco.intValue();
	}

	/* Aggiunto questo metodo per resettare la Partita al cambio di livello - Si è deciso di evitare di creare nuovi
	 * oggetti e, a questo proposito, da notare come la responsabilità del reset sia effettivamente alla classe Partita*/
	public DiaDia reset() {
		this.partita.setCfu(Integer.parseInt(prop.getProperty("CFU_Iniziali", "30")));
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaCorrente());
		return this;
	}

	/* Aggiungo un setter del Labirinto per modellare i livelli di difficoltà crescenti 
	 *  Si è deciso di sfruttare la logica method - chaining*/
	public DiaDia setLabirinto(Labirinto labirinto) {
		this.partita.setLabirinto(labirinto);
		return this;
	}

	public void gioca(int livelliDia) throws ClassNotFoundException, IllegalAccessException, 
	NoSuchMethodException, InstantiationException, 
	InvocationTargetException, SecurityException {
		String istruzione; 

		this.console.mostraMessaggio(prop.getProperty("Messaggio_Benvenuto", 

				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
						"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
						"I locali sono popolati da strani personaggi, " +
						"alcuni amici, altri... chissa!\n"+
						"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
						"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
						"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'."));		
		do		
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione, livelliDia));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 */
	public boolean processaIstruzione(String istruzione, int livelliDia) throws ClassNotFoundException, 
	IllegalAccessException, NoSuchMethodException,InstantiationException, 
	InvocationTargetException, SecurityException {
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();
		AbstractComando comando = factory.costruisciComando(istruzione, this.console);

		comando.esegui(this.partita);
		if(this.partita.vinta()) {
			if(this.getLivello() < livelliDia)
				this.console.mostraMessaggio("Hai vinto!... Prossimo livello!\n");
			else
				this.console.mostraMessaggio("Hai vinto!");
			++ this.livelloGioco;
		}
		else
			if(!this.partita.giocatoreIsVivo()) {
				this.console.mostraMessaggio("Hai perso! Cfu esauriti...\nRicomincia da capo!\n");
				this.livelloGioco = 1;
			}
		return this.partita.isFinita();
	}

	public static void main(String[] argc) throws ClassNotFoundException, 
	IllegalAccessException, NoSuchMethodException, InstantiationException, 
	InvocationTargetException, SecurityException, FormatoFileNonValidoException, 
	FileNotFoundException {
		
		try(Scanner scanner = new Scanner(System.in)) {
			try {
				prop.load(new FileReader("resources/diadia.properties"));
			} catch(IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Errore nel caricamento delle configurazioni"
						,new Throwable("File corrotto o inesistente"));
			}

			final int livelliDia = Integer.parseInt(prop.getProperty("Livelli_Dia", "3"));
			DiaDia gioco = new DiaDia(scanner);

			while(gioco.getLivello() <= livelliDia) {
				Labirinto labirinto = Labirinto.newBuilder("resources/Labirinto" + gioco.getLivello()).getLabirinto();
				gioco.setLabirinto(labirinto).reset().gioca(livelliDia);
			}
		}
		
	}

}
