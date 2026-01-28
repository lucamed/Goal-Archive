package com.goalarchive.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe Competizione
 */
class CompetizioneTest {

    @Test
    void testCostruttoreVuoto() {
        Competizione competizione = new Competizione();
        assertNotNull(competizione);
    }

    @Test
    void testSettersGetters() {
        Competizione competizione = new Competizione();

        competizione.setIdCompetizione(1);
        competizione.setNome("Champions League");
        competizione.setTipo("Internazionale");
        competizione.setNazione("Europa");

        assertEquals(1, competizione.getIdCompetizione());
        assertEquals("Champions League", competizione.getNome());
        assertEquals("Internazionale", competizione.getTipo());
        assertEquals("Europa", competizione.getNazione());
    }

    @Test
    void testCompetizioneCoppaItalia() {
        Competizione competizione = new Competizione();
        competizione.setNome("Coppa Italia");
        competizione.setTipo("Nazionale");
        competizione.setNazione("Italia");

        assertEquals("Coppa Italia", competizione.getNome());
        assertEquals("Nazionale", competizione.getTipo());
    }

    @Test
    void testTipoNull() {
        Competizione competizione = new Competizione();
        competizione.setTipo(null);
        assertNull(competizione.getTipo());
    }
}
