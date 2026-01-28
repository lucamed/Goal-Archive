package com.goalarchive.controller;

import com.goalarchive.dao.ManagerTopic;
import com.goalarchive.model.Topic;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

/**
 * ControlApprofondimenti - Mostra lista topic
 */
@WebServlet("/approfondimenti")
public class ControlApprofondimenti extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ManagerTopic managerTopic = new ManagerTopic();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            List<Topic> topics = managerTopic.getAllTopics();
            request.setAttribute("topics", topics);
            request.getRequestDispatcher("/approfondimenti.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore nel caricamento degli approfondimenti: " + e.getMessage());
            request.getRequestDispatcher("/approfondimenti.jsp").forward(request, response);
        }
    }
}
