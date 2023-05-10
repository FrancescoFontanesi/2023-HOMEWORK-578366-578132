package it.uniroma3.diadia.Giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Attrezzo.*;
import java.util.List;
import java.util.SortedSet;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;

public class TestOrdinamentoMinimale {
	private Borsa borsa;
	private Attrezzo a;
	private Attrezzo b;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.borsa = new Borsa(5);
		this.a = new Attrezzo("a",1);
		this.b = new Attrezzo("b",2);
		this.borsa.addAttrezzo(this.a);
		this.borsa.addAttrezzo(this.b);
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.borsa = null;
		this.a = null;
		this.b = null;
	}

	@Test
	public void testOrdinatoPeso() {
		List<Attrezzo> ord = this.borsa.getContenutoOrdinatoPerPeso();
		assertTrue(ord.get(0).equals(this.a) && ord.get(1).equals(this.b));
	}
	
	@Test
	public void testOrdinatoNome() {
		SortedSet<Attrezzo> ord = this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it = ord.iterator();
		assertTrue(it.next().equals(this.a) && it.next().equals(this.b));
	}
	
	@Test
	public void testRaggruppatoPeso1() {
		Map<Integer,Set<Attrezzo>> mappa = this.borsa.getContenutoRaggruppatoPerPeso();
		Iterator<Attrezzo> it = mappa.get(1).iterator();
		assertEquals(it.next(),this.a);
		it = mappa.get(2).iterator();
		assertEquals(it.next(),this.b);
	}
	
	@Test
	public void testRaggruppatoPeso2() {
		this.borsa.removeAttrezzo("b");
		this.b = new Attrezzo("b",1);
		this.borsa.addAttrezzo(this.b);
		Map<Integer,Set<Attrezzo>> mappa = this.borsa.getContenutoRaggruppatoPerPeso();
		Iterator<Attrezzo> it = mappa.get(1).iterator();
		assertTrue(it.next().equals(this.a) && it.next().equals(this.b));
	}
}
