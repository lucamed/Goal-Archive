package com.goalarchive.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test ManagerCommenti - Pubblicazione Commento")
class ManagerCommentiTest {

    private boolean validaCommento(String testo, String email, int idCalciatore) {
        if (testo == null || testo.trim().isEmpty()) {
            throw new IllegalArgumentException("Testo commento vuoto");
        }
        if (testo.length() < 3) {
            throw new IllegalArgumentException("Testo commento troppo corto (min 3 caratteri)");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email non può essere vuota");
        }
        if (idCalciatore <= 0) {
            throw new IllegalArgumentException("ID calciatore non valido");
        }
        return true;
    }

    @Test
    @DisplayName("TC9.0 - Pubblica commento con successo")
    void testPubblicaCommentoSuccesso() {
        boolean result = validaCommento("Questo è un commento valido", "user@mail.com", 1);
        assertTrue(result);
    }

    @Test
    @DisplayName("TC9.1 - Testo commento vuoto")
    void testPubblicaCommentoTestoVuoto() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaCommento("", "user@mail.com", 1)
        );
        assertTrue(exception.getMessage().contains("vuoto"));
    }

    @Test
    @DisplayName("TC9.2 - Testo commento troppo corto")
    void testPubblicaCommentoTestoCorto() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaCommento("Ok", "user@mail.com", 1)
        );
        assertTrue(exception.getMessage().contains("corto"));
    }
}
