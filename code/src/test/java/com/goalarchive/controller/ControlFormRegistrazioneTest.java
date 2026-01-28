package com.goalarchive.controller;

import com.goalarchive.dao.ManagerAccount;
import com.goalarchive.dao.ManagerUtente;
import com.goalarchive.model.UtenteRegistrato;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Test per ControlFormRegistrazione - UC2 Registrazione (RAD pag. 11)
 */
@ExtendWith(MockitoExtension.class)
class ControlFormRegistrazioneTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private ManagerUtente managerUtente;

    @Mock
    private ManagerAccount managerAccount;

    private ControlFormRegistrazione servlet;

    @BeforeEach
    void setUp() throws Exception {
        servlet = new ControlFormRegistrazione();

        // Inietta i mock
        Field fieldUtente = ControlFormRegistrazione.class.getDeclaredField("managerUtente");
        fieldUtente.setAccessible(true);
        fieldUtente.set(servlet, managerUtente);

        Field fieldAccount = ControlFormRegistrazione.class.getDeclaredField("managerAccount");
        fieldAccount.setAccessible(true);
        fieldAccount.set(servlet, managerAccount);
    }

    /**
     * TC 2.0 - Registrazione con successo
     */
    @Test
    void testRegistrazioneSuccesso() throws Exception {
        when(request.getParameter("nome")).thenReturn("Mario");
        when(request.getParameter("cognome")).thenReturn("Rossi");
        when(request.getParameter("nomeUtente")).thenReturn("mariorossi");
        when(request.getParameter("email")).thenReturn("mario.rossi@example.com");
        when(request.getParameter("password")).thenReturn("Password123");
        when(request.getParameter("dataNascita")).thenReturn("2000-01-01");
        when(request.getParameter("domandaSicurezza")).thenReturn("Nome del tuo primo animale?");
        when(request.getParameter("rispostaSicurezza")).thenReturn("Fido");
        when(request.getParameter("squadraCuore")).thenReturn("Juventus");

        when(managerUtente.emailEsiste("mario.rossi@example.com")).thenReturn(false);
        when(managerAccount.creazioneAccount(any(UtenteRegistrato.class))).thenReturn(true);
        when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("messaggio"), anyString());
        verify(dispatcher).forward(request, response);
    }

    /**
     * TC 2.1 - Nome vuoto
     */
    @Test
    void testRegistrazioneNomeVuoto() throws Exception {
        when(request.getParameter("nome")).thenReturn("");
        when(request.getParameter("cognome")).thenReturn("Rossi");
        when(request.getParameter("nomeUtente")).thenReturn("mariorossi");
        when(request.getParameter("email")).thenReturn("mario.rossi@example.com");
        when(request.getParameter("password")).thenReturn("Password123");
        when(request.getParameter("dataNascita")).thenReturn("2000-01-01");
        when(request.getParameter("domandaSicurezza")).thenReturn("Domanda?");
        when(request.getParameter("rispostaSicurezza")).thenReturn("Risposta");
        when(request.getParameter("squadraCuore")).thenReturn("Juventus");
        when(request.getRequestDispatcher("/registrazione.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("errore", "Errore Campo Vuoto: Tutti i campi obbligatori devono essere compilati.");
        verify(dispatcher).forward(request, response);
    }

    /**
     * TC 2.18 - Email già esistente
     */
    @Test
    void testRegistrazioneEmailEsistente() throws Exception {
        when(request.getParameter("nome")).thenReturn("Mario");
        when(request.getParameter("cognome")).thenReturn("Rossi");
        when(request.getParameter("nomeUtente")).thenReturn("mariorossi2");
        when(request.getParameter("email")).thenReturn("esistente@example.com");
        when(request.getParameter("password")).thenReturn("Password123");
        when(request.getParameter("dataNascita")).thenReturn("2000-01-01");
        when(request.getParameter("domandaSicurezza")).thenReturn("Domanda?");
        when(request.getParameter("rispostaSicurezza")).thenReturn("Risposta");
        when(request.getParameter("squadraCuore")).thenReturn("Juventus");

        when(managerUtente.emailEsiste("esistente@example.com")).thenReturn(true);
        when(request.getRequestDispatcher("/registrazione.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("errore", "Errore Dati Esistenti: L'email è già registrata.");
        verify(dispatcher).forward(request, response);
    }
}
