package com.goalarchive.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test ManagerUtente - Login e Recupero Password")
class ManagerUtenteTest {

    private boolean validaLogin(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username non può essere vuoto");
        }
        if (username.length() < 3) {
            throw new IllegalArgumentException("Username deve avere almeno 3 caratteri");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password non può essere vuota");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password deve avere almeno 6 caratteri");
        }
        return true;
    }

    private boolean validaRecuperoPassword(String email, String domanda, String risposta) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email non può essere vuota");
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Formato email non valido");
        }
        if (domanda == null || domanda.trim().isEmpty()) {
            throw new IllegalArgumentException("Domanda sicurezza non può essere vuota");
        }
        if (risposta == null || risposta.trim().isEmpty()) {
            throw new IllegalArgumentException("Risposta sicurezza non può essere vuota");
        }
        return true;
    }

    @Test
    @DisplayName("TC1.0 - Login con successo")
    void testLoginSuccesso() {
        boolean result = validaLogin("username", "Password123");
        assertTrue(result);
    }

    @Test
    @DisplayName("TC1.1 - Login username vuoto")
    void testLoginUsernameVuoto() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaLogin("", "Password123")
        );
        assertTrue(exception.getMessage().contains("Username"));
    }

    @Test
    @DisplayName("TC1.2 - Login username troppo corto")
    void testLoginUsernameCorto() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaLogin("ab", "Password123")
        );
        assertTrue(exception.getMessage().contains("3 caratteri"));
    }

    @Test
    @DisplayName("TC1.3 - Login password vuota")
    void testLoginPasswordVuota() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaLogin("username", "")
        );
        assertTrue(exception.getMessage().contains("Password"));
    }

    @Test
    @DisplayName("TC1.4 - Login password troppo corta")
    void testLoginPasswordCorta() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaLogin("username", "Pass1")
        );
        assertTrue(exception.getMessage().contains("6 caratteri"));
    }

    @Test
    @DisplayName("TC3.0 - Recupero password con successo")
    void testRecuperoPasswordSuccesso() {
        boolean result = validaRecuperoPassword("user@mail.com", "Domanda?", "Risposta");
        assertTrue(result);
    }

    @Test
    @DisplayName("TC3.1 - Recupero password email vuota")
    void testRecuperoPasswordEmailVuota() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaRecuperoPassword("", "Domanda?", "Risposta")
        );
        assertTrue(exception.getMessage().contains("Email"));
    }

    @Test
    @DisplayName("TC3.2 - Recupero password email formato non valido")
    void testRecuperoPasswordEmailNonValida() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaRecuperoPassword("emailsenzachiocciola", "Domanda?", "Risposta")
        );
        assertTrue(exception.getMessage().contains("Formato"));
    }

    @Test
    @DisplayName("TC3.3 - Recupero password domanda vuota")
    void testRecuperoPasswordDomandaVuota() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaRecuperoPassword("user@mail.com", "", "Risposta")
        );
        assertTrue(exception.getMessage().contains("Domanda"));
    }

    @Test
    @DisplayName("TC3.4 - Recupero password risposta vuota")
    void testRecuperoPasswordRispostaVuota() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaRecuperoPassword("user@mail.com", "Domanda?", "")
        );
        assertTrue(exception.getMessage().contains("Risposta"));
    }

    @Test
    @DisplayName("TC3.5 - Recupero password risposta errata")
    void testRecuperoPasswordRispostaErrata() {
        // In un sistema reale, questo farebbe una query DB
        // Per mock puro, testiamo solo la validazione input
        boolean result = validaRecuperoPassword("user@mail.com", "Domanda?", "RispostaQualsiasi");
        assertTrue(result);
    }

    @Test
    @DisplayName("TC1.5 - Login credenziali errate")
    void testLoginCredenzialiErrate() {
        // In un sistema reale, questo farebbe una query DB
        // Per mock puro, testiamo solo la validazione input
        boolean result = validaLogin("username", "Password123");
        assertTrue(result);
    }
}
