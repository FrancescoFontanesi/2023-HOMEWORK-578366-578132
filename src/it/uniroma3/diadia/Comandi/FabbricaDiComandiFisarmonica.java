package it.uniroma3.diadia.Comandi;
import java.util.Scanner;
import it.uniroma3.diadia.Partita.*;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{
	
	public Comando costruisciComando(String istruzione, IO console) {
		Scanner scanner = new Scanner(istruzione);
		String nomeComando = null, parametroComando = null;
		Comando comando = null;
		
		if(scanner.hasNext())
			nomeComando = scanner.next();
		if(scanner.hasNext())
			parametroComando = scanner.next();
		
		if(nomeComando == null)
			comando = new ComandoNonValido();
		else
			if(nomeComando.equals("vai"))
				comando = new ComandoVai();
			else
				if(nomeComando.equals("prendi"))
					comando = new ComandoPrendi();
				else
					if(nomeComando.equals("posa"))
						comando = new ComandoPosa();
					else
						if(nomeComando.equals("guarda"))
							comando = new ComandoGuarda();
						else
							if(nomeComando.equals("aiuto"))
								comando = new ComandoAiuto();
							else
								if(nomeComando.equals("fine"))
									comando = new ComandoFine();
								else
									comando = new ComandoNonValido();
		comando.setParametro(parametroComando);
		comando.setIO(console);
		return comando;			
	}

}
