package health_management;


public class Meniu {
	private Utilizator utilizatorCurent = null;
	
	
	
	public void signup() {
		String tip = InputHandler.alegereTipCont();
	    String nume = InputHandler.validare("nume", InputHandler.patternNume);
	    String prenume = InputHandler.validare("prenume", InputHandler.patternNume);
	    String username = InputHandler.validare("username", InputHandler.patternUsername);
	    String parola = InputHandler.validare("parola", InputHandler.patternParola);
	    
	    switch (tip) {
	    case "student"   -> utilizatorCurent = new Student(nume, prenume, username, parola);
	    case "consilier" -> utilizatorCurent = new Consilier(nume, prenume, username, parola);
	    }
	}
	
	public void showMeniu() {
	    System.out.println("--------------------------------------");
	    System.out.println("Alegeti o optiune:");
	    System.out.println("1. Login");
	    System.out.println("2. Sign Up");
	    System.out.println("3. Close");
	    System.out.println("--------------------------------------");
	}

	

	
	public static void main(String[] args) {
		Meniu meniu = new Meniu();
		meniu.showMeniu();
		
	}
	
}
