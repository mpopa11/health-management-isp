package health_management;

import java.util.ArrayList;
import java.util.Scanner;

public class Intrebare {
	private String textIntrebare;
	private ArrayList<String> raspunsuri;
	private ArrayList<Integer> puncte;
	
	public Intrebare() {
		this.raspunsuri = new ArrayList<String>();
		this.puncte = new ArrayList<Integer>();
	}
	
	public void creareIntrebare() {
		Scanner scanner = InputHandler.getScanner();
		String ans;
		
		do {
			System.out.println("--------------------------------------");
			System.out.println("Introduceti intrebarea: ");
			this.textIntrebare = scanner.nextLine();
			
			if (this.textIntrebare.isBlank()) System.out.println("\nATENTIE: Acest este un camp obligatoriu!\n");
		} while (this.textIntrebare.isBlank());
		
		
		do {
			
			String raspuns;
			do {
				System.out.print("Introduceti raspuns: ");
				raspuns = scanner.nextLine();
				if (raspuns.isBlank()) System.out.println("\nATENTIE: Raspunsul nu trebuie sa fie un camp gol!\n");
			} while (raspuns.isBlank());
			
			this.raspunsuri.add(raspuns);
				
			Integer punctaj = null;
	        while (punctaj == null || punctaj < 0) {
	            System.out.print("Introduceti punctele asociate: ");
	            try {
	                punctaj = Integer.parseInt(scanner.nextLine());
	                if (punctaj >= 0) this.puncte.add(punctaj);
	                else throw new NumberFormatException();
	            } catch (NumberFormatException e) {
	                System.out.println("\nATENTIE: Trebuie sa introduceti un numar intreg si pozitiv!\n");
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
	
	public Integer obtinePunctaj(Integer raspuns) {
		return this.puncte.get(raspuns);
	}
	
	public int obtinePunctajMaxim() {
		int max = Integer.MIN_VALUE;
	    for (int p : this.puncte) {
	        if (p > max) {
	            max = p;
	        }
	    }
	    return max;
	}
	
	public int numarRaspunsuri() {
		return this.raspunsuri.size();
	}
	
	public String getTextIntrebare() {
		return this.textIntrebare;
	}
	
	public String getRaspuns(int index) {
	    return raspunsuri.get(index);
	}
	
	public void setTextIntrebare(String textIntrebare) {
        this.textIntrebare = textIntrebare;
    }

    public void setRaspunsuri(ArrayList<String> raspunsuri) {
        this.raspunsuri = new ArrayList<>(raspunsuri);
    }

    public void setPuncte(ArrayList<Integer> puncte) {
        this.puncte = new ArrayList<>(puncte);
    }

    public ArrayList<String> getRaspunsuri() {
        return new ArrayList<>(this.raspunsuri);
    }

    public ArrayList<Integer> getPuncte() {
        return new ArrayList<>(this.puncte);
    }
}
