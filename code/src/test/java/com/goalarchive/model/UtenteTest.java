package com.goalarchive.model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe Utente
 */
class UtenteTest {

    @Test
    void testCostruttoreVuoto() {
        Utente utente = new Utente();
        assertNotNull(utente);
    }

    @Test
    void testCostruttoreCompleto() {
        Date dataNascita = new Date();
        Utente utente = new Utente(
                "test@example.com",
                "Mario",
                "Rossi",
                "mariorossi",
                dataNascita,
                "password123",
                "user"
        );

        assertEquals("test@example.com", utente.getEmail());
        assertEquals("Mario", utente.getNome());
        assertEquals("Rossi", utente.getCognome());
        assertEquals("mariorossi", utente.getUsername());
        assertEquals(dataNascita, utente.getDataNascita());
        assertEquals("password123", utente.getPassword());
        assertEquals("user", utente.getRuolo());
    }

    @Test
    void testSettersGetters() {
        Utente utente = new Utente();
        Date dataNascita = new Date();

        utente.setEmail("nuovo@example.com");
        utente.setNome("Luigi");
        utente.setCognome("Verdi");
        utente.setUsername("luigiverdi");
        utente.setDataNascita(dataNascita);
        utente.setPassword("newpass");
        utente.setRuolo("admin");

        assertEquals("nuovo@example.com", utente.getEmail());
        assertEquals("Luigi", utente.getNome());
        assertEquals("Verdi", utente.getCognome());
        assertEquals("luigiverdi", utente.getUsername());
        assertEquals(dataNascita, utente.getDataNascita());
        assertEquals("newpass", utente.getPassword());
        assertEquals("admin", utente.getRuolo());
    }

    @Test
    void testEmailNull() {
        Utente utente = new Utente();
        utente.setEmail(null);
        assertNull(utente.getEmail());
    }

    @Test
    void testPasswordVuota() {
        Utente utente = new Utente();
        utente.setPassword("");
        assertEquals("", utente.getPassword());
    }
}
