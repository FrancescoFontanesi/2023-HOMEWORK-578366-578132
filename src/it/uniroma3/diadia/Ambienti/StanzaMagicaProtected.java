package it.uniroma3.diadia.Ambienti;
import it.uniroma3.diadia.Attrezzo.*;

public class StanzaMagicaProtected extends StanzaProtected {
	final static protected int SOGLIA_MAGICA_DEFAULT = 3;
	protected int contatoreAttrezziPosati;
	protected int sogliaMagica;
	
	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagicaProtected(String nome, int sogliaMagica) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = sogliaMagica;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(!super.spazioLibero())
			return false;
		else {
			this.contatoreAttrezziPosati += 1;
			if(this.contatoreAttrezziPosati < this.sogliaMagica)
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

}
