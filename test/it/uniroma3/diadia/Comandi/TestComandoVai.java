package it.uniroma3.diadia.Comandi;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Ambienti.Stanza;
import it.uniroma3.diadia.Partita.IOConsole;
import it.uniroma3.diadia.Partita.Partita;
import it.uniroma3.diadia.Ambienti.*;

public class TestComandoVai {
	private Labirinto labirinto;
	private Partita partita;
	private ComandoVai vai;

	@BeforeEach
	public void setUp() throws Exception {
		vai = new ComandoVai(new IOConsole(new Scanner(System.in)));
		labirinto = new Labirinto.LabirintoBuilder()
				.addStanzaIniziale("start")
				.addStanza("StNord")
				.addStanza("StSud")
				.addStanza("StEst")
				.addStanza("StOvest")
				.addAdiacenza("start", "StNord", "nord")
				.addAdiacenza("start", "StSud", "sud")
				.addAdiacenza("start", "StEst", "est")
				.addAdiacenza("start", "StOvest", "ovest")
				.getLabirinto();
		partita = new Partita(this.labirinto);
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.labirinto= null;
		this.partita = null;
	}

	@Test
	public void testNull() {
		Stanza start = partita.getStanzaCorrente();
		vai.setParametro(null);
		vai.esegui(partita);
		assertEquals(start, partita.getStanzaCorrente());
	}
	
	@Test
	public void testNord() {
		Stanza StNord = partita.getStanzaCorrente().getStanzaAdiacente("nord");
		vai.setParametro("nord");
		vai.esegui(partita);
		assertEquals(StNord, partita.getStanzaCorrente());
	}
	
	@Test
	public void testSud() {
		Stanza StSud = partita.getStanzaCorrente().getStanzaAdiacente("sud");
		vai.setParametro("sud");
		vai.esegui(partita);
		assertEquals(StSud, partita.getStanzaCorrente());
	}
	
	@Test
	public void testFalse1() {
		Stanza start = partita.getStanzaCorrente();
		vai.setParametro("est");
		vai.esegui(partita);
		assertNotEquals(start, partita.getStanzaCorrente());
	}
	
	@Test
	public void testFalse2() {
		Stanza start = partita.getStanzaCorrente();
		vai.setParametro("ovest");
		vai.esegui(partita);
		assertNotEquals(start, partita.getStanzaCorrente());
	}

}
