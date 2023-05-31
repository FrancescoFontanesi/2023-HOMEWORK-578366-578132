package it.uniroma3.diadia.Comandi;

import it.uniroma3.diadia.Partita.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	@Override
	public AbstractComando costruisciComando(String istruzione,IO console) 
			throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, 
			InstantiationException, InvocationTargetException, 
			SecurityException,NullPointerException
	{
		try(Scanner scanner = new Scanner(istruzione)){
			String nomeComando = null, parametroComando = null;
			AbstractComando comando = null;
			StringBuilder nomeClasse = null;

			if(scanner.hasNext())
				nomeComando = scanner.next();
			if(scanner.hasNext())
				parametroComando = scanner.next();

			try {
				nomeClasse = new StringBuilder("it.uniroma3.diadia.Comandi.Comando");
				nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
				nomeClasse.append(nomeComando.substring(1));
				comando = (AbstractComando) Class.forName(nomeClasse.toString())
						.getDeclaredConstructor(IO.class).newInstance(console);
			} catch(NullPointerException | ClassNotFoundException e) {
				comando = new ComandoNonValido(console);
			}
			comando.setParametro(parametroComando);
			return comando;
		}
	}

}
