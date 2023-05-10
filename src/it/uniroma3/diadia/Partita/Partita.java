package it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Ambienti.*;
import it.uniroma3.diadia.Giocatore.*;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private Giocatore giocatore;
	private Labirinto labirinto;
	private boolean finita;

	
	public Partita(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.finita = false;
		this.giocatore = new Giocatore();
	}
	
	/**
	 * Per evitare di scrivere più codice non scrivo un metodo getLabirinto()
	 * per accedere a stanzaCorrente e Vincente, ma permetto l'accesso da Partita
	 */ 
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.labirinto.setStanzaCorrente(stanzaCorrente);
	}
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.labirinto.setStanzaVincente(stanzaVincente);
	}
	
	public Stanza getStanzaCorrente() {
		return this.labirinto.getStanzaCorrente();
	}
	public Stanza getStanzaVincente() {
		return this.labirinto.getStanzaVincente();
	}
	
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || ( getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	/**
	 * Ambedue i metodi permettono di accedere ai cfu correnti dalla classe
	 * Partita, benchè sfruttino l'istanza di Giocatore per lasciarne a 
	 * quest'ultima la gestione. Ho deciso di adottare questa soluzione poichè,
	 * almeno dove non strettamente necessario, di sfruttare meno codice possibile
	 * e non ricorrere per forza al metodo getGiocatore()
	 * @see Giocatore
	 * @return numero di cfu correnti
	 * @return void
	 */
	public int getCfu() {
		return this.giocatore.getCfu();
	}

	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);	
	}	
	
	/**
	 * setLabirinto permette di impostare il labirinto in cui si vuole giocare
	 */
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}
	
	/**
	 * GetGiocatore
	 * @return ritorna il giocatore associato alla partita
	 */
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	/**
	 * GiocatoreIsVivo
	 * @return boolean value che indica se il giocatore è vivo o meno (cfu esauriti o no)
	 */
	public boolean giocatoreIsVivo() {
		return getCfu() != 0;
	}
}