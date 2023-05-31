package it.uniroma3.diadia.Ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaricatoreLabirintoTestFixtureTrilocale {
	
	private String trilocale = "Stanze: biblioteca, N10,N11\n"
			+ "Inizio: N10\n"
			+ "Vincente: N11\n"
			+ "StanzeBloccate: \n"
			+ "StanzeMagiche: \n"
			+ "StanzeBuie: \n"
			+ "Personaggi: \n"
			+ "Attrezzi: martello 10 biblioteca, pinza 2 N10\n"
			+ "Uscite: biblioteca nord N10, biblioteca sud N11, N11 nord biblioteca, N10 sud biblioteca";

	private Labirinto l;

	@BeforeEach
	public void setUp() throws Exception {
		this.l = Labirinto.newBuilder(new StringReader(trilocale)).getLabirinto();
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
				getStanzaAdiacente("sud").getNome(), "biblioteca");
	}
	
	@Test
	public void testAdiacenzeVincente() {
		assertEquals(this.l.getStanzaVincente().
				getStanzaAdiacente("nord").getNome(), "biblioteca");
	}
	
	@Test
	public void testAdiacenzeTerzaStanza() {
		assertEquals(this.l.getStanzaCorrente().getStanzaAdiacente("sud")
				.getStanzaAdiacente("nord").getNome(),"N10");
		assertEquals(this.l.getStanzaCorrente().getStanzaAdiacente("sud")
				.getStanzaAdiacente("sud").getNome(),"N11");
	}
	
	@Test
	public void testAttrezziPresentiNonPresenti() {
		assertFalse(this.l.getStanzaVincente().hasAttrezzo("martello"));
		assertTrue(this.l.getStanzaCorrente().hasAttrezzo("pinza"));
		assertTrue(this.l.getStanzaCorrente().getStanzaAdiacente("sud").hasAttrezzo("martello"));
		assertFalse(this.l.getStanzaCorrente().hasAttrezzo("chiave")
				&& this.l.getStanzaVincente().hasAttrezzo("chiave"));
	}

}
