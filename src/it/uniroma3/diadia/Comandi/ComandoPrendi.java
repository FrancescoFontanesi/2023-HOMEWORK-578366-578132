package it.uniroma3.diadia.Comandi;
import it.uniroma3.diadia.Partita.*;
import it.uniroma3.diadia.Ambienti.*;
import it.uniroma3.diadia.Attrezzo.*;

public class ComandoPrendi implements Comando {
	private String nomeAttrezzo;
	private final static String NOME_CMD = "Comando Prendi";
	private IO console;
	
	public ComandoPrendi() {
		this.console = new IOConsole();
	}
	
	@Override
	public void esegui(Partita partita) {
		if(!partita.getStanzaCorrente().hasAttrezzo(this.nomeAttrezzo))
			this.console.mostraMessaggio("" + this.nomeAttrezzo + " non presente in Stanza");
		else {
			Stanza corrente = partita.getStanzaCorrente();
			Attrezzo daRimuovere = corrente.getAttrezzo(this.nomeAttrezzo);
			if(partita.getGiocatore().prendiAttrezzo(daRimuovere)) {
				corrente.removeAttrezzo(daRimuovere);
				this.console.mostraMessaggio("" + this.nomeAttrezzo + " aggiunto in Borsa");
			} else {
				this.console.mostraMessaggio("Impossibile prendere " + this.nomeAttrezzo);
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
