package health_management;

import java.util.ArrayList;
import java.util.Scanner;

public class Intrebare {
	private String textIntrebare;
	private ArrayList<String> raspunsuri;
	private ArrayList<Integer> puncte;
	
	// puncteRaspuns
	
	public Intrebare() {
		this.raspunsuri = new ArrayList<String>();
		this.puncte = new ArrayList<Integer>();
	}
	
	public void creareIntrebare() {
		Scanner scanner = InputHandler.getScanner();
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

    /** for tests to inject whole list of answers */
    public void setRaspunsuri(ArrayList<String> raspunsuri) {
        this.raspunsuri = new ArrayList<>(raspunsuri);
    }

    /** for tests to inject corresponding points */
    public void setPuncte(ArrayList<Integer> puncte) {
        this.puncte = new ArrayList<>(puncte);
    }

    /** optional: expose all answers if you need them in assertions */
    public ArrayList<String> getRaspunsuri() {
        return new ArrayList<>(this.raspunsuri);
    }

    /** optional: expose all point values if you need them in assertions */
    public ArrayList<Integer> getPuncte() {
        return new ArrayList<>(this.puncte);
    }
}
