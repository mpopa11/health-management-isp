package testare;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

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
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        StudentRegistry.clear();       
        FacultateRegistry.clear();  
    }

    @Test
    void testSignupStudentRegistersCorrectly() {
        String simulatedInput = String.join(
            "\n",
            "student",   // tip cont
            "Mihai",     // nume
            "Mihai",     // prenume
            "mihai22",     // username
            "parola1.",
            "Parola1.",// parola
            "1",
            "5",
            "3"
        ) + "\n";

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Meniu meniu = new Meniu();
        meniu.signup();

        Student saved = StudentRegistry.get("mihai22");
        assertNotNull(saved, "StudentRegistry should contain the new student");
        assertEquals("Mihai", saved.getNume());
        assertEquals("Mihai", saved.getPrenume());
        assertEquals("mihai22", saved.getUsername());
        assertEquals("Parola1.", saved.getParola());

        Facultate acs = FacultateRegistry.get("ACS");
        assertNotNull(acs, "FacultateRegistry should have created/get ACS");
        assertTrue(
            acs.getStudenti().contains(saved),
            "ACS should contain the newly signed-up student"
        );

        String printed = outContent.toString();
        assertTrue(printed.contains("Alegeti o optiune:"), 
                   "Should have prompted for account type");
        assertTrue(printed.contains("Format invalid pentru parola"), 
                "Should have prompted for account type");
        assertTrue(printed.contains("Log out"));
//        assertTrue(printed.contains("3. Close"));
    }
}
