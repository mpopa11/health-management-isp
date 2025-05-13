package health_management;

import java.util.ArrayList;
import java.util.Scanner;

public class Formular {
	private Integer id;
	private String titlu;
	private String autor;
	private ArrayList<Intrebare> intrebari;
	private ArrayList<Recomandare> recomandari;
	
	private static int idCounter = 0;

	public Formular(String usernameAutor) {
		this.id = idCounter;
		idCounter++;
		this.autor = usernameAutor;
		this.intrebari = new ArrayList<Intrebare>();
		this.recomandari = new ArrayList<Recomandare>();
	}

	public void creare() {
		Scanner scanner = InputHandler.getScanner();
		int menuAnswer = 0;
		
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
						intrebare.creareIntrebare();
						this.intrebari.add(intrebare);
						System.out.print("Doriti sa mai introduceti o alta intrebare? [y/n]: ");
					} while (scanner.nextLine().trim().toLowerCase().equals("y"));
					break;
				}
				case 2: {
					do { 
						Recomandare recomandare = new Recomandare();
						recomandare.creareRecomandare();
						this.recomandari.add(recomandare);
						System.out.print("Doriti sa mai introduceti o alta recomandare? [y/n]: ");
					} while (scanner.nextLine().trim().toLowerCase().equals("y"));
					break;
				}
			}
		}
		System.out.println("--------------------------------------");
	}
	
	public Formular(Formular obj) {
		this.titlu = obj.titlu;
		this.autor = obj.autor;
		this.intrebari = obj.intrebari;
		this.recomandari = obj.recomandari;
	}
	
	public void showMeniuFormular() {
		System.out.println("--------------------------------------");
        System.out.println("Alegeti o optiune:");
        System.out.println("1. Adauga intrebari");
        System.out.println("2. Adauga recomandari");
        System.out.println("3. Iesi");
        System.out.println("--------------------------------------");
	}
	
	public float calculareScor(ArrayList<Integer> raspunsuri) {
		float suma = 0.0f;
		
		for(int idx = 0; idx < this.intrebari.size(); idx++) {
			Intrebare intrebareCurenta = this.intrebari.get(idx);
			suma += intrebareCurenta.obtinePunctaj(raspunsuri.get(idx));
		}
		
		return suma / this.intrebari.size();
	}
	
	public ArrayList<Integer> completareFormular() {
		ArrayList<Integer> raspunsuri = new ArrayList<Integer>();
		
		for (Intrebare intrebare : this.intrebari) {
			intrebare.afisare();
			Integer raspuns = InputHandler.alegereActiuneMeniu(1, intrebare.numarRaspunsuri());
			raspunsuri.add(raspuns-1);
		}
		float scor = this.calculareScor(raspunsuri);
		System.out.println("SCOR: " + scor);
		
		return raspunsuri;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getTitlu() {
		return this.titlu;
	}
}
