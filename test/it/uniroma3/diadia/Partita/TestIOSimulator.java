package it.uniroma3.diadia.Partita;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Comandi.ComandoFine;
import it.uniroma3.diadia.Ambienti.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class TestIOSimulator {
	private DiaDia gioco;
	private IOSimulator simulatore;
	private Labirinto labirinto;
	private final String MESSAGGIO_BENVENUTO = DiaDia.prop.getProperty("Messaggio_Benvenuto", 
			
					"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
					"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
					"I locali sono popolati da strani personaggi, " +
					"alcuni amici, altri... chissa!\n"+
					"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
					"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
					"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
					"Per conoscere le istruzioni usa il comando 'aiuto'.");

	@BeforeEach
	public void setUp() {
		this.labirinto = new Labirinto.LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N10")
				.addAttrezzo("lanterna", 3)
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
		this.gioco = null;
		this.simulatore = null;
		this.labirinto = null;
	}
	

	@Test
	public void testFine() throws ClassNotFoundException, IllegalAccessException, 
	NoSuchMethodException, InstantiationException, 
	InvocationTargetException, SecurityException {
		List<String> istruzioni = new ArrayList<>();
		istruzioni.add("fine");
		this.simulatore = new IOSimulator(istruzioni);
		this.gioco = new DiaDia(this.simulatore,this.labirinto);
		this.gioco.gioca(1);
		assertEquals(this.simulatore.getMsgCorrente(), MESSAGGIO_BENVENUTO);
		assertEquals(this.simulatore.getMsgCorrente(), ComandoFine.MSG_FINE);
	}
	@Test
	public void testSconosciuto() throws ClassNotFoundException, IllegalAccessException, 
	NoSuchMethodException, InstantiationException, 
	InvocationTargetException, SecurityException {
		List<String> istruzioni = new ArrayList<>();
		istruzioni.add("ABCDE");
		this.simulatore = new IOSimulator(istruzioni);
		this.gioco = new DiaDia(this.simulatore,this.labirinto);
		this.gioco.gioca(1);
		
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(), "Comando sconosciuto...");
		
	}
	
	@Test
	public void testNull() throws ClassNotFoundException, IllegalAccessException, 
	NoSuchMethodException, InstantiationException, 
	InvocationTargetException, SecurityException {
		this.simulatore = new IOSimulator(new ArrayList<String>());
		this.gioco = new DiaDia(this.simulatore,this.labirinto);
		this.gioco.gioca(1);
		
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(), ComandoFine.MSG_FINE);
	}
	
	@Test
	public void testPrendi() throws ClassNotFoundException, IllegalAccessException, 
	NoSuchMethodException, InstantiationException, 
	InvocationTargetException, SecurityException {
		
		this.simulatore = new IOSimulator(new ArrayList<String>(Arrays.asList("prendi lanterna","prendi osso")));
		this.gioco = new DiaDia(this.simulatore,this.labirinto);
		this.gioco.gioca(1);
		
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(), "lanterna non presente in Stanza");
		assertEquals(this.simulatore.getMsgCorrente(), "osso aggiunto in Borsa");
	}
	
	@Test
	public void testVai() throws ClassNotFoundException, IllegalAccessException, 
	NoSuchMethodException, InstantiationException, 
	InvocationTargetException, SecurityException {
		List<String> istruzioni = new ArrayList<>();
		istruzioni.add("vai est");
		Stanza iniziale = this.labirinto.getStanzaCorrente();
		this.simulatore = new IOSimulator(istruzioni);
		this.gioco = new DiaDia(this.simulatore,this.labirinto);
		this.gioco.gioca(1);
	
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(), iniziale.getStanzaAdiacente("est").getDescrizione());
	}
	
	@Test
	public void testPosa() throws ClassNotFoundException, IllegalAccessException, 
	NoSuchMethodException, InstantiationException, 
	InvocationTargetException, SecurityException {
		this.simulatore = new IOSimulator(new ArrayList<String>(Arrays.asList("posa osso","prendi osso","posa osso")));
		this.gioco = new DiaDia(this.simulatore,this.labirinto);
		this.gioco.gioca(1);
		
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(), "osso non presente in Borsa");
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(), "osso posato");
	}
	
	@Test
	public void testGuarda() throws ClassNotFoundException, IllegalAccessException, 
	NoSuchMethodException, InstantiationException, 
	InvocationTargetException, SecurityException {
		List<String> istruzioni = new ArrayList<>();
		istruzioni.add("guarda");
		this.simulatore = new IOSimulator(istruzioni);
		this.gioco = new DiaDia(this.simulatore,this.labirinto);
		this.gioco.gioca(1);
	
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(),this.labirinto.getStanzaCorrente().getDescrizione() + "\nCfu residui: 20\n" + "Borsa Vuota");
	}
	
	@Test
	public void testVinta() throws ClassNotFoundException, IllegalAccessException, 
	NoSuchMethodException, InstantiationException, 
	InvocationTargetException, SecurityException {
		List<String> istruzioni = new ArrayList<>();
		istruzioni.add("vai nord");
		this.simulatore = new IOSimulator(istruzioni);
		this.gioco = new DiaDia(this.simulatore,this.labirinto);
		this.gioco.gioca(1);
		
		this.simulatore.skipMsgCorrente();
		this.simulatore.skipMsgCorrente();
		assertEquals(this.simulatore.getMsgCorrente(), "Hai vinto!");
	}
	
	@Test
	public void testCompleta() throws ClassNotFoundException, IllegalAccessException, 
	NoSuchMethodException, InstantiationException, 
	InvocationTargetException, SecurityException {
		this.simulatore = new IOSimulator(new ArrayList<String>(Arrays.asList("vai sud","prendi lanterna","vai nord","vai nord")));
		this.gioco = new DiaDia(this.simulatore,this.labirinto);
		Stanza iniziale = this.labirinto.getStanzaCorrente();
		String descrAulaN10 = iniziale.getStanzaAdiacente("sud").getDescrizione();
		this.gioco.gioca(1);
		
		assertEquals(this.simulatore.getMsgCorrente(), MESSAGGIO_BENVENUTO);
		assertEquals(this.simulatore.getMsgCorrente(), descrAulaN10);
		assertEquals(this.simulatore.getMsgCorrente(), "lanterna aggiunto in Borsa");
		assertEquals(this.simulatore.getMsgCorrente(), iniziale.getDescrizione());
		assertEquals(this.simulatore.getMsgCorrente(), iniziale.getStanzaAdiacente("nord").getDescrizione());
		assertEquals(this.simulatore.getMsgCorrente(), "Hai vinto!");
	}
	
	@Test
	public void testGetRecordIstruzioni() throws ClassNotFoundException, IllegalAccessException, 
	NoSuchMethodException, InstantiationException, 
	InvocationTargetException, SecurityException {
		this.simulatore = new IOSimulator(new ArrayList<String>(Arrays.asList("vai sud","vai nord","vai nord")));
		this.gioco = new DiaDia(this.simulatore,this.labirinto);
		Stanza iniziale = this.labirinto.getStanzaCorrente();
		this.gioco.gioca(1);
		
		assertEquals(this.simulatore.getMsgCorrente(),MESSAGGIO_BENVENUTO);
		assertEquals(this.simulatore.getMsgCorrente(),iniziale.getStanzaAdiacente("sud").getDescrizione());
		assertEquals(this.simulatore.getMsgCorrente(),iniziale.getDescrizione());
		assertEquals(this.simulatore.getMsgCorrente(),this.labirinto.getStanzaVincente().getDescrizione());
		assertEquals(this.simulatore.getMsgCorrente(), "Hai vinto!");
		assertTrue(this.simulatore.getRecordIstruzioni().containsKey("vai sud"));
		assertTrue(this.simulatore.getRecordIstruzioni().containsKey("vai nord"));
		assertEquals(iniziale.getStanzaAdiacente("sud").getDescrizione(),this.simulatore.getRecordIstruzioni().get("vai sud").get(0));
		assertEquals(iniziale.getDescrizione(),this.simulatore.getRecordIstruzioni().get("vai nord").get(0));
		assertEquals(this.labirinto.getStanzaVincente().getDescrizione(),this.simulatore.getRecordIstruzioni().get("vai nord").get(1));
		assertEquals("Hai vinto!",this.simulatore.getRecordIstruzioni().get("vai nord").get(2));
	}
}
