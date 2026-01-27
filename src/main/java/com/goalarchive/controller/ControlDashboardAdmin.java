package com.goalarchive.controller;

import com.goalarchive.model.Utente;
import com.goalarchive.util.LogFile;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * ControlDashboardAdmin - Dashboard amministratore
 * Mostra statistiche e ultimi log dal file logs/modifiche.txt
 */
@WebServlet("/admin/dashboard")
public class ControlDashboardAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verifica autenticazione e ruolo admin
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Utente utente = (Utente) session.getAttribute("utente");
        if (!"admin".equals(utente.getRuolo())) {
            response.sendRedirect(request.getContextPath() + "/home.jsp?errore=accesso_negato");
            return;
        }

        try {
            // Leggi le ultime righe del log (opzionale)
            String tuttoIlLog = LogFile.leggiModifiche();

            // Prendi solo le ultime 5 righe
            String[] righe = tuttoIlLog.split("\n");
            StringBuilder ultimeRighe = new StringBuilder();
            int start = Math.max(0, righe.length - 5);
            for (int i = start; i < righe.length; i++) {
                ultimeRighe.append(righe[i]).append("\n");
            }

            request.setAttribute("ultimiLog", ultimeRighe.toString());
            request.getRequestDispatcher("/admin/dashboardAdmin.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            LogFile.error("Errore in ControlDashboardAdmin: " + e.getMessage());
            request.setAttribute("errore", "Errore nel caricamento della dashboard: " + e.getMessage());
            request.getRequestDispatcher("/admin/dashboardAdmin.jsp").forward(request, response);
        }
    }
}
