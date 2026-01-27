package com.goalarchive.dao;

import com.goalarchive.model.Club;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerClub {

    public List<String> getNazioni() throws SQLException {
        List<String> nazioni = new ArrayList<>();
        String sql = "SELECT DISTINCT nazione FROM Club ORDER BY nazione";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                nazioni.add(rs.getString("nazione"));
            }
        }
        return nazioni;
    }

    public List<Club> getClubPerNazione(String nazione) throws SQLException {
        List<Club> clubs = new ArrayList<>();
        String sql = "SELECT * FROM Club WHERE nazione = ? ORDER BY nome";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nazione);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Club club = new Club();
                club.setIdClub(rs.getInt("idClub"));
                club.setNome(rs.getString("nome"));
                club.setNazione(rs.getString("nazione"));
                club.setCampionato(rs.getString("campionato"));
                clubs.add(club);
            }
        }
        return clubs;
    }


    public Club getClubById(int idClub) throws SQLException {
        String sql = "SELECT * FROM Club WHERE idClub = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idClub);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Club club = new Club();
                club.setIdClub(rs.getInt("idClub"));
                club.setNome(rs.getString("nome"));
                club.setNazione(rs.getString("nazione"));
                club.setCampionato(rs.getString("campionato"));
                club.setAnnoFondazione(rs.getInt("annoFondazione"));
                club.setStadio(rs.getString("stadio"));
                return club;
            }
        }
        return null;
    }

    public List<String> getStagioniPerClub(int idClub) throws SQLException {
        List<String> stagioni = new ArrayList<>();
        String sql = "SELECT DISTINCT stagione FROM RosaStagionale WHERE idClub = ? ORDER BY stagione DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idClub);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                stagioni.add(rs.getString("stagione"));
            }
        }
        return stagioni;
    }
}
