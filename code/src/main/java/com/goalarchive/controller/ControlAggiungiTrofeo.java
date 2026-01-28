package com.goalarchive.controller;

import com.goalarchive.dao.*;
import com.goalarchive.model.*;
import com.goalarchive.util.LogFile;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

/**
 * ControlAggiungiTrofeo - UC11 Aggiungi Trofeo (RAD pag. 20)
 * Gestisce l'aggiunta di trofei al palmares di un club da parte dell'amministratore.
 * Le modifiche vengono registrate nel file logs/modifiche.txt
 */
@WebServlet("/admin/aggiungiTrofeo")
public class ControlAggiungiTrofeo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ManagerClub managerClub = new ManagerClub();
    private ManagerPalmares managerPalmares = new ManagerPalmares();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verifica autenticazione admin
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
            // Carica nazioni, club e competizioni per i dropdown
            List<String> nazioni = managerClub.getNazioni();
            List<Competizione> competizioni = managerPalmares.getTutteCompetizioni();

            request.setAttribute("nazioni", nazioni);
            request.setAttribute("competizioni", competizioni);
            request.getRequestDispatcher("/admin/aggiungiTrofeo.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            LogFile.error("Errore in doGet di ControlAggiungiTrofeo: " + e.getMessage());
            request.setAttribute("errore", "Errore nel caricamento della pagina: " + e.getMessage());
            request.getRequestDispatcher("/admin/aggiungiTrofeo.jsp").forward(request, response);
        }
    }

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
            response.sendRedirect(request.getContextPath() + "/home.jsp?errore=accesso_negato");
            return;
        }

        String idClubStr = request.getParameter("idClub");
        String annoStr = request.getParameter("anno");
        String idCompetizioneStr = request.getParameter("idCompetizione");
        String motivo = request.getParameter("motivo");

        try {
            // Validazione (UC11 - Eccezione: Errore Campo Vuoto)
            if (idClubStr == null || idClubStr.trim().isEmpty() ||
                    annoStr == null || annoStr.trim().isEmpty() ||
                    idCompetizioneStr == null || idCompetizioneStr.trim().isEmpty()) {

                request.setAttribute("errore", "Errore Campo Vuoto: Tutti i campi sono obbligatori.");
                doGet(request, response);
                return;
            }

            // Validazione motivo (obbligatorio per il log - RAD Scenario 5)
            if (motivo == null || motivo.trim().isEmpty()) {
                request.setAttribute("errore", "Il motivo della modifica è obbligatorio.");
                doGet(request, response);
                return;
            }

            int idClub = Integer.parseInt(idClubStr);
            int anno = Integer.parseInt(annoStr);
            int idCompetizione = Integer.parseInt(idCompetizioneStr);

            // Aggiungi trofeo nel database
            boolean successo = managerPalmares.aggiungiTrofeo(idClub, anno, idCompetizione);

            if (successo) {
                // Recupera il nome del club per il log
                Club club = managerClub.getClubById(idClub);
                String nomeClub = (club != null) ? club.getNome() : "Club ID " + idClub;

                // Scrivi nel file logs/modifiche.txt
                String username = utente.getUsername();
                String tipoModifica = "AGGIUNTA TROFEO";
                String descrizione = String.format("Club: %s | ID Competizione: %d | Anno: %d",
                        nomeClub, idCompetizione, anno);

                LogFile.scriviModifica(username, tipoModifica, descrizione, motivo);

                // Redirect con successo
                response.sendRedirect(request.getContextPath() + "/admin/aggiungiTrofeo?successo=true");

            } else {
                request.setAttribute("errore", "Errore durante l'inserimento del trofeo nel database.");
                doGet(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("errore", "Errore: Anno o ID non validi.");
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            LogFile.error("Errore in doPost di ControlAggiungiTrofeo: " + e.getMessage());
            request.setAttribute("errore", "Errore del sistema: " + e.getMessage());
            doGet(request, response);
        }
    }
}
