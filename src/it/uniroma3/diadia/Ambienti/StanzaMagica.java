package it.uniroma3.diadia.Ambienti;
import it.uniroma3.diadia.Attrezzo.*;
import it.uniroma3.diadia.Partita.DiaDia;

public class StanzaMagica extends Stanza {
	final static private int SOGLIA_MAGICA_DEFAULT = Integer.parseInt(DiaDia.prop.
			getProperty("Soglia_Magica_Default", "3"));
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(!super.spazioLibero())
			return false;
		else {
			this.contatoreAttrezziPosati += 1;
			if(this.contatoreAttrezziPosati <= this.sogliaMagica)
				return super.addAttrezzo(attrezzo);
			else
				return super.addAttrezzo(modificaAttrezzo(attrezzo));
		}
			
	}
	
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder invertito = new StringBuilder(attrezzo.getNome());
		invertito = invertito.reverse();
		return new Attrezzo(invertito.toString(),attrezzo.getPeso()*2);
	}
	
	public boolean isMagica() {
		if(this instanceof StanzaMagica)
			return true;
		else
			return false;
	}
}
