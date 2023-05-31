
package it.uniroma3.diadia.Ambienti;
import it.uniroma3.diadia.Attrezzo.*;
import it.uniroma3.diadia.Partita.DiaDia;
import it.uniroma3.diadia.Personaggi.AbstractPersonaggio;

import java.util.Set;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	final static private int NUMERO_MASSIMO_ATTREZZI = Integer.parseInt(
			DiaDia.prop.getProperty("Numero_Massimo_Attrezzi", "10"));
	
	private String nome;
	private Map<String,Attrezzo> attrezzi;
	private static List<String> interazioni = new ArrayList<>(Arrays.asList(",si trova nell'angolo a fissare il muro",
			",si trova al centro e sta guardando il soffitto",",sta fischiettando e ti osserva",",sembra volere qualcosa da te"));
	private EnumMap<Direzione,Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.attrezzi = new HashMap<>(10);
		this.stanzeAdiacenti = new EnumMap<Direzione,Stanza>(Direzione.class);
	}

	/**
	 * Imposta una stanza adiacente.
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public boolean impostaStanzaAdiacente(String direzione, Stanza stanza) 
			throws IllegalArgumentException {
		try {
			Direzione dir = Direzione.valueOf(direzione);
			this.stanzeAdiacenti.put(dir, stanza);
		} catch(IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		try {
			return this.stanzeAdiacenti.get(Direzione.valueOf(direzione));			
		} catch(IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Map<String,Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.spazioLibero())
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
		else
			return false;
		return true;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(this.nome);
		s.append("\nUscite:\n");
		Set<Direzione> direzioni = getDirezioni();
		for(Direzione dir : direzioni) {
			s.append(dir.name()+ " ");
		}
		s.append("\nAttrezzi nella Stanza:");
		for(Attrezzo attr : this.attrezzi.values()) {
			s.append(attr.toString());
		}
		if(this.hasPersonaggio()) {
			Random rnd = new Random();
			s.append("\nC'è " + this.personaggio.desc4Stanza() + "nella Stanza" 
						+ interazioni.get(rnd.nextInt(3)));
		}
		return s.toString();
	}

	/**
	 * Controlla se un attrezzo è presente nella stanza. L'implementazione 
	 * di Map qui è naturale.
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(this.hasAttrezzo(nomeAttrezzo))
			return this.attrezzi.get(nomeAttrezzo);
		else
			return null;
	}

	/**
	 * Rimuove un attrezzo dalla stanza
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo.getNome(), attrezzo);			
	}

	public Set<Direzione> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}


	/**
	 * Verifico se il numero di attrezzi all'interno della stanza non ecceda la
	 * disponibilità della stanza stessa
	 * @return boolean value identificativo se è possibile aggiungere o no 
	 * nuovi attrezzi 
	 */
	public boolean spazioLibero() {
		return this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI;
	}

	/**
	 * Ritorna la mappatura delle stanze adiacenti alla stanza in analisi
	 * @return mappa delle stanze adiacenti
	 */
	public EnumMap<Direzione,Stanza> getMapStanzeAdiacenti(){
		return this.stanzeAdiacenti;
	}
	/**
	 * Criterio di uguaglianza per due Stanze: definisco due stanze uguali se hanno lo stesso
	 * nome, non controllo uscite e stanze collegate poichè non c'è, di base, motivo per chiamare
	 * due stanze distinte con lo stesso nome
	 * @return boolean value identificativo
	 */
	@Override
	public boolean equals(Object o) {
		Stanza s = (Stanza) o;
		return this.getNome().equals(s.getNome());
	}
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	public boolean hasPersonaggio() {
		return this.personaggio != null;
	}

}