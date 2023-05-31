package it.uniroma3.diadia.Comandi;

import it.uniroma3.diadia.Partita.IO;
import it.uniroma3.diadia.Partita.Partita;

public class ComandoSaluta extends AbstractComando {
	final static protected String NOME_CMD = "Comando Saluta";
	final static protected String PNG_NONPRESENTE = "Non c'è nessuno da salutare...";

	public ComandoSaluta(IO console) {
		super(console);
	}

	@Override
	public void esegui(Partita partita) {
		if(!partita.getStanzaCorrente().hasPersonaggio())
			super.getConsole().mostraMessaggio(PNG_NONPRESENTE);
		if(super.getParametro() == null)
			super.getConsole().mostraMessaggio("Non ho capito, chi vuoi salutare?");
		else {
			String pngClass = "it.uniroma3.diadia.Personaggi." 
					+ Character.toUpperCase(super.getParametro().charAt(0)) + super.getParametro().substring(1);
			if(pngClass.equals(partita.getStanzaCorrente().getPersonaggio().getClass().getName()))
				super.getConsole().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
			else
				super.getConsole().mostraMessaggio("Non c'è un " + super.getParametro() + " da salutare");
		}
	}

	@Override
	public String getNome() {
		return NOME_CMD;
	}

}
