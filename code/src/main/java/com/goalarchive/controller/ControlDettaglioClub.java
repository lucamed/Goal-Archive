package com.goalarchive.controller;

import com.goalarchive.dao.ManagerClub;
import com.goalarchive.model.Club;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

/**
 * ControlDettaglioClub - Mostra pagina principale del club
 */
@WebServlet("/dettaglioClub")
public class ControlDettaglioClub extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ManagerClub managerClub = new ManagerClub();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idClubStr = request.getParameter("idClub");
        
        try {
            if (idClubStr == null || idClubStr.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/club");
                return;
            }
            
            int idClub = Integer.parseInt(idClubStr);
            Club club = managerClub.getClubById(idClub);
            
            if (club == null) {
                request.setAttribute("errore", "Club non trovato.");
                request.getRequestDispatcher("/club.jsp").forward(request, response);
                return;
            }
            
            // Ottieni stagioni disponibili
            List<String> stagioni = managerClub.getStagioniPerClub(idClub);
            
            request.setAttribute("club", club);
            request.setAttribute("stagioni", stagioni);
            request.getRequestDispatcher("/dettaglioClub.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore nel caricamento del club: " + e.getMessage());
            request.getRequestDispatcher("/club.jsp").forward(request, response);
        }
    }
}
