package com.goalarchive.integration;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
@DisplayName("Test Integrazione - Recupero Password con Database Reale")
class RecuperoPasswordIntegrationTest {

    @BeforeAll
    void setupDatabase() {
        try {
            TestDatabaseSetup.getTestDataSource();
            TestDatabaseSetup.insertTestData();
        } catch (Exception e) {
            throw new RuntimeException("Errore setup database", e);
        }
    }

    @Test
    @DisplayName("IT12: Recupero domanda di sicurezza")
    void testRecuperoDomandaSicurezza() throws Exception {
        String sql = "SELECT domanda_sicurezza FROM utenti WHERE email = ?";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "test@example.com");
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertEquals("Nome animale?", rs.getString("domanda_sicurezza"));
        }
    }

    @Test
    @DisplayName("IT13: Verifica risposta sicurezza corretta")
    void testVerificaRispostaCorretta() throws Exception {
        String sql = "SELECT * FROM utenti WHERE email = ? AND risposta_sicurezza = ?";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "test@example.com");
            stmt.setString(2, "Fido");

            ResultSet rs = stmt.executeQuery();
            assertTrue(rs.next(), "Risposta corretta deve trovare l'utente");
        }
    }

    @Test
    @DisplayName("IT14: Verifica risposta sicurezza errata")
    void testVerificaRispostaErrata() throws Exception {
        String sql = "SELECT * FROM utenti WHERE email = ? AND risposta_sicurezza = ?";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "test@example.com");
            stmt.setString(2, "RispostaErrata");

            ResultSet rs = stmt.executeQuery();
            assertFalse(rs.next(), "Risposta errata non deve trovare l'utente");
        }
    }

    @Test
    @DisplayName("IT15: Aggiornamento password")
    void testAggiornamentoPassword() throws Exception {
        String updateSql = "UPDATE utenti SET password = ? WHERE email = ?";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateSql)) {

            stmt.setString(1, "NuovaPassword123");
            stmt.setString(2, "test@example.com");

            int rowsAffected = stmt.executeUpdate();
            assertEquals(1, rowsAffected, "Deve essere aggiornata 1 riga");
        }

        // Verifica che la password sia stata aggiornata
        String selectSql = "SELECT password FROM utenti WHERE email = ?";
        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectSql)) {

            stmt.setString(1, "test@example.com");
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertEquals("NuovaPassword123", rs.getString("password"));
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
