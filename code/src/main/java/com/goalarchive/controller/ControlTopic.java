package com.goalarchive.controller;

import com.goalarchive.dao.*;
import com.goalarchive.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;


@WebServlet("/topic")
public class ControlTopic extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ManagerTopic managerTopic = new ManagerTopic();
    private ManagerCommenti managerCommenti = new ManagerCommenti();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idTopicStr = request.getParameter("id");

        try {

            if (idTopicStr == null || idTopicStr.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/approfondimenti?errore=id_mancante");
                return;
            }

            int idTopic = Integer.parseInt(idTopicStr);


            Topic topic = managerTopic.getTopicById(idTopic);
            if (topic == null) {
                response.sendRedirect(request.getContextPath() + "/approfondimenti?errore=topic_non_trovato");
                return;
            }


            List<Commento> commenti = managerCommenti.getCommentiPerTopic(idTopic);


            request.setAttribute("topic", topic);
            request.setAttribute("commenti", commenti);


            request.getRequestDispatcher("/topic.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/approfondimenti?errore=id_invalido");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/approfondimenti?errore=errore_generico");
        }
    }
}
