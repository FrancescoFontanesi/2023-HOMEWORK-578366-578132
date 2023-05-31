package it.uniroma3.diadia.Personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Partita.*;
import it.uniroma3.diadia.Ambienti.Labirinto;
import it.uniroma3.diadia.Attrezzo.*;

public class TestCane {
	private Partita partita;
	private Labirinto lab;
	private AbstractPersonaggio cane;

	@BeforeEach
	public void setUp() throws Exception {
		this.cane = new Cane("p","Un cane di prova",new Attrezzo("provaCibo",1),new Attrezzo("provaRegalo",1));
		this.lab = new Labirinto.LabirintoBuilder().addStanzaIniziale("start").addPersonaggio(cane).getLabirinto();
		this.partita = new Partita(this.lab);
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.partita = null;
		this.lab = null;
		this.cane = null;
	}

	@Test
	public void testAgisci() {
		assertEquals(this.cane.agisci(this.partita),Cane.MSG_MORSO);
		assertEquals(this.partita.getCfu(),19);
	}
	
	@Test
	public void testRiceviRegaloCorretto() {
		assertEquals(Cane.MSG_REGALO,this.cane.riceviRegalo(new Attrezzo("provaCibo",1), partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("provaRegalo"));
	}
	
	@Test
	public void testRiceviRegaloScorretto() {
		assertEquals(Cane.MSG_MORSO,this.cane.riceviRegalo(new Attrezzo("Sbagliato",1), partita));
		assertEquals(this.partita.getCfu(),19);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("provaRegalo"));
	}
	
	@Test
	public void testSaluta() {
		assertEquals("Ciao, io sono p.Un cane di prova",this.cane.saluta());
	}
	
	@Test
	public void testGiaSalutato() {
		this.cane.saluta();
		assertEquals("Ciao, io sono p.Ci siamo gia' presentati!",this.cane.saluta());
	}
	

}
