package com.goalarchive.dao;

import com.goalarchive.model.Trofeo;
import com.goalarchive.model.Competizione;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerPalmares {

    public List<Trofeo> getPalmares(int idClub) throws SQLException {
        List<Trofeo> trofei = new ArrayList<>();

        String sql = "SELECT t.*, c.nome AS nomeCompetizione, c.tipo AS tipoCompetizione " +
                "FROM Trofeo t " +
                "JOIN Competizione c ON t.idCompetizione = c.idCompetizione " +
                "WHERE t.idClub = ? " +
                "ORDER BY c.tipo DESC, c.nome, t.anno DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idClub);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Trofeo trofeo = new Trofeo();
                trofeo.setIdTrofeo(rs.getInt("idTrofeo"));
                trofeo.setIdClub(rs.getInt("idClub"));
                trofeo.setAnno(rs.getInt("anno"));
                trofeo.setIdCompetizione(rs.getInt("idCompetizione"));
                trofeo.setNomeCompetizione(rs.getString("nomeCompetizione"));
                trofeo.setTipoCompetizione(rs.getString("tipoCompetizione"));

                trofei.add(trofeo);
            }
        }
        return trofei;
    }

    public int contaTrofeiPerTipo(int idClub, String tipo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Trofeo t " +
                "JOIN Competizione c ON t.idCompetizione = c.idCompetizione " +
                "WHERE t.idClub = ? AND c.tipo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idClub);
            stmt.setString(2, tipo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
    public boolean aggiungiTrofeo(int idClub, int anno, int idCompetizione) throws SQLException {
        String sql = "INSERT INTO Trofeo (idClub, anno, idCompetizione) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idClub);
            stmt.setInt(2, anno);
            stmt.setInt(3, idCompetizione);

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }


    public List<Competizione> getTutteCompetizioni() throws SQLException {
        List<Competizione> competizioni = new ArrayList<>();
        String sql = "SELECT * FROM Competizione ORDER BY tipo, nome";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Competizione comp = new Competizione();
                comp.setIdCompetizione(rs.getInt("idCompetizione"));
                comp.setNome(rs.getString("nome"));
                comp.setTipo(rs.getString("tipo"));
                comp.setNazione(rs.getString("nazione"));
                competizioni.add(comp);
            }
        }
        return competizioni;
    }

    public boolean modificaAnnoTrofeo(int idClub, int annoVecchio, int idCompetizione, int annoNuovo) throws SQLException {
        String sql = "UPDATE Trofeo SET anno = ? WHERE idClub = ? AND anno = ? AND idCompetizione = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, annoNuovo);
            stmt.setInt(2, idClub);
            stmt.setInt(3, annoVecchio);
            stmt.setInt(4, idCompetizione);

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean eliminaTrofeo(int idClub, int anno, int idCompetizione) throws SQLException {
        String sql = "DELETE FROM Trofeo WHERE idClub = ? AND anno = ? AND idCompetizione = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idClub);
            stmt.setInt(2, anno);
            stmt.setInt(3, idCompetizione);

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public Trofeo getTrofeoById(int idClub, int anno, int idCompetizione) throws SQLException {
        String sql = "SELECT t.*, c.nome as nomeCompetizione " +
                "FROM Trofeo t " +
                "JOIN Competizione c ON t.idCompetizione = c.idCompetizione " +
                "WHERE t.idClub = ? AND t.anno = ? AND t.idCompetizione = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idClub);
            stmt.setInt(2, anno);
            stmt.setInt(3, idCompetizione);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Trofeo trofeo = new Trofeo();
                trofeo.setIdClub(rs.getInt("idClub"));
                trofeo.setAnno(rs.getInt("anno"));
                trofeo.setIdCompetizione(rs.getInt("idCompetizione"));
                trofeo.setNomeCompetizione(rs.getString("nomeCompetizione"));
                return trofeo;
            }
        }
        return null;
    }

}