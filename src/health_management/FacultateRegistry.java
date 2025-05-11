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
  
}
