<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goalarchive.model.Club" %>
<%@ include file="/utils.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <% Club club = (Club) request.getAttribute("club"); %>
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
            <div>
                <h1><%= club.getNome() %></h1>
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
