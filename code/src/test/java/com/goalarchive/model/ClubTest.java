package com.goalarchive.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe Club
 */
class ClubTest {

    @Test
    void testCostruttoreVuoto() {
        Club club = new Club();
        assertNotNull(club);
    }

    @Test
    void testSettersGetters() {
        Club club = new Club();

        club.setIdClub(1);
        club.setNome("Juventus");
        club.setNazione("Italia");
        club.setCampionato("Serie A");
        club.setAnnoFondazione(1897);
        club.setStadio("Allianz Stadium");

        assertEquals(1, club.getIdClub());
        assertEquals("Juventus", club.getNome());
        assertEquals("Italia", club.getNazione());
        assertEquals("Serie A", club.getCampionato());
        assertEquals(1897, club.getAnnoFondazione());
        assertEquals("Allianz Stadium", club.getStadio());
    }

    @Test
    void testAnnoFondazioneAntico() {
        Club club = new Club();
        club.setAnnoFondazione(1850);
        assertEquals(1850, club.getAnnoFondazione());
    }

    @Test
    void testNomeVuoto() {
        Club club = new Club();
        club.setNome("");
        assertEquals("", club.getNome());
    }

    @Test
    void testStadioNull() {
        Club club = new Club();
        club.setStadio(null);
        assertNull(club.getStadio());
    }

    @Test
    void testCampionatoEstero() {
        Club club = new Club();
        club.setNazione("Inghilterra");
        club.setCampionato("Premier League");

        assertEquals("Inghilterra", club.getNazione());
        assertEquals("Premier League", club.getCampionato());
    }
}
