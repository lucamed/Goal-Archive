package com.goalarchive.controller;

import com.goalarchive.dao.*;
import com.goalarchive.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/palmares")
public class ControlVisualizzazionePalmares extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ManagerClub managerClub = new ManagerClub();
    private ManagerPalmares managerPalmares = new ManagerPalmares();
    
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
                response.sendRedirect(request.getContextPath() + "/club");
                return;
            }
            

            List<Trofeo> trofei = managerPalmares.getPalmares(idClub);
            

            int trofeiNazionali = managerPalmares.contaTrofeiPerTipo(idClub, "Nazionale");
            int trofeiInternazionali = managerPalmares.contaTrofeiPerTipo(idClub, "Internazionale");
            
            request.setAttribute("club", club);
            request.setAttribute("trofei", trofei);
            request.setAttribute("trofeiNazionali", trofeiNazionali);
            request.setAttribute("trofeiInternazionali", trofeiInternazionali);
            request.getRequestDispatcher("/palmares.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore nel caricamento del palmarès: " + e.getMessage());
            request.getRequestDispatcher("/club.jsp").forward(request, response);
        }
    }
}
