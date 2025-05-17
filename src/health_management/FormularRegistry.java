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
	 
	 public static Map<Integer, Formular> getFormularePersonale(String username) {
		 Map<Integer, Formular> formularePersonale = new HashMap<>();
		 for (Formular formular : formulare.values()) {
		 		if(username.equals(formular.getAutor())) {
		 			formularePersonale.put(formular.getId(), formular);
		 		}
		 	}
		 
		 return formularePersonale;
	 }
	 
	 public static void showFormulareOfUser(String username) {
		 	Map<Integer, Formular> formularePersonale = getFormularePersonale(username);
		 	
	        if (formularePersonale.isEmpty()) {
	            System.out.println("Nu exista formulare disponibile.");
	            return;
	        }

	        System.out.println("Formulare:");
	        int i = 1;
	        for (Formular formular : formularePersonale.values()) {
	            System.out.println(i
	                + ". " + formular.getTitlu());
	            i++;
	        }
	    }
	 
	 public static int getSizeFormulareUser(String username) {
		 return getFormularePersonale(username).size();
	 }
	 
	 public static int getSize() {
		 return formulare.size();
	 }
	 
    public static void registerFormular(Formular f) {
        formulare.put(f.getId(), f);
    }

    public static void clear() {
        formulare.clear();
    }
}
