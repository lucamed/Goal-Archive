package com.goalarchive.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe Trofeo
 */
class TrofeoTest {

    @Test
    void testCostruttoreVuoto() {
        Trofeo trofeo = new Trofeo();
        assertNotNull(trofeo);
    }

    @Test
    void testSettersGetters() {
        Trofeo trofeo = new Trofeo();

        trofeo.setIdTrofeo(1);
        trofeo.setIdClub(10);
        trofeo.setAnno(2023);
        trofeo.setIdCompetizione(5);
        trofeo.setNomeCompetizione("Champions League");
        trofeo.setTipoCompetizione("Internazionale");

        assertEquals(1, trofeo.getIdTrofeo());
        assertEquals(10, trofeo.getIdClub());
        assertEquals(2023, trofeo.getAnno());
        assertEquals(5, trofeo.getIdCompetizione());
        assertEquals("Champions League", trofeo.getNomeCompetizione());
        assertEquals("Internazionale", trofeo.getTipoCompetizione());
    }

    @Test
    void testAnnoVecchio() {
        Trofeo trofeo = new Trofeo();
        trofeo.setAnno(1950);
        assertEquals(1950, trofeo.getAnno());
    }

    @Test
    void testNomeCompetizioneNull() {
        Trofeo trofeo = new Trofeo();
        trofeo.setNomeCompetizione(null);
        assertNull(trofeo.getNomeCompetizione());
    }

    @Test
    void testTipoNazionale() {
        Trofeo trofeo = new Trofeo();
        trofeo.setTipoCompetizione("Nazionale");
        assertEquals("Nazionale", trofeo.getTipoCompetizione());
    }
}
