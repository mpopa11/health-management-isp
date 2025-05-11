package health_management;

import java.util.Objects;

public class Utilizator {
	private String nume;
	private String prenume;
	private String username;
	private String parola;
	
	public Utilizator(String nume, String prenume, String username, String parola) {
		this.nume = nume;
		this.prenume = prenume;
		this.username = username;
		this.parola = parola;
	}
	
	public Utilizator() {
		
	}

	public String getNume() {
		return this.nume;
	}
	
	public String getPrenume() {
		return this.prenume;
	}
	
	public String getUsername( ) {
		return this.username;
	}
	
	public String getParola() {
		return this.parola;
	}
	
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setParola(String parola) {
		this.parola = parola;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilizator)) return false;
        Utilizator that = (Utilizator) o;
        return Objects.equals(this.getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
	
}
