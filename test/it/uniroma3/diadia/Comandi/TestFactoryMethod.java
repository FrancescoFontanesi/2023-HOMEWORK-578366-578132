package it.uniroma3.diadia.Comandi;
import it.uniroma3.diadia.Partita.*;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestFactoryMethod {
	private FabbricaDiComandiRiflessiva factory;
	private IO console;
	
	@BeforeEach
	void setUp() throws Exception {
		this.factory = new FabbricaDiComandiRiflessiva();
		this.console = new IOConsole(new Scanner(System.in));
	}

	@AfterEach
	void tearDown() throws Exception {
		this.factory = null;
		this.console = null;
	}

	@Test
	void testVai() throws IllegalAccessException, NoSuchMethodException, 
		ClassNotFoundException, InstantiationException, 
		InvocationTargetException, SecurityException {
		AbstractComando comando = this.factory.costruisciComando("vai nord", this.console);
		assertEquals(comando.getNome(),"Comando Vai");
		assertEquals(comando.getParametro(), "nord");
	}
	
	@Test
	void testPrendi() throws IllegalAccessException, NoSuchMethodException, 
	ClassNotFoundException, InstantiationException, 
	InvocationTargetException, SecurityException {
		AbstractComando comando = this.factory.costruisciComando("prendi fill", this.console);
		assertEquals(comando.getNome(),"Comando Prendi");
		assertEquals(comando.getParametro(), "fill");
	}
	
	@Test
	void testPosa() throws IllegalAccessException, NoSuchMethodException, 
	ClassNotFoundException, InstantiationException, 
	InvocationTargetException, SecurityException {
		AbstractComando comando = this.factory.costruisciComando("posa fill", this.console);
		assertEquals(comando.getNome(),"Comando Posa");
		assertEquals(comando.getParametro(), "fill");
	}
	
	@Test
	void testFine() throws IllegalAccessException, NoSuchMethodException, 
	ClassNotFoundException, InstantiationException, 
	InvocationTargetException, SecurityException {
		AbstractComando comando = this.factory.costruisciComando("fine", this.console);
		assertEquals(comando.getNome(),"Comando Fine");
		assertNull(comando.getParametro());
	}
	
	@Test
	void testGuarda() throws IllegalAccessException, NoSuchMethodException, 
	ClassNotFoundException, InstantiationException, 
	InvocationTargetException, SecurityException {
		AbstractComando comando = this.factory.costruisciComando("guarda", this.console);
		assertEquals(comando.getNome(),"Comando Guarda");
		assertNull(comando.getParametro());
	}
	
	@Test
	void testNonValido1() throws IllegalAccessException, NoSuchMethodException, 
	ClassNotFoundException, InstantiationException, 
	InvocationTargetException, SecurityException {
		AbstractComando comando = this.factory.costruisciComando("ABC", this.console);
		assertEquals(comando.getNome(),"Comando NonValido");
		assertNull(comando.getParametro());
	}
	
	@Test
	void testNonValid2() throws IllegalAccessException, NoSuchMethodException, 
	ClassNotFoundException, InstantiationException, 
	InvocationTargetException, SecurityException {
		AbstractComando comando = this.factory.costruisciComando("", this.console);
		assertEquals(comando.getNome(),"Comando NonValido");
		assertNull(comando.getParametro());
	}
	
	@Test
	void testAiuto() throws IllegalAccessException, NoSuchMethodException, 
	ClassNotFoundException, InstantiationException, 
	InvocationTargetException, SecurityException {
		AbstractComando comando = this.factory.costruisciComando("aiuto", this.console);
		assertEquals(comando.getNome(),"Comando Aiuto");
		assertNull(comando.getParametro());
	}
	
	

}
