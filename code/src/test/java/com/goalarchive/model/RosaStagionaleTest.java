package com.goalarchive.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe RosaStagionale
 */
class RosaStagionaleTest {

    @Test
    void testCostruttoreVuoto() {
        RosaStagionale rosa = new RosaStagionale();
        assertNotNull(rosa);
    }

    @Test
    void testSettersGetters() {
        RosaStagionale rosa = new RosaStagionale();

        rosa.setIdRosaStagionale(1);
        rosa.setIdClub(10);
        rosa.setIdCalciatore(5);
        rosa.setStagione("2023-2024");
        rosa.setPresenze(30);
        rosa.setGol(15);
        rosa.setNomeCalciatore("Cristiano");
        rosa.setCognomeCalciatore("Ronaldo");
        rosa.setRuoloCalciatore("Attaccante");
        rosa.setNazionalitaCalciatore("Portogallo");

        assertEquals(1, rosa.getIdRosaStagionale());
        assertEquals(10, rosa.getIdClub());
        assertEquals(5, rosa.getIdCalciatore());
        assertEquals("2023-2024", rosa.getStagione());
        assertEquals(30, rosa.getPresenze());
        assertEquals(15, rosa.getGol());
        assertEquals("Cristiano", rosa.getNomeCalciatore());
        assertEquals("Ronaldo", rosa.getCognomeCalciatore());
        assertEquals("Attaccante", rosa.getRuoloCalciatore());
        assertEquals("Portogallo", rosa.getNazionalitaCalciatore());
    }

    @Test
    void testPresenzeZero() {
        RosaStagionale rosa = new RosaStagionale();
        rosa.setPresenze(0);
        assertEquals(0, rosa.getPresenze());
    }

    @Test
    void testGolNegativi() {
        RosaStagionale rosa = new RosaStagionale();
        rosa.setGol(-1);
        assertEquals(-1, rosa.getGol());
    }

    @Test
    void testStagioneNull() {
        RosaStagionale rosa = new RosaStagionale();
        rosa.setStagione(null);
        assertNull(rosa.getStagione());
    }

    @Test
    void testPortiere() {
        RosaStagionale rosa = new RosaStagionale();
        rosa.setRuoloCalciatore("Portiere");
        rosa.setGol(0);

        assertEquals("Portiere", rosa.getRuoloCalciatore());
        assertEquals(0, rosa.getGol());
    }
}
