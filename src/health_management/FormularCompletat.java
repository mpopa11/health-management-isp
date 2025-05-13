package health_management;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FormularCompletat {
	private int idFormular;
	private float scor;
	private ArrayList<Integer> raspunsuri = new ArrayList<>();
	private String timestamp;
	
	public FormularCompletat(int idFormular, ArrayList<Integer> arrayList, float scor) {
		this.idFormular = idFormular;
		this.raspunsuri = arrayList;
		this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm"));
		this.scor = scor;
	}

	
	public void show() {
        System.out.println("FormularCompletat details:");
        System.out.println("  idFormular: " + idFormular);
        System.out.println("  scor: " + scor);
        System.out.println("  timestamp: " + timestamp);
        System.out.println("  raspunsuri: " + raspunsuri);
    }
}
