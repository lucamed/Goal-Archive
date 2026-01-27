package com.goalarchive.controller;

import com.goalarchive.dao.ManagerUtente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/verificaRecupero")
public class ControlFormRecuperaPassword extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ManagerUtente managerUtente = new ManagerUtente();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String step = request.getParameter("step");
        
        try {
            if ("verificaDati".equals(step)) {
            	
                String email = request.getParameter("email");
                String rispostaSicurezza = request.getParameter("rispostaSicurezza");
                
                if (email == null || email.trim().isEmpty() ||
                    rispostaSicurezza == null || rispostaSicurezza.trim().isEmpty()) {
                    
                    request.setAttribute("errore", "Errore: Inserisci email e risposta di sicurezza.");
                    request.getRequestDispatcher("/recuperaPassword.jsp").forward(request, response);
                    return;
                }
                
                String domanda = managerUtente.getDomandaSicurezza(email);
                
                if (domanda == null) {
                	
                    request.setAttribute("errore", "Errore Utente Non Trovato: L'email non esiste.");
                    request.getRequestDispatcher("/recuperaPassword.jsp").forward(request, response);
                    return;
                }
                
                request.setAttribute("domanda", domanda);
                
                boolean verificato = managerUtente.verificaDatiRecupero(email, rispostaSicurezza);
                
                if (verificato) {
                    request.setAttribute("email", email);
                    request.setAttribute("verificato", true);
                    request.getRequestDispatcher("/recuperaPassword.jsp").forward(request, response);
                } else {
                	
                    request.setAttribute("errore", "Errore Risposta Errata: La risposta di sicurezza non è corretta.");
                    request.setAttribute("domanda", domanda);
                    request.getRequestDispatcher("/recuperaPassword.jsp").forward(request, response);
                }
                
            } else if ("nuovaPassword".equals(step)) {
            	
                String email = request.getParameter("email");
                String nuovaPassword = request.getParameter("nuovaPassword");
                String confermaPassword = request.getParameter("confermaPassword");
                
                if (nuovaPassword == null || nuovaPassword.trim().isEmpty() ||
                    !nuovaPassword.equals(confermaPassword)) {
                    
                    request.setAttribute("errore", "Errore: Le password non coincidono.");
                    request.setAttribute("email", email);
                    request.setAttribute("verificato", true);
                    request.getRequestDispatcher("/recuperaPassword.jsp").forward(request, response);
                    return;
                }
                
                boolean aggiornata = managerUtente.impostaNuovaPassword(email, nuovaPassword);
                
                if (aggiornata) {
                    request.setAttribute("messaggio", "Password aggiornata con successo! Effettua il login.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("errore", "Errore durante l'aggiornamento della password.");
                    request.getRequestDispatcher("/recuperaPassword.jsp").forward(request, response);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore del sistema: " + e.getMessage());
            request.getRequestDispatcher("/recuperaPassword.jsp").forward(request, response);
        }
    }
}
