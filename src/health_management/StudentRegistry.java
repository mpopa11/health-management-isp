package health_management;

import java.util.HashMap;
import java.util.Map;

public class StudentRegistry {
	
	private static final Map<String, Student> studenti= new HashMap<>();
	
	
	public static Student getOrCreate(String nume,
            String prenume,
            String username,
            String parola) {
			String key = username.trim().toLowerCase();
			return studenti.computeIfAbsent(key, k -> 
				new Student(nume, prenume, username, parola));
			}

	public static Student get(String username) {
		return studenti.get(username.trim().toLowerCase());
	}
	
	public static int getNumarStudenti() {
		return studenti.size();
	}
}
