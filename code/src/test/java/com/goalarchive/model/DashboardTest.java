package com.goalarchive.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe Dashboard
 */
class DashboardTest {

    @Test
    void testCostruttoreVuoto() {
        Dashboard dashboard = new Dashboard();
        assertNotNull(dashboard);
    }

    @Test
    void testSettersGetters() {
        Dashboard dashboard = new Dashboard();
        UtenteRegistrato utente = new UtenteRegistrato();
        List<Preferito> preferitiClub = new ArrayList<>();
        List<Preferito> preferitiCalciatori = new ArrayList<>();

        dashboard.setUtente(utente);
        dashboard.setPreferitiClub(preferitiClub);
        dashboard.setPreferitiCalciatori(preferitiCalciatori);
        dashboard.setTotalePreferiti(5);

        assertNotNull(dashboard.getUtente());
        assertNotNull(dashboard.getPreferitiClub());
        assertNotNull(dashboard.getPreferitiCalciatori());
        assertEquals(5, dashboard.getTotalePreferiti());
    }

    @Test
    void testListeVuote() {
        Dashboard dashboard = new Dashboard();
        dashboard.setPreferitiClub(new ArrayList<>());
        dashboard.setPreferitiCalciatori(new ArrayList<>());

        assertEquals(0, dashboard.getPreferitiClub().size());
        assertEquals(0, dashboard.getPreferitiCalciatori().size());
    }

    @Test
    void testTotalePreferitiZero() {
        Dashboard dashboard = new Dashboard();
        dashboard.setTotalePreferiti(0);
        assertEquals(0, dashboard.getTotalePreferiti());
    }

    @Test
    void testUtenteNull() {
        Dashboard dashboard = new Dashboard();
        dashboard.setUtente(null);
        assertNull(dashboard.getUtente());
    }
}
