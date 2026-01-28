package com.goalarchive.controller;

import com.goalarchive.dao.ManagerCommenti;
import com.goalarchive.model.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/pubblicaCommento")
public class ControlPubblicaCommento extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ManagerCommenti managerCommenti = new ManagerCommenti();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        Utente utente = (Utente) session.getAttribute("utente");
        String idTopicStr = request.getParameter("idTopic");
        String testo = request.getParameter("testo");
        
        try {
            if (idTopicStr == null || testo == null || testo.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/topic?id=" + idTopicStr + "&errore=campo_vuoto");
                return;
            }

            if (testo.trim().length() < 10) {
                response.sendRedirect(request.getContextPath() + "/topic?id=" + idTopicStr + "&errore=lunghezza_minima");
                return;
            }
            
            int idTopic = Integer.parseInt(idTopicStr);
            
            boolean successo = managerCommenti.aggiungiCommento(idTopic, utente.getEmail(), testo.trim());
            
            if (successo) {
                response.sendRedirect(request.getContextPath() + "/topic?id=" + idTopic + "&commento=ok");
            } else {
                response.sendRedirect(request.getContextPath() + "/topic?id=" + idTopic + "&errore=pubblicazione");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/approfondimenti?errore=" + e.getMessage());
        }
    }
}
