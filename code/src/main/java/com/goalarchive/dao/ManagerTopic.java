package com.goalarchive.dao;

import com.goalarchive.model.Topic;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerTopic {

    public List<Topic> getAllTopics() throws SQLException {
        List<Topic> topics = new ArrayList<>();
        
        String sql = "SELECT t.*, COUNT(c.idCommento) AS numeroCommenti " +
                    "FROM Topic t " +
                    "LEFT JOIN Commento c ON t.idTopic = c.idTopic " +
                    "GROUP BY t.idTopic " +
                    "ORDER BY t.dataCreazione DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setIdTopic(rs.getInt("idTopic"));
                topic.setTitolo(rs.getString("titolo"));
                topic.setDescrizione(rs.getString("descrizione"));
                topic.setDataCreazione(rs.getTimestamp("dataCreazione"));
                topic.setNumeroCommenti(rs.getInt("numeroCommenti"));
                topics.add(topic);
            }
        }
        return topics;
    }

    public Topic getTopicById(int idTopic) throws SQLException {
        String sql = "SELECT * FROM Topic WHERE idTopic = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idTopic);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Topic topic = new Topic();
                topic.setIdTopic(rs.getInt("idTopic"));
                topic.setTitolo(rs.getString("titolo"));
                topic.setDescrizione(rs.getString("descrizione"));
                topic.setDataCreazione(rs.getTimestamp("dataCreazione"));
                return topic;
            }
        }
        return null;
    }
}
