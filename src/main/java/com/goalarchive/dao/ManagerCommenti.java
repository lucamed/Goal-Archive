package com.goalarchive.dao;

import com.goalarchive.model.Commento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerCommenti {

    public List<Commento> getCommentiPerTopic(int idTopic) throws SQLException {
        List<Commento> commenti = new ArrayList<>();
        
        String sql = "SELECT c.*, u.nomeUtente " +
                    "FROM Commento c " +
                    "JOIN UtenteRegistrato u ON c.email = u.email " +
                    "WHERE c.idTopic = ? " +
                    "ORDER BY c.dataPubblicazione ASC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idTopic);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Commento commento = new Commento();
                commento.setIdCommento(rs.getInt("idCommento"));
                commento.setIdTopic(rs.getInt("idTopic"));
                commento.setEmail(rs.getString("email"));
                commento.setTesto(rs.getString("testo"));
                commento.setDataPubblicazione(rs.getTimestamp("dataPubblicazione"));
                commento.setNomeUtente(rs.getString("nomeUtente"));
                commenti.add(commento);
            }
        }
        return commenti;
    }
    public boolean aggiungiCommento(int idTopic, String email, String testo) throws SQLException {
        String sql = "INSERT INTO Commento (idTopic, email, testo) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idTopic);
            stmt.setString(2, email);
            stmt.setString(3, testo);
            
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }
}
