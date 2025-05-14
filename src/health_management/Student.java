package health_management;

import java.util.ArrayList;

public class Student extends Utilizator {
	private ArrayList<FormularCompletat> formulareCompletate;

	public Student(String nume, String prenume, String username, String parola) {
		super(nume, prenume, username, parola);
		this.formulareCompletate = new ArrayList<>();
	}
	
	public void completareFormular(int idFormular) {
		ArrayList<Integer> raspunsuri = FormularRegistry.get(idFormular).completareFormular();
		int scor = FormularRegistry.get(idFormular).calculareScor(raspunsuri);
		
		formulareCompletate.add(new FormularCompletat(idFormular, raspunsuri, scor));
	}
	
	
	public void afisareIstoricFormulare() {
		if (formulareCompletate.isEmpty()) {
			System.out.println("Nu ai completat niciun formular.");
			return;
		}
		
		System.out.println("Formulare completate:");
		for (int i = formulareCompletate.size() - 1; i >= 0; i--) {
			FormularCompletat formular = formulareCompletate.get(i);
			System.out.println((i + 1) + ". FormularID: " + formular.getIdFormular() + ", Titlu: " + FormularRegistry.get(formular.getIdFormular()).getTitlu() + " Scor: " + formular.getScor() + ", Data: " + formular.getTimestamp());
		}
		
		System.out.println("--------------------------------------");
		System.out.println("Doriti detalii suplimentare? \n[1] - Da \n[2] - Nu");
		System.out.println("--------------------------------------");
		int optiune = InputHandler.alegereActiuneMeniu(1, 2);
		
		if (optiune == 1) {
			System.out.println("--------------------------------------");
			System.out.println("Introduceti numarul formularului pentru detalii suplimentare:");
			System.out.println("--------------------------------------");
			int numarFormular = InputHandler.alegereActiuneMeniu(1, formulareCompletate.size());
			System.out.println("--------------------------------------");
			
			FormularCompletat formular = formulareCompletate.get(numarFormular - 1);
			int idFormular = formular.getIdFormular();
			ArrayList<Intrebare> intrebari =  FormularRegistry.get(idFormular).getIntrebari();
			
			System.out.println("FormularID: " + idFormular);
			System.out.println("Titlu: " + FormularRegistry.get(idFormular).getTitlu());
			System.out.println("Autor: " + FormularRegistry.get(idFormular).getAutor());
			System.out.println("Scor: " + formular.getScor());
			System.out.println("Data: " + formular.getTimestamp());
			System.out.println("Intrebari si raspunsuri:");
			for (int i = 0; i < intrebari.size(); i++) {
				System.out.println("--------------------------------------");
				System.out.println("Intrebare " + (i + 1) + ":");
				intrebari.get(i).afisare();
				System.out.println("Raspunsul tau: " + (formular.getRaspunsuri().get(i)+1));
			}
		} else {
			System.out.println("Reveniti la meniu.");
			
		}
	}
	
	public void afisareRecomandari() {
		System.out.println("Recomandari:");
		for (FormularCompletat formular : formulareCompletate) {
			
			ArrayList<Recomandare> recomandari = FormularRegistry.get(formular.getIdFormular()).getRecomandari();
			for (Recomandare recomandare : recomandari) {
				if (recomandare.getPragInferior() <= formular.getScor() && formular.getScor() <= recomandare.getPragSuperior()) {
					recomandare.afisare();
				}
			}
		}
	}
	
	public ArrayList<Integer> getScoruri() {
		ArrayList<Integer> scoruri = new ArrayList<>();
		for (FormularCompletat formular : formulareCompletate) {
			scoruri.add((int) formular.getScor());
		}
		return scoruri;
	}
	
	// ----------------------------- EXPERIMENTAL -----------------------------
	public static void afisareEvolutie(ArrayList<Integer> scoruri) {
        if (scoruri == null || scoruri.size() == 0) {
            System.out.println("Nu exista scoruri de afisat.");
            return;
        }

        int max = Integer.MIN_VALUE;
        for (int scor : scoruri) {
            if (scor > max) max = scor;
        }

        final int chartHeight = 10;  // number of rows in the chart
        System.out.println("Evolutia scorurilor:");

        // Build chart row by row from top to bottom
        for (int i = chartHeight; i >= 1; i--) {
            int threshold = (int) Math.ceil((double) max * i / chartHeight);
            for (int scor : scoruri) {
                if (scor >= threshold) {
                    System.out.print("  █  ");
                } else {
                    System.out.print("     ");
                }
            }
            System.out.println();
        }

        // Print horizontal axis
        for (int i = 0; i < scoruri.size(); i++) {
            System.out.print("─────");
        }
        System.out.println();

        System.out.println();
    }

}

