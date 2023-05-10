package it.uniroma3.diadia.Comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Ambienti.Stanza;
import it.uniroma3.diadia.Attrezzo.Attrezzo;
import it.uniroma3.diadia.Partita.Partita;
import it.uniroma3.diadia.Ambienti.*;

class TestComandoPrendi {
	private Stanza stanza;
	private Partita partita;
	private ComandoPrendi comando;

	@BeforeEach
	public void setUp() throws Exception {
		this.stanza = new Stanza("prova");
		this.partita = new Partita(new Labirinto());
		this.comando = new ComandoPrendi();

		this.stanza.addAttrezzo(new Attrezzo("attr1",1));
		this.stanza.addAttrezzo(new Attrezzo("attr2",20));
		this.partita.setStanzaCorrente(stanza);
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.stanza = null;
		this.partita = null;
		this.comando = null;
	}

	@Test
	public void testTrue1() {
		this.comando.setParametro("attr1");
		this.comando.esegui(partita);
		assertFalse(this.stanza.hasAttrezzo("attr1"));
	}

	@Test
	public void testTrue2() {
		this.comando.setParametro("attr2");
		this.comando.esegui(partita);
		assertTrue(this.stanza.hasAttrezzo("attr2"));
	}

	@Test
	public void testTrue3() {
		this.comando.setParametro("Piccone");
		this.comando.esegui(partita);
		assertFalse(this.stanza.hasAttrezzo("Piccone"));
	}

}
