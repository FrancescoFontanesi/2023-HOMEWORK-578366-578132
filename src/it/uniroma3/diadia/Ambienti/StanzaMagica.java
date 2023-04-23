package it.uniroma3.diadia.Ambienti;
import it.uniroma3.diadia.Attrezzo.*;

public class StanzaMagica extends Stanza {
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
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
		if(!super.isEmpty())
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
