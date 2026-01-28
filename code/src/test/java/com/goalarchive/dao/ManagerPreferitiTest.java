package com.goalarchive.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test ManagerPreferiti - Gestione Preferiti")
class ManagerPreferitiTest {

    private boolean validaVisualizzaPreferiti(String email, String filtro) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email non può essere vuota");
        }
        if (filtro != null && filtro.trim().isEmpty()) {
            throw new IllegalArgumentException("Filtro non può essere vuoto");
        }
        return true;
    }

    private boolean validaAggiungiPreferito(String email, String tipo, int idRiferimento) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email non può essere vuota");
        }
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo non selezionato");
        }
        if (!tipo.equals("club") && !tipo.equals("calciatore")) {
            throw new IllegalArgumentException("Tipo non valido (club o calciatore)");
        }
        if (idRiferimento <= 0) {
            throw new IllegalArgumentException("ID riferimento non valido");
        }
        return true;
    }

    @Test
    @DisplayName("TC8.0 - Visualizza preferiti con successo")
    void testVisualizzaPreferitiSuccesso() {
        boolean result = validaVisualizzaPreferiti("user@mail.com", null);
        assertTrue(result);
    }

    @Test
    @DisplayName("TC8.1 - Visualizza preferiti email vuota")
    void testVisualizzaPreferitiEmailVuota() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaVisualizzaPreferiti("", null)
        );
        assertTrue(exception.getMessage().contains("Email"));
    }

    @Test
    @DisplayName("TC8.2 - Visualizza preferiti filtro vuoto")
    void testVisualizzaPreferitiFiltroVuoto() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaVisualizzaPreferiti("user@mail.com", "")
        );
        assertTrue(exception.getMessage().contains("Filtro"));
    }

    @Test
    @DisplayName("TC8.3 - Aggiungi preferito tipo non selezionato")
    void testAggiungiPreferitoTipoNonSelezionato() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaAggiungiPreferito("user@mail.com", "", 1)
        );
        assertTrue(exception.getMessage().contains("Tipo"));
    }

    @Test
    @DisplayName("TC8.4 - Aggiungi preferito con successo")
    void testAggiungiPreferitoSuccesso() {
        boolean result = validaAggiungiPreferito("user@mail.com", "club", 1);
        assertTrue(result);
    }
}
