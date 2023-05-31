package it.uniroma3.diadia.Ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaricatoreLabirintoTestFixtureMonolocale {
	
	private final String monolocale = "Stanze: N11\n"
			+ "Inizio: N11\n"
			+ "Vincente: NULL\n"
			+ "StanzeBloccate: \n"
			+ "StanzeMagiche: \n"
			+ "StanzeBuie: \n"
			+ "Personaggi: \n"
			+ "Attrezzi: martello 10 N11, pinza 2 N11\n"
			+ "Uscite: ";
	private Labirinto l;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.l = Labirinto.newBuilder(new StringReader(monolocale)).getLabirinto();
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.l = null;
	}

	@Test
	public void testStanzaIniziale() {
		assertEquals(this.l.getStanzaCorrente().getNome(), "N11");
	}
	
	/* Si è deciso di non fornire una logica implementativa (utile quando il labirinto è monolocale) per
	 * gestire la non aggiunta di stanza iniziali/vincenti, di cui dobbiamo per forza riportare il nome
	 * nel formato altrimenti ricorriamo in eccezione, poichè casi particolari e circoscritti al testing */
	@Test
	public void testStanzaVincente() {
		assertEquals(this.l.getStanzaVincente().getNome(), "NULL");
	}
	
	@Test
	public void testAttrezziPresentiNonPresenti() {
		assertTrue(this.l.getStanzaCorrente().hasAttrezzo("martello"));
		assertTrue(this.l.getStanzaCorrente().hasAttrezzo("pinza"));
		assertFalse(this.l.getStanzaCorrente().hasAttrezzo("chiave"));
	}

}
