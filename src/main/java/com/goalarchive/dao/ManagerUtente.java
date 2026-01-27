package com.goalarchive.dao;

import com.goalarchive.model.*;
import com.goalarchive.util.PasswordUtil;

import java.sql.*;

public class ManagerUtente {

    public Utente verificaCredenziali(String emailOrUsername, String password) throws SQLException {
        String sql = "SELECT * FROM UtenteRegistrato WHERE (email = ? OR nomeUtente = ?) AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, emailOrUsername);
            stmt.setString(2, emailOrUsername);
            stmt.setString(3, PasswordUtil.hash(password));
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                UtenteRegistrato utente = new UtenteRegistrato();
                utente.setEmail(rs.getString("email"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setUsername(rs.getString("nomeUtente"));
                utente.setDataNascita(rs.getDate("dataNascita"));
                utente.setPassword(rs.getString("password"));
                utente.setRuolo(rs.getString("ruolo"));
                utente.setDomandaSicurezza(rs.getString("domandaSicurezza"));
                utente.setRispostaSicurezza(rs.getString("rispostaSicurezza"));
                utente.setDataRegistrazione(rs.getTimestamp("dataRegistrazione"));
                utente.setSquadraCuore(rs.getString("squadraCuore"));
                return utente;
            }
        }
        
        sql = "SELECT * FROM Amministratore WHERE (email = ? OR nomeUtente = ?) AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, emailOrUsername);
            stmt.setString(2, emailOrUsername);
            stmt.setString(3, PasswordUtil.hash(password));
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Amministratore admin = new Amministratore();
                admin.setEmail(rs.getString("email"));
                admin.setNome(rs.getString("nome"));
                admin.setCognome(rs.getString("cognome"));
                admin.setUsername(rs.getString("nomeUtente"));
                admin.setDataNascita(rs.getDate("dataNascita"));
                admin.setPassword(rs.getString("password"));
                admin.setRuolo(rs.getString("ruolo"));
                admin.setLivelloAccesso(rs.getInt("livelloAccesso"));
                admin.setDomandaSicurezza(rs.getString("domandaSicurezza"));
                admin.setRispostaSicurezza(rs.getString("rispostaSicurezza"));
                return admin;
            }
        }
        
        return null;
    }

    public boolean emailEsiste(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM UtenteRegistrato WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) return true;
        }
        
        sql = "SELECT COUNT(*) FROM Amministratore WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) return true;
        }
        
        return false;
    }

    public boolean verificaDatiRecupero(String email, String rispostaSicurezza) throws SQLException {
        String sql = "SELECT * FROM UtenteRegistrato WHERE email = ? AND rispostaSicurezza = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, rispostaSicurezza);
            
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public boolean impostaNuovaPassword(String email, String nuovaPassword) throws SQLException {
        String sql = "UPDATE UtenteRegistrato SET password = ? WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, PasswordUtil.hash(nuovaPassword)); 
            stmt.setString(2, email);
            
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public String getDomandaSicurezza(String email) throws SQLException {
        String sql = "SELECT domandaSicurezza FROM UtenteRegistrato WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("domandaSicurezza");
            }
        }
        return null;
    }
}
