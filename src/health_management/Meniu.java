package health_management;

public class Meniu {
    private static Utilizator utilizatorCurent = null;

    public void signup() {
        String tip = InputHandler.alegereTipCont();
        String nume = InputHandler.validare("nume",    InputHandler.patternNume);
        String prenume = InputHandler.validare("prenume", InputHandler.patternNume);
        String username = InputHandler.validare("username",InputHandler.patternUsername);
        String parola = InputHandler.validare("parola",  InputHandler.patternParola);

        switch (tip) {
            case "student" -> {
            	this.alegeFacultate();
            	int facultateChoice = InputHandler.alegereActiuneMeniu(1, 4);
                String[] names = { "ACS", "ETTI", "FILS", "IE" };
                String numeFacultate= names[facultateChoice - 1];
                Facultate facultate = FacultateRegistry.getOrCreate(numeFacultate);
                Student student = StudentRegistry.getOrCreate(nume, prenume, username, parola);
            	utilizatorCurent = student;
            	facultate.adaugareStudent(student);
            }
            case "consilier" -> {
            	
            	Consilier consilier = ConsilierRegistry.getOrCreate(nume, prenume, username, parola);
            	utilizatorCurent = consilier;
            }
        }

        meniuUtilizatorLogat();
    }
    
    public void login() {
    	utilizatorCurent = InputHandler.authenticate();
    	meniuUtilizatorLogat();
    }
    

    private void meniuUtilizatorLogat() {
        while (utilizatorCurent != null) {
            if (utilizatorCurent instanceof Student) {
                showMeniuStudent();
                int choice = InputHandler.alegereActiuneMeniu(1, 5);
                switch (choice) {
                    case 1 -> {
	                    	FormularRegistry.showFormulare();
	                    	int id = InputHandler.alegereActiuneMeniu(1, FormularRegistry.getSize()) - 1;
	                    	((Student) utilizatorCurent).completareFormular(id);
                    	}
                    case 2 -> {
                    	((Student)utilizatorCurent).afisareIstoricFormulare();
                    } 
                    case 3 -> {}
                    case 4 -> {}
                    case 5-> {utilizatorCurent = null;}
                }
            }
            else if (utilizatorCurent instanceof Consilier) {
                showMeniuConsilier();
                int choice = InputHandler.alegereActiuneMeniu(1, 4);
                switch (choice) {
                    case 1 -> ((Consilier) utilizatorCurent).creareFormular(); 
                    case 2 -> {}  
                    case 3 -> {}
                    case 4 -> utilizatorCurent = null;
                }
            }
            else {
                utilizatorCurent = null;
            }
        }
    }


    public static void main(String[] args) {
        Meniu meniu = new Meniu();
        Meniu.init();

        while (true) {
            meniu.showMeniuInitial();
            int choice = InputHandler.alegereActiuneMeniu(1, 3);

            switch (choice) {
                case 1 -> {
                	meniu.login();
                }
                case 2 -> meniu.signup();
                case 3 -> {
                    System.exit(0);
                }
            }
        }
    }
    
    public static void init() {
    	ConsilierRegistry.getOrCreate("Consilier", "Ana", "anaa", "Parola1.");
    	StudentRegistry.getOrCreate("Mihai","Mihai", "Mihai", "Parola1.");
    }
    
    public void alegeFacultate() {
        System.out.println("--------------------------------------");
        System.out.println("Alegeti o optiune:");
        System.out.println("1. ACS");
        System.out.println("2. ETTI");
        System.out.println("3. FILS");
        System.out.println("4. IE");
        System.out.println("--------------------------------------");
    }

    public void showMeniuInitial() {
        System.out.println("--------------------------------------");
        System.out.println("Alegeti o optiune:");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("3. Close");
        System.out.println("--------------------------------------");
    }

    public void showMeniuStudent() {
        System.out.println("--------------------------------------");
        System.out.println("1. Completeaza Formular");
        System.out.println("2. Verificare Istoric Formulare");
        System.out.println("3. Statistici");
        System.out.println("4. Vizualizare Recomandari");
        System.out.println("5. Log out");
        System.out.println("--------------------------------------");
    }
    
    public void showMeniuConsilier() {
        System.out.println("--------------------------------------");
        System.out.println("1. Creaza formular");
        System.out.println("2. Vizualizare Formulare");
        System.out.println("3. Statistici");
        System.out.println("4. Log out");
        System.out.println("--------------------------------------");
    }
    
}
