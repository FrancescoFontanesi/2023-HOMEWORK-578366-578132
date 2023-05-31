package it.uniroma3.diadia.Ambienti;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.Ambienti.CaricatoreLabirinto.FormatoFileNonValidoException;
import it.uniroma3.diadia.Attrezzo.Attrezzo;
import it.uniroma3.diadia.Personaggi.AbstractPersonaggio;

public class Labirinto {
	private Stanza stanzaCorrente, stanzaVincente;

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	private Labirinto(CaricatoreLabirinto c) throws FormatoFileNonValidoException, ClassNotFoundException, 
	InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
	NoSuchMethodException, SecurityException {
		c.carica();
	}
	
	private Labirinto(String nomeFile, LabirintoBuilder builder) throws FormatoFileNonValidoException, ClassNotFoundException, 
	InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
	NoSuchMethodException, SecurityException, FileNotFoundException {
		this(new CaricatoreLabirinto(nomeFile, builder));
	}

	/* Controparte del costruttore in CaricatoreLabirinto - Permette di tornare il labirinto e
	 * lavorarci direttamente senza complicare la logica*/
	private Labirinto(StringReader fixture, LabirintoBuilder builder) throws FormatoFileNonValidoException, ClassNotFoundException, 
	InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
	NoSuchMethodException, SecurityException, FileNotFoundException {
		this(new CaricatoreLabirinto(fixture, builder));
	}

	/* Aggiungo questo costruttore per non distruggere la logica dei test*/
	private Labirinto(Stanza iniziale, Stanza vincente) {
		this.stanzaCorrente = iniziale;
		this.stanzaVincente = vincente;
	}
	
	public static LabirintoBuilder newBuilder(String nomeFile) throws FormatoFileNonValidoException, ClassNotFoundException, 
	InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
	NoSuchMethodException, SecurityException, FileNotFoundException {
		return new LabirintoBuilder(nomeFile);
	}
	
	public static LabirintoBuilder newBuilder(StringReader fixture) throws FormatoFileNonValidoException, ClassNotFoundException, 
	InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
	NoSuchMethodException, SecurityException, FileNotFoundException {
		return new LabirintoBuilder(fixture);
	}

	/* Sarebbe preferibile per semplicità dichiarare la classe statica nidificata come public, poichè 
	 * non è una pura classe di accompagnamento a Labirinto ma svolge un ruolo fondamentale nella sua
	 * creazione, quindi è utile poterla accedere anche dall'esterno (vedi CaricatoreLabirinto)*/
	public static class LabirintoBuilder {
		
		private Labirinto labirinto;
		private Map<String,Stanza> mappatura;
		private Stanza iniziale,vincente,ultimaAdd;

		public LabirintoBuilder(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException, 
		ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, 
		InvocationTargetException, NoSuchMethodException, SecurityException {
			this.mappatura = new HashMap<>();
			this.labirinto = new Labirinto(nomeFile,this);
		}
		
		public LabirintoBuilder(StringReader fixture) throws FormatoFileNonValidoException, FileNotFoundException, 
		ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, 
		InvocationTargetException, NoSuchMethodException, SecurityException {
			this.mappatura = new HashMap<>();
			this.labirinto = new Labirinto(fixture,this);
		}
		
		public LabirintoBuilder() {
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
			this.iniziale = new Stanza(nomeStanza);
			this.mappatura.put(nomeStanza, this.iniziale);
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
			this.vincente = new Stanza(nomeStanza);
			this.mappatura.put(nomeStanza, this.vincente);
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
		
		public LabirintoBuilder addStanzaMagica(String nomeStanza) {
			StanzaMagica magica = new StanzaMagica(nomeStanza);
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

		/**
		 * Imposta il personaggio nell'ultima stanza aggiunta
		 * @param p
		 * @return riferimento per method - chaining
		 */
		public LabirintoBuilder addPersonaggio(AbstractPersonaggio p) {
			this.ultimaAdd.setPersonaggio(p);
			return this;
		}

		public Stanza getStanzaIniziale() {
			return this.iniziale;
		}

		public Stanza getStanzaVincente() {
			return this.vincente;
		}

		public Labirinto getLabirinto() {
			if(this.labirinto == null)
				return new Labirinto(this.iniziale, this.vincente);
			this.labirinto.setStanzaCorrente(iniziale);
			this.labirinto.setStanzaVincente(vincente);
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
}
