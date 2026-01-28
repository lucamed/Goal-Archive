package com.goalarchive.dao;

import com.goalarchive.model.RosaStagionale;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerRosa {

    public List<RosaStagionale> getRosaStagionale(int idClub, String stagione) throws SQLException {
        List<RosaStagionale> rosa = new ArrayList<>();
        
        String sql = "SELECT r.*, c.nome, c.cognome, c.ruolo, c.nazionalita " +
                    "FROM RosaStagionale r " +
                    "JOIN Calciatore c ON r.idCalciatore = c.idCalciatore " +
                    "WHERE r.idClub = ? AND r.stagione = ? " +
                    "ORDER BY " +
                    "CASE c.ruolo " +
                    "  WHEN 'Portiere' THEN 1 " +
                    "  WHEN 'Difensore' THEN 2 " +
                    "  WHEN 'Centrocampista' THEN 3 " +
                    "  WHEN 'Attaccante' THEN 4 " +
                    "END, c.cognome";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idClub);
            stmt.setString(2, stagione);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                RosaStagionale giocatore = new RosaStagionale();
                giocatore.setIdRosaStagionale(rs.getInt("idRosaStagionale"));
                giocatore.setIdClub(rs.getInt("idClub"));
                giocatore.setIdCalciatore(rs.getInt("idCalciatore"));
                giocatore.setStagione(rs.getString("stagione"));
                giocatore.setPresenze(rs.getInt("presenze"));
                giocatore.setGol(rs.getInt("gol"));
                
                // Dati dal JOIN con Calciatore
                giocatore.setNomeCalciatore(rs.getString("nome"));
                giocatore.setCognomeCalciatore(rs.getString("cognome"));
                giocatore.setRuoloCalciatore(rs.getString("ruolo"));
                giocatore.setNazionalitaCalciatore(rs.getString("nazionalita"));
                
                rosa.add(giocatore);
            }
        }
        return rosa;
    }
}
