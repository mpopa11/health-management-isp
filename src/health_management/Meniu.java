package health_management;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	                    	int id = -1;
	                    	if (FormularRegistry.getSize() > 0) {
	                    		id = InputHandler.alegereActiuneMeniu(1, FormularRegistry.getSize()) - 1;
		                    	System.out.println(id);
		                    	((Student) utilizatorCurent).completareFormular(id);
	                    	}
	                    	
                    	}
                    case 2 -> {
                    	((Student)utilizatorCurent).afisareIstoricFormulare();
                    } 
                    case 3 -> {
                    	((Student) utilizatorCurent).afisareEvolutie(((Student) utilizatorCurent).getScoruri());
                    }
                    case 4 -> {((Student) utilizatorCurent).afisareRecomandari();}
                    case 5-> {utilizatorCurent = null;}
                }
            }
            else if (utilizatorCurent instanceof Consilier) {
                showMeniuConsilier();
                int choice = InputHandler.alegereActiuneMeniu(1, 4);
                switch (choice) {
                    case 1 -> ((Consilier) utilizatorCurent).creareFormular(); 
                    case 2 -> {
                    	FormularRegistry.showFormulareOfUser(utilizatorCurent.getUsername());
                    	
                    	if (FormularRegistry.getSizeFormulareUser(utilizatorCurent.getUsername()) > 0) {
                    		System.out.println("Alegeti un formular pentru detalii.\nIntoruceti 0 pentru a naviga inapoi");
                    		int formChoice = InputHandler.alegereActiuneMeniu(0, FormularRegistry.getSize());
                    		
                    		if (formChoice == 0) {
                    			break;
                    		} else {
                        		List<Integer> keys = new ArrayList<>(FormularRegistry.getFormularePersonale(utilizatorCurent.getUsername()).keySet());
                        		int id = keys.get(formChoice - 1);
                        		((Consilier) utilizatorCurent).modificaFormular(id);
                        		
                    		}
                    	}
                    }
                    case 3 -> {
                    	FormularRegistry.showFormulareOfUser(utilizatorCurent.getUsername());
                    	if (FormularRegistry.getSizeFormulareUser(utilizatorCurent.getUsername()) > 0) {
                    		System.out.println("Alegeti un formular pentru detalii.\nIntoruceti 0 pentru a naviga inapoi");
                    		int formChoice = InputHandler.alegereActiuneMeniu(0, FormularRegistry.getSize());
                    		
                    		if (formChoice == 0) {
                    			break;
                    		} else {
                        		List<Integer> keys = new ArrayList<>(FormularRegistry.getFormularePersonale(utilizatorCurent.getUsername()).keySet());
                        		int id = keys.get(formChoice - 1);
                        		((Consilier) utilizatorCurent).vizualizareStatisticiFormular(id);
                        		
                    		}
                    	}
          
                    }
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
    	ConsilierRegistry.getOrCreate("Consilier", "Ana2", "ana", "Parola1.");
    	StudentRegistry.getOrCreate("Mihai","Mihai", "Mihai", "Parola1.");
    	StudentRegistry.getOrCreate("Mihai","Robert", "Robert", "Parola1.");
    	FacultateRegistry.getOrCreate("ACS").adaugareStudent(StudentRegistry.get("mihai"));
    	FacultateRegistry.getOrCreate("ETTI").adaugareStudent(StudentRegistry.get("robert"));
    	
    	Formular f = new Formular("anaa");
    	f.setTitlu("Chestionar de Test");

    	Intrebare q1 = new Intrebare();
    	q1.setTextIntrebare("Întrebare 1?");
    	q1.setRaspunsuri(new ArrayList<>(List.of("Da", "Nu")));
    	q1.setPuncte (new ArrayList<>(List.of(1, 0)));
    	f.getIntrebari().add(q1);

    	Intrebare q2 = new Intrebare();
    	q2.setTextIntrebare("Întrebare 2?");
    	q2.setRaspunsuri(new ArrayList<>(List.of("Opțiunea A", "Opțiunea B")));
    	q2.setPuncte (new ArrayList<>(List.of(2, 1)));
    	f.getIntrebari().add(q2);

    	Recomandare r1 = new Recomandare();
    	r1.setTextRecomandare("Recomandare X");
    	r1.setPragInferior(0);
    	r1.setPragSuperior(2);
    	f.getRecomandari().add(r1);

    	 FormularRegistry.registerFormular(f);
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
