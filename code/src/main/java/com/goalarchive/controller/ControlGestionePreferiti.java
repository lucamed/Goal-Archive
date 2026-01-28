package com.goalarchive.controller;

import com.goalarchive.dao.ManagerPreferiti;
import com.goalarchive.model.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/gestionePreferiti")
public class ControlGestionePreferiti extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ManagerPreferiti managerPreferiti = new ManagerPreferiti();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Utente utente = (Utente) session.getAttribute("utente");
        String azione = request.getParameter("azione");
        String tipo = request.getParameter("tipo");
        String idRiferimentoStr = request.getParameter("idRiferimento");


        System.out.println("===== DEBUG ControlGestionePreferiti =====");
        System.out.println("Azione: " + azione);
        System.out.println("Tipo: " + tipo);
        System.out.println("ID Riferimento: " + idRiferimentoStr);

        try {

            if (idRiferimentoStr == null || tipo == null || azione == null) {
                System.out.println("ERRORE: Parametri mancanti!");
                response.sendRedirect(request.getContextPath() + "/dashboard?errore=parametri");
                return;
            }

            int idRiferimento = Integer.parseInt(idRiferimentoStr);
            boolean successo = false;


            if ("aggiungi".equals(azione)) {
                successo = managerPreferiti.aggiungiPreferito(utente.getEmail(), tipo, idRiferimento);
            } else if ("rimuovi".equals(azione)) {
                successo = managerPreferiti.rimuoviPreferito(utente.getEmail(), tipo, idRiferimento);
            }

            System.out.println("Operazione riuscita: " + successo);


            String referer = request.getHeader("Referer");

            System.out.println("Referer: " + referer);

            if (referer != null && !referer.isEmpty()) {

                String baseUrl = referer.split("\\?")[0];


                String queryString = "";
                if (referer.contains("?")) {
                    queryString = referer.substring(referer.indexOf("?") + 1);

                    queryString = queryString.replaceAll("&?preferito=(ok|errore)", "");
                }

                String finalUrl = baseUrl;
                if (!queryString.isEmpty()) {
                    finalUrl += "?" + queryString + "&preferito=" + (successo ? "ok" : "errore");
                } else {
                    finalUrl += "?preferito=" + (successo ? "ok" : "errore");
                }

                System.out.println("Redirect verso: " + finalUrl);
                response.sendRedirect(finalUrl);
            } else {
                System.out.println("Nessun Referer, redirect alla dashboard");
                response.sendRedirect(request.getContextPath() + "/dashboard");
            }

        } catch (NumberFormatException e) {
            System.out.println("ERRORE: ID non valido");
            e.printStackTrace();


            String referer = request.getHeader("Referer");
            if (referer != null) {
                response.sendRedirect(referer + "?preferito=errore");
            } else {
                response.sendRedirect(request.getContextPath() + "/dashboard?errore=id_invalido");
            }
        } catch (Exception e) {
            System.out.println("ERRORE: Eccezione generica");
            e.printStackTrace();


            String referer = request.getHeader("Referer");
            if (referer != null) {
                response.sendRedirect(referer + "?preferito=errore");
            } else {
                response.sendRedirect(request.getContextPath() + "/dashboard?errore=" + e.getMessage());
            }
        }
    }
}
