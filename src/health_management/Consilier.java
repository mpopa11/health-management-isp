package health_management;

import java.util.ArrayList;

public class Consilier extends Utilizator {

	private ArrayList<Integer> formulareCreate;
	
	public Consilier(String nume, String prenume, String username, String parola) {
		super(nume, prenume, username, parola);
		this.formulareCreate = new ArrayList<Integer>();
	}
	
	public void creareFormular() {
		Formular formular = FormularRegistry.create(this.getUsername());
		this.formulareCreate.add(formular.getId()); 
	}
	
	public void modificaFormular(Formular formular) {
		int answer;
		
		do {
			System.out.println("--------------------------------------");
			System.out.println("Alegeti o optiune:");
			System.out.println("1. Adauga intrebari");
			System.out.println("2. Adauga recomandari");
			System.out.println("3. Sterge intrebari");
			System.out.println("4. Sterge recomandari");
			System.out.println("5. Exit");
			System.out.println("--------------------------------------");
			
			answer = InputHandler.alegereActiuneMeniu(1, 5);
			
			switch (answer) {
				case 1 -> formular.adaugaIntrebare();
				case 2 -> formular.adaugaRecomandare();
				case 3 -> formular.stergeIntrebare();
				case 4 -> formular.stergeRecomandare();
				}
		} while (answer != 5);
	}
}
