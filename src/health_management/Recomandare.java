package health_management;

import java.util.Scanner;

public class Recomandare {
	private String textRecomandare;
	private int pragInferior;
	private int pragSuperior;
	
	public Recomandare() {}
	
	public void creareRecomandare() {
		Scanner scanner = InputHandler.getScanner();
		Integer pragInf = null;
		Integer pragSup = null;
		
		System.out.println("--------------------------------------");
		System.out.println("Introduceti recomadare: ");
		
		do {
			this.textRecomandare = scanner.nextLine();
			if (this.textRecomandare.isBlank()) System.out.println("\nATENTIE: Recomandarea nu trebuie sa fie un camp gol!\n");
		} while (this.textRecomandare.isBlank());
		
        while (pragInf == null || pragInf < 0) {
            System.out.print("Introduceti pragul inferior: ");
            try {
                pragInf = Integer.parseInt(scanner.nextLine());
                if (pragInf >= 0 ) this.pragInferior = pragInf;
                else throw new NumberFormatException();
            } catch (NumberFormatException e) {
            	System.out.println("\nATENTIE: Trebuie sa introduceti un numar intreg si pozitiv!\n");
            }
        }
        
        while (pragSup == null) {
            System.out.print("Introduceti pragul superior: ");
            try {
                pragSup = Integer.parseInt(scanner.nextLine());
                this.pragSuperior = pragSup;
            } catch (NumberFormatException e) {
            	System.out.println("\nATENTIE: Trebuie sa introduceti un numar intreg!\n");
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
	
	public int getPragInferior() {
		return this.pragInferior;
	}
	
	public int getPragSuperior() {
		return this.pragSuperior;
	}
	
	public String getTextRecomandare() {
		return this.textRecomandare;
	}
	
	 public void setTextRecomandare(String textRecomandare) {
	        this.textRecomandare = textRecomandare;
	    }

    public void setPragInferior(int pragInferior) {
        this.pragInferior = pragInferior;
    }

    public void setPragSuperior(int pragSuperior) {
        this.pragSuperior = pragSuperior;
    }
}
