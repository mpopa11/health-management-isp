package testare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import health_management.Formular;
import health_management.FormularRegistry;
import health_management.InputHandler;
import health_management.Intrebare;
import health_management.Recomandare;

class TestareCreareFormular {
	
	
    private final InputStream  originalIn  = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); 
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        FormularRegistry.clear();
    }
	

    @Test
    public void testCreareFormular() {
    	String simulatedInput;
        Formular formular = new Formular("autorTest");
        String printed;
    	
        simulatedInput = String.join("\n",
            "Test Formular",
            "1",       
            "Cum te simti?",
            "Bine", 
            "5",    
            "n", 
            "n",
            "2",
            "Ar trebui sa faci mai mult sport",
            "2",
            "4",
            "n",
            "3" 
        );

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();

        formular.creare();

        assertEquals("Test Formular", formular.getTitlu());
        assertEquals("autorTest", formular.getAutor());
        assertEquals(1, formular.getIntrebari().size());
        assertNotEquals(null, formular.getIntrebari());
        assertNotEquals(null, formular.getRecomandari());
        assertEquals(1, formular.getIntrebari().size());
        assertEquals(1, formular.getRecomandari().size());
       
        //Test camp titlu
        simulatedInput = String.join("\n",
        		"",
                "Test Formular",
                "1",       
                "Cum te simti?",
                "Bine", 
                "5",    
                "n", 
                "n",
                "3"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular.creare();
        
        printed = outContent.toString();
        assertTrue(printed.contains("ATENTIE: Titlul este un camp obligatoriu!"), 
        		"test titlu");
        
      //Test exit
        simulatedInput = String.join("\n",
        		"",
                "Test Formular",
                "3",
                "1",       
                "Cum te simti?",
                "Bine", 
                "5",    
                "n", 
                "n",
                "3"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular = new Formular("autorTest");
        formular.creare();
        
        printed = outContent.toString();
        assertTrue(printed.contains("ATENTIE: Adaugati cel putin o intrebare!"), 
        		"test exit");
        
    }
    
    @Test
    public void testAdaugaIntrebare() {
    	String printed;
    	String simulatedInput;
    	Formular formular = new Formular("autorTest");
    	
    	
    	simulatedInput = String.join("\n",
            "Cum te simti?",
            "Bine", 
            "5", 
            "n", 
            "n"
        );

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();

        
        formular.adaugaIntrebare();
        
        Intrebare intrebare = formular.getIntrebari().get(0);
        assertEquals("Cum te simti?", intrebare.getTextIntrebare());
        assertEquals("Bine",intrebare.getRaspuns(0));
        assertEquals((Integer)5, (intrebare.getPuncte()).get(0));
        
        //Test camp intrebare
        simulatedInput = String.join("\n",
                "",
                "Cum te simti?",
                "Bine", 
                "5", 
                "n", 
                "n"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular.adaugaIntrebare();
        
        printed = outContent.toString();
        assertTrue(printed.contains("ATENTIE: Acest este un camp obligatoriu!"), 
        		"test text intrebare");
        
        //Test camp raspuns
        simulatedInput = String.join("\n",
                "Cum te simti?",
                "",
                "Bine",
                "5", 
                "n", 
                "n"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular.adaugaIntrebare();
        
        printed = outContent.toString();
        assertTrue(printed.contains("ATENTIE: Raspunsul nu trebuie sa fie un camp gol!"),
        		"test text raspuns");
        
      //Test camp punctaj
        simulatedInput = String.join("\n",
                "Cum te simti?",
                "Bine",
                "",
                "5",
                "n", 
                "n"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular.adaugaIntrebare();
        
        printed = outContent.toString();
        assertTrue(printed.contains("ATENTIE: Trebuie sa introduceti un numar intreg si pozitiv!"),
        		"test punctaj 1");
        
      //Test camp punctaj
        simulatedInput = String.join("\n",
                "Cum te simti?",
                "Bine",
                "-3",
                "5",
                "n", 
                "n"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular.adaugaIntrebare();
        
        printed = outContent.toString();
        assertTrue(printed.contains("ATENTIE: Trebuie sa introduceti un numar intreg si pozitiv!"),
        		"test punctaj 2");
        
        //Test camp punctaj
        simulatedInput = String.join("\n",
                "Cum te simti?",
                "Bine",
                "dgs",
                "5",
                "n", 
                "n"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular.adaugaIntrebare();
        
        printed = outContent.toString();
        assertTrue(printed.contains("ATENTIE: Trebuie sa introduceti un numar intreg si pozitiv!"),
        		"test punctaj 3");
        
      //Test introducere alt raspuns
        simulatedInput = String.join("\n",
                "Cum te simti?",
                "Bine",
                "5",
                "y",
                "Rau",
                "0",
                "n",
                "n"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular.adaugaIntrebare();
        
        Intrebare intrebare2 = formular.getIntrebari().get(formular.getIntrebari().size()-1);
        assertEquals(2, intrebare2.getRaspunsuri().size());
        
        //Test introducere alta intrebare
        simulatedInput = String.join("\n",
                "Cum te simti?",
                "Bine",
                "5",
                "n",
                "y",
                "Este totul bine?",
                "Da",
                "5",
                "n",
                "n"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular = new Formular("autorTest");
        
        formular.adaugaIntrebare();
        
        assertEquals(2, formular.getIntrebari().size());
    }
    
    @Test
    public void testAdaugaRecomandare() {
    	String printed;
    	String simulatedInput;
    	Formular formular = new Formular("autorTest");
    	//formular.getIntrebari().add(new Intrebare());
    	
    	
    	
    	simulatedInput = String.join("\n",
            "Ar trebui sa faci mai mult sport.",
            "2",
            "5",
            "n"
        );

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();

        formular.adaugaRecomandare();
        
        Recomandare recomandare = formular.getRecomandari().get(0);
        assertEquals("Ar trebui sa faci mai mult sport.", recomandare.getTextRecomandare());
        assertEquals(2,recomandare.getPragInferior());
        assertEquals(5, recomandare.getPragSuperior());
        
        //Test camp recomandare
        simulatedInput = String.join("\n",
        		"",
                "Ar trebui sa faci mai mult sport.",
                "2",
                "5",
                "n"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular.adaugaRecomandare();
        
        printed = outContent.toString();
        assertTrue(printed.contains("ATENTIE: Recomandarea nu trebuie sa fie un camp gol!"), 
        		"test text recomandare");
        
      //Test prag inferior
        simulatedInput = String.join("\n",
                "Ar trebui sa faci mai mult sport.",
                "",
                "2",
                "5",
                "n"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular.adaugaRecomandare();
        
        printed = outContent.toString();
        assertTrue(printed.contains("ATENTIE: Trebuie sa introduceti un numar intreg si pozitiv!"), 
        		"test prag inferior 1");
        
      //Test prag inferior
        simulatedInput = String.join("\n",
                "Ar trebui sa faci mai mult sport.",
                "-5",
                "2",
                "5",
                "n"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular.adaugaRecomandare();
        
        printed = outContent.toString();
        assertTrue(printed.contains("ATENTIE: Trebuie sa introduceti un numar intreg si pozitiv!"), 
        		"test prag inferior 2");
        
      //Test prag superior
        simulatedInput = String.join("\n",
                "Ar trebui sa faci mai mult sport.",
                "2",
                "",
                "5",
                "n"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular.adaugaRecomandare();
        
        printed = outContent.toString();
        assertTrue(printed.contains("ATENTIE: Trebuie sa introduceti un numar intreg si pozitiv!"), 
        		"test prag superior 1");
        
        
      //Test prag superior
        simulatedInput = String.join("\n",
                "Ar trebui sa faci mai mult sport.",
                "2",
                "1",
                "5",
                "n"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular.adaugaRecomandare();
        
        printed = outContent.toString();
        assertTrue(printed.contains("Pragul superior trebuie sa fie mai mare decat cel inferior!"), 
        		"test prag superior 2");
        
      //Test adauga alta recomandare
        simulatedInput = String.join("\n",
                "Ar trebui sa faci mai mult sport.",
                "2",
                "5",
                "y",
                "Ar trebui sa mananci mai sanatos.",
                "3",
                "4",
                "n"
            );
        
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.resetScanner();
        formular = new Formular("autorTest");
        formular.adaugaRecomandare();
        
        assertEquals(2, formular.getRecomandari().size());
    }
}











