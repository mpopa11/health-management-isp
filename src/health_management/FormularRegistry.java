package health_management;

import java.util.HashMap;
import java.util.Map;

public class FormularRegistry {

	private static final Map<Integer, Formular> formulare = new HashMap<>();

	public static Formular create(String usernameAutor) {
        Formular formular = new Formular(usernameAutor);
        formular.creare();
        formulare.put(formular.getId(), formular);
        return formular;
    }

	public static Formular get(Integer id) {
		return formulare.get(id);
	}
	
	 public static void showFormulare() {
	        if (formulare.isEmpty()) {
	            System.out.println("Nu exista formulare disponibile.");
	            return;
	        }

	        System.out.println("Formulare:");
	        for (Formular formular : formulare.values()) {
	            System.out.println(1 + formular.getId()
	                + ". " + formular.getTitlu());
	        }
	    }
	 
	 public static int getSize() {
		 return formulare.size();
	 }
}
