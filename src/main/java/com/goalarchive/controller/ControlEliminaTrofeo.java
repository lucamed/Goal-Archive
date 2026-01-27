package com.goalarchive.controller;

import com.goalarchive.dao.*;
import com.goalarchive.model.*;
import com.goalarchive.util.LogFile;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/admin/eliminaTrofeo")
public class ControlEliminaTrofeo extends HttpServlet {

    private ManagerPalmares managerPalmares = new ManagerPalmares();
    private ManagerClub managerClub = new ManagerClub();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verifica autenticazione admin
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Utente utente = (Utente) session.getAttribute("utente");
        if (!"admin".equals(utente.getRuolo())) {
            response.sendRedirect(request.getContextPath() + "/home.jsp");
            return;
        }

        try {
            // Recupera parametri
            int idClub = Integer.parseInt(request.getParameter("idClub"));
            int anno = Integer.parseInt(request.getParameter("anno"));
            int idCompetizione = Integer.parseInt(request.getParameter("idCompetizione"));
            String motivo = request.getParameter("motivo");

            // Validazione motivo
            if (motivo == null || motivo.trim().isEmpty()) {
                session.setAttribute("errore", "Il motivo dell'eliminazione è obbligatorio.");
                response.sendRedirect(request.getContextPath() + "/palmares?idClub=" + idClub);
                return;
            }

            // Recupera info del trofeo prima di eliminarlo (per il log)
            Trofeo trofeo = managerPalmares.getTrofeoById(idClub, anno, idCompetizione);
            Club club = managerClub.getClubById(idClub);

            if (trofeo == null || club == null) {
                session.setAttribute("errore", "Trofeo non trovato.");
                response.sendRedirect(request.getContextPath() + "/palmares?idClub=" + idClub);
                return;
            }

            // Elimina il trofeo
            boolean successo = managerPalmares.eliminaTrofeo(idClub, anno, idCompetizione);

            if (successo) {
                // Log dell'eliminazione
                String descrizione = String.format(
                        "Club: %s | Competizione: %s | Anno: %d",
                        club.getNome(),
                        trofeo.getNomeCompetizione(),
                        anno
                );

                LogFile.scriviModifica(
                        utente.getUsername(),
                        "ELIMINAZIONE TROFEO",
                        descrizione,
                        motivo
                );

                session.setAttribute("successo", "Trofeo eliminato con successo!");
            } else {
                session.setAttribute("errore", "Errore durante l'eliminazione del trofeo.");
            }

            response.sendRedirect(request.getContextPath() + "/palmares?idClub=" + idClub);

        } catch (NumberFormatException e) {
            session.setAttribute("errore", "Parametri non validi.");
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        } catch (Exception e) {
            e.printStackTrace();
            LogFile.error("Errore in ControlEliminaTrofeo: " + e.getMessage());
            session.setAttribute("errore", "Errore del sistema: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect to dashboard if accessed via GET
        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
    }
}
