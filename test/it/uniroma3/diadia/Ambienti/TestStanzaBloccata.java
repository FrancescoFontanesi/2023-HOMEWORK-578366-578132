package it.uniroma3.diadia.Ambienti;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Attrezzo.Attrezzo;

class TestStanzaBloccata {
	private static StanzaBloccata bloccata; 
	private static Stanza dopoNord, prova;

	@BeforeEach
	public void setUp() throws Exception {
		bloccata = new StanzaBloccata("bloccata", "chiave", "nord");
		dopoNord = new Stanza("dopoNord");
		prova = new Stanza("prova");
		
		bloccata.impostaStanzaAdiacente("nord", dopoNord);
		bloccata.impostaStanzaAdiacente("est", prova);
	}

	@AfterEach
	 public void tearDown() throws Exception {
		bloccata = null;
		dopoNord = null;
		prova = null;
	}

	@Test
	public void testBloccata() {
		assertEquals(bloccata, bloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testAltraDir() {
		assertEquals(prova, bloccata.getStanzaAdiacente("est"));
	}
	
	@Test
	public void testConAttrezzo() {
		Attrezzo chiave = new Attrezzo("chiave",1);
		bloccata.addAttrezzo(chiave);
		assertEquals(dopoNord, bloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testConAltroAttrezzo() {
		Attrezzo martello = new Attrezzo("martello",3);
		bloccata.addAttrezzo(martello);
		assertEquals(bloccata, bloccata.getStanzaAdiacente("nord"));
	}
}
