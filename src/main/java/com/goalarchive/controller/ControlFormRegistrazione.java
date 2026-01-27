package com.goalarchive.controller;

import com.goalarchive.dao.*;
import com.goalarchive.model.UtenteRegistrato;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/registraDati")
public class ControlFormRegistrazione extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ManagerUtente managerUtente = new ManagerUtente();
    private ManagerAccount managerAccount = new ManagerAccount();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String nomeUtente = request.getParameter("nomeUtente");
        String dataNascitaStr = request.getParameter("dataNascita");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String domandaSicurezza = request.getParameter("domandaSicurezza");
        String rispostaSicurezza = request.getParameter("rispostaSicurezza");
        String squadraCuore = request.getParameter("squadraCuore");

        try {

            if (nome == null || nome.trim().isEmpty() ||
                    cognome == null || cognome.trim().isEmpty() ||
                    nomeUtente == null || nomeUtente.trim().isEmpty() ||
                    email == null || email.trim().isEmpty() ||
                    password == null || password.trim().isEmpty() ||
                    dataNascitaStr == null || dataNascitaStr.trim().isEmpty() ||
                    domandaSicurezza == null || domandaSicurezza.trim().isEmpty() ||
                    rispostaSicurezza == null || rispostaSicurezza.trim().isEmpty()) {

                request.setAttribute("errore", "Errore Campo Vuoto: Tutti i campi obbligatori devono essere compilati.");
                request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
                return;
            }


            if (nome.length() < 3) {
                request.setAttribute("errore", "Il nome deve contenere almeno 3 caratteri.");
                request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
                return;
            }

            if (cognome.length() < 3) {
                request.setAttribute("errore", "Il cognome deve contenere almeno 3 caratteri.");
                request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
                return;
            }

            if (password.length() < 6) {
                request.setAttribute("errore", "La password deve contenere almeno 6 caratteri.");
                request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dataNascita = sdf.parse(dataNascitaStr);

            Calendar oggi = Calendar.getInstance();
            Calendar dataNascitaCal = Calendar.getInstance();
            dataNascitaCal.setTime(dataNascita);

            int eta = oggi.get(Calendar.YEAR) - dataNascitaCal.get(Calendar.YEAR);

            if (oggi.get(Calendar.DAY_OF_YEAR) < dataNascitaCal.get(Calendar.DAY_OF_YEAR)) {
                eta--;
            }

            if (eta < 13) {
                request.setAttribute("errore", "Devi avere almeno 13 anni per registrarti.");
                request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
                return;
            }

            if (managerUtente.emailEsiste(email)) {
                request.setAttribute("errore", "Errore Dati Esistenti: L'email è già registrata.");
                request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
                return;
            }

            UtenteRegistrato utente = new UtenteRegistrato(
                    email, nome, cognome, nomeUtente, dataNascita, password,
                    domandaSicurezza, rispostaSicurezza, squadraCuore
            );

            boolean successo = managerAccount.creazioneAccount(utente);

            if (successo) {
                request.setAttribute("messaggio", "Registrazione avvenuta con successo! Effettua il login.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                request.setAttribute("errore", "Errore durante la registrazione. Riprova.");
                request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore del sistema: " + e.getMessage());
            request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
        }
    }
}
