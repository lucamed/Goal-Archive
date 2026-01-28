package com.goalarchive.model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe UtenteRegistrato
 */
class UtenteRegistratoTest {

    @Test
    void testCostruttoreVuoto() {
        UtenteRegistrato utente = new UtenteRegistrato();
        assertNotNull(utente);
    }

    @Test
    void testCostruttoreCompleto() {
        Date dataNascita = new Date();
        UtenteRegistrato utente = new UtenteRegistrato(
                "test@example.com",
                "Mario",
                "Rossi",
                "mariorossi",
                dataNascita,
                "password123",
                "Nome del tuo primo animale?",
                "Fido",
                "Juventus"
        );

        assertEquals("test@example.com", utente.getEmail());
        assertEquals("Mario", utente.getNome());
        assertEquals("Rossi", utente.getCognome());
        assertEquals("mariorossi", utente.getUsername());
        assertEquals(dataNascita, utente.getDataNascita());
        assertEquals("password123", utente.getPassword());
        assertEquals("utente", utente.getRuolo());
        assertEquals("Nome del tuo primo animale?", utente.getDomandaSicurezza());
        assertEquals("Fido", utente.getRispostaSicurezza());
        assertEquals("Juventus", utente.getSquadraCuore());
        assertNotNull(utente.getDataRegistrazione());
    }

    @Test
    void testSettersGetters() {
        UtenteRegistrato utente = new UtenteRegistrato();
        Date dataReg = new Date();

        utente.setDataRegistrazione(dataReg);
        utente.setDomandaSicurezza("Città natale?");
        utente.setRispostaSicurezza("Bologna");
        utente.setSquadraCuore("Inter");

        assertEquals(dataReg, utente.getDataRegistrazione());
        assertEquals("Città natale?", utente.getDomandaSicurezza());
        assertEquals("Bologna", utente.getRispostaSicurezza());
        assertEquals("Inter", utente.getSquadraCuore());
    }

    @Test
    void testEreditaDaUtente() {
        UtenteRegistrato utente = new UtenteRegistrato();
        utente.setEmail("eredita@test.com");
        utente.setNome("Test");
        utente.setUsername("testuser");

        assertEquals("eredita@test.com", utente.getEmail());
        assertEquals("Test", utente.getNome());
        assertEquals("testuser", utente.getUsername());
    }

    @Test
    void testSquadraCuoreNull() {
        UtenteRegistrato utente = new UtenteRegistrato();
        utente.setSquadraCuore(null);
        assertNull(utente.getSquadraCuore());
    }
}
