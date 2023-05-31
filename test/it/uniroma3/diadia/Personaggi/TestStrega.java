package it.uniroma3.diadia.Personaggi;

import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.Partita.*;
import it.uniroma3.diadia.Ambienti.Labirinto;
import it.uniroma3.diadia.Attrezzo.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestStrega {
	private Partita partita;
	private Labirinto lab;
	private AbstractPersonaggio strega;

	@BeforeEach
	public void setUp() throws Exception {
		this.strega = new Strega("p","Una strega di prova");
		this.lab = new Labirinto.LabirintoBuilder().addStanzaIniziale("start")
				.addPersonaggio(strega).addStanza("piuAttrezzi").addAttrezzo("prova", 1)
				.addStanza("menoAttrezzi").addAdiacenza("start", "menoAttrezzi", "nord")
				.addAdiacenza("start", "piuAttrezzi", "sud").getLabirinto();
		this.partita = new Partita(this.lab);
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.partita = null;
		this.lab = null;
		this.strega = null;
	}

	@Test
	public void testStregaPresente() {
		assertTrue(this.partita.getStanzaCorrente().hasPersonaggio());
		assertEquals(this.partita.getStanzaCorrente().getPersonaggio(),this.strega);
	}
	
	@Test
	public void testSaluta() {
		assertEquals(this.strega.saluta(),"Ciao, io sono p.Una strega di prova");
	}
	
	@Test
	public void testGiaSalutato() {
		this.strega.saluta();
		assertEquals("Ciao, io sono p.Ci siamo gia' presentati!",this.strega.saluta());
	}
	
	@Test
	public void testMenoAttrezzi() {
		assertEquals(Strega.MSG_MENOATTREZZI + this.partita.getStanzaCorrente().getStanzaAdiacente("nord")
				.getDescrizione(),this.strega.agisci(partita));
	}
	
	@Test
	public void testPiuAttrezzi() {
		this.strega.saluta();
		assertEquals(Strega.MSG_PIUATTREZZI + this.partita.getStanzaCorrente().getStanzaAdiacente("sud")
				.getDescrizione(),this.strega.agisci(partita));
	}
	
	@Test
	public void testRiceviRegalo() {
		assertEquals(Strega.MSG_REGALO,this.strega.riceviRegalo(new Attrezzo("dono",1), partita));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("dono"));
	}

}
