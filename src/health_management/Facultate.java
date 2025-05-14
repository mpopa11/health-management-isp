package health_management;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Facultate {
	private String nume;
	private final Set<Student> studenti = new LinkedHashSet<>();
	private int numarStudenti;
	

	public boolean adaugareStudent(Student student) {
	    Objects.requireNonNull(student);
	    boolean added = studenti.add(student);
	    if (added) numarStudenti = studenti.size();
	    return added;
	}
	
	public Facultate(String nume) {
		this.nume = nume;
	}
	
	public int getNumarStudenti() {
		return numarStudenti;
	}
	public String getNume() {
		return this.nume;
	}
	
	public Set<Student> getStudenti(){
		return this.studenti;
	}
	
}
