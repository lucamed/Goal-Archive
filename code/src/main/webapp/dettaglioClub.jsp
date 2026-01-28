<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goalarchive.model.*" %>
<%@ page import="com.goalarchive.dao.ManagerPreferiti" %>
<%@ include file="/utils.jsp" %>

<%
    // ✅ Recupera utente e club
    Utente utente = (Utente) session.getAttribute("utente");
    Club club = (Club) request.getAttribute("club");

    // ✅ Verifica se è già nei preferiti
    boolean isPreferito = false;
    if (utente != null && club != null) {
        ManagerPreferiti managerPreferiti = new ManagerPreferiti();
        try {
            isPreferito = managerPreferiti.esistePreferito(utente.getEmail(), "club", club.getIdClub());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= club != null ? club.getNome() : "Club" %> - Goal Archive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/header.jsp" />

<% if (club != null) {
    String logoPath = getLogoClub(club.getNome());
%>
<section class="club-header">
    <div class="container">
        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/club">Club</a> →
            <a href="${pageContext.request.contextPath}/club?nazione=<%= club.getNazione() %>"><%= club.getNazione() %></a>
        </div>

        <div class="club-info">
            <div class="club-badge">
                <img src="${pageContext.request.contextPath}/<%= logoPath %>"
                     alt="<%= club.getNome() %>"
                     onerror="this.src='${pageContext.request.contextPath}/img/club/default.png'">
            </div>
            <div class="club-details-text">
                <div class="club-title-with-star">
                    <h1><%= club.getNome() %></h1>

                    <!-- ⭐ STELLINA PREFERITI -->
                    <% if (utente != null) { %>
                    <form action="${pageContext.request.contextPath}/gestionePreferiti"
                          method="post"
                          class="favorite-form-inline">
                        <input type="hidden" name="tipo" value="club">
                        <input type="hidden" name="idRiferimento" value="<%= club.getIdClub() %>">

                        <% if (isPreferito) { %>
                        <input type="hidden" name="azione" value="rimuovi">
                        <button type="submit" class="btn-favorite">⭐</button>
                        <% } else { %>
                        <input type="hidden" name="azione" value="aggiungi">
                        <button type="submit" class="btn-favorite">☆</button>
                        <% } %>
                    </form>

                    <% } %>
                </div>

                <div class="club-meta">
                    <span>🏆 <%= club.getCampionato() %></span>
                    <span>📅 Fondato: <%= club.getAnnoFondazione() %></span>
                    <span>🏟️ <%= club.getStadio() %></span>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Navigation tabs -->
<div class="club-navigation">
    <div class="container">
        <div class="tab-menu">
            <a href="${pageContext.request.contextPath}/dettaglioClub?idClub=<%= club.getIdClub() %>" class="tab-active">Info</a>
            <a href="${pageContext.request.contextPath}/archivioRose?idClub=<%= club.getIdClub() %>">Archivio Rose</a>
            <a href="${pageContext.request.contextPath}/palmares?idClub=<%= club.getIdClub() %>">Palmarès</a>
        </div>
    </div>
</div>

<section class="content-section">
    <div class="container">
        <!-- ✅ Messaggio Feedback Preferito -->
        <% if ("ok".equals(request.getParameter("preferito"))) { %>
        <div class="alert alert-success">
            ✅ Preferito aggiornato con successo!
        </div>
        <% } else if ("errore".equals(request.getParameter("preferito"))) { %>
        <div class="alert alert-error">
            ⚠️ Errore nell'aggiornamento del preferito.
        </div>
        <% } %>

        <div class="info-cards">
            <div class="info-card">
                <h3>Storia</h3>
                <p>Informazioni storiche sul club <%= club.getNome() %>, fondato nel <%= club.getAnnoFondazione() %>.</p>
            </div>

            <div class="info-card">
                <h3>Stadio</h3>
                <p>Le partite casalinghe si giocano presso lo stadio <%= club.getStadio() %>.</p>
            </div>
        </div>
    </div>
</section>
<% } else { %>
<section class="content-section">
    <div class="container">
        <div class="alert alert-error">Club non trovato.</div>
    </div>
</section>
<% } %>

<jsp:include page="/footer.jsp" />
</body>
</html>
