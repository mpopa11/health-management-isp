package health_management;

import java.util.*;
import java.util.stream.Stream;

public class FacultateRegistry {
    private static final Map<String, Facultate> facultati = new HashMap<>();

    static {
        Stream.of(
            "ACS",
            "ETTI",
            "FILS",
            "IE"
        ).forEach(FacultateRegistry::getOrCreate);
    }

    public static Facultate getOrCreate(String nume) {
        String key = nume.trim().toLowerCase();
        return facultati.computeIfAbsent(key, k -> new Facultate(nume.trim()));
    }
    
    public static Collection<Facultate> getAll() {
        return Collections.unmodifiableCollection(facultati.values());
    }

    public static void clear() {
        facultati.clear();
    }
    
    public static Facultate get(String nume) {
        if (nume == null) return null;
        String key = nume.trim().toLowerCase();
        return facultati.get(key);
    }


}
