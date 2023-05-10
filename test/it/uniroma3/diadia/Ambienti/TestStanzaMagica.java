package it.uniroma3.diadia.Ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Attrezzo.*;

public class TestStanzaMagica {
	private StanzaMagica magica;
	private Attrezzo attr;
	private Attrezzo pipa;

	@BeforeEach
	public void setUp() throws Exception {
		this.magica = new StanzaMagica("magica",1);
		this.attr = new Attrezzo("attrezzo",1);
		this.pipa = new Attrezzo("pipa",1);
		
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.magica = null;
		this.attr = null;
		this.pipa = null;
	}
	
	@Test
	public void testNoAttrezzi() {
		assertNull(this.magica.getAttrezzo("pipa"));
	}

	@Test
	public void testSottoSoglia() {
		this.magica.addAttrezzo(attr);
		assertEquals(this.attr,this.magica.getAttrezzo("attrezzo"));
	}
	
	@Test
	public void testSopraSoglia() {
		Attrezzo pipaReverse = new Attrezzo("apip",2);
		this.magica.addAttrezzo(this.attr);
		this.magica.addAttrezzo(pipa);
		assertTrue(this.magica.hasAttrezzo("apip"));
		assertEquals(this.magica.getAttrezzo("apip"),pipaReverse);
	}

}
