package it.uniroma3.diadia.Ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLabirinto {
	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N10")
				.addStanza("Aula N11")
				.addStanza("Laboratorio Campus")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Atrio", "Aula N11", "est")
				.addAdiacenza("Atrio", "Aula N10", "sud")
				.addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
				.addAdiacenza("Aula N11", "Laboratorio Campus", "est")
				.addAdiacenza("Aula N11", "Atrio", "ovest")
				.addAdiacenza("Aula N10", "Atrio", "nord")
				.addAdiacenza("Aula N10", "Aula N11", "est")
				.addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")
				.addAdiacenza("Laboratorio Campus", "Atrio", "est")
				.addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")
				.addAdiacenza("Biblioteca", "Atrio", "sud")
				.getLabirinto();
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
