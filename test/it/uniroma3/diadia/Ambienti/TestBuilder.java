package it.uniroma3.diadia.Ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Attrezzo.*;

public class TestBuilder {
	private Labirinto.LabirintoBuilder builder;

	@BeforeEach
	public void setUp() throws Exception {
		this.builder = new Labirinto.LabirintoBuilder();
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.builder = null;
	}

	@Test
	public void testStanzaIniziale() {
		this.builder.addStanzaIniziale("start");
		assertEquals(new Stanza("start"),this.builder.getMappaStanze().get("start"));
	}

	@Test
	public void testStanzaVincente() {
		this.builder.addStanzaVincente("end");
		assertEquals(new Stanza("end"),this.builder.getMappaStanze().get("end"));
	}

	@Test
	public void testStanzaMagica() {
		this.builder.addStanzaMagica("magica", 1);
		this.builder.getMappaStanze().get("magica").addAttrezzo(new Attrezzo("a1",1));
		this.builder.getMappaStanze().get("magica").addAttrezzo(new Attrezzo("AB",1));
		assertEquals(this.builder.getMappaStanze().get("magica").getAttrezzo("BA"),new Attrezzo("BA",2));
	}
	
	@Test
	public void testAddAdiacenza() {
		this.builder.addStanzaIniziale("start");
		this.builder.addStanza("aEst");
		this.builder.addAdiacenza("start", "aEst", "est");
		assertEquals(new Stanza("aEst"),this.builder.getMappaStanze().get("start").getStanzaAdiacente("est"));
	}

	@Test
	public void testStanzaBloccataSenzaSbloccante() {
		this.builder.addStanzaBloccata("bloccata", "chiave", "nord");
		Stanza bloccata = this.builder.getMappaStanze().get("bloccata");
		this.builder.addAdiacenza("bloccata", "aNord", "nord");
		assertEquals(bloccata,bloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testStanzaBloccataConSbloccante() {
		this.builder.addStanzaBloccata("bloccata", "chiave", "nord");
		this.builder.addAttrezzo("chiave", 1);
		Stanza bloccata = this.builder.getMappaStanze().get("bloccata");
		Stanza aNord = new Stanza("aNord");
		this.builder.addStanza("aNord");
		this.builder.addAdiacenza("bloccata", "aNord", "nord");
		assertEquals(aNord,bloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testStanzaBuioSenzaLuce() {
		this.builder.addStanzaBuia("buia", "lanterna");
		assertEquals("Qui c'è buio pesto...",this.builder.getMappaStanze().get("buia").getDescrizione());	
	}
	
	@Test
	public void testStanzaBuioConLuce() {
		this.builder.addStanzaBuia("buia", "lanterna");
		this.builder.addAttrezzo("lanterna", 1);
		StanzaBuia buia = new StanzaBuia("buia","lanterna");
		buia.addAttrezzo(new Attrezzo("lanterna",1));
		assertEquals(buia.getDescrizione(),this.builder.getMappaStanze().get("buia").getDescrizione());
		
	}
	
	@Test
	public void testAddStanza() {
		this.builder.addStanza("prova");
		assertTrue(this.builder.getMappaStanze().size() == 1 && this.builder.getMappaStanze().containsKey("prova"));
	}
	
	@Test
	public void testAddAttrezzo() {
		Attrezzo attrezzo = new Attrezzo("attrezzo",1);
		this.builder.addStanzaIniziale("start");
		this.builder.getMappaStanze().get("start").addAttrezzo(attrezzo);
		assertTrue(this.builder.getMappaStanze().get("start").getAttrezzi().containsValue(attrezzo));
		
	}
}
