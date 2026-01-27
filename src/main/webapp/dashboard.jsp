<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goalarchive.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Goal Archive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/header.jsp" />


    <section class="dashboard-header">
        <div class="container">
            <h1>Dashboard Personale</h1>
            <% 
            Utente utente = (Utente) request.getAttribute("utente");
            if (utente != null) { 
            %>
                <p>Benvenuto, <strong><%= utente.getNome() %> <%= utente.getCognome() %></strong></p>
            <% } %>
        </div>
    </section>

    <section class="content-section">
        <div class="container">
            <% if (request.getAttribute("errore") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("errore") %>
                </div>
            <% } %>

            <div class="dashboard-stats">
                <div class="stat-card">
                    <span class="stat-number"><%= request.getAttribute("totalePreferiti") != null ? request.getAttribute("totalePreferiti") : 0 %></span>
                    <span class="stat-label">Preferiti Totali</span>
                </div>
            </div>

            <!-- Preferiti Club -->
            <div class="dashboard-section">
                <h2>⚽ Club Preferiti</h2>
                <% 
                @SuppressWarnings("unchecked")
                List<Preferito> preferitiClub = (List<Preferito>) request.getAttribute("preferitiClub");
                if (preferitiClub != null && !preferitiClub.isEmpty()) {
                %>
                    <div class="preferiti-grid">
                        <% for (Preferito pref : preferitiClub) { %>
                            <div class="preferito-card">
                                <div class="preferito-info">
                                    <strong><%= pref.getNome() %></strong>
                                    <span><%= pref.getDescrizione() %></span>
                                </div>
                                <div class="preferito-actions">
                                    <a href="${pageContext.request.contextPath}/dettaglioClub?idClub=<%= pref.getIdRiferimento() %>" class="btn-link">Visualizza</a>
                                    <form action="${pageContext.request.contextPath}/gestionePreferiti" method="post" style="display:inline;">
                                        <input type="hidden" name="azione" value="rimuovi">
                                        <input type="hidden" name="tipo" value="club">
                                        <input type="hidden" name="idRiferimento" value="<%= pref.getIdRiferimento() %>">
                                        <button type="submit" class="btn-remove">Rimuovi</button>
                                    </form>
                                </div>
                            </div>
                        <% } %>
                    </div>
                <% } else { %>
                    <p class="no-data">Nessun club preferito. Esplora i club e aggiungili ai preferiti!</p>
                <% } %>
            </div>


        </div>
        <!-- Preferiti Calciatori -->
        <div class="dashboard-section">
            <h2>👤 Calciatori Preferiti</h2>
            <%
                @SuppressWarnings("unchecked")
                List<Preferito> preferitiCalciatori = (List<Preferito>) request.getAttribute("preferitiCalciatori");
                if (preferitiCalciatori != null && !preferitiCalciatori.isEmpty()) {
            %>
            <div class="preferiti-grid">
                <% for (Preferito pref : preferitiCalciatori) { %>
                <div class="preferito-card">
                    <div class="preferito-info">
                        <strong><%= pref.getNome() %></strong>
                        <span><%= pref.getDescrizione() %></span>
                    </div>
                    <div class="preferito-actions">
                        <form action="${pageContext.request.contextPath}/gestionePreferiti" method="post" style="display:inline;">
                            <input type="hidden" name="azione" value="rimuovi">
                            <input type="hidden" name="tipo" value="calciatore">
                            <input type="hidden" name="idRiferimento" value="<%= pref.getIdRiferimento() %>">
                            <button type="submit" class="btn-remove">Rimuovi</button>
                        </form>
                    </div>
                </div>
                <% } %>
            </div>
            <% } else { %>
            <p class="no-data">Nessun calciatore preferito.</p>
            <% } %>
        </div>
        </div>
    </section>


<jsp:include page="/footer.jsp" />


</body>
</html>
