package it.uniroma3.diadia.Partita;
import java.util.Scanner;

public class IOConsole {
	
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	public String leggiRiga() {
		Scanner scanner = new Scanner(System.in);
		String riga = scanner.nextLine();
		return riga;
	}
}
