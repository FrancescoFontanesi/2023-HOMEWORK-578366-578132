package it.uniroma3.diadia.Partita;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class IOSimulator implements IO {
	private List<String> istruzioniIN, msgGioco;
	private Map<String,List<String>> recordIstruzione;
	
	private final static int MAX_MSG = 50;
	
	private int istruzioniDate;
	private int daMemorizzare;
	private int msgCorrente;
	private String istrCorrente;
	
	public IOSimulator(List<String> istruzioniIN) {
		this.istruzioniIN = istruzioniIN;
		this.msgGioco = new ArrayList<>(50);
		this.recordIstruzione = new HashMap<>();
		
		this.istruzioniDate = 0;
		this.daMemorizzare = 0;
		this.msgCorrente = 0;
		this.istrCorrente = null;
	}
	
	/**
	 * Con questo metodo riesco a mantenere un record delle "risposte" del gioco alle
	 * istruzioni impartite dalla stessa console
	 */
	@Override
	public void mostraMessaggio(String msg) {
		if(isEmpty()) {
			this.msgGioco.add(this.daMemorizzare, msg);
			this.daMemorizzare++;
			salvaRecord(msg);
		}	
	}
	
	/**
	 * salvaRecord sfrutta la struttura di Map<String,List<String>> che associa ad una stringa,
	 * che è il comando, una Lista di stringhe che è tutte le risposte del gioco al comando stesso,
	 * potendo istruzioni essere le stesse in ripetizione
	 * @param msg
	 */
	public void salvaRecord(String msg) {
		if(this.istrCorrente != null) {
			if(this.recordIstruzione.keySet().contains(this.istrCorrente))
				this.recordIstruzione.get(this.istrCorrente).add(msg);
			else {
				List<String> istrSucc = new ArrayList<>();
				istrSucc.add(msg);
				this.recordIstruzione.put(this.istrCorrente, istrSucc);
			}	
		}	
	}
	
	public Map<String,List<String>> getRecordIstruzioni() {
		return this.recordIstruzione;
	}
	
	/**
	 * Con questo metodo posso tornare il messaggio corrente per verificare la corretta
	 * simulazione della partita
	 * @return messaggio corrente dal gioco
	 */
	public String getMsgCorrente() {
		if(isEmpty()) {
			String corrente = this.msgGioco.get(this.msgCorrente);
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
		if(this.istruzioniDate == this.istruzioniIN.size())
			return "fine";
		else {
			String istruzione = this.istruzioniIN.get(this.istruzioniDate);
			this.istruzioniDate++;
			this.istrCorrente = istruzione;
			return istruzione;
		}		
	}
}
