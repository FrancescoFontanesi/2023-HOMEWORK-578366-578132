package it.uniroma3.diadia.Partita;

public class IOSimulator implements IO {
	private String[] istruzioniIN;
	private String[] msgGioco;
	
	private final static int MAX_MSG = 50;
	
	private int istruzioniDate;
	private int daMemorizzare;
	private int msgCorrente;
	
	public IOSimulator(String[] istruzioniIN) {
		this.istruzioniIN = istruzioniIN;
		this.msgGioco = new String[MAX_MSG];
		
		this.istruzioniDate = 0;
		this.daMemorizzare = 0;
		this.msgCorrente = 0;
	}
	
	/**
	 * Con questo metodo riesco a mantenere un record delle "risposte" del gioco alle
	 * istruzioni impartite dalla stessa console
	 */
	@Override
	public void mostraMessaggio(String msg) {
		if(isEmpty()) {
			this.msgGioco[this.daMemorizzare] = msg;
			this.daMemorizzare++;
		}	
	}
	
	/**
	 * Con questo metodo posso tornare il messaggio corrente per verificare la corretta
	 * simulazione della partita
	 * @return messaggio corrente dal gioco
	 */
	public String getMsgCorrente() {
		if(isEmpty()) {
			String corrente = this.msgGioco[this.msgCorrente];
			this.msgCorrente++;
			return corrente;
		}
		else
			return null;
	}
	
	/**
	 * Con questo metodo rendo possibile saltare determinate iterazioni in fase di testing
	 */
	public void skipMsgCorrente() {
		this.msgCorrente = this.msgCorrente + 1;
	}
	
	/**
	 * Verifico se nell'Array di messaggi c'è spazio per evitare ripetizioni di codice
	 * @return boolean value di verifica
	 */
	public boolean isEmpty() {
		return this.daMemorizzare < MAX_MSG;
	}
	
	/**
	 * Suppongo che l'iterazione di istruzioni sia sequenziale: vengono simulate le istruzioni
	 * in ordine di inserimento nell'Array passato a costruttore
	 * @return String di istruzione
	 * 
	 */
	
	@Override
	public String leggiRiga() {
		if(this.istruzioniDate == this.istruzioniIN.length)
			return "fine";
		else {
			String istruzione = this.istruzioniIN[this.istruzioniDate];
			this.istruzioniDate++;
			return istruzione;
		}		
	}
}
