package health_management;

public class Utilizator {
	private String nume;
	private String prenume;
	private String username;

	public String getNume() {
		return this.nume;
	}
	
	public String getPrenume() {
		return this.prenume;
	}
	
	public String getUsername( ) {
		return this.username;
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
}
