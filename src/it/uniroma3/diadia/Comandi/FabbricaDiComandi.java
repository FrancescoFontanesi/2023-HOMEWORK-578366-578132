package it.uniroma3.diadia.Comandi;
import java.lang.reflect.InvocationTargetException;

import it.uniroma3.diadia.Partita.*;

public interface FabbricaDiComandi {
	public AbstractComando costruisciComando(String istruzione, IO console)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, 
			NoSuchMethodException, SecurityException, ClassNotFoundException;
}
