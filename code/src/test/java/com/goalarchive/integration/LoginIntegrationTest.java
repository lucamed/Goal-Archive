package com.goalarchive.integration;

import org.junit.jupiter.api.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
@DisplayName("Test Integrazione - Login con Database Reale")
class LoginIntegrationTest {

    private DataSource dataSource;

    @BeforeAll
    void setupDatabase() {
        try {
            dataSource = TestDatabaseSetup.getTestDataSource();
            TestDatabaseSetup.insertTestData();
        } catch (Exception e) {
            throw new RuntimeException("Errore setup database", e);
        }
    }

    @Test
    @DisplayName("IT1: Verifica credenziali corrette - Query diretta al DB")
    void testVerificaCredenzialiCorrette() throws Exception {
        String sql = "SELECT * FROM utenti WHERE email = ? AND password = ?";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "test@example.com");
            stmt.setString(2, "Password123");

            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next(), "Utente deve essere trovato");
            assertEquals("Mario", rs.getString("nome"));
            assertEquals("Rossi", rs.getString("cognome"));
            assertEquals("mariorossi", rs.getString("username"));
            assertEquals("utente", rs.getString("ruolo"));
        }
    }

    @Test
    @DisplayName("IT2: Verifica credenziali errate - Query diretta al DB")
    void testVerificaCredenzialiErrate() throws Exception {
        String sql = "SELECT * FROM utenti WHERE email = ? AND password = ?";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "wrong@example.com");
            stmt.setString(2, "WrongPassword");

            ResultSet rs = stmt.executeQuery();

            assertFalse(rs.next(), "Utente non deve essere trovato");
        }
    }

    @Test
    @DisplayName("IT3: Verifica password errata - Query diretta al DB")
    void testVerificaPasswordErrata() throws Exception {
        String sql = "SELECT * FROM utenti WHERE email = ? AND password = ?";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "test@example.com");
            stmt.setString(2, "PasswordErrata");

            ResultSet rs = stmt.executeQuery();

            assertFalse(rs.next(), "Utente non deve essere trovato con password errata");
        }
    }

    @Test
    @DisplayName("IT4: Verifica login admin - Query diretta al DB")
    void testVerificaLoginAdmin() throws Exception {
        String sql = "SELECT * FROM utenti WHERE email = ? AND password = ? AND ruolo = ?";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "admin@example.com");
            stmt.setString(2, "AdminPass123");
            stmt.setString(3, "admin");

            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next(), "Admin deve essere trovato");
            assertEquals("admin", rs.getString("username"));
            assertEquals(3, rs.getInt("livello_accesso"));
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
