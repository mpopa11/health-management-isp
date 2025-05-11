package health_management;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputHandler {
	public static final Pattern patternNume = Pattern.compile("^[A-Z][a-z]+(?:[-'][A-Z][a-z]+)*$");
	public static final Pattern patternUsername = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]{2,15}$");
	public static final Pattern patternParola = Pattern.compile(
		        "^(?=.{8,}$)"         +  
		        "(?=.*[a-z])"         + 
		        "(?=.*[A-Z])"         +  
		        "(?=.*\\d)"           +  
		        "(?=.*[.@$!%*?&])"     +  
		        "[A-Za-z\\d.@$!%*?&]+" +  
		        "$"
		    );
	
	private static Scanner scanner = new Scanner(System.in);
	 
	
	public static String validare(String tip, Pattern pattern) {
	    String line;
	    while (true) {
	        System.out.print("Introduceti " + tip + ": ");
	        line = scanner.nextLine().trim();

	        if (!pattern.matcher(line).matches()) {
	            System.out.println("Format invalid pentru " + tip + ".");
	            continue;
	        }

	        if ("username".equalsIgnoreCase(tip)) {
	            boolean existsInStudents   = StudentRegistry.get(line) != null;
	            boolean existsInConsilieri = ConsilierRegistry.get(line) != null;
	            if (existsInStudents || existsInConsilieri) {
	                System.out.println("Username deja folosit. Alegeti alt username.");
	                continue;
	            }
	        }
	        return line;
	    }
	}


	public static Utilizator authenticate() {
        Utilizator user;
        String username;
        while (true) {
            System.out.print("Introduceti username: ");
            username = scanner.nextLine().trim();
            if (!patternUsername.matcher(username).matches()) {
                System.out.println("Format invalid pentru username.");
                continue;
            }
            user = StudentRegistry.get(username);
            if (user == null) user = ConsilierRegistry.get(username);
            if (user == null) {
                System.out.println("Username necunoscut. Incearca din nou.");
                continue;
            }
            break;
        }
        
        while (true) {
            System.out.print("Introduceti parola: ");
            String parola = scanner.nextLine().trim();
            if (!patternParola.matcher(parola).matches()) {
                System.out.println("Format invalid pentru parola.");
                continue;
            }
            if (!user.getParola().equals(parola)) {
                System.out.println("Parola incorecta. Incearca din nou.");
                continue;
            }
            break;
        }

        return user;
    }


	
	public static String alegereTipCont() {
		String line;
        while (true) {
            System.out.print("Ce tip de cont doriti(student/consilier): ");
            line = scanner.nextLine().trim().toLowerCase();
            if(line.equals("student") || line.equals("consilier")) {
            	break;
            }
            System.out.println("Introduceti un tip valid.");
        }
        return line;
	}
	
	public static int alegereActiuneMeniu(int lower, int upper) {
		int choice = -1;
		do {
		String line = scanner.nextLine().trim();
        choice = Integer.parseInt(line);
        if (choice < lower || choice > upper) {
            System.out.println("Optiune invalida. Alege un număr intre " + lower + " si " + upper);
            choice = -1; 
        }
    } while (choice == -1);
		return choice;
	}
	
}
