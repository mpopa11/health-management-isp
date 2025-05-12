package health_management;

import java.util.ArrayList;
import java.util.Scanner;

public class Intrebare {
	private String textIntrebare;
	private ArrayList<String> raspunsuri;
	private ArrayList<Integer> puncte;
	
	// puncteRaspuns
	
	public Intrebare() {
		Scanner scanner = InputHandler.getScanner();
		this.raspunsuri = new ArrayList<String>();
		this.puncte = new ArrayList<Integer>();
		String ans;

		System.out.println("--------------------------------------");
		System.out.println("Introduceti intrebarea: ");
		this.textIntrebare = scanner.nextLine();		
		
		do {
			System.out.print("Introduceti raspuns: ");
			this.raspunsuri.add(scanner.nextLine());
			
			Integer punctaj = null;
	        while (punctaj == null) {
	            System.out.print("Introduceti punctele asociate: ");
	            try {
	                punctaj = Integer.parseInt(scanner.nextLine());
	                this.puncte.add(punctaj);
	            } catch (NumberFormatException e) {
	                System.out.println("Eroare: trebuie sa introduceti un numar intreg!");
	            }
	        }
			
			System.out.print("Doriti sa mai introduceti raspunsuri? [y/n]: ");
			ans = scanner.nextLine();
		} while (ans.trim().toLowerCase().equals("y"));
		System.out.println("--------------------------------------");
	}
		
	public void afisare() {
		System.out.println("--------------------------------------");
		System.out.println(this.textIntrebare);	
		for (int i = 0; i < this.raspunsuri.size(); i++) {
			System.out.println((i+1) + ". " + this.raspunsuri.get(i));
		}
		System.out.println("--------------------------------------");
	}
}
