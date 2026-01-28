package com.goalarchive.dao;

import com.goalarchive.model.Preferito;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerPreferiti {


    public List<Preferito> getPreferitiUtente(String email) throws SQLException {
        List<Preferito> preferiti = new ArrayList<>();
        
        String sql = "SELECT p.*, " +
                    "CASE " +
                    "  WHEN p.tipo = 'club' THEN c.nome " +
                    "  WHEN p.tipo = 'calciatore' THEN CONCAT(cal.nome, ' ', cal.cognome) " +
                    "END AS nome, " +
                    "CASE " +
                    "  WHEN p.tipo = 'club' THEN c.nazione " +
                    "  WHEN p.tipo = 'calciatore' THEN cal.nazionalita " +
                    "END AS descrizione " +
                    "FROM Preferito p " +
                    "LEFT JOIN Club c ON p.tipo = 'club' AND p.idRiferimento = c.idClub " +
                    "LEFT JOIN Calciatore cal ON p.tipo = 'calciatore' AND p.idRiferimento = cal.idCalciatore " +
                    "WHERE p.email = ? " +
                    "ORDER BY p.dataAggiunta DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Preferito preferito = new Preferito();
                preferito.setIdPreferito(rs.getInt("idPreferito"));
                preferito.setEmail(rs.getString("email"));
                preferito.setTipo(rs.getString("tipo"));
                preferito.setIdRiferimento(rs.getInt("idRiferimento"));
                preferito.setDataAggiunta(rs.getTimestamp("dataAggiunta"));
                preferito.setNome(rs.getString("nome"));
                preferito.setDescrizione(rs.getString("descrizione"));
                preferiti.add(preferito);
            }
        }
        return preferiti;
    }


    public List<Preferito> getPreferitiPerTipo(String email, String tipo) throws SQLException {
        List<Preferito> preferiti = new ArrayList<>();
        
        String sql = "SELECT p.*, " +
                    "CASE " +
                    "  WHEN p.tipo = 'club' THEN c.nome " +
                    "  WHEN p.tipo = 'calciatore' THEN CONCAT(cal.nome, ' ', cal.cognome) " +
                    "END AS nome, " +
                    "CASE " +
                    "  WHEN p.tipo = 'club' THEN c.nazione " +
                    "  WHEN p.tipo = 'calciatore' THEN cal.nazionalita " +
                    "END AS descrizione " +
                    "FROM Preferito p " +
                    "LEFT JOIN Club c ON p.tipo = 'club' AND p.idRiferimento = c.idClub " +
                    "LEFT JOIN Calciatore cal ON p.tipo = 'calciatore' AND p.idRiferimento = cal.idCalciatore " +
                    "WHERE p.email = ? AND p.tipo = ? " +
                    "ORDER BY p.dataAggiunta DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, tipo);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Preferito preferito = new Preferito();
                preferito.setIdPreferito(rs.getInt("idPreferito"));
                preferito.setEmail(rs.getString("email"));
                preferito.setTipo(rs.getString("tipo"));
                preferito.setIdRiferimento(rs.getInt("idRiferimento"));
                preferito.setDataAggiunta(rs.getTimestamp("dataAggiunta"));
                preferito.setNome(rs.getString("nome"));
                preferito.setDescrizione(rs.getString("descrizione"));
                preferiti.add(preferito);
            }
        }
        return preferiti;
    }


    public boolean aggiungiPreferito(String email, String tipo, int idRiferimento) throws SQLException {
        // Verifica se esiste già
        if (esistePreferito(email, tipo, idRiferimento)) {
            return false;
        }
        
        String sql = "INSERT INTO Preferito (email, tipo, idRiferimento) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, tipo);
            stmt.setInt(3, idRiferimento);
            
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean rimuoviPreferito(String email, String tipo, int idRiferimento) throws SQLException {
        String sql = "DELETE FROM Preferito WHERE email = ? AND tipo = ? AND idRiferimento = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, tipo);
            stmt.setInt(3, idRiferimento);
            
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean esistePreferito(String email, String tipo, int idRiferimento) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Preferito WHERE email = ? AND tipo = ? AND idRiferimento = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, tipo);
            stmt.setInt(3, idRiferimento);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }


    public int contaPreferiti(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Preferito WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
}
