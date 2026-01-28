<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goalarchive.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Approfondimenti - Goal Archive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/header.jsp" />


<section class="hero" style="padding: 3rem 0;">
        <div class="container">
            <h2>Approfondimenti</h2>
            <p>Partecipa alle discussioni con altri appassionati di calcio storico</p>
        </div>
    </section>

    <section class="content-section">
        <div class="container">
            <% if (request.getAttribute("errore") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("errore") %>
                </div>
            <% } %>

            <div class="topics-list">
                <% 
                @SuppressWarnings("unchecked")
                List<Topic> topics = (List<Topic>) request.getAttribute("topics");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                
                if (topics != null && !topics.isEmpty()) {
                    for (Topic topic : topics) {
                %>
                    <a href="${pageContext.request.contextPath}/topic?id=<%= topic.getIdTopic() %>" class="topic-card">
                        <div class="topic-header">
                            <h3><%= topic.getTitolo() %></h3>
                            <span class="topic-date"><%= sdf.format(topic.getDataCreazione()) %></span>
                        </div>
                        <p class="topic-description"><%= topic.getDescrizione() %></p>
                        <div class="topic-footer">
                            <span class="topic-comments">💬 <%= topic.getNumeroCommenti() %> commenti</span>
                        </div>
                    </a>
                <% 
                    }
                } else {
                %>
                    <p class="no-data">Nessun topic disponibile al momento.</p>
                <% } %>
            </div>
        </div>
    </section>

<jsp:include page="/footer.jsp" />

</body>
</html>
