package it.uniroma3.diadia.Ambienti;

import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.Attrezzo.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestStanza {
	private Stanza stanza;
	private Attrezzo prova;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.stanza = new Stanza("stanza");
		this.prova = new Attrezzo("prova",1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.stanza = null;
		this.prova = null;
	}
	
	@Test
	public void testAddAttrezzo() {
		assertTrue(this.stanza.addAttrezzo(this.prova));
	}

	@Test
	public void testStanzaAdiacente() {
		Stanza adiacente = new Stanza("adiacente");
		this.stanza.impostaStanzaAdiacente("nord", adiacente);
		assertEquals(adiacente, this.stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testHasAttrezzo() {
		this.stanza.addAttrezzo(this.prova);
		assertTrue(this.stanza.hasAttrezzo("prova"));
	}
	
	@Test
	public void testGetAttrezzo() {
		this.stanza.addAttrezzo(this.prova);
		assertEquals(this.stanza.getAttrezzo("prova"), this.prova);
	}

}
