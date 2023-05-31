package it.uniroma3.diadia.Ambienti;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import it.uniroma3.diadia.Attrezzo.Attrezzo;
import it.uniroma3.diadia.Personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.Personaggi.Mago;
import it.uniroma3.diadia.Personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite: ";

	/* prefisso della riga contenente le specifiche delle stanze bloccate> */
	private static final String STANZEBLOCCATE_MARKER = "StanzeBloccate: "; 

	/* prefisso della riga contenente le specifiche delle stanze magiche> */
	private static final String STANZEMAGICHE_MARKER = "StanzeMagiche: ";    

	/* prefisso della riga contenente le specifiche delle stanze buie> */
	private static final String STANZEBUIE_MARKER = "StanzeBuie: ";   

	/* prefisso della riga contenente le specifiche dei personaggi */
	private static final String PERSONAGGI_MARKER = "Personaggi: ";             

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */

	/*
	 * DOPO ESERCIZIO 7:
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N9, N10, N11
		Inizio: N10
		Vincente: N11
		StanzeBloccate: ripostiglio chiave nord
		StanzeMagiche: laboratorio 3
		StanzeBuie: cantina lanterna
		Personaggi: Mago Merlino "Un grande mago" bacchetta 2 biblioteca, Strega Sabrina "Strega Cattiva" laboratorio
		Attrezzi: martello 10 biblioteca, pinza 2 N10, chiave 1 N9, lanterna 2 laboratorio

		Uscite: biblioteca nord N10, biblioteca sud N9, N10 sud biblioteca, N19 nord biblioteca, 
				N10 est ripostiglio, ripostiglio ovest N10, ripostiglio nord N11,
				biblioteca est laboratorio, laboratorio nord ripostiglio, cantina est N10,
				N10 ovest cantina

		La logica della voce Stanze è stata cambiata: potendo aggiungere stanze di tipi diversi, per "Stanze"
		si intendono quelle generiche che non rientrano non nessuna particolarità

	 */
	private LineNumberReader reader;

	private Labirinto.LabirintoBuilder builder;

	public CaricatoreLabirinto(String nomeFile, Labirinto.LabirintoBuilder builder) throws FileNotFoundException {
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.builder = builder;
	}

	/* Costruttore per il testing - passo uno String Reader che contiene la specifica del labirinto
	 * a fixture di complessità crescenti, disaccoppiando dalla necessità di avere un File*/
	public CaricatoreLabirinto(StringReader fixture, Labirinto.LabirintoBuilder builder) throws FileNotFoundException {
		this.reader = new LineNumberReader(fixture);
		this.builder = builder;
	}

	public void carica() throws FormatoFileNonValidoException, ClassNotFoundException, InstantiationException,
	IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.LeggiECreaStanzeChiuse();
			this.LeggiECreaStanzeMagiche();
			this.LeggiECreaStanzeBuie();
			this.leggiECollocaPersonaggi();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	/* Suppongo che la forma del File sia stretta come indicato - non si può scambiare l'ordine delle righe*/
	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException  e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		try(Scanner scannerLinee = new Scanner(nomiStanze)){
			while(scannerLinee.hasNext()) {
				this.builder.addStanza(rimuoviVirgola(scannerLinee.next()));
			}
		}
	}

	private String rimuoviVirgola(String s) {
		if(s.charAt(s.length() - 1) == ',')
			s = s.substring(0, s.length() - 1);
		return s;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		this.builder.addStanzaIniziale(nomeStanzaIniziale);
		this.builder.addStanzaVincente(nomeStanzaVincente);
	}

	private void LeggiECreaStanzeChiuse() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZEBLOCCATE_MARKER);
		String nomeStanza = null;
		String perSbloccare = null;
		String dirBloccata = null;
		try(Scanner scannerLinea = new Scanner(nomiStanze)) {
			while(scannerLinea.hasNext()) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una StanzaBloccata."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'Attrezzo sbloccante."));
				perSbloccare = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della direzione bloccata."));
				dirBloccata = rimuoviVirgola(scannerLinea.next());
				this.builder.addStanzaBloccata(nomeStanza, perSbloccare, dirBloccata);
			}
		}
	}

	private void LeggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZEBUIE_MARKER);
		String nomeStanza = null;
		String perSbloccare = null;
		try(Scanner scannerLinea = new Scanner(nomiStanze)) {
			while(scannerLinea.hasNext()) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una StanzaBuia."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'Attrezzo che può far luce."));
				perSbloccare = rimuoviVirgola(scannerLinea.next());
				this.builder.addStanzaBuia(nomeStanza, perSbloccare);
			}
		}
	}

	private void LeggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZEMAGICHE_MARKER);
		String nomeStanza = null;
		try(Scanner scannerLinea = new Scanner(nomiStanze)) {
			while(scannerLinea.hasNext()) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una Stanza."));
				nomeStanza = scannerLinea.next();
				if(scannerLinea.hasNext()) {
					this.builder.addStanzaMagica(nomeStanza, Integer.
							parseInt(rimuoviVirgola(scannerLinea.next())));
				} else
					this.builder.addStanzaMagica(rimuoviVirgola(nomeStanza));

			}
		}
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);
		String nomeAttrezzo = null;
		String pesoAttrezzo = null;
		String nomeStanza = null; 

		try (Scanner scannerLinea = new Scanner(specificheAttrezzi)) {
			while(scannerLinea.hasNext()) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = rimuoviVirgola(scannerLinea.next());	
				posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
			}
		}				
	}

	private void leggiECollocaPersonaggi() throws FormatoFileNonValidoException, ClassNotFoundException, 
	InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, 
	NoSuchMethodException, SecurityException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
		String tipoPersonaggio = null;
		String nome = null, descrizione = null, nomeAttrezzo1 = null, nomeAttrezzo2 = null,
				pesoAttrezzo1 = null, pesoAttrezzo2 = null, nomeStanza = null;
		try(Scanner scannerLinea = new Scanner(specifichePersonaggi)){
			while(scannerLinea.hasNext()) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il tipo di un personaggio"));
				tipoPersonaggio = scannerLinea.next();
				StringBuilder s = new StringBuilder("it.uniroma3.diadia.Personaggi.");
				s.append(tipoPersonaggio);
				@SuppressWarnings("unchecked")
				Class<? extends AbstractPersonaggio> c = (Class<? extends AbstractPersonaggio>) 
				Class.forName(s.toString());
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un personaggio"));
				nome = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un personaggio"));
				descrizione = scannerLinea.next();	
				if(descrizione.charAt(0) != '"')
					check(false,msgTerminazionePrecoce("la descrizione di un personaggio"));
				else {
					StringBuilder t = new StringBuilder(descrizione.substring(1) + " ");
					String add = scannerLinea.next();
					while(add.charAt(add.length() - 1) != '"') {
						if(add.charAt(add.length() - 1) == ',')
							check(false,msgTerminazionePrecoce("la descrizione di un personaggio"));
						else {
							t.append(add + " ");
							add = scannerLinea.next();
						}
					}
					t.append(add.substring(0, add.length() - 1));
					descrizione = t.toString();
				}
				if(c != Strega.class) {
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo di un personaggio"));
					nomeAttrezzo1 = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo di un personaggio"));
					pesoAttrezzo1 = scannerLinea.next();
					if(c != Mago.class) {
						check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo di un personaggio"));
						nomeAttrezzo2 = scannerLinea.next();
						check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo di un personaggio"));
						pesoAttrezzo2 = scannerLinea.next();
						check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove è collocato un personaggio"));
						nomeStanza = rimuoviVirgola(scannerLinea.next());
						if(this.builder.getMappaStanze().containsKey(nomeStanza))
							this.builder.getMappaStanze().get(nomeStanza).setPersonaggio(
									c.getDeclaredConstructor(String.class,String.class,Attrezzo.class,Attrezzo.class).
									newInstance(nome,descrizione,new Attrezzo(nomeAttrezzo1,Integer.parseInt(pesoAttrezzo1)), 
											new Attrezzo(nomeAttrezzo2,Integer.parseInt(pesoAttrezzo2))));
						else
							check(false,msgTerminazionePrecoce("la stanza dove è collocato un personaggio"));

					} else {
						check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove è collocato un personaggio"));
						nomeStanza = rimuoviVirgola(scannerLinea.next());
						if(this.builder.getMappaStanze().containsKey(nomeStanza))
							this.builder.getMappaStanze().get(nomeStanza).setPersonaggio(
									c.getDeclaredConstructor(String.class,String.class,Attrezzo.class).
									newInstance(nome,descrizione,new Attrezzo(nomeAttrezzo1,Integer.parseInt(pesoAttrezzo1))));
						else
							check(false,msgTerminazionePrecoce("la stanza dove è collocato un personaggio"));

					}
				} else {
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove è collocato un personaggio"));
					nomeStanza = rimuoviVirgola(scannerLinea.next());
					if(this.builder.getMappaStanze().containsKey(nomeStanza))
						this.builder.getMappaStanze().get(nomeStanza).setPersonaggio(
								c.getDeclaredConstructor(String.class,String.class).
								newInstance(nome,descrizione));
					else
						check(false,msgTerminazionePrecoce("la stanza dove è collocato un personaggio"));

				}
			}
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.builder.getMappaStanze().get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.builder.getMappaStanze().containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);

		try(Scanner scannerDiLinea = new Scanner(specificheUscite)) {			

			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = rimuoviVirgola(scannerDiLinea.next());				
				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		} 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		this.builder.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.builder.getStanzaIniziale();
	}

	public Stanza getStanzaVincente() {
		return this.builder.getStanzaVincente();
	}

	public static class FormatoFileNonValidoException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FormatoFileNonValidoException(String msg){
			super(msg);
		}
	}
}
