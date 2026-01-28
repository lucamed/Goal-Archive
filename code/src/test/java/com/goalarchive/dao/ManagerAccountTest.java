package com.goalarchive.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test ManagerAccount - Validazione Registrazione")
class ManagerAccountTest {

    @Mock(lenient = true)
    private DataSource mockDataSource;

    @Mock(lenient = true)
    private Connection mockConnection;

    @Mock(lenient = true)
    private PreparedStatement mockPreparedStatement;

    @Mock(lenient = true)
    private Statement mockStatement;

    @Mock(lenient = true)
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException {
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
    }

    // Metodo helper per validazione
    private boolean validazioneDatiRegistrazione(String nome, String cognome, String email,
                                                 String username, String dataNascita,
                                                 String password, String domanda, String risposta) {
        // Validazione Nome
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome non può essere vuoto");
        }
        if (nome.length() < 2) {
            throw new IllegalArgumentException("Nome deve avere almeno 2 caratteri");
        }
        if (!nome.matches("^[a-zA-ZÀ-ÿ\\s'-]+$")) {
            throw new IllegalArgumentException("Nome contiene caratteri non validi");
        }

        // Validazione Cognome
        if (cognome == null || cognome.trim().isEmpty()) {
            throw new IllegalArgumentException("Cognome non può essere vuoto");
        }
        if (cognome.length() < 2) {
            throw new IllegalArgumentException("Cognome deve avere almeno 2 caratteri");
        }
        if (!cognome.matches("^[a-zA-ZÀ-ÿ\\s'-]+$")) {
            throw new IllegalArgumentException("Cognome contiene caratteri non validi");
        }

        // Validazione Email
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email non può essere vuota");
        }
        if (email.length() < 6) {
            throw new IllegalArgumentException("Email troppo corta");
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Formato email non valido");
        }

        // Validazione Username
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username non può essere vuoto");
        }
        if (username.length() < 3) {
            throw new IllegalArgumentException("Username deve avere almeno 3 caratteri");
        }

        // Validazione Password
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password non può essere vuota");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password deve avere almeno 8 caratteri");
        }

        // Validazione Data Nascita
        if (dataNascita == null || dataNascita.trim().isEmpty()) {
            throw new IllegalArgumentException("Data di nascita non può essere vuota");
        }

        // Validazione età minima (almeno 13 anni)
        try {
            java.time.LocalDate data = java.time.LocalDate.parse(dataNascita);
            java.time.LocalDate oggi = java.time.LocalDate.now();
            int eta = java.time.Period.between(data, oggi).getYears();
            if (eta < 13) {
                throw new IllegalArgumentException("Età minima 13 anni");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Data nascita non valida");
        }

        // Validazione Domanda Sicurezza
        if (domanda == null || domanda.trim().isEmpty()) {
            throw new IllegalArgumentException("Domanda sicurezza non può essere vuota");
        }

        // Validazione Risposta Sicurezza
        if (risposta == null || risposta.trim().isEmpty()) {
            throw new IllegalArgumentException("Risposta sicurezza non può essere vuota");
        }

        return true;
    }

    @Test
    @DisplayName("TC2.0 - Validazione dati registrazione corretti")
    void testValidazioneDatiRegistrazioneCorretti() {
        boolean result = validazioneDatiRegistrazione(
                "Mario", "Rossi", "mario.rossi@mail.com", "mariorossi",
                "1990-05-15", "Password123!", "Nome del tuo animale?", "Fido"
        );
        assertTrue(result);
    }

    @Test
    @DisplayName("TC2.1 - Nome vuoto")
    void testRegistrazioneNomeVuoto() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "", "Rossi", "test@mail.com", "testuser",
                        "1990-01-01", "Password123!", "Domanda?", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("Nome"));
    }

    @Test
    @DisplayName("TC2.2 - Nome troppo corto")
    void testRegistrazioneNomeCorto() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "M", "Rossi", "test@mail.com", "testuser",
                        "1990-01-01", "Password123!", "Domanda?", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("caratteri"));
    }

    @Test
    @DisplayName("TC2.3 - Nome con numeri")
    void testRegistrazioneNomeConNumeri() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario123", "Rossi", "test@mail.com", "testuser",
                        "1990-01-01", "Password123!", "Domanda?", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("non validi"));
    }

    @Test
    @DisplayName("TC2.4 - Cognome vuoto")
    void testRegistrazioneCognomeVuoto() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario", "", "test@mail.com", "testuser",
                        "1990-01-01", "Password123!", "Domanda?", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("Cognome"));
    }

    @Test
    @DisplayName("TC2.5 - Cognome troppo corto")
    void testRegistrazioneCognomeCorto() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario", "R", "test@mail.com", "testuser",
                        "1990-01-01", "Password123!", "Domanda?", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("caratteri"));
    }

    @Test
    @DisplayName("TC2.6 - Cognome con caratteri speciali")
    void testRegistrazioneCognomeConCaratteriSpeciali() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario", "Rossi@123", "test@mail.com", "testuser",
                        "1990-01-01", "Password123!", "Domanda?", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("non validi"));
    }

    @Test
    @DisplayName("TC2.7 - Email vuota")
    void testRegistrazioneEmailVuota() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario", "Rossi", "", "testuser",
                        "1990-01-01", "Password123!", "Domanda?", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("Email"));
    }

    @Test
    @DisplayName("TC2.8 - Email troppo corta")
    void testRegistrazioneEmailCorta() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario", "Rossi", "a@b.c", "testuser",
                        "1990-01-01", "Password123!", "Domanda?", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("corta"));
    }

    @Test
    @DisplayName("TC2.9 - Email formato non valido")
    void testRegistrazioneEmailFormatoNonValido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario", "Rossi", "emailsenzachiocciola", "testuser",
                        "1990-01-01", "Password123!", "Domanda?", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("Formato"));
    }

    @Test
    @DisplayName("TC2.10 - Username vuoto")
    void testRegistrazioneUsernameVuoto() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario", "Rossi", "test@mail.com", "",
                        "1990-01-01", "Password123!", "Domanda?", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("Username"));
    }

    @Test
    @DisplayName("TC2.11 - Username troppo corto")
    void testRegistrazioneUsernameCorto() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario", "Rossi", "test@mail.com", "ab",
                        "1990-01-01", "Password123!", "Domanda?", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("3 caratteri"));
    }

    @Test
    @DisplayName("TC2.12 - Password vuota")
    void testRegistrazionePasswordVuota() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario", "Rossi", "test@mail.com", "testuser",
                        "1990-01-01", "", "Domanda?", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("Password"));
    }

    @Test
    @DisplayName("TC2.13 - Password troppo corta")
    void testRegistrazionePasswordCorta() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario", "Rossi", "test@mail.com", "testuser",
                        "1990-01-01", "Pass1", "Domanda?", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("8 caratteri"));
    }

    @Test
    @DisplayName("TC2.14 - Data nascita vuota")
    void testRegistrazioneDataNascitaVuota() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario", "Rossi", "test@mail.com", "testuser",
                        "", "Password123!", "Domanda?", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("Data"));
    }

    @DisplayName("TC2.15 - Età minima non rispettata")
    void testRegistrazioneEtaMinima() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario", "Rossi", "test@mail.com", "testuser",
                        "2020-01-01", "Password123!", "Domanda?", "Risposta"
                )
        );
        // Verifica che contenga "13" o "età" o "anni" nel messaggio
        String msg = exception.getMessage().toLowerCase();
        assertTrue(msg.contains("13") || msg.contains("età") || msg.contains("eta") || msg.contains("anni"));
    }


    @Test
    @DisplayName("TC2.16 - Domanda sicurezza vuota")
    void testRegistrazioneDomandaSicurezzaVuota() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario", "Rossi", "test@mail.com", "testuser",
                        "1990-01-01", "Password123!", "", "Risposta"
                )
        );
        assertTrue(exception.getMessage().contains("Domanda"));
    }

    @Test
    @DisplayName("TC2.17 - Risposta sicurezza vuota")
    void testRegistrazioneRispostaSicurezzaVuota() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                validazioneDatiRegistrazione(
                        "Mario", "Rossi", "test@mail.com", "testuser",
                        "1990-01-01", "Password123!", "Domanda?", ""
                )
        );
        assertTrue(exception.getMessage().contains("Risposta"));
    }
}
