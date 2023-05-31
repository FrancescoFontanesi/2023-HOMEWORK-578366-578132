package it.uniroma3.diadia.Comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Ambienti.Stanza;
import it.uniroma3.diadia.Attrezzo.Attrezzo;
import it.uniroma3.diadia.Partita.IOConsole;
import it.uniroma3.diadia.Partita.Partita;
import it.uniroma3.diadia.Ambienti.*;

class TestComandoPosa {
	private Partita partita;
	private Stanza stanza; 
	private ComandoPosa comando;

	@BeforeEach
	public void setUp() throws Exception {
		Labirinto lab = new Labirinto.LabirintoBuilder().addStanzaIniziale("prova").getLabirinto();
		this.partita = new Partita(lab);
		this.stanza = new Stanza("prova");
		this.comando = new ComandoPosa(new IOConsole(new Scanner(System.in)));

		this.partita.getGiocatore().prendiAttrezzo(new Attrezzo("prova1",1));
		this.partita.getGiocatore().prendiAttrezzo(new Attrezzo("prova2",2));
		this.partita.setStanzaCorrente(stanza);

	}

	@AfterEach
	public void tearDown() throws Exception {
		this.comando = null;
		this.partita = null;
		this.stanza = null;
	}

	@Test
	public void testTrue1() {
		for(int i = 0; i < 10; i++) {
			this.stanza.addAttrezzo(new Attrezzo("filler" + i,1));
		}
		this.comando.setParametro("prova2");
		this.comando.esegui(partita);
		assertFalse(this.stanza.hasAttrezzo("prova2"));
	}
	
	@Test
	public void testTrue2() {
		this.comando.setParametro("prova1");
		this.comando.esegui(partita);
		assertTrue(this.stanza.hasAttrezzo("prova1"));
	}
	
	@Test
	public void testTrue3() {
		this.comando.setParametro("Piccone");
		this.comando.esegui(partita);
		assertFalse(this.stanza.hasAttrezzo("Piccone"));
	}

}
