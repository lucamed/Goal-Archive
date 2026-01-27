package com.goalarchive.controller;

import com.goalarchive.dao.ManagerUtente;
import com.goalarchive.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/autenticazione")
public class ControlFormLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ManagerUtente managerUtente = new ManagerUtente();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String emailOrUsername = request.getParameter("emailOrUsername");
        String password = request.getParameter("password");
        
        try {
            // 🔴 Controllo campi vuoti
            if (emailOrUsername == null || emailOrUsername.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
                
                request.setAttribute("errore", "Errore Campo Vuoto: Email/Username e Password sono obbligatori.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            
            // 🔴 Verifica credenziali
            Utente utente = managerUtente.verificaCredenziali(emailOrUsername, password);
            
            if (utente != null) {
                
                HttpSession session = request.getSession();
                session.setAttribute("utente", utente);
                session.setAttribute("ruolo", utente.getRuolo());
                session.setAttribute("nomeUtente", utente.getUsername());
                
                if ("admin".equals(utente.getRuolo())) {
                    response.sendRedirect(request.getContextPath() + "/home.jsp?admin=true");
                } else {
                    response.sendRedirect(request.getContextPath() + "/home.jsp");
                }
            } else {
                
                request.setAttribute("errore", "Errore Credenziali Errate: Email/Username o Password non corretti.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore del sistema: " + e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
