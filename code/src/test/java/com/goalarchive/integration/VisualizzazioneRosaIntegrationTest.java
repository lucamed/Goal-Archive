package com.goalarchive.integration;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
@DisplayName("Test Integrazione - Visualizzazione Rosa con Database Reale")
class VisualizzazioneRosaIntegrationTest {

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
    @DisplayName("IT8: Recupero club dal database")
    void testRecuperoClub() throws Exception {
        String sql = "SELECT * FROM clubs WHERE id_club = ?";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, 1);
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next(), "Club deve essere trovato");
            assertEquals("Juventus", rs.getString("nome"));
            assertEquals("Torino", rs.getString("citta"));
            assertEquals("Allianz Stadium", rs.getString("stadio"));
            assertEquals(1897, rs.getInt("anno_fondazione"));
        }
    }

    @Test
    @DisplayName("IT9: Recupero rosa stagionale dal database")
    void testRecuperoRosaStagionale() throws Exception {
        String sql = "SELECT r.*, c.nome as nome_calciatore, c.cognome as cognome_calciatore " +
                "FROM rose_stagionali r " +
                "JOIN calciatori c ON r.id_calciatore = c.id_calciatore " +
                "WHERE r.id_club = ? AND r.stagione = ?";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, 1);
            stmt.setString(2, "2023-2024");

            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next(), "Rosa deve contenere almeno un calciatore");
            assertEquals("Cristiano", rs.getString("nome_calciatore"));
            assertEquals("Ronaldo", rs.getString("cognome_calciatore"));
            assertEquals(30, rs.getInt("presenze"));
            assertEquals(15, rs.getInt("gol"));
        }
    }

    @Test
    @DisplayName("IT10: Recupero stagioni disponibili per club")
    void testRecuperoStagioniPerClub() throws Exception {
        String sql = "SELECT DISTINCT stagione FROM rose_stagionali WHERE id_club = ? ORDER BY stagione DESC";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, 1);
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next(), "Deve esserci almeno una stagione");
            assertEquals("2023-2024", rs.getString("stagione"));
        }
    }

    @Test
    @DisplayName("IT11: Recupero club inesistente")
    void testRecuperoClubInesistente() throws Exception {
        String sql = "SELECT * FROM clubs WHERE id_club = ?";

        try (Connection conn = TestDatabaseSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, 9999);
            ResultSet rs = stmt.executeQuery();

            assertFalse(rs.next(), "Club inesistente non deve essere trovato");
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
