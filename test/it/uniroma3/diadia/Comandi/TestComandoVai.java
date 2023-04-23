package it.uniroma3.diadia.Comandi;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Ambienti.Stanza;
import it.uniroma3.diadia.Partita.Partita;

public class TestComandoVai {
	private Stanza start;
	private Partita partita;
	private ComandoVai vai;

	@BeforeEach
	public void setUp() throws Exception {
		vai = new ComandoVai();
		partita = new Partita();
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		start = null;
		partita = null;
	}
	

	@Test
	public void testNull() {
		start = partita.getStanzaCorrente();
		vai.setParametro(null);
		vai.esegui(partita);
		assertEquals(start, partita.getStanzaCorrente());
	}
	
	@Test
	public void testNord() {
		start = partita.getStanzaCorrente().getStanzaAdiacente("nord");
		vai.setParametro("nord");
		vai.esegui(partita);
		assertEquals(start, partita.getStanzaCorrente());
	}
	
	@Test
	public void testSud() {
		start = partita.getStanzaCorrente().getStanzaAdiacente("sud");
		vai.setParametro("sud");
		vai.esegui(partita);
		assertEquals(start, partita.getStanzaCorrente());
	}
	
	@Test
	public void testFalse1() {
		start = partita.getStanzaCorrente();
		vai.setParametro("est");
		vai.esegui(partita);
		assertNotEquals(start, partita.getStanzaCorrente());
	}
	
	@Test
	public void testFalse2() {
		start = partita.getStanzaCorrente();
		vai.setParametro("ovest");
		vai.esegui(partita);
		assertNotEquals(start, partita.getStanzaCorrente());
	}

}
