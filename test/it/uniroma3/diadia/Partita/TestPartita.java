package it.uniroma3.diadia.Partita;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Ambienti.*;

public class TestPartita {
	private Partita partita;
	
	@BeforeEach
	void setUp() throws Exception {
		this.partita = new Partita(new Labirinto());
	}

	@AfterEach
	void tearDown() throws Exception {
		this.partita = null;
	}

	@Test
	void testSetFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	void testSetCfu() {
		this.partita.setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	void testStanzaVincente() {
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}

}
