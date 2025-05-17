package testare;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import health_management.Formular;
import health_management.Intrebare;

class TestareCreareFormular {
	
	
    private final InputStream originalIn = System.in;

    @AfterEach
    public void restoreSystemInput() {
        System.setIn(originalIn); 
    }
	

    @Test
    public void testCreareFormular() {
        String simulatedInput = String.join("\n",
            "Test Form",
            "1",       
            "Ce este Java?",
            "Un limbaj", 
            "5",    
            "n", 
            "n", 
            "3" 
        );

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Formular formular = new Formular("autorTest");
        formular.creare();

        assertEquals("Test Form", formular.getTitlu());
        assertEquals("autorTest", formular.getAutor());
        assertEquals(1, formular.getIntrebari().size());

        Intrebare intrebare = formular.getIntrebari().get(0);
        assertEquals("Ce este Java?", intrebare.getTextIntrebare());
    }
}











