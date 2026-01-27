<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goalarchive.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%

    Topic topic = (Topic) request.getAttribute("topic");
    

    Utente utente = (Utente) session.getAttribute("utente"); 
    

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= topic != null ? topic.getTitolo() : "Topic" %> - Goal Archive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/header.jsp" />

<section class="content-section">
    <div class="container">
        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/approfondimenti">← Torna agli Approfondimenti</a>
        </div>

        <% if (topic != null) { %>
            <div class="topic-detail">
                <h1><%= topic.getTitolo() %></h1>
                <p class="topic-description-full"><%= topic.getDescrizione() %></p>
            </div>

            <% if ("ok".equals(request.getParameter("commento"))) { %>
                <div class="alert alert-success">Commento pubblicato con successo!</div>
            <% } %>
            
            <% if (request.getParameter("errore") != null) { %>
                <div class="alert alert-error">
                    <% if ("campo_vuoto".equals(request.getParameter("errore"))) { %>
                        Errore: il commento non può essere vuoto.
                    <% } else { %>
                        Errore nella pubblicazione del commento, deve essere di almeno 10 caratteri.
                    <% } %>
                </div>
            <% } %>

            <% if (utente != null) { %>
                <div class="commento-form-section">
                    <h3>Esprimi la tua opinione</h3>
                    <form action="${pageContext.request.contextPath}/pubblicaCommento" method="post">
                        <input type="hidden" name="idTopic" value="<%= topic.getIdTopic() %>">
                        <div class="form-group">
                            <textarea name="testo" rows="4" placeholder="Scrivi il tuo commento..." required maxlength="1000"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Pubblica Commento</button>
                    </form>
                </div>
            <% } else { %>
                <div class="alert alert-info">
                    <a href="${pageContext.request.contextPath}/login">Effettua il login</a> per partecipare alla discussione.
                </div>
            <% } %>

            <div class="commenti-section">
                <h3>Commenti</h3>
                <% 
                @SuppressWarnings("unchecked")
                List<Commento> commenti = (List<Commento>) request.getAttribute("commenti");
                
                if (commenti != null && !commenti.isEmpty()) {
                    for (Commento commento : commenti) {
                %>
                    <div class="commento-card">
                        <div class="commento-header">
                            <strong>👤 <%= commento.getNomeUtente() %></strong>
                            <span class="commento-date"><%= sdf.format(commento.getDataPubblicazione()) %></span>
                        </div>
                        <p class="commento-testo"><%= commento.getTesto() %></p>
                    </div>
                <% 
                    }
                } else {
                %>
                    <p class="no-data">Nessun commento ancora. Sii il primo a commentare!</p>
                <% } %>
            </div>

        <% } else { %>
            <div class="alert alert-error">Topic non trovato.</div>
        <% } %>
    </div>
</section>

<jsp:include page="/footer.jsp" />

</body>
</html>