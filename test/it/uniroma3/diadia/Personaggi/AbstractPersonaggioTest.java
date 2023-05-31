package it.uniroma3.diadia.Personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AbstractPersonaggioTest {
	AbstractPersonaggio p;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.p = new FakeAbstractPersonaggio("p"," Sono un personaggio di test");
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.p = null;
	}

	@Test
	public void testHaSalutatoFalse() {
		assertFalse(this.p.haSalutato());
	}
	
	@Test
	public void testSalutoStringa1() {
		String saluto = this.p.saluta();
		assertEquals(saluto,"Ciao, io sono p. Sono un personaggio di test");
	}
	
	@Test
	public void testHaSalutatoTrue() {
		this.p.saluta();
		assertTrue(p.haSalutato());
	}
	
	@Test
	public void testSalutoStringa2() {
		this.p.saluta();
		String saluto = this.p.saluta();
		assertEquals(saluto,"Ciao, io sono p.Ci siamo gia' presentati!");
	}
	
	@Test
	public void testGetNome() {
		assertEquals("p",this.p.getNome());
	}
	
	@Test
	public void testToString() {
		assertEquals("p",this.p.toString());
	}
	
	
	
	

}
