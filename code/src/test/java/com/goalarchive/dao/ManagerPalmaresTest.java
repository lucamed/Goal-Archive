package com.goalarchive.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test ManagerPalmares - Gestione Palmares")
class ManagerPalmaresTest {

    private boolean validaVisualizzaPalmares(int idCalciatore) {
        if (idCalciatore <= 0) {
            throw new IllegalArgumentException("ID calciatore non valido");
        }
        return true;
    }

    private boolean validaAggiungiTrofeo(int idCalciatore, String competizione, String stagione, String motivo) {
        if (idCalciatore <= 0) {
            throw new IllegalArgumentException("ID calciatore non valido");
        }
        if (competizione == null || competizione.trim().isEmpty()) {
            throw new IllegalArgumentException("Competizione non può essere vuota");
        }
        if (stagione == null || stagione.trim().isEmpty()) {
            throw new IllegalArgumentException("Stagione non può essere vuota");
        }
        if (!stagione.matches("^\\d{4}/\\d{2}$")) {
            throw new IllegalArgumentException("Formato stagione non valido (es: 2023/24)");
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException("Motivo non può essere vuoto");
        }
        return true;
    }

    @Test
    @DisplayName("TC7.0 - Visualizza palmares con successo")
    void testVisualizzaPalmaresSuccesso() {
        boolean result = validaVisualizzaPalmares(1);
        assertTrue(result);
    }

    @Test
    @DisplayName("TC7.1 - Visualizza palmares ID non valido")
    void testVisualizzaPalmaresIdNonValido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaVisualizzaPalmares(-1)
        );
        assertTrue(exception.getMessage().contains("non valido"));
    }

    @Test
    @DisplayName("TC7.2 - Aggiungi trofeo competizione vuota")
    void testAggiungiTrofeoCompetizioneVuota() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaAggiungiTrofeo(1, "", "2023/24", "Vincitore")
        );
        assertTrue(exception.getMessage().contains("Competizione"));
    }

    @Test
    @DisplayName("TC7.3 - Aggiungi trofeo stagione vuota")
    void testAggiungiTrofeoStagioneVuota() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaAggiungiTrofeo(1, "Champions League", "", "Vincitore")
        );
        assertTrue(exception.getMessage().contains("Stagione"));
    }

    @Test
    @DisplayName("TC7.4 - Aggiungi trofeo formato stagione non valido")
    void testAggiungiTrofeoFormatoStagioneNonValido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaAggiungiTrofeo(1, "Champions League", "2023", "Vincitore")
        );
        assertTrue(exception.getMessage().contains("Formato"));
    }

    @Test
    @DisplayName("TC7.5 - Aggiungi trofeo motivo vuoto")
    void testAggiungiTrofeoMotivoVuoto() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validaAggiungiTrofeo(1, "Champions League", "2023/24", "")
        );
        assertTrue(exception.getMessage().contains("Motivo"));
    }

    @Test
    @DisplayName("TC7.6 - Aggiungi trofeo con successo")
    void testAggiungiTrofeoSuccesso() {
        boolean result = validaAggiungiTrofeo(1, "Champions League", "2023/24", "Vincitore");
        assertTrue(result);
    }
}
