package health_management;

import java.util.ArrayList;

public class Student extends Utilizator {
	private ArrayList<FormularCompletat> formulareCompletate;

	public Student(String nume, String prenume, String username, String parola) {
		super(nume, prenume, username, parola);
		this.formulareCompletate = new ArrayList<>();
	}
}
