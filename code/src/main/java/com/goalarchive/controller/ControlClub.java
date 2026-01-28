package com.goalarchive.controller;

import com.goalarchive.dao.ManagerClub;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import com.goalarchive.model.Club;

/**
 * ControlClub - Mostra selezione nazioni e club (UC5, UC6)
 */
@WebServlet("/club")
public class ControlClub extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ManagerClub managerClub = new ManagerClub();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String nazione = request.getParameter("nazione");
        
        try {
            if (nazione == null || nazione.trim().isEmpty()) {
                // Mostra lista nazioni
                List<String> nazioni = managerClub.getNazioni();
                request.setAttribute("nazioni", nazioni);
                request.getRequestDispatcher("/club.jsp").forward(request, response);
            } else {
                // Mostra club per nazione selezionata
                List<Club> clubs = managerClub.getClubPerNazione(nazione);
                request.setAttribute("nazione", nazione);
                request.setAttribute("clubs", clubs);
                request.getRequestDispatcher("/clubLista.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore nel caricamento dei club: " + e.getMessage());
            request.getRequestDispatcher("/club.jsp").forward(request, response);
        }
    }

}
