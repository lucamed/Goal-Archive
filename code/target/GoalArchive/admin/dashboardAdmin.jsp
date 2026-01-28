<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goalarchive.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Admin - Goal Archive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <div class="container">
            <div class="logo">
                <h1>Goal Archive</h1>
            </div>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/home.jsp">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/club">Club</a></li>
                    <li><a href="${pageContext.request.contextPath}/approfondimenti">Approfondimenti</a></li>
                    
                    <% 
                    Utente utente = (Utente) session.getAttribute("utente");
                    if (utente != null && "admin".equals(utente.getRuolo())) { 
                    %>
                        <li class="dropdown">
                            <a href="#" class="user-menu">🔧 <%= utente.getUsername() %> (Admin)</a>
                            <div class="dropdown-content">
                                <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard Admin</a>
                                <a href="${pageContext.request.contextPath}/admin/aggiungiTrofeo">Aggiungi Trofeo</a>
                                <a href="${pageContext.request.contextPath}/admin/logModifiche">Log Modifiche</a>
                                <a href="${pageContext.request.contextPath}/logout">Logout</a>
                            </div>
                        </li>
                    <% } %>
                </ul>
            </nav>
        </div>
    </header>

    <section class="admin-header">
        <div class="container">
            <h1>🔧 Dashboard Amministratore</h1>
            <p>Gestione contenuti e monitoraggio sistema</p>
        </div>
    </section>

    <section class="content-section">
        <div class="container">
            <% if (request.getAttribute("errore") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("errore") %>
                </div>
            <% } %>

            <!-- Azioni Rapide -->
            <div class="admin-actions">
                <h2>Azioni Rapide</h2>
                <div class="action-grid">
                    <a href="${pageContext.request.contextPath}/admin/aggiungiTrofeo" class="action-card">
                        <div class="action-icon">🏆</div>
                        <h3>Aggiungi Trofeo</h3>
                        <p>Inserisci un nuovo trofeo per un club</p>
                    </a>
                    
                    <a href="${pageContext.request.contextPath}/admin/logModifiche" class="action-card">
                        <div class="action-icon">📋</div>
                        <h3>Log Modifiche</h3>
                        <p>Visualizza lo storico delle modifiche</p>
                    </a>
                    
                    <a href="${pageContext.request.contextPath}/club" class="action-card">
                        <div class="action-icon">⚽</div>
                        <h3>Gestisci Club</h3>
                        <p>Visualizza e gestisci i club</p>
                    </a>
                </div>
            </div>

            <!-- Ultimi Log -->
            <div class="admin-logs-preview">
                <h2>Ultime Modifiche</h2>
                <% 
                @SuppressWarnings("unchecked")
                List<LogModifica> ultimiLog = (List<LogModifica>) request.getAttribute("ultimiLog");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                
                if (ultimiLog != null && !ultimiLog.isEmpty()) {
                %>
                    <div class="log-table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Data</th>
                                    <th>Operazione</th>
                                    <th>Tabella</th>
                                    <th>Descrizione</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (LogModifica log : ultimiLog) { %>
                                    <tr>
                                        <td><%= sdf.format(log.getDataModifica()) %></td>
                                        <td><span class="badge badge-<%= log.getTipoOperazione().toLowerCase() %>"><%= log.getTipoOperazione() %></span></td>
                                        <td><%= log.getTabella() %></td>
                                        <td><%= log.getDescrizione() %></td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    <a href="${pageContext.request.contextPath}/admin/logModifiche" class="btn-link">Visualizza tutti i log →</a>
                <% } else { %>
                    <p class="no-data">Nessuna modifica recente.</p>
                <% } %>
            </div>
        </div>
    </section>

    <footer>
        <div class="container">
            <p>&copy; 2025 Goal Archive</p>
        </div>
    </footer>
</body>
</html>
