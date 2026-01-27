package com.goalarchive.model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe Commento
 */
class CommentoTest {

    @Test
    void testCostruttoreVuoto() {
        Commento commento = new Commento();
        assertNotNull(commento);
    }

    @Test
    void testSettersGetters() {
        Commento commento = new Commento();
        Date data = new Date();

        commento.setIdCommento(1);
        commento.setIdTopic(10);
        commento.setEmail("user@test.com");
        commento.setTesto("Ottimo articolo!");
        commento.setDataPubblicazione(data);
        commento.setNomeUtente("testuser");

        assertEquals(1, commento.getIdCommento());
        assertEquals(10, commento.getIdTopic());
        assertEquals("user@test.com", commento.getEmail());
        assertEquals("Ottimo articolo!", commento.getTesto());
        assertEquals(data, commento.getDataPubblicazione());
        assertEquals("testuser", commento.getNomeUtente());
    }

    @Test
    void testTestoLungo() {
        Commento commento = new Commento();
        String testoLungo = "a".repeat(1000);
        commento.setTesto(testoLungo);
        assertEquals(1000, commento.getTesto().length());
    }

    @Test
    void testDataNull() {
        Commento commento = new Commento();
        commento.setDataPubblicazione(null);
        assertNull(commento.getDataPubblicazione());
    }

    @Test
    void testIdTopicNegativo() {
        Commento commento = new Commento();
        commento.setIdTopic(-1);
        assertEquals(-1, commento.getIdTopic());
    }
}
