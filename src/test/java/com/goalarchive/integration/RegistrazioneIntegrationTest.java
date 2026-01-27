package com.goalarchive.integration;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
@DisplayName("Test Integrazione - Registrazione con Database Reale")
class RegistrazioneIntegrationTest {

    @BeforeAll
    void setupDatabase() {
        try {
            TestDatabaseSetup.getTestDataSource();
            TestDatabaseSetup.insertTestData();
        } catch (Exception e) {
            throw new RuntimeException("Errore setup database", e);
        }
    }

    @BeforeEach
    void cleanupBeforeEach() throws Exception {
        String sql = "DELETE FROM utenti WHERE email = ?";
        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "nuovo@example.com");
            stmt.executeUpdate();
        }
    }

    @Test
    @DisplayName("IT5: Inserimento nuovo utente - Query diretta al DB")
    void testInserimentoNuovoUtente() throws Exception {
        String insertSql = "INSERT INTO utenti (email, nome, cognome, username, data_nascita, " +
                "password, domanda_sicurezza, risposta_sicurezza, squadra_cuore) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSql)) {

            stmt.setString(1, "nuovo@example.com");
            stmt.setString(2, "Luigi");
            stmt.setString(3, "Verdi");
            stmt.setString(4, "luigiverdi");
            stmt.setString(5, "1995-06-15");
            stmt.setString(6, "Password123");
            stmt.setString(7, "Nome città?");
            stmt.setString(8, "Bologna");
            stmt.setString(9, "Inter");

            int rowsAffected = stmt.executeUpdate();
            assertEquals(1, rowsAffected, "Deve essere inserita 1 riga");
        }

        // Verifica inserimento
        String selectSql = "SELECT * FROM utenti WHERE email = ?";
        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectSql)) {

            stmt.setString(1, "nuovo@example.com");
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next(), "Utente deve essere presente");
            assertEquals("Luigi", rs.getString("nome"));
            assertEquals("Verdi", rs.getString("cognome"));
        }
    }

    @Test
    @DisplayName("IT6: Verifica email già esistente - Query diretta al DB")
    void testVerificaEmailEsistente() throws Exception {
        String sql = "SELECT COUNT(*) as count FROM utenti WHERE email = ?";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "test@example.com");
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertTrue(rs.getInt("count") > 0, "Email deve già esistere");
        }
    }

    @Test
    @DisplayName("IT7: Verifica domanda di sicurezza dopo registrazione")
    void testDomandaSicurezzaDopoRegistrazione() throws Exception {
        // Prima inserisci
        String insertSql = "INSERT INTO utenti (email, nome, cognome, username, data_nascita, " +
                "password, domanda_sicurezza, risposta_sicurezza) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSql)) {

            stmt.setString(1, "sicurezza@example.com");
            stmt.setString(2, "Anna");
            stmt.setString(3, "Neri");
            stmt.setString(4, "annaneri");
            stmt.setString(5, "1998-07-10");
            stmt.setString(6, "SecurePass123");
            stmt.setString(7, "Nome primo animale?");
            stmt.setString(8, "Pluto");

            stmt.executeUpdate();
        }

        // Poi verifica
        String selectSql = "SELECT domanda_sicurezza, risposta_sicurezza FROM utenti WHERE email = ?";
        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectSql)) {

            stmt.setString(1, "sicurezza@example.com");
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertEquals("Nome primo animale?", rs.getString("domanda_sicurezza"));
            assertEquals("Pluto", rs.getString("risposta_sicurezza"));
        }
    }

    @AfterAll
    void cleanupDatabase() {
        try {
            TestDatabaseSetup.cleanDatabase();
        } catch (Exception e) {
            System.err.println("Errore pulizia database: " + e.getMessage());
        }
    }
}
