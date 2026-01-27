<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goalarchive.model.*" %>
<%@ page import="java.util.List" %>
<%@ include file="/utils.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <% Club club = (Club) request.getAttribute("club"); %>
    <title>Archivio Rose - <%= club != null ? club.getNome() : "Club" %> - Goal Archive</title>
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
            <a href="${pageContext.request.contextPath}/club?nazione=<%= club.getNazione() %>"><%= club.getNazione() %></a> →
            <a href="${pageContext.request.contextPath}/dettaglioClub?idClub=<%= club.getIdClub() %>"><%= club.getNome() %></a> →
            Archivio Rose
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

<section class="club-navigation">
    <div class="container">
        <div class="tab-menu">
            <a href="${pageContext.request.contextPath}/dettaglioClub?idClub=<%= club.getIdClub() %>">Info</a>
            <a href="${pageContext.request.contextPath}/archivioRose?idClub=<%= club.getIdClub() %>" class="tab-active">Archivio Rose</a>
            <a href="${pageContext.request.contextPath}/palmares?idClub=<%= club.getIdClub() %>">Palmarès</a>
        </div>
    </div>
</section>

<section class="content-section">
    <div class="container">
        <%
            @SuppressWarnings("unchecked")
            List<String> stagioni = (List<String>) request.getAttribute("stagioni");
            String stagioneSelezionata = (String) request.getAttribute("stagioneSelezionata");

            if (stagioni != null && !stagioni.isEmpty()) {
        %>
        <div class="stagione-selector">
            <label for="stagione">Seleziona Stagione:</label>
            <select id="stagione" onchange="location.href='${pageContext.request.contextPath}/archivioRose?idClub=<%= club.getIdClub() %>&stagione=' + this.value;">
                <% for (String stagione : stagioni) { %>
                <option value="<%= stagione %>" <%= stagione.equals(stagioneSelezionata) ? "selected" : "" %>>
                    Stagione <%= stagione %>
                </option>
                <% } %>
            </select>
        </div>

        <h2>Rosa Stagione <%= stagioneSelezionata %></h2>

        <%
            @SuppressWarnings("unchecked")
            List<RosaStagionale> rosa = (List<RosaStagionale>) request.getAttribute("rosa");

            if (rosa != null && !rosa.isEmpty()) {
                String ruoloCorrente = "";

                for (RosaStagionale giocatore : rosa) {
                    if (!ruoloCorrente.equals(giocatore.getRuoloCalciatore())) {
                        if (!ruoloCorrente.isEmpty()) {
        %>
    </div>
    </div>
    <%
        }
        ruoloCorrente = giocatore.getRuoloCalciatore();
    %>
    <div class="ruolo-section">
        <h3 class="ruolo-header"><%= ruoloCorrente %></h3>
        <div class="giocatori-list">
            <%
                }
            %>
            <div class="giocatore-card">
                <div class="giocatore-info">
                    <strong><%= giocatore.getNomeCalciatore() %> <%= giocatore.getCognomeCalciatore() %></strong>
                    <span class="nazionalita"> <%= giocatore.getNazionalitaCalciatore() %></span>
                </div>
                <div class="giocatore-stats">
                    <span>Presenze: <strong><%= giocatore.getPresenze() %></strong></span>
                    <span>Gol: <strong><%= giocatore.getGol() %></strong></span>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
    <%
    } else {
    %>
    <p class="no-data">Nessun giocatore disponibile per questa stagione.</p>
    <%
        }
    } else {
    %>
    <p class="no-data">Nessuna rosa disponibile per questo club.</p>
    <% } %>
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
