package health_management;

import java.util.Scanner;

public class Recomandare {
	private String textRecomandare;
	private float pragInferior;
	private float pragSuperior;
	
	public Recomandare() {
		Scanner scanner = InputHandler.getScanner();
		Float pragInf = null;
		Float pragSup = null;
		
		System.out.println("--------------------------------------");
		System.out.println("Introduceti recomadare: ");
		this.textRecomandare = scanner.nextLine();
		
        while (pragInf == null) {
            System.out.print("Introduceti pragul inferior: ");
            try {
                pragInf = Float.parseFloat(scanner.nextLine());
                this.pragInferior = pragInf;
            } catch (NumberFormatException e) {
                System.out.println("Eroare: Format invalid!");
            }
        }
        
        while (pragSup == null) {
            System.out.print("Introduceti pragul superior: ");
            try {
                pragSup = Float.parseFloat(scanner.nextLine());
                this.pragSuperior = pragSup;
            } catch (NumberFormatException e) {
                System.out.println("Eroare: Format invalid!");
            }
            
            if (pragSup <= this.pragInferior) {
            	System.out.println("Pragul superior trebuie sa fie mai mare decat cel inferior!");
            	pragSup = null;
            }
        }
        System.out.println("--------------------------------------");
	}
	
	public void afisare() {
		System.out.println("--------------------------------------");
		System.out.println(this.textRecomandare);
		System.out.println("--------------------------------------");
	}
	
	public float getPragInferior() {
		return this.pragInferior;
	}
	
	public float getPragSuperior() {
		return this.pragSuperior;
	}
}
