package com.goalarchive.controller;

import com.goalarchive.dao.*;
import com.goalarchive.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/archivioRose")
public class ControlVisualizzazioneRosa extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ManagerClub managerClub = new ManagerClub();
    private ManagerRosa managerRosa = new ManagerRosa();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idClubStr = request.getParameter("idClub");
        String stagione = request.getParameter("stagione");
        
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
            

            List<String> stagioni = managerClub.getStagioniPerClub(idClub);
            

            if (stagione == null || stagione.trim().isEmpty()) {
                if (!stagioni.isEmpty()) {
                    stagione = stagioni.get(0);
                } else {
                    request.setAttribute("errore", "Nessuna rosa disponibile per questo club.");
                    request.setAttribute("club", club);
                    request.getRequestDispatcher("/archivioRose.jsp").forward(request, response);
                    return;
                }
            }
            

            List<RosaStagionale> rosa = managerRosa.getRosaStagionale(idClub, stagione);
            
            request.setAttribute("club", club);
            request.setAttribute("stagioni", stagioni);
            request.setAttribute("stagioneSelezionata", stagione);
            request.setAttribute("rosa", rosa);
            request.getRequestDispatcher("/archivioRose.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore nel caricamento della rosa: " + e.getMessage());
            request.getRequestDispatcher("/club.jsp").forward(request, response);
        }
    }
}
