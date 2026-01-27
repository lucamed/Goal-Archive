package com.goalarchive.integration;

import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDatabaseSetup {

    private static DataSource dataSource;

    public static DataSource getTestDataSource() {
        if (dataSource == null) {
            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MySQL");
            ds.setUser("sa");
            ds.setPassword("");
            dataSource = ds;

            try {
                initializeSchema();
            } catch (SQLException e) {
                throw new RuntimeException("Errore inizializzazione schema", e);
            }
        }
        return dataSource;
    }

    private static void initializeSchema() throws SQLException {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            // Drop tables se esistono
            stmt.execute("DROP TABLE IF EXISTS preferiti");
            stmt.execute("DROP TABLE IF EXISTS trofei");
            stmt.execute("DROP TABLE IF EXISTS rose_stagionali");
            stmt.execute("DROP TABLE IF EXISTS calciatori");
            stmt.execute("DROP TABLE IF EXISTS competizioni");
            stmt.execute("DROP TABLE IF EXISTS clubs");
            stmt.execute("DROP TABLE IF EXISTS utenti");

            // Tabella utenti
            stmt.execute(
                    "CREATE TABLE utenti (" +
                            "    email VARCHAR(100) PRIMARY KEY," +
                            "    nome VARCHAR(50) NOT NULL," +
                            "    cognome VARCHAR(50) NOT NULL," +
                            "    username VARCHAR(50) UNIQUE NOT NULL," +
                            "    data_nascita DATE NOT NULL," +
                            "    password VARCHAR(255) NOT NULL," +
                            "    ruolo VARCHAR(20) DEFAULT 'utente'," +
                            "    data_registrazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                            "    domanda_sicurezza VARCHAR(255)," +
                            "    risposta_sicurezza VARCHAR(255)," +
                            "    squadra_cuore VARCHAR(100)," +
                            "    livello_accesso INT DEFAULT 0" +
                            ")"
            );

            // Tabella clubs
            stmt.execute(
                    "CREATE TABLE clubs (" +
                            "    id_club INT PRIMARY KEY AUTO_INCREMENT," +
                            "    nome VARCHAR(100) NOT NULL," +
                            "    citta VARCHAR(100)," +
                            "    stadio VARCHAR(100)," +
                            "    anno_fondazione INT" +
                            ")"
            );

            // Tabella calciatori
            stmt.execute(
                    "CREATE TABLE calciatori (" +
                            "    id_calciatore INT PRIMARY KEY AUTO_INCREMENT," +
                            "    nome VARCHAR(50) NOT NULL," +
                            "    cognome VARCHAR(50) NOT NULL," +
                            "    data_nascita DATE," +
                            "    nazionalita VARCHAR(50)," +
                            "    ruolo VARCHAR(30)" +
                            ")"
            );

            // Tabella rose_stagionali
            stmt.execute(
                    "CREATE TABLE rose_stagionali (" +
                            "    id_rosa INT PRIMARY KEY AUTO_INCREMENT," +
                            "    id_club INT NOT NULL," +
                            "    id_calciatore INT NOT NULL," +
                            "    stagione VARCHAR(20) NOT NULL," +
                            "    presenze INT DEFAULT 0," +
                            "    gol INT DEFAULT 0," +
                            "    FOREIGN KEY (id_club) REFERENCES clubs(id_club)," +
                            "    FOREIGN KEY (id_calciatore) REFERENCES calciatori(id_calciatore)" +
                            ")"
            );

            // Tabella competizioni
            stmt.execute(
                    "CREATE TABLE competizioni (" +
                            "    id_competizione INT PRIMARY KEY AUTO_INCREMENT," +
                            "    nome VARCHAR(100) NOT NULL," +
                            "    tipo VARCHAR(50)" +
                            ")"
            );

            // Tabella trofei
            stmt.execute(
                    "CREATE TABLE trofei (" +
                            "    id_trofeo INT PRIMARY KEY AUTO_INCREMENT," +
                            "    id_club INT NOT NULL," +
                            "    id_competizione INT NOT NULL," +
                            "    anno INT NOT NULL," +
                            "    FOREIGN KEY (id_club) REFERENCES clubs(id_club)," +
                            "    FOREIGN KEY (id_competizione) REFERENCES competizioni(id_competizione)" +
                            ")"
            );

            // Tabella preferiti
            stmt.execute(
                    "CREATE TABLE preferiti (" +
                            "    id_preferito INT PRIMARY KEY AUTO_INCREMENT," +
                            "    email VARCHAR(100) NOT NULL," +
                            "    tipo VARCHAR(20) NOT NULL," +
                            "    id_riferimento INT NOT NULL," +
                            "    data_aggiunta TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                            "    FOREIGN KEY (email) REFERENCES utenti(email)" +
                            ")"
            );

        }
    }

    public static void insertTestData() throws SQLException {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            // Utente di test
            stmt.execute(
                    "INSERT INTO utenti (email, nome, cognome, username, data_nascita, " +
                            "password, ruolo, domanda_sicurezza, risposta_sicurezza, squadra_cuore) " +
                            "VALUES ('test@example.com', 'Mario', 'Rossi', 'mariorossi', '1990-01-01', " +
                            "'Password123', 'utente', 'Nome animale?', 'Fido', 'Juventus')"
            );

            // Admin di test
            stmt.execute(
                    "INSERT INTO utenti (email, nome, cognome, username, data_nascita, " +
                            "password, ruolo, livello_accesso) " +
                            "VALUES ('admin@example.com', 'Admin', 'User', 'admin', '1985-05-15', " +
                            "'AdminPass123', 'admin', 3)"
            );

            // Club di test
            stmt.execute(
                    "INSERT INTO clubs (nome, citta, stadio, anno_fondazione) " +
                            "VALUES ('Juventus', 'Torino', 'Allianz Stadium', 1897)"
            );

            stmt.execute(
                    "INSERT INTO clubs (nome, citta, stadio, anno_fondazione) " +
                            "VALUES ('Inter', 'Milano', 'San Siro', 1908)"
            );

            // Calciatore di test
            stmt.execute(
                    "INSERT INTO calciatori (nome, cognome, data_nascita, nazionalita, ruolo) " +
                            "VALUES ('Cristiano', 'Ronaldo', '1985-02-05', 'Portogallo', 'Attaccante')"
            );

            // Rosa stagionale (usa ID 1 per club e calciatore)
            stmt.execute(
                    "INSERT INTO rose_stagionali (id_club, id_calciatore, stagione, presenze, gol) " +
                            "VALUES (1, 1, '2023-2024', 30, 15)"
            );

            // Competizione
            stmt.execute(
                    "INSERT INTO competizioni (nome, tipo) " +
                            "VALUES ('Champions League', 'Internazionale')"
            );

        }
    }

    public static void cleanDatabase() throws SQLException {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("DELETE FROM preferiti");
            stmt.execute("DELETE FROM trofei");
            stmt.execute("DELETE FROM rose_stagionali");
            stmt.execute("DELETE FROM calciatori");
            stmt.execute("DELETE FROM clubs");
            stmt.execute("DELETE FROM competizioni");
            stmt.execute("DELETE FROM utenti");

        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
