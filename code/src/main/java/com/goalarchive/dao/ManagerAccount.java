package com.goalarchive.dao;

import com.goalarchive.model.UtenteRegistrato;
import com.goalarchive.util.PasswordUtil;

import java.sql.*;

public class ManagerAccount {

    public boolean creazioneAccount(UtenteRegistrato utente) throws SQLException {
        String sql = "INSERT INTO UtenteRegistrato (email, nome, cognome, nomeUtente, dataNascita, " +
                    "password, domandaSicurezza, rispostaSicurezza, squadraCuore) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, utente.getEmail());
            stmt.setString(2, utente.getNome());
            stmt.setString(3, utente.getCognome());
            stmt.setString(4, utente.getUsername());
            stmt.setDate(5, new java.sql.Date(utente.getDataNascita().getTime()));
            stmt.setString(6, PasswordUtil.hash(utente.getPassword()));
            stmt.setString(7, utente.getDomandaSicurezza());
            stmt.setString(8, utente.getRispostaSicurezza());
            stmt.setString(9, utente.getSquadraCuore());
            
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }
}
