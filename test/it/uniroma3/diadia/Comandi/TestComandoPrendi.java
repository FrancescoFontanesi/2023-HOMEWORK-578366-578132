package it.uniroma3.diadia.Comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita.IOConsole;
import it.uniroma3.diadia.Partita.Partita;
import it.uniroma3.diadia.Ambienti.*;

class TestComandoPrendi {
	private Partita partita;
	private ComandoPrendi comando;

	@BeforeEach
	public void setUp() throws Exception {
		Labirinto lab = new Labirinto.LabirintoBuilder().addStanzaIniziale("prova")
				.addAttrezzo("attr1", 1).addAttrezzo("attr2", 20).getLabirinto();
		this.partita = new Partita(lab);
		this.comando = new ComandoPrendi(new IOConsole(new Scanner(System.in)));
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.partita = null;
		this.comando = null;
	}

	@Test
	public void testTrue1() {
		this.comando.setParametro("attr1");
		this.comando.esegui(partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("attr1"));
	}

	@Test
	public void testTrue2() {
		this.comando.setParametro("attr2");
		this.comando.esegui(partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("attr2"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("attr2"));
	}

	@Test
	public void testTrue3() {
		this.comando.setParametro("Piccone");
		this.comando.esegui(partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("Piccone"));
	}

}
