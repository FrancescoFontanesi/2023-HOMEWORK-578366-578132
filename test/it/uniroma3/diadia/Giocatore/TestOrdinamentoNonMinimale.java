package it.uniroma3.diadia.Giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Attrezzo.*;
import java.util.List;
import java.util.SortedSet;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class TestOrdinamentoNonMinimale {
	private Borsa borsa;
	private Attrezzo piombo;
	private Attrezzo ps;
	private Attrezzo piuma;
	private Attrezzo libro;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.borsa = new Borsa(30);
		this.piombo = new Attrezzo("piombo",10);
		this.ps = new Attrezzo("ps",5);
		this.piuma = new Attrezzo("piuma",1);
		this.libro = new Attrezzo("libro",5);
		
		this.borsa.addAttrezzo(piombo);
		this.borsa.addAttrezzo(ps);
		this.borsa.addAttrezzo(piuma);
		this.borsa.addAttrezzo(libro);
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.borsa = null;
		this.piombo = null;
		this.ps = null;
		this.piuma = null;
		this.libro = null;
	}

	@Test
	public void testOrdinatoPeso() {
		List<Attrezzo> ord = borsa.getContenutoOrdinatoPerPeso();
		assertEquals(ord.get(0),this.piuma);
		assertEquals(ord.get(1),this.libro);
		assertEquals(ord.get(2),this.ps);
		assertEquals(ord.get(3),this.piombo);
	}
	
	@Test
	public void testOrdinatoNome() {
		SortedSet<Attrezzo> ord = this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it = ord.iterator();
		assertEquals(it.next(),this.libro);
		assertEquals(it.next(),this.piombo);
		assertEquals(it.next(),this.piuma);
		assertEquals(it.next(),this.ps);
	}
	
	@Test
	public void testContenutoPeso() {
		Map<Integer,Set<Attrezzo>> mappa = this.borsa.getContenutoRaggruppatoPerPeso();
		Iterator<Attrezzo> it = mappa.get(1).iterator();
		assertEquals(it.next(),this.piuma);
		it = mappa.get(5).iterator();
		assertEquals(it.next(),this.libro);
		assertEquals(it.next(),this.ps);
		it = mappa.get(10).iterator();
		assertEquals(it.next(),this.piombo);
	}
	
	@Test
	public void testSortedSetOrdinatoPeso() {
		SortedSet<Attrezzo> ord = this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> it = ord.iterator();
		assertEquals(it.next(),this.piuma);
		assertEquals(it.next(),this.libro);
		assertEquals(it.next(),this.ps);
		assertEquals(it.next(),this.piombo);
	}
}
