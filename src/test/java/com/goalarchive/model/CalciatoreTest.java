package com.goalarchive.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe Calciatore
 */
class CalciatoreTest {

    @Test
    void testCostruttoreVuoto() {
        Calciatore calciatore = new Calciatore();
        assertNotNull(calciatore);
    }

    @Test
    void testCostruttoreCompleto() {
        Calciatore calciatore = new Calciatore(
                1,
                "Cristiano",
                "Ronaldo",
                "Attaccante",
                "Portogallo"
        );

        assertEquals(1, calciatore.getIdCalciatore());
        assertEquals("Cristiano", calciatore.getNome());
        assertEquals("Ronaldo", calciatore.getCognome());
        assertEquals("Attaccante", calciatore.getRuolo());
        assertEquals("Portogallo", calciatore.getNazionalita());
    }

    @Test
    void testSettersGetters() {
        Calciatore calciatore = new Calciatore();

        calciatore.setIdCalciatore(10);
        calciatore.setNome("Lionel");
        calciatore.setCognome("Messi");
        calciatore.setRuolo("Centrocampista");
        calciatore.setNazionalita("Argentina");

        assertEquals(10, calciatore.getIdCalciatore());
        assertEquals("Lionel", calciatore.getNome());
        assertEquals("Messi", calciatore.getCognome());
        assertEquals("Centrocampista", calciatore.getRuolo());
        assertEquals("Argentina", calciatore.getNazionalita());
    }

    @Test
    void testRuoloPortiere() {
        Calciatore calciatore = new Calciatore();
        calciatore.setRuolo("Portiere");
        assertEquals("Portiere", calciatore.getRuolo());
    }

    @Test
    void testNazionalitaNull() {
        Calciatore calciatore = new Calciatore();
        calciatore.setNazionalita(null);
        assertNull(calciatore.getNazionalita());
    }
}
