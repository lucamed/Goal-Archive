package com.goalarchive.controller;

import com.goalarchive.dao.ManagerUtente;
import com.goalarchive.model.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Test per ControlFormLogin - UC1 Autenticazione (RAD pag. 10)
 */
@ExtendWith(MockitoExtension.class)
class ControlFormLoginTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private ManagerUtente managerUtente;

    private ControlFormLogin servlet;

    @BeforeEach
    void setUp() throws Exception {
        servlet = new ControlFormLogin();

        // Inietta il mock nel campo privato
        Field field = ControlFormLogin.class.getDeclaredField("managerUtente");
        field.setAccessible(true);
        field.set(servlet, managerUtente);
    }

    /**
     * TC 1.0 - Email/Username vuoto
     */
    @Test
    void testLoginEmailUsernameVuoto() throws Exception {
        when(request.getParameter("emailOrUsername")).thenReturn("");
        when(request.getParameter("password")).thenReturn("password123");
        when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("errore", "Errore Campo Vuoto: Email/Username e Password sono obbligatori.");
        verify(dispatcher).forward(request, response);
    }

    /**
     * TC 1.2 - Password vuota
     */
    @Test
    void testLoginPasswordVuota() throws Exception {
        when(request.getParameter("emailOrUsername")).thenReturn("user@example.com");
        when(request.getParameter("password")).thenReturn("");
        when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("errore", "Errore Campo Vuoto: Email/Username e Password sono obbligatori.");
        verify(dispatcher).forward(request, response);
    }

    /**
     * TC 1.4 - Utente non trovato
     */
    @Test
    void testLoginUtenteNonTrovato() throws Exception {
        when(request.getParameter("emailOrUsername")).thenReturn("nonexist@example.com");
        when(request.getParameter("password")).thenReturn("password123");
        when(managerUtente.verificaCredenziali("nonexist@example.com", "password123")).thenReturn(null);
        when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("errore", "Errore Credenziali Errate: Email/Username o Password non corretti.");
        verify(dispatcher).forward(request, response);
    }

    /**
     * TC 1.5 - Password errata
     */
    @Test
    void testLoginPasswordErrata() throws Exception {
        when(request.getParameter("emailOrUsername")).thenReturn("user@example.com");
        when(request.getParameter("password")).thenReturn("wrongpassword");
        when(managerUtente.verificaCredenziali("user@example.com", "wrongpassword")).thenReturn(null);
        when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("errore", "Errore Credenziali Errate: Email/Username o Password non corretti.");
        verify(dispatcher).forward(request, response);
    }

    /**
     * TC 1.6 - Login con successo (utente normale)
     */
    @Test
    void testLoginSuccessoUtenteNormale() throws Exception {
        Utente utente = new Utente();
        utente.setEmail("user@example.com");
        utente.setUsername("testuser");
        utente.setRuolo("user");

        when(request.getParameter("emailOrUsername")).thenReturn("user@example.com");
        when(request.getParameter("password")).thenReturn("password123");
        when(managerUtente.verificaCredenziali("user@example.com", "password123")).thenReturn(utente);
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/goalarchive");

        servlet.doPost(request, response);

        verify(session).setAttribute("utente", utente);
        verify(session).setAttribute("ruolo", "user");
        verify(session).setAttribute("nomeUtente", "testuser");
        verify(response).sendRedirect("/goalarchive/home.jsp");
    }

    /**
     * TC 1.6 - Login con successo (admin)
     */
    @Test
    void testLoginSuccessoAdmin() throws Exception {
        Utente utente = new Utente();
        utente.setEmail("admin@example.com");
        utente.setUsername("admin");
        utente.setRuolo("admin");

        when(request.getParameter("emailOrUsername")).thenReturn("admin@example.com");
        when(request.getParameter("password")).thenReturn("adminpass");
        when(managerUtente.verificaCredenziali("admin@example.com", "adminpass")).thenReturn(utente);
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/goalarchive");

        servlet.doPost(request, response);

        verify(session).setAttribute("utente", utente);
        verify(session).setAttribute("ruolo", "admin");
        verify(session).setAttribute("nomeUtente", "admin");
        verify(response).sendRedirect("/goalarchive/home.jsp?admin=true");
    }
}
