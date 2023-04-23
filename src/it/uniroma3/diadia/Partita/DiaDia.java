package it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Ambienti.*;
import it.uniroma3.diadia.Attrezzo.*;

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

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa"};

	private Partita partita;
	
	private IOConsole console;

	public DiaDia() {
		this.partita = new Partita();
		this.console = new IOConsole();
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
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if(comandoDaEseguire.getNome() == null)
			this.console.mostraMessaggio("Comando Non Valido");
		else if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else
			if(comandoDaEseguire.getNome().equals("prendi"))
				this.prendi(comandoDaEseguire.getParametro());
			else
				if(comandoDaEseguire.getNome().equals("posa"))
					this.posa(comandoDaEseguire.getParametro());
				else
			this.console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			if(this.partita.isFinita()) {
				this.console.mostraMessaggio("Hai perso!");
				return true;
			}
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		StringBuilder s = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++) {
			s.append(elencoComandi[i] + " ");	
		}
		this.console.mostraMessaggio(s.toString());
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.console.mostraMessaggio("Dove vuoi andare? Non ho capito la direzione");
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null)
				this.console.mostraMessaggio("Direzione inesistente");
			else {
				this.partita.setStanzaCorrente(prossimaStanza);
				int cfu = this.partita.getCfu();
				this.partita.setCfu(--cfu);
			}
			this.console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		}
	}
	/**
	 * Comando "Prendi": il giocatore può prendere un oggetto dalla Stanza, se
	 * questo è ovviamente presente, metterlo nella Borsa se è libero spazio,
	 * in caso contrario l'oggetto rimane in Stanza
	 */
	public void prendi(String nomeAttrezzo) {
		if(!this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo))
			this.console.mostraMessaggio("" + nomeAttrezzo + " non presente in Stanza");
		else {
			Stanza corrente = this.partita.getStanzaCorrente();
			Attrezzo daRimuovere = corrente.getAttrezzo(nomeAttrezzo);
			if(this.partita.getGiocatore().prendiAttrezzo(daRimuovere)) {
				corrente.removeAttrezzo(daRimuovere);
				this.console.mostraMessaggio("" + nomeAttrezzo + " aggiunto in Borsa");
			} else {
				this.console.mostraMessaggio("Impossibile prendere " + nomeAttrezzo);
			}
		}
	}
	
	/**
	 * Comando "Posa": permette di, se presente, spostare un oggetto dalla borsa ed aggiungerlo alla stanza
	 */
	public void posa(String nomeAttrezzo) {
		if(!this.partita.getGiocatore().hasAttrezzo(nomeAttrezzo))
			this.console.mostraMessaggio("" + nomeAttrezzo + " non presente in Borsa");
		else {
			Attrezzo daPosare = this.partita.getGiocatore().posaAttrezzo(nomeAttrezzo);
			if(this.partita.getStanzaCorrente().addAttrezzo(daPosare))
				this.console.mostraMessaggio("" + nomeAttrezzo + " posato");
			else {
				this.console.mostraMessaggio("Impossibile posare oggetto: stanza piena!");
				this.partita.getGiocatore().prendiAttrezzo(daPosare);
			}
		}
	}
	
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}
