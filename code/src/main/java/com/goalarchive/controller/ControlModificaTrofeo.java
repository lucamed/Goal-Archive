package com.goalarchive.controller;

import com.goalarchive.dao.*;
import com.goalarchive.model.*;
import com.goalarchive.util.LogFile;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/admin/modificaTrofeo")
public class ControlModificaTrofeo extends HttpServlet {

    private ManagerPalmares managerPalmares = new ManagerPalmares();
    private ManagerClub managerClub = new ManagerClub();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            int idClub = Integer.parseInt(request.getParameter("idClub"));
            int anno = Integer.parseInt(request.getParameter("anno"));
            int idCompetizione = Integer.parseInt(request.getParameter("idCompetizione"));

            Trofeo trofeo = managerPalmares.getTrofeoById(idClub, anno, idCompetizione);
            Club club = managerClub.getClubById(idClub);

            request.setAttribute("trofeo", trofeo);
            request.setAttribute("club", club);
            request.getRequestDispatcher("/admin/modificaTrofeo.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/dashboard?errore=trofeo_non_trovato");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            int idClub = Integer.parseInt(request.getParameter("idClub"));
            int annoVecchio = Integer.parseInt(request.getParameter("annoVecchio"));
            int idCompetizione = Integer.parseInt(request.getParameter("idCompetizione"));
            int annoNuovo = Integer.parseInt(request.getParameter("annoNuovo"));
            String motivo = request.getParameter("motivo");

            if (motivo == null || motivo.trim().isEmpty()) {
                request.setAttribute("errore", "Il motivo della modifica è obbligatorio.");
                doGet(request, response);
                return;
            }

            boolean successo = managerPalmares.modificaAnnoTrofeo(idClub, annoVecchio, idCompetizione, annoNuovo);

            if (successo) {
                Club club = managerClub.getClubById(idClub);
                String nomeClub = (club != null) ? club.getNome() : "Club ID " + idClub;

                String descrizione = String.format("Club: %s | ID Competizione: %d | Anno modificato: %d → %d",
                        nomeClub, idCompetizione, annoVecchio, annoNuovo);

                LogFile.scriviModifica(utente.getUsername(), "MODIFICA TROFEO", descrizione, motivo);

                response.sendRedirect(request.getContextPath() + "/palmares?idClub=" + idClub + "&successo=modifica");
            } else {
                request.setAttribute("errore", "Errore durante la modifica del trofeo.");
                doGet(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            LogFile.error("Errore in ControlModificaTrofeo: " + e.getMessage());
            request.setAttribute("errore", "Errore del sistema: " + e.getMessage());
            doGet(request, response);
        }
    }
}
