package com.goalarchive.controller;

import com.goalarchive.dao.ManagerPreferiti;
import com.goalarchive.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

/**
 * ControlDashboard - UC7 Visualizzazione Dashboard Utente (RAD pag. 17)
 */
@WebServlet("/dashboard")
public class ControlDashboard extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ManagerPreferiti managerPreferiti = new ManagerPreferiti();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Verifica autenticazione
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        Utente utente = (Utente) session.getAttribute("utente");
        
        try {
            // Ottieni preferiti
            List<Preferito> preferitiClub = managerPreferiti.getPreferitiPerTipo(utente.getEmail(), "club");
            List<Preferito> preferitiCalciatori = managerPreferiti.getPreferitiPerTipo(utente.getEmail(), "calciatore");
            int totalePreferiti = managerPreferiti.contaPreferiti(utente.getEmail());
            
            request.setAttribute("utente", utente);
            request.setAttribute("preferitiClub", preferitiClub);
            request.setAttribute("preferitiCalciatori", preferitiCalciatori);
            request.setAttribute("totalePreferiti", totalePreferiti);
            
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore nel caricamento della dashboard: " + e.getMessage());
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
        }
    }
}
