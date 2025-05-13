package health_management;

import java.util.ArrayList;

public class Consilier extends Utilizator {

	private ArrayList<Integer> formulareCreate;
	
	public Consilier(String nume, String prenume, String username, String parola) {
		super(nume, prenume, username, parola);
		this.formulareCreate = new ArrayList<Integer>();
	}
	
	public void creareFormular() {
		Formular formular = FormularRegistry.create(this.getUsername());
		this.formulareCreate.add(formular.getId());
	}
}
