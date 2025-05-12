package health_management;

import java.util.ArrayList;
import java.util.Scanner;


public class Formular {
	private String titlu;
	private String autor;
	private ArrayList<Intrebare> intrebari;
	private ArrayList<Recomandare> recomandari;
	private ArrayList<Integer> raspunsuri;
	
	private float scor;

	public Formular() {
		Scanner scanner = InputHandler.getScanner();
		int menuAnswer = 0;
		this.intrebari = new ArrayList<Intrebare>();
		this.raspunsuri = new ArrayList<Integer>();
		this.recomandari = new ArrayList<Recomandare>();
		
		System.out.println("--------------------------------------");
		System.out.print("Introduceti titlul formularului: ");
		this.titlu = scanner.nextLine();
		
		
		while (menuAnswer != 3) {
			this.showMeniuFormular();
			menuAnswer = InputHandler.alegereActiuneMeniu(1, 3);
			
			switch (menuAnswer) {
				case 1: {
					do {
						Intrebare intrebare = new Intrebare();
						this.intrebari.add(intrebare);
						System.out.print("Doriti sa mai introduceti o alta intrebare? [y/n]: ");
					} while (scanner.nextLine().trim().toLowerCase().equals("y"));
					break;
				}
				case 2: {
					do { 
						Recomandare recomandare = new Recomandare();
						this.recomandari.add(recomandare);
						System.out.print("Doriti sa mai introduceti o alta recomandare? [y/n]: ");
					} while (scanner.nextLine().trim().toLowerCase().equals("y"));
					break;
				}
			}
		}
	}

	public Formular(Formular obj) {
		this.titlu = obj.titlu;
		this.autor = obj.autor;
		this.intrebari = obj.intrebari;
		this.recomandari = obj.recomandari;
		this.raspunsuri = obj.raspunsuri;
	}
	
	public void showMeniuFormular() {
		System.out.println("--------------------------------------");
        System.out.println("Alegeti o optiune:");
        System.out.println("1. Adauga intrebari");
        System.out.println("2. Adauga recomandari");
        System.out.println("3. Iesi");
        System.out.println("--------------------------------------");
	}
	
	public void calculareScor() {
		if(!this.raspunsuri.isEmpty()) {
			int suma = 0;
			for(Integer raspuns : this.raspunsuri) {
				suma += raspuns;
			}
			this.scor = (float) suma / this.raspunsuri.size();
		}
	}
	
	public void completareFormular() {
		Scanner scanner = new Scanner(System.in);
		Integer raspuns = -1;
		
		for(Intrebare intrebare : this.intrebari) {
			raspuns = -1;
			while(raspuns < 0 || raspuns > 5) {
				intrebare.afisare();
				System.out.println("Raspuns [0-5]: ");
				raspuns = scanner.nextInt();
				}
			this.raspunsuri.add(raspuns);
		}
		scanner.close();
		this.calculareScor();
	}
	
	public float getScor() {
		return this.scor;
	}
}
