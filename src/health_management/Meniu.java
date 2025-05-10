package health_management;

public class Meniu {
    private static Utilizator utilizatorCurent = null;

    public void signup() {
        String tip      = InputHandler.alegereTipCont();
        String nume     = InputHandler.validare("nume",    InputHandler.patternNume);
        String prenume  = InputHandler.validare("prenume", InputHandler.patternNume);
        String username = InputHandler.validare("username",InputHandler.patternUsername);
        String parola   = InputHandler.validare("parola",  InputHandler.patternParola);

        switch (tip) {
            case "student"   -> utilizatorCurent = new Student(   nume, prenume, username, parola);
            case "consilier" -> utilizatorCurent = new Consilier(nume, prenume, username, parola);
        }

        meniuUtilizatorLogat();
    }

    private void meniuUtilizatorLogat() {
        while (utilizatorCurent != null) {
            showMeniuStudent();
            int choice = InputHandler.alegereActiuneMeniu(1, 2);

            switch (choice) {
                case 1 -> { 
                    utilizatorCurent = null; 
                }
                case 2 -> {
                }
            }
        }
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

    public static void main(String[] args) {
        Meniu meniu = new Meniu();

        while (true) {
            meniu.showMeniuInitial();
            int choice = InputHandler.alegereActiuneMeniu(1, 3);

            switch (choice) {
                case 1 -> {
                }
                case 2 -> meniu.signup();
                case 3 -> {
                    System.exit(0);
                }
            }
        }
    }
}
