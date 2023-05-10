package it.uniroma3.diadia.Giocatore;
import it.uniroma3.diadia.Attrezzo.*;

/**
 * 
 * La classe Giocatore mi permette di scaricare le responsabilità di gestione
 * dei cfu dalla classe Partita
 * 
 * @see Partita
 * @see Borsa
 *
 */

public class Giocatore {
	private Borsa borsa;
	private final static int CFU_INIZIALI = 20;
	private int cfu;
	
	public Giocatore() {
		this.borsa = new Borsa();
		this.cfu = CFU_INIZIALI;
	}
	
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	public int getCfu() {
		return this.cfu;
	}
	
	public boolean prendiAttrezzo(Attrezzo attrezzo) {
		return this.borsa.addAttrezzo(attrezzo);
	}
	
	public Attrezzo posaAttrezzo(String nomeAttrezzo) {
		return this.borsa.removeAttrezzo(nomeAttrezzo);
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.borsa.hasAttrezzo(nomeAttrezzo);
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}

}
