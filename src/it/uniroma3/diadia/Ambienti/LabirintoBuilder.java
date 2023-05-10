package it.uniroma3.diadia.Ambienti;
import it.uniroma3.diadia.Attrezzo.*;
import java.util.Map;
import java.util.HashMap;

public class LabirintoBuilder extends Labirinto{
	private Labirinto labirinto;
	private Map<String,Stanza> mappatura;
	private Stanza ultimaAdd;
	
	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.mappatura = new HashMap<>();
	}
	
	/**
	 * Crea la stanza iniziale e la imposta a stanzaCorrente (che in
	 * prima battuta, appena viene creato il Labirinto, è la stanza Iniziale)
	 * nella superclasse Labirinto.
	 * @param nomeStanza
	 * @return riferimento per method - chaining
	 */
	public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
		Stanza iniziale = new Stanza(nomeStanza);
		this.labirinto.setStanzaCorrente(iniziale);
		this.mappatura.put(nomeStanza, iniziale);
		this.ultimaAdd = iniziale;
		return this;
	}
	
	/**
	 * Crea la stanza vincente e la imposta a stanzaVincente(che è la
	 * stanza vincente del Labirinto) nella superclasse Labirinto.
	 * @param nomeStanza
	 * @return riferimento per method - chaining
	 */
	public LabirintoBuilder addStanzaVincente(String nomeStanza) {
		Stanza vincente = new Stanza(nomeStanza);
		this.labirinto.setStanzaVincente(vincente);
		this.mappatura.put(nomeStanza, vincente);
		this.ultimaAdd = vincente;
		return this;
	}
	
	/**
	 * Aggiunge al Labirinto una stanzaMagica (da collegare) dati nome e soglia
	 * @param nomeStanza
	 * @param sogliaMagica
	 * @return riferimento per method - chaining
	 */
	public LabirintoBuilder addStanzaMagica(String nomeStanza, int sogliaMagica) {
		StanzaMagica magica = new StanzaMagica(nomeStanza,sogliaMagica);
		this.mappatura.put(nomeStanza, magica);
		this.ultimaAdd = magica;
		return this;
	}
	
	/**
	 * Aggiunge al Labirinto una stanzaBloccata (da collegare) dati nome, direzione
	 * bloccata e nome dell'attrezzo per sbloccare
	 * @param nomeStanza
	 * @param perSbloccare
	 * @param dirBloccata
	 * @return riferimento per method - chaining
	 */
	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String perSbloccare, String dirBloccata) {
		StanzaBloccata bloccata = new StanzaBloccata(nomeStanza,perSbloccare,dirBloccata);
		this.mappatura.put(nomeStanza, bloccata);
		this.ultimaAdd = bloccata;
		return this;
	}
	
	/**
	 * Aggiunge al Labirinto una stanzaBuia (da collegare) dati nome e nome
	 * dell'attrezzo per accendere luce
	 * @param nomeStanza
	 * @param perSbloccare
	 * @return riferimento per method - chaining
	 */
	public LabirintoBuilder addStanzaBuia(String nomeStanza, String perSbloccare) {
		StanzaBuia buia = new StanzaBuia(nomeStanza,perSbloccare);
		this.mappatura.put(nomeStanza, buia);
		this.ultimaAdd = buia;
		return this;
	}
	
	/**
	 * Aggiunge le stanza al labirinto (da Collegare). Posto un controllo
	 * per evitare che ci siano stanze identiche con lo stesso nome
	 * @return riferimento per method - chaining
	 */
	public LabirintoBuilder addStanza(String nomeStanza) {
		if(!this.mappatura.containsKey(nomeStanza)) {
			Stanza add = new Stanza(nomeStanza);
			this.mappatura.put(nomeStanza, add);
			this.ultimaAdd = add;
		}
		return this;
	}
	
	/**
	 * Si occupa di creare le adiacenze tra le stanze del labirinto. Posso "collegare"
	 * solo stanze già esistenti e aggiunte nel Labirinto
	 * @param nomeStanza
	 * @param nomeAdiacente
	 * @param direzione
	 * @return riferimento per method - chaining
	 */
	public LabirintoBuilder addAdiacenza(String nomeStanza, String nomeAdiacente, String direzione) {
		if(this.mappatura.containsKey(nomeStanza) && this.mappatura.containsKey(nomeAdiacente)) 
			this.mappatura.get(nomeStanza).impostaStanzaAdiacente(direzione, this.mappatura.get(nomeAdiacente));
		return this;
	}
	
	/**
	 * Aggiunge l'attrezzo formato dai parametri passati ad argomento del metodo
	 * @param nomeAttrezzo
	 * @param peso
	 * @return riferimento per method - chaining
	 */
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		if(this.ultimaAdd != null) {
			this.ultimaAdd.addAttrezzo(new Attrezzo(nomeAttrezzo,peso));
		}
		return this;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	/**
	 * Metodo richiesto per batteria di test @see TestBuilderFornito
	 * @return Mappa delle stanze del labirinto
	 */
	public Map<String,Stanza> getMappaStanze(){
		return this.mappatura;
	}
}
