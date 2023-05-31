
package it.uniroma3.diadia.Personaggi;
import it.uniroma3.diadia.Ambienti.Stanza;
import it.uniroma3.diadia.Attrezzo.Attrezzo;
import it.uniroma3.diadia.Partita.*;
import java.util.List;
import java.util.ArrayList;

public class Strega extends AbstractPersonaggio {
	final static protected String MSG_PIUATTREZZI = "Adesso ti faccio un favore!\n";
	final static protected String MSG_MENOATTREZZI = "Adesso ti faccio vedere io!\n";
	final static protected String MSG_REGALO = "Tutto per me! Ihihi";

	
	public Strega(String nome, String presentazione) {
		super(nome,presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		List<Stanza> adiacenti = 
			new ArrayList<>(partita.getStanzaCorrente().getMapStanzeAdiacenti().values());
		Stanza app = adiacenti.get(0);
		StringBuilder des = new StringBuilder();
		if(!super.haSalutato()) {
			for(Stanza s : adiacenti) {
				if(s.getAttrezzi().size() < app.getAttrezzi().size())
					app = s;
			}
			des.append(MSG_MENOATTREZZI + app.getDescrizione());
		} else {
			for(Stanza s : adiacenti) {
				if(s.getAttrezzi().size() > app.getAttrezzi().size())
					app = s;
			}
			des.append(MSG_PIUATTREZZI + app.getDescrizione());
		}
		partita.setStanzaCorrente(app);
		return des.toString();
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		attrezzo = null;
		return MSG_REGALO;
	}
	
	@Override
	public String desc4Stanza() {
		return "una strega ";
	}

}
