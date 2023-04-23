package it.uniroma3.diadia.Ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLabirinto {
	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.labirinto = new Labirinto();
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.labirinto = null;
	}

	@Test
	public void testBiblioteca() {
		assertEquals(this.labirinto.getStanzaVincente().getNome(),"Biblioteca");
	}
	
	@Test
	public void testAtrio() {
		assertEquals(this.labirinto.getStanzaCorrente().getNome(),"Atrio");
	}
	
	@Test
	public void testN11() {
		assertEquals(this.labirinto.getStanzaCorrente().getStanzaAdiacente("est").getNome(), "Aula N11");
	}
	
	@Test
	public void testN10() {
		assertEquals(this.labirinto.getStanzaCorrente().getStanzaAdiacente("sud").getNome(), "Aula N10");
	}
	
	@Test
	public void testLaboratorio() {
		assertEquals(this.labirinto.getStanzaCorrente().getStanzaAdiacente("ovest").getNome(), "Laboratorio Campus");
	}

}
