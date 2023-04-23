package it.uniroma3.diadia.Ambienti;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Attrezzo.Attrezzo;

class TestStanzaBuia {
	private StanzaBuia prova;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.prova = new StanzaBuia("prova", "lanterna");
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.prova = null;
	}

	@Test
	public void testMSG() {
		assertEquals("Qui c'è buio pesto...", this.prova.getDescrizione());
	}
	
	@Test
	public void testAttrezzo() {
		Attrezzo lanterna = new Attrezzo("lanterna",1);
		this.prova.addAttrezzo(lanterna);
		String s = this.prova.getDescrizione();
		assertEquals(this.prova.getDescrizione(), s);
	}

}
