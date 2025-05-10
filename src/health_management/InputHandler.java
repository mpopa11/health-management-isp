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
            if (pattern.matcher(line).matches()) {
                return line;
            }
            System.out.println("Format invalid pentru " + tip + ".");
        }
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
}
