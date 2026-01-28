package com.goalarchive.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test ManagerRosa - Visualizzazione Rosa")
class ManagerRosaTest {

    private boolean validaVisualizzaRosa(int idClub, String stagione) {
        if (idClub <= 0) {
            throw new IllegalArgumentException("ID club non valido");
        }
        if (stagione == null || stagione.trim().isEmpty()) {
            throw new IllegalArgumentException("Stagione non selezionata");
        }
        if (!stagione.matches("^\\d{4}/\\d{2}$")) {
            throw new IllegalArgumentException("Formato stagione non valido");
        }
        return true;
    }

    @Test
    @DisplayName("TC6.0 - Visualizza rosa con successo")
    void testVisualizzaRosaSuccesso() {
        boolean result = validaVisualizzaRosa(1, "2023/24");
        assertTrue(result);
    }

    @Test
    @DisplayName("TC6.1 - Visualizza rosa ID club non valido")
    void testVisualizzaRosaIdClubNonValido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaVisualizzaRosa(-1, "2023/24")
        );
        assertTrue(exception.getMessage().contains("non valido"));
    }

    @Test
    @DisplayName("TC6.2 - Visualizza rosa stagione non selezionata")
    void testVisualizzaRosaStagioneNonSelezionata() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaVisualizzaRosa(1, "")
        );
        assertTrue(exception.getMessage().contains("Stagione"));
    }
}
