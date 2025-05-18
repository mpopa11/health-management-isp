package testare;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import health_management.*;

class TestareVizualizareStatistici {
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
	  void testNuExistaFormularePentruConsilier() throws IOException {
	    String allInput = String.join("\n",
//	    		formular nu exista
	      "1",      
	      "ana",
	      "Parola1.",
	      "3",
	      "4",
	      
//	      formular nu are completari
	      "1",      
	      "anaa",
	      "Parola1.",
	      "3",
	      "1",
	      "4",
	      
//	      completeaza un formular
	      "1",      
	      "mihai",
	      "Parola1.",
	      "1",
	      "1",
	      "2",
	      "1",
	      "5",
	      
//	      verificare statistici formular completat o data + input prost
	      "1",      
	      "anaa",
	      "Parola1.",
	      "3",
	      "3", //input prost
	      "1",
	      "4",
	      
//	      completare alt student, alta facultate
	      "1",
	      "robert",
	      "Parola1.",
	      "1",
	      "1",
	      "1",
	      "2",
	      "5",
	      
//	      verificare statistici formular cu mai multe compeltari
	      "1",      
	      "anaa",
	      "Parola1.",
	      "3",
	      "1",
	      "4",
	      
//	      completare fomrular iar, stundet precedent
	      "1",
	      "robert",
	      "Parola1.",
	      "1",
	      "1",
	      "2",
	      "2",
	      "5",
	      
	      "1",      
	      "anaa",
	      "Parola1.",
	      "3",
	      "1",
	      "4",
	      
	      "3"
	      
	    ) + "\n";
	    
	    

	    System.setIn(new ByteArrayInputStream(allInput.getBytes()));

	    Meniu meniu = new Meniu();
	    runEntireFlow(meniu);

	    Consilier saved = ConsilierRegistry.get("ana");
	    assertNotNull(saved);
	    assertEquals("Ana", saved.getPrenume());
	    Consilier saved2 = ConsilierRegistry.get("anaa");
	    assertNotNull(saved2);
	    assertEquals("Ana2", saved2.getPrenume());

	    String printed = outContent.toString();
	    
	    Files.writeString(
	            Path.of("test-output.log"),
	            printed,
	            StandardOpenOption.CREATE,
	            StandardOpenOption.TRUNCATE_EXISTING
	        );
//	    Consilierul nu are un formular
	    assertTrue(printed.contains("Nu exista formulare disponibile"));
//	    Formularul consilierului nu are vruen raspuns
	    assertTrue(printed.contains("Formularul nu a fost completat de catre niciun Student"));
//	    Alegere invalida a formularului
	    assertTrue(printed.contains("Optiune invalida. Alege un număr intre 0 si 1"));
//	    Formular completat o singura data
	    assertTrue(printed.contains("1. Completeaza Formular"));
	    assertTrue(printed.contains("Titlu: Chestionar de Test\r\n"
	    		+ "Formularul a fost completat de 1 ori\r\n"));
	    assertTrue(printed.contains(" 1: Întrebare 1?\r\n"
	    		+ " 1: Da\r\n"
	    		+ " 2: Nu\r\n"
	    		+ "    ACS: 1"));
	    assertTrue(printed.contains("2: Întrebare 2?\r\n"
	    		+ " 1: Opțiunea A\r\n"
	    		+ "    ACS: 1\r\n"
	    		+ " 2: Opțiunea B\r\n"));
	    
	    assertTrue(printed.contains("Scorul mediu: 2.0/3"));
	    
//	    Formular completat de doua ori
	    
	    assertTrue(printed.contains("Titlu: Chestionar de Test\r\n"
	    		+ "Formularul a fost completat de 2 ori\r\n"));
	    assertTrue(printed.contains("1: Întrebare 1?\r\n"
	    		+ " 1: Da\r\n"
	    		+ "    ETTI: 1\r\n"
	    		+ " 2: Nu\r\n"
	    		+ "    ACS: 1"));

	    assertTrue(printed.contains("2: Întrebare 2?\r\n"
	    		+ " 1: Opțiunea A\r\n"
	    		+ "    ACS: 1\r\n"
	    		+ " 2: Opțiunea B\r\n"
	    		+ "    ETTI: 1"));
	    
//	    formular completat de 3 ori
	    
	    assertTrue(printed.contains("Titlu: Chestionar de Test\r\n"
	    		+ "Formularul a fost completat de 3 ori"));
	    
	    assertTrue(printed.contains(" 1: Întrebare 1?\r\n"
	    		+ " 1: Da\r\n"
	    		+ "    ETTI: 1\r\n"
	    		+ " 2: Nu\r\n"
	    		+ "    ETTI: 1\r\n"
	    		+ "    ACS: 1"));

	    assertTrue(printed.contains(" 2: Întrebare 2?\r\n"
	    		+ " 1: Opțiunea A\r\n"
	    		+ "    ACS: 1\r\n"
	    		+ " 2: Opțiunea B\r\n"
	    		+ "    ETTI: 2"));
	    
	    assertTrue(printed.contains("Scorul mediu: 1.6666666/3"));
	  }

	  
}
