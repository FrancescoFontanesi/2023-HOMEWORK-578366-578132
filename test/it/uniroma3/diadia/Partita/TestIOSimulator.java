package it.uniroma3.diadia.Partita;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Ambienti.Labirinto;
import it.uniroma3.diadia.Ambienti.Stanza;
import it.uniroma3.diadia.Comandi.ComandoFine;

class TestIOSimulator {
	private DiaDia gioco;
	private IOSimulator simulatore;

	@AfterEach
	void tearDown() throws Exception {
		this.gioco = null;
		this.simulatore = null;
	}

	@Test
	public void testFine() {
		this.simulatore = new IOSimulator(new String[]{"fine"});
		this.gioco = new DiaDia(this.simulatore);
		this.gioco.gioca();
		assertEquals(this.simulatore.getMsgCorrente(), DiaDia.MESSAGGIO_BENVENUTO);
		assertEquals(this.simulatore.getMsgCorrente(), ComandoFine.MSG_FINE);
	}
	@Test
	public void testSconosciuto() {
		this.simulatore = new IOSimulator(new String[] {"ABCDE"});
		this.gioco = new DiaDia(this.simulatore);
		this.gioco.gioca();
		
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(), "Comando sconosciuto...");
		
	}
	
	@Test
	public void testNull() {
		this.simulatore = new IOSimulator(new String[] {});
		this.gioco = new DiaDia(this.simulatore);
		this.gioco.gioca();
		
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(), ComandoFine.MSG_FINE);
	}
	
	@Test
	public void testPrendi() {
		this.simulatore = new IOSimulator(new String[] {"prendi lanterna","prendi osso"});
		this.gioco = new DiaDia(this.simulatore);
		this.gioco.gioca();
		
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(), "lanterna non presente in Stanza");
		assertEquals(this.simulatore.getMsgCorrente(), "osso aggiunto in Borsa");
	}
	
	@Test
	public void testVai() {
		this.simulatore = new IOSimulator(new String[] {"vai est"});
		this.gioco = new DiaDia(this.simulatore);
		this.gioco.gioca();
	
		this.simulatore.skipMsgCorrente();
		Labirinto labirinto = new Labirinto();
		Stanza corrente = labirinto.getStanzaCorrente().getStanzaAdiacente("est");
		assertEquals(this.simulatore.getMsgCorrente(), corrente.getDescrizione());
	}
	
	@Test
	public void testPosa() {
		this.simulatore = new IOSimulator(new String[] {"posa osso","prendi osso", "posa osso"});
		this.gioco = new DiaDia(this.simulatore);
		this.gioco.gioca();
		
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(), "osso non presente in Borsa");
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(), "osso posato");
	}
	
	@Test
	public void testGuarda() {
		this.simulatore = new IOSimulator(new String[] {"guarda"});
		this.gioco = new DiaDia(this.simulatore);
		this.gioco.gioca();
		
		Labirinto prova = new Labirinto();
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(), prova.getStanzaCorrente().getDescrizione() + "\nCfu residui: " + 20);
	}
	
	@Test
	public void testVinta() {
		this.simulatore = new IOSimulator(new String[] {"vai nord"});
		this.gioco = new DiaDia(this.simulatore);
		this.gioco.gioca();
		
		this.simulatore.skipMsgCorrente();
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(), "Hai vinto!");
	}
	
	@Test
	public void testCompleta() {
		this.simulatore = new IOSimulator(new String[] {"vai sud", "prendi lanterna", "vai nord", "vai nord"});
		this.gioco = new DiaDia(this.simulatore);
		this.gioco.gioca();
		Labirinto labirinto = new Labirinto();
		
		assertEquals(this.simulatore.getMsgCorrente(), DiaDia.MESSAGGIO_BENVENUTO);
		assertEquals(this.simulatore.getMsgCorrente(), labirinto.getStanzaCorrente().getStanzaAdiacente("sud").getDescrizione());
		assertEquals(this.simulatore.getMsgCorrente(), "lanterna aggiunto in Borsa");
		assertEquals(this.simulatore.getMsgCorrente(), labirinto.getStanzaCorrente().getDescrizione());
		assertEquals(this.simulatore.getMsgCorrente(), labirinto.getStanzaVincente().getDescrizione());
		assertEquals(this.simulatore.getMsgCorrente(), "Hai vinto!");
	}
}
