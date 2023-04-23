package it.uniroma3.diadia.Giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Attrezzo.*;

public class TestBorsa {
	private Borsa borsa;
	private Attrezzo prova;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.borsa = new Borsa();	
		this.prova = new Attrezzo("prova",1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.borsa = null;
		this.prova = null;
	}

	@Test
	public void testAggiungiTrue() {
		assertTrue(this.borsa.addAttrezzo(this.prova));
	}
	
	@Test
	public void testAggiungiFalse() {
		assertFalse(this.borsa.addAttrezzo(new Attrezzo("Pesante",20)));
	}
	
	@Test
	public void testGetAttrezzo() {
		this.borsa.addAttrezzo(prova);
		assertEquals(this.prova, this.borsa.getAttrezzo("prova"));
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	public void testGetPeso() {
		assertTrue(this.borsa.getPeso() == 0);
	}
	
	@Test
	public void testHasAttrezzo() {
		this.borsa.addAttrezzo(prova);
		assertTrue(this.borsa.hasAttrezzo("prova"));
	}
	
	@Test
	public void testRemoveTrue() {
		this.borsa.addAttrezzo(this.prova);
		assertEquals(this.borsa.removeAttrezzo("prova"), this.prova);
	}
	
	@Test
	public void testRemoveFalse() {
		assertNull(this.borsa.removeAttrezzo("nonPresente"));
	}
}
