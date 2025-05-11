package health_management;

import java.util.HashMap;
import java.util.Map;

public class ConsilierRegistry {
	
	private static final Map<String, Consilier> consilieri= new HashMap<>();
	
	
	public static Consilier getOrCreate(String nume,
            String prenume,
            String username,
            String parola) {
			String key = username.trim().toLowerCase();
			return consilieri.computeIfAbsent(key, k -> 
				new Consilier(nume, prenume, username, parola));
			}

	public static Consilier get(String username) {
		return consilieri.get(username.trim().toLowerCase());
	}
	
	public static int getNumarConsilieri() {
		return consilieri.size();
	}
}
