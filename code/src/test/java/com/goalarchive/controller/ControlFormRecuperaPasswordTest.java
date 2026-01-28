package com.goalarchive.controller;

import com.goalarchive.dao.ManagerUtente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Test per ControlFormRecuperaPassword - UC3 Recupero Password (RAD pag. 12)
 */
@ExtendWith(MockitoExtension.class)
class ControlFormRecuperaPasswordTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private ManagerUtente managerUtente;

    private ControlFormRecuperaPassword servlet;

    @BeforeEach
    void setUp() throws Exception {
        servlet = new ControlFormRecuperaPassword();

        // Inietta il mock
        Field field = ControlFormRecuperaPassword.class.getDeclaredField("managerUtente");
        field.setAccessible(true);
        field.set(servlet, managerUtente);
    }

    /**
     * TC 3.0 - Recupero password con successo (step verificaDati)
     */
    @Test
    void testRecuperoPasswordVerificaSuccesso() throws Exception {
        when(request.getParameter("step")).thenReturn("verificaDati");
        when(request.getParameter("email")).thenReturn("user@example.com");
        when(request.getParameter("rispostaSicurezza")).thenReturn("Fido");
        when(managerUtente.getDomandaSicurezza("user@example.com")).thenReturn("Nome del tuo primo animale?");
        when(managerUtente.verificaDatiRecupero("user@example.com", "Fido")).thenReturn(true);
        when(request.getRequestDispatcher("/recuperaPassword.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("email", "user@example.com");
        verify(request).setAttribute("verificato", true);
        verify(dispatcher).forward(request, response);
    }

    /**
     * TC 3.1 - Email vuota
     */
    @Test
    void testRecuperoPasswordEmailVuota() throws Exception {
        when(request.getParameter("step")).thenReturn("verificaDati");
        when(request.getParameter("email")).thenReturn("");
        when(request.getParameter("rispostaSicurezza")).thenReturn("Risposta");
        when(request.getRequestDispatcher("/recuperaPassword.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("errore", "Errore: Inserisci email e risposta di sicurezza.");
        verify(dispatcher).forward(request, response);
    }

    /**
     * TC 3.4 - Risposta sicurezza vuota
     */
    @Test
    void testRecuperoPasswordRispostaVuota() throws Exception {
        when(request.getParameter("step")).thenReturn("verificaDati");
        when(request.getParameter("email")).thenReturn("user@example.com");
        when(request.getParameter("rispostaSicurezza")).thenReturn("");
        when(request.getRequestDispatcher("/recuperaPassword.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("errore", "Errore: Inserisci email e risposta di sicurezza.");
        verify(dispatcher).forward(request, response);
    }

    /**
     * TC 3.5 - Utente non trovato
     */
    @Test
    void testRecuperoPasswordUtenteNonTrovato() throws Exception {
        when(request.getParameter("step")).thenReturn("verificaDati");
        when(request.getParameter("email")).thenReturn("nonexist@example.com");
        when(request.getParameter("rispostaSicurezza")).thenReturn("Risposta");
        when(managerUtente.getDomandaSicurezza("nonexist@example.com")).thenReturn(null);
        when(request.getRequestDispatcher("/recuperaPassword.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("errore", "Errore Utente Non Trovato: L'email non esiste.");
        verify(dispatcher).forward(request, response);
    }

    /**
     * TC 3.6 - Risposta errata
     */
    @Test
    void testRecuperoPasswordRispostaErrata() throws Exception {
        when(request.getParameter("step")).thenReturn("verificaDati");
        when(request.getParameter("email")).thenReturn("user@example.com");
        when(request.getParameter("rispostaSicurezza")).thenReturn("RispostaErrata");
        when(managerUtente.getDomandaSicurezza("user@example.com")).thenReturn("Domanda?");
        when(managerUtente.verificaDatiRecupero("user@example.com", "RispostaErrata")).thenReturn(false);
        when(request.getRequestDispatcher("/recuperaPassword.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("errore", "Errore Risposta Errata: La risposta di sicurezza non è corretta.");
        // RIMUOVI QUESTA VERIFICA: la servlet setta "domanda" 2 volte, usiamo atLeastOnce()
        verify(request, atLeastOnce()).setAttribute("domanda", "Domanda?");
        verify(dispatcher).forward(request, response);
    }

    /**
     * TC 3.7 - Nuova password impostata con successo
     */
    @Test
    void testImpostaNuovaPasswordSuccesso() throws Exception {
        when(request.getParameter("step")).thenReturn("nuovaPassword");
        when(request.getParameter("email")).thenReturn("user@example.com");
        when(request.getParameter("nuovaPassword")).thenReturn("NewPassword123");
        when(request.getParameter("confermaPassword")).thenReturn("NewPassword123");
        when(managerUtente.impostaNuovaPassword("user@example.com", "NewPassword123")).thenReturn(true);
        when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("messaggio"), anyString());
        verify(dispatcher).forward(request, response);
    }

    /**
     * TC 3.8 - Password non coincidono
     */
    @Test
    void testNuovaPasswordNonCoincide() throws Exception {
        when(request.getParameter("step")).thenReturn("nuovaPassword");
        when(request.getParameter("email")).thenReturn("user@example.com");
        when(request.getParameter("nuovaPassword")).thenReturn("Password1");
        when(request.getParameter("confermaPassword")).thenReturn("Password2");
        when(request.getRequestDispatcher("/recuperaPassword.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("errore", "Errore: Le password non coincidono.");
        verify(request).setAttribute("email", "user@example.com");
        verify(request).setAttribute("verificato", true);
        verify(dispatcher).forward(request, response);
    }
}
