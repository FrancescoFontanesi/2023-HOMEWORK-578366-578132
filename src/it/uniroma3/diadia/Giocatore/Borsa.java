package it.uniroma3.diadia.Giocatore;
import it.uniroma3.diadia.Attrezzo.*;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	public final static int NON_PRESENTE = -1;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10];
		this.numeroAttrezzi = 0;
	}
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		else {
			if(this.numeroAttrezzi == 10)
				return false;
			else {
				this.attrezzi[this.numeroAttrezzi] = attrezzo;
				this.numeroAttrezzi++;
				return true;
			}
		}
	}
	
	/**
	 * Utilizzo un metodo getIndice per evitare ripetizioni di codice nei metodi @see removeAttrezzo
	 * @see getAttrezzo poichè si basano tutti su quest'operazione di ricerca comune
	 * @param nomeAttrezzo
	 * @return indice identificativo dell'oggetto
	 */
	public int getIndice(String nomeAttrezzo) {
		int index = NON_PRESENTE;
		for(int i = 0; i < this.numeroAttrezzi && index == NON_PRESENTE ; i++) {
			if(this.attrezzi[i].getNome().equals(nomeAttrezzo))
				index = i;
		}
		return index;
	}
	
	/*
	 * Si suppongano uguali due attrezzi con lo stesso nome
	 * vedi removeAttrezzo(), possibile implementazione anche senza dichiarare
	 * un nuovo oggetto attrezzo: in questo modo i test equals risultano
	 * più facilmente implementabili (@see removeAttrezzo()) poichè ritorna
	 * il riferimento all'oggetto stesso
	 * 
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		int index = getIndice(nomeAttrezzo);
		
		if(index == NON_PRESENTE)
			return null;
		else
			return this.attrezzi[index];
	}	
	
	public int getPeso() {
		int peso = 0;
		for(int i = 0; i < this.numeroAttrezzi; i++) {
			peso += this.attrezzi[i].getPeso();
		}
		return peso;
	}
	public int getPesoMax() {
		return this.pesoMax;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}
	/**
	 * La rimozione dall'Array, in questo caso, si basa su un algoritmo che, dopo
	 * aver ricavato l'indice corrispondente all'elemento da eliminare, sposta tutti
	 * gli elementi successivi di un posto a sinistra, creando un duplicato in ultima
	 * posizione che viene posto null per sfruttare il garbage collector
	 * 
	 * @param nomeAttrezzo
	 * @return Attrezzo rimosso (Da mettere nella stanza)
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		if(!this.hasAttrezzo(nomeAttrezzo))
			return null;
		else {
			int index = getIndice(nomeAttrezzo);
			Attrezzo rimosso = this.attrezzi[index];
			
			for(int j = index; j < this.numeroAttrezzi - 1; j++) {
				this.attrezzi[j] = this.attrezzi[j + 1];
			}
			this.attrezzi[this.numeroAttrezzi - 1] = null;
			this.numeroAttrezzi--;
			return rimosso;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if(!this.isEmpty()) {
			s.append("Contenuto Borsa(" + this.getPeso() + "kg/" + this.getPesoMax()
			 + "kg):");
			for(int i = 0; i < this.numeroAttrezzi; i++) {
				s.append(attrezzi[i].toString());
			}
		} else
			s.append("Borsa Vuota");
		return s.toString();
	}
}