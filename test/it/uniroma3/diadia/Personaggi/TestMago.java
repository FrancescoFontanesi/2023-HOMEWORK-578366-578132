package it.uniroma3.diadia.Personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Ambienti.Labirinto;
import it.uniroma3.diadia.Attrezzo.*;
import it.uniroma3.diadia.Partita.*;

public class TestMago {
	private AbstractPersonaggio mago;
	Labirinto lab;
	Partita partita;

	@BeforeEach
	public void setUp() throws Exception {
		this.mago = new Mago("p","Un mago di prova",new Attrezzo("bacchetta",1));
		this.lab = new Labirinto.LabirintoBuilder().addStanzaIniziale("start")
				.addPersonaggio(mago).getLabirinto();
		this.partita = new Partita(this.lab);
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.mago = null;
		this.lab = null;
		this.partita = null;
	}

	@Test
	public void testMagoPresente() {
		assertTrue(this.partita.getStanzaCorrente().hasPersonaggio());
		assertEquals(this.partita.getStanzaCorrente().getPersonaggio(),this.mago);
	}
	
	@Test
	public void testDono() {
		assertEquals(this.mago.agisci(this.partita),Mago.MESSAGGIO_DONO);
		assertTrue(this.lab.getStanzaCorrente().hasAttrezzo("bacchetta"));
	}
	
	@Test
	public void testScuse() {
		this.mago.agisci(this.partita);
		assertEquals(this.mago.agisci(this.partita),Mago.MESSAGGIO_SCUSE);
	}
	
	@Test
	public void testRiceviRegalo() {
		assertEquals(Mago.MESSAGGIO_REGALO,this.mago.riceviRegalo(new Attrezzo("prova",10), this.partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("prova"));
		assertEquals(this.partita.getStanzaCorrente().getAttrezzo("prova").getPeso(),5);
	}

}
