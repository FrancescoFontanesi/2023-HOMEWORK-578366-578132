package it.uniroma3.diadia.Comandi;
import it.uniroma3.diadia.Attrezzo.Attrezzo;
import it.uniroma3.diadia.Partita.*;

public class ComandoPosa implements Comando {
	private String nomeAttrezzo;
	private final static String NOME_CMD = "Comando Posa";
	private IO console;
	
	public ComandoPosa() {
		this.console = new IOConsole();
	}
	@Override
	public void esegui(Partita partita) {
		if(!partita.getGiocatore().hasAttrezzo(this.nomeAttrezzo))
			this.console.mostraMessaggio("" + this.nomeAttrezzo + " non presente in Borsa");
		else {
			if(!partita.getStanzaCorrente().isEmpty())
				this.console.mostraMessaggio("Impossibile posare oggetto: stanza piena!");
			else {
				Attrezzo daPosare = partita.getGiocatore().posaAttrezzo(this.nomeAttrezzo);
				if(partita.getStanzaCorrente().addAttrezzo(daPosare))
					this.console.mostraMessaggio("" + this.nomeAttrezzo + " posato");	
			}
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}
	
	@Override
	public void setIO(IO console) {
		this.console = console;
	}
	
	@Override
	public String getNome() {
		return NOME_CMD;
	}

}
