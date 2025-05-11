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
                int choice = InputHandler.alegereActiuneMeniu(1, 2);
                switch (choice) {
                    case 1 -> utilizatorCurent = null;
                    case 2 -> {}  
                }
            }
            else if (utilizatorCurent instanceof Consilier) {
                showMeniuConsilier();
                int choice = InputHandler.alegereActiuneMeniu(1, 3);
                switch (choice) {
                    case 1 -> utilizatorCurent = null;
                    case 2 -> {}  
                    case 3 -> {}
                }
            }
            else {
                utilizatorCurent = null;
            }
        }
    }


    public static void main(String[] args) {
        Meniu meniu = new Meniu();

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
        System.out.println("1. Log out");
        System.out.println("--------------------------------------");
    }
    
    public void showMeniuConsilier() {
        System.out.println("--------------------------------------");
        System.out.println("1. Log out");
        System.out.println("1. Log out");
        System.out.println("--------------------------------------");
    }
    
}
