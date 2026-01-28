package com.goalarchive.model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe Amministratore
 */
class AmministratoreTest {

    @Test
    void testCostruttoreVuoto() {
        Amministratore admin = new Amministratore();
        assertNotNull(admin);
    }

    @Test
    void testCostruttoreCompleto() {
        Date dataNascita = new Date();
        Amministratore admin = new Amministratore(
                "admin@example.com",
                "Admin",
                "User",
                "adminuser",
                dataNascita,
                "adminpass",
                3,
                "Codice di sicurezza?",
                "ABC123"
        );

        assertEquals("admin@example.com", admin.getEmail());
        assertEquals("Admin", admin.getNome());
        assertEquals("User", admin.getCognome());
        assertEquals("adminuser", admin.getUsername());
        assertEquals(dataNascita, admin.getDataNascita());
        assertEquals("adminpass", admin.getPassword());
        assertEquals("admin", admin.getRuolo());
        assertEquals(3, admin.getLivelloAccesso());
        assertEquals("Codice di sicurezza?", admin.getDomandaSicurezza());
        assertEquals("ABC123", admin.getRispostaSicurezza());
    }

    @Test
    void testSettersGetters() {
        Amministratore admin = new Amministratore();

        admin.setLivelloAccesso(5);
        admin.setDomandaSicurezza("Domanda?");
        admin.setRispostaSicurezza("Risposta");

        assertEquals(5, admin.getLivelloAccesso());
        assertEquals("Domanda?", admin.getDomandaSicurezza());
        assertEquals("Risposta", admin.getRispostaSicurezza());
    }

    @Test
    void testEreditaDaUtente() {
        Amministratore admin = new Amministratore();
        admin.setEmail("admin2@test.com");
        admin.setNome("Super");
        admin.setRuolo("admin");

        assertEquals("admin2@test.com", admin.getEmail());
        assertEquals("Super", admin.getNome());
        assertEquals("admin", admin.getRuolo());
    }

    @Test
    void testLivelloAccessoZero() {
        Amministratore admin = new Amministratore();
        admin.setLivelloAccesso(0);
        assertEquals(0, admin.getLivelloAccesso());
    }
}
