package health_management;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FormularCompletat {
	private int idFormular;
	private ArrayList<Integer> raspunsuri = new ArrayList<>();
	private String timestamp;
	
	public FormularCompletat(int idFormular, ArrayList<Integer> arrayList) {
		this.idFormular = idFormular;
		this.raspunsuri = arrayList;
		this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm"));
	}

}
