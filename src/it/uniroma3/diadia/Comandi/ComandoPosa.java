package it.uniroma3.diadia.Comandi;
import it.uniroma3.diadia.Attrezzo.Attrezzo;
import it.uniroma3.diadia.Partita.*;

public class ComandoPosa extends AbstractComando {
	private final static String NOME_CMD = "Comando Posa";
	
	public ComandoPosa(IO console) {
		super(console);
	}
	@Override
	public void esegui(Partita partita) {
		if(!partita.getGiocatore().hasAttrezzo(super.getParametro()))
			super.getConsole().mostraMessaggio("" + super.getParametro() + " non presente in Borsa");
		else {
			if(!partita.getStanzaCorrente().spazioLibero())
				super.getConsole().mostraMessaggio("Impossibile posare oggetto: stanza piena!");
			else {
				Attrezzo daPosare = partita.getGiocatore().posaAttrezzo(super.getParametro());
				if(partita.getStanzaCorrente().addAttrezzo(daPosare))
					super.getConsole().mostraMessaggio("" + super.getParametro() + " posato");	
			}
		}
	}
	
	@Override
	public String getNome() {
		return NOME_CMD;
	}

}
