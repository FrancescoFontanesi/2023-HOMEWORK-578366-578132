package it.uniroma3.diadia.Comandi;
import it.uniroma3.diadia.Partita.*;

public interface Comando {
	public void esegui(Partita partita);
	public void setParametro(String parametro);
	public String getNome();
	public String getParametro();
	public void setIO(IO console);
}
