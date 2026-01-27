package com.goalarchive.controller;

import com.goalarchive.dao.ManagerClub;
import com.goalarchive.dao.ManagerRosa;
import com.goalarchive.model.Club;
import com.goalarchive.model.RosaStagionale;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Test per ControlVisualizzazioneRosa - UC5 Visualizzazione Rosa (RAD pag. 15)
 */
@ExtendWith(MockitoExtension.class)
class ControlVisualizzazioneRosaTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private ManagerClub managerClub;

    @Mock
    private ManagerRosa managerRosa;

    private ControlVisualizzazioneRosa servlet;

    @BeforeEach
    void setUp() throws Exception {
        servlet = new ControlVisualizzazioneRosa();

        // Inietta i mock
        Field fieldClub = ControlVisualizzazioneRosa.class.getDeclaredField("managerClub");
        fieldClub.setAccessible(true);
        fieldClub.set(servlet, managerClub);

        Field fieldRosa = ControlVisualizzazioneRosa.class.getDeclaredField("managerRosa");
        fieldRosa.setAccessible(true);
        fieldRosa.set(servlet, managerRosa);
    }

    /**
     * TC 6.0 - Visualizza rosa con successo
     */
    @Test
    void testVisualizzaRosaSuccesso() throws Exception {
        Club club = new Club();
        club.setIdClub(1);
        club.setNome("Juventus");

        List<String> stagioni = new ArrayList<>();
        stagioni.add("2023-2024");
        stagioni.add("2022-2023");

        List<RosaStagionale> rosa = new ArrayList<>();

        when(request.getParameter("idClub")).thenReturn("1");
        when(request.getParameter("stagione")).thenReturn("2023-2024");
        when(managerClub.getClubById(1)).thenReturn(club);
        when(managerClub.getStagioniPerClub(1)).thenReturn(stagioni);
        when(managerRosa.getRosaStagionale(1, "2023-2024")).thenReturn(rosa);
        when(request.getRequestDispatcher("/archivioRose.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("club"), any(Club.class));
        verify(request).setAttribute("stagioni", stagioni);
        verify(request).setAttribute("stagioneSelezionata", "2023-2024");
        verify(request).setAttribute("rosa", rosa);
        verify(dispatcher).forward(request, response);
    }

    /**
     * TC 6.1 - ID club non valido (vuoto)
     */
    @Test
    void testVisualizzaRosaIdClubVuoto() throws Exception {
        when(request.getParameter("idClub")).thenReturn("");
        when(request.getContextPath()).thenReturn("/goalarchive");

        servlet.doGet(request, response);

        verify(response).sendRedirect("/goalarchive/club");
    }

    /**
     * TC 6.2 - Club non trovato
     */
    @Test
    void testVisualizzaRosaClubNonTrovato() throws Exception {
        when(request.getParameter("idClub")).thenReturn("999");
        when(managerClub.getClubById(999)).thenReturn(null);
        when(request.getContextPath()).thenReturn("/goalarchive");

        servlet.doGet(request, response);

        verify(request).setAttribute("errore", "Club non trovato.");
        verify(response).sendRedirect("/goalarchive/club");
    }

    /**
     * TC 6.3 - Nessuna stagione disponibile
     */
    @Test
    void testVisualizzaRosaNessunaStagione() throws Exception {
        Club club = new Club();
        club.setIdClub(1);
        club.setNome("Juventus");

        List<String> stagioni = new ArrayList<>();

        when(request.getParameter("idClub")).thenReturn("1");
        when(request.getParameter("stagione")).thenReturn("");
        when(managerClub.getClubById(1)).thenReturn(club);
        when(managerClub.getStagioniPerClub(1)).thenReturn(stagioni);
        when(request.getRequestDispatcher("/archivioRose.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("errore", "Nessuna rosa disponibile per questo club.");
        verify(request).setAttribute(eq("club"), any(Club.class));
        verify(dispatcher).forward(request, response);
    }
}
