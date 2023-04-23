package it.uniroma3.diadia.Giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Attrezzo.*;

public class TestGiocatore {
	private Giocatore giocatore;
	private Attrezzo prova;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.giocatore = new Giocatore();
		this.prova = new Attrezzo("prova",1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.giocatore = null;
		this.prova = null;
	}

	@Test
	public void testGetCfu() {
		assertTrue(this.giocatore.getCfu() == 20);
	}
	
	@Test
	public void testPrendiTrue() {
		assertTrue(this.giocatore.prendiAttrezzo(this.prova));
	}
	
	@Test
	public void testPrendiFalse() {
		assertFalse(this.giocatore.prendiAttrezzo(new Attrezzo("prova2",11)));
	}
	
	@Test
	public void testPosaTrue() {
		this.giocatore.prendiAttrezzo(this.prova);
		assertEquals(this.prova, this.giocatore.posaAttrezzo("prova"));
	}
	
	@Test
	public void testPosaIsNot() {
		assertNull(this.giocatore.posaAttrezzo("prova"));
	}
}
