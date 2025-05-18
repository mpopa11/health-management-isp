package health_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Consilier extends Utilizator {

	private ArrayList<Integer> formulareCreate;
	
	public Consilier(String nume, String prenume, String username, String parola) {
		super(nume, prenume, username, parola);
		this.formulareCreate = new ArrayList<Integer>();
	}
	
	public void creareFormular() {
		Formular formular = FormularRegistry.create(this.getUsername());
		this.formulareCreate.add(formular.getId()); 
	}
	
	public void modificaFormular(int idFormular) {
		Formular formular = FormularRegistry.get(idFormular);
		
		int answer;
		
		do {
			System.out.println("--------------------------------------");
			System.out.println("Alegeti o optiune:");
			System.out.println("1. Adauga intrebari");
			System.out.println("2. Adauga recomandari");
			System.out.println("3. Sterge intrebari");
			System.out.println("4. Sterge recomandari");
			System.out.println("5. Exit");
			System.out.println("--------------------------------------");
			
			answer = InputHandler.alegereActiuneMeniu(1, 5);
			
			switch (answer) {
				case 1 -> formular.adaugaIntrebare();
				case 2 -> formular.adaugaRecomandare();
				case 3 -> formular.stergeIntrebare();
				case 4 -> formular.stergeRecomandare();
				}
		} while (answer != 5);
	}
	
	public void vizualizareStatisticiFormular(int id) {
		Map <Facultate, List<FormularCompletat>> formularePerFacultate = new HashMap<>();
		int numOfForms = 0;
		for (Facultate facultate : FacultateRegistry.getAll()) {
			if (facultate.getNumarStudenti() > 0) {
				for (Student student : facultate.getStudenti()) {
					if (student.formularCompletatSpecific(id).size() > 0) {
						formularePerFacultate.put(facultate, new ArrayList<>(student.formularCompletatSpecific(id)));
						numOfForms += student.formularCompletatSpecific(id).size();
					}
				}
			}
		}
		
		if (numOfForms == 0) {
			System.out.println("--------------------------------------");
			System.out.println("Formularul nu a fost completat de catre niciun Student");
			System.out.println("--------------------------------------");
			return;
		}

	    Formular formularAles = FormularRegistry.get(id);
	    System.out.println("--------------------------------------");
	    System.out.println("Titlu: " + formularAles.getTitlu());
	    System.out.println("Formularul a fost completat de " 
	        + numOfForms + " ori");

	    List<Intrebare> intrebari = formularAles.getIntrebari();
	    for (int q = 0; q < intrebari.size(); q++) {
	        Intrebare intrebare = intrebari.get(q);
	        System.out.println("\n " + (q + 1) + ": " 
	            + intrebare.getTextIntrebare());

	        for (int opt = 0; opt < intrebare.numarRaspunsuri(); opt++) {
	            System.out.println(" "+ (opt + 1) + ": " 
	                + intrebare.getRaspuns(opt));

	            for (Map.Entry<Facultate, List<FormularCompletat>> entry 
	                    : formularePerFacultate.entrySet()) {
	                Facultate fac = entry.getKey();
	                int count = 0;
	                for (FormularCompletat fc : entry.getValue()) {
	                    if (fc.getRaspunsuri().get(q).intValue() == opt) {
	                        count++;
	                    }
	                }
	                if (count > 0) {
	                	System.out.println("    " + fac.getNume() + ": " + count);
	                }
	            }
	        }
	    }
	    
	    int suma = 0;
	    for (List<FormularCompletat> list : formularePerFacultate.values()) {
	        for (FormularCompletat fc : list) {
	            suma += fc.getScor();
	        }
	    }
	    float scorMediu = (float)suma / numOfForms;
	    System.out.println("\nScorul mediu: " 
	        + scorMediu + "/" + formularAles.calculareScorMaxim());
	    System.out.println("--------------------------------------");
	}

}