package it.uniroma3.diadia.Ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaricatoreLabirintoTestFixtureBilocale {
	
	private String bilocale = "Stanze: N10, N11\n"
			+ "Inizio: N10\n"
			+ "Vincente: N11\n"
			+ "StanzeBloccate: \n"
			+ "StanzeMagiche: \n"
			+ "StanzeBuie: \n"
			+ "Personaggi: \n"
			+ "Attrezzi: martello 10 N11, pinza 2 N10\n"
			+ "Uscite: N10 sud N11, N11 nord N10";
	private Labirinto l;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.l = Labirinto.newBuilder(new StringReader(bilocale)).getLabirinto();
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.l = null;
	}

	@Test
	public void testStanzaIniziale() {
		assertEquals(this.l.getStanzaCorrente().getNome(), "N10");
	}
	
	@Test
	public void testStanzaVincente() {
		assertEquals(this.l.getStanzaVincente().getNome(), "N11");
	}

	@Test
	public void testAdiacenzeIniziale() {
		assertEquals(this.l.getStanzaCorrente().
				getStanzaAdiacente("sud").getNome(), "N11");
	}
	
	@Test
	public void testAdiacenzeVincente() {
		assertEquals(this.l.getStanzaVincente().
				getStanzaAdiacente("nord").getNome(), "N10");
	}
	
	@Test
	public void testAttrezziPresentiNonPresenti() {
		assertTrue(this.l.getStanzaVincente().hasAttrezzo("martello"));
		assertTrue(this.l.getStanzaCorrente().hasAttrezzo("pinza"));
		assertFalse(this.l.getStanzaCorrente().hasAttrezzo("chiave")
				&& this.l.getStanzaVincente().hasAttrezzo("chiave"));
	}

}
