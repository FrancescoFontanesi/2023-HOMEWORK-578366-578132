package it.uniroma3.diadia.Giocatore;
import it.uniroma3.diadia.Attrezzo.*;
import it.uniroma3.diadia.Partita.DiaDia;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Set;

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = Integer.parseInt(DiaDia.prop.
			getProperty("Peso_Max_Borsa", "10"));
	
	private Map<String,Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
	}
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.spazioLibero(attrezzo.getPeso()))
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
		else
			return false;
		return true;
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
		return this.attrezzi.get(nomeAttrezzo);
	}	
	
	public int getPeso() {
		int pesotot = 0;
		for(Attrezzo a : this.attrezzi.values()) {
			pesotot += a.getPeso();
		}
		return pesotot;
	}
	
	public int getPesoMax() {
		return this.pesoMax;
	}
	
	/**
	 * Verifico se la capacità massima di peso trasportabile non è ecceduta
	 * @return boolean value identificativo se è possibile aggiungere nuovi oggetti o meno
	 */
	public boolean spazioLibero(int peso) {
		return this.getPeso() + peso < this.pesoMax;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}
	/**
	 * Rimuove l'attrezzo indicato e lo ritorna
	 * @param nomeAttrezzo
	 * @return Attrezzo rimosso (Da mettere nella stanza)
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		if(this.hasAttrezzo(nomeAttrezzo))
			return this.attrezzi.remove(nomeAttrezzo);
		else
			return null;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if(this.isEmpty())
			s.append("Borsa Vuota");
		else {
			s.append("Contenuto Borsa(" + this.getPeso() + "kg/" + this.getPesoMax()
			 + "kg):");
			List<Attrezzo> ord = this.getContenutoOrdinatoPerPeso();
			for(Attrezzo a : ord) {
				s.append(a.toString() + " ");
			}
		}
		return s.toString();
	}
	
	Comparator<Attrezzo> cmp = new Comparator<Attrezzo>() {
		@Override
		public int compare(Attrezzo a1, Attrezzo a2) {
			int cmp = a1.getPeso() - a2.getPeso();
			if(cmp == 0)
				return a1.getNome().compareTo(a2.getNome());
			return cmp;
		}
	};
	
	/**
	 * Metodo di ordinamento di attrezzi per peso e, a parità di peso, per nome
	 * @return Lista di attrezzi ordinata per peso
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> ordinata = new ArrayList<>();
		ordinata.addAll(this.attrezzi.values());
		Collections.sort(ordinata, cmp);
		return ordinata;
	}
	
	/**
	 * Metodo di ordinamento di attrezzi per peso e, a parità di peso, per nome
	 * @return SortedSet di attrezzi ordinata per peso
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> ord = new TreeSet<>(cmp);
		ord.addAll(this.attrezzi.values());
		return ord;
	}
	
	/**
	 * Metodo di ordinamento di attrezzi per nome
	 * @return SortedSet di attrezzi ordinati per nome
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		return new TreeSet<Attrezzo>(this.attrezzi.values());
	}

	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer,Set<Attrezzo>> perPeso = new HashMap<>();
		for(Attrezzo a : this.attrezzi.values()) {
			Integer peso = a.getPeso();
			if(perPeso.keySet().contains(peso)) {
				perPeso.get(peso).add(a);
			} else {
				Set<Attrezzo> s = new TreeSet<>();
				s.add(a);
				perPeso.put(peso, s);
			}
		}
		return perPeso;
	}
}