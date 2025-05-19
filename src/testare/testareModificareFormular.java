package testare;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import health_management.ConsilierRegistry;
import health_management.FacultateRegistry;
import health_management.Formular;
import health_management.FormularRegistry;
import health_management.InputHandler;
import health_management.Intrebare;
import health_management.Meniu;
import health_management.Recomandare;
import health_management.StudentRegistry;

public class testareModificareFormular {
	
	private final InputStream  originalIn  = System.in;
	  private final PrintStream originalOut = System.out;
	  private ByteArrayOutputStream outContent;

	  @BeforeEach
	  void setUp() {
	    outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    StudentRegistry.clear();
	    FacultateRegistry.clear();
	    ConsilierRegistry.clear();
	    FormularRegistry.clear();
	    
	    ConsilierRegistry.getOrCreate("Consilier", "Ana", "ana", "Parola1.");
	    ConsilierRegistry.getOrCreate("Consilier", "Ana2", "anaa", "Parola1.");
	    ConsilierRegistry.getOrCreate("Andrei", "Andrei", "andrei", "Parola1.");
	    StudentRegistry.getOrCreate("Mihai","Mihai", "Mihai", "Parola1.");
  	StudentRegistry.getOrCreate("Robert","Robert", "Robert", "Parola1.");
  	FacultateRegistry.getOrCreate("ACS").adaugareStudent(StudentRegistry.get("mihai"));
  	FacultateRegistry.getOrCreate("ETTI").adaugareStudent(StudentRegistry.get("robert"));
  	
  	Formular f = new Formular("anaa");
  	f.setTitlu("Chestionar de Test");

  	Intrebare q1 = new Intrebare();
  	q1.setTextIntrebare("Întrebare 1?");
  	q1.setRaspunsuri(new ArrayList<>(List.of("Da", "Nu")));
  	q1.setPuncte (new ArrayList<>(List.of(1, 0)));
  	f.getIntrebari().add(q1);

  	Intrebare q2 = new Intrebare();
  	q2.setTextIntrebare("Întrebare 2?");
  	q2.setRaspunsuri(new ArrayList<>(List.of("Opțiunea A", "Opțiunea B")));
  	q2.setPuncte (new ArrayList<>(List.of(2, 1)));
  	f.getIntrebari().add(q2);

  	Recomandare r1 = new Recomandare();
  	r1.setTextRecomandare("Recomandare X");
  	r1.setPragInferior(0);
  	r1.setPragSuperior(2);
  	f.getRecomandari().add(r1);

  	FormularRegistry.registerFormular(f);
  	
	  }

	  @AfterEach
	  void tearDown() {
	    System.setIn(originalIn);
	    System.setOut(originalOut);
	    StudentRegistry.clear();
	    FacultateRegistry.clear();
	    ConsilierRegistry.clear();
	    FormularRegistry.clear();
	    
	  }
	  
	  private void runEntireFlow(Meniu meniu) {
		  boolean inApp = true;
		  while (inApp) {
		    meniu.showMeniuInitial();
		    int choice = InputHandler.alegereActiuneMeniu(1, 3);
		    switch (choice) {
		      case 1 -> {
		        meniu.login();
		        // assume login() returns here once you log out
		      }
		      case 2 -> meniu.signup();
		      case 3 -> inApp = false;
		    }
		  }
		}


	@Test
	  void testModificareFormular() throws IOException {
	    String allInput = String.join("\n",
	    		//	    		formular nu exista
	      "1",      
	      "ana",
	      "Parola1.",
	      "2",
	      "4",
	      //	      Alege formular gresit
	      "1",
	      "anaa",
	      "Parola1.",
	      "2",
	      "5",
	      "0",
	      "4",
	      "3"
	      ) + "\n";
	    
	    System.setIn(new ByteArrayInputStream(allInput.getBytes()));

	    Meniu meniu = new Meniu();
	    runEntireFlow(meniu);
	    

	    String printed = outContent.toString();
	    
	    Files.writeString(
	            Path.of("test-output.log"),
	            printed,
	            StandardOpenOption.CREATE,
	            StandardOpenOption.TRUNCATE_EXISTING
	        );
	    //	    Consilierul nu are un formular
	    assertTrue(printed.contains("Nu exista formulare disponibile"));
	    //	    Utilizatorul alege un formular gresit
	    assertTrue(printed.contains("Optiune invalida. Alege un număr intre 0 si 1"));
	    
	  }
	
}
