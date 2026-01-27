<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goalarchive.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log Modifiche - Goal Archive</title>
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
            <h1>📋 Log Modifiche</h1>
            <p>Storico completo delle modifiche al sistema</p>
        </div>
    </section>

    <section class="content-section">
        <div class="container">
            <div class="breadcrumb">
                <a href="${pageContext.request.contextPath}/admin/dashboard">← Dashboard Admin</a>
            </div>

            <% if (request.getAttribute("errore") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("errore") %>
                </div>
            <% } %>

            <% 
            @SuppressWarnings("unchecked")
            List<LogModifica> logs = (List<LogModifica>) request.getAttribute("logs");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            if (logs != null && !logs.isEmpty()) {
            %>
                <div class="log-table-full">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Data</th>
                                <th>Amministratore</th>
                                <th>Operazione</th>
                                <th>Tabella</th>
                                <th>Descrizione</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (LogModifica log : logs) { %>
                                <tr>
                                    <td><%= log.getIdLog() %></td>
                                    <td><%= sdf.format(log.getDataModifica()) %></td>
                                    <td><%= log.getNomeUtente() %></td>
                                    <td><span class="badge badge-<%= log.getTipoOperazione().toLowerCase() %>"><%= log.getTipoOperazione() %></span></td>
                                    <td><%= log.getTabella() %></td>
                                    <td><%= log.getDescrizione() %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            <% } else { %>
                <p class="no-data">Nessun log disponibile.</p>
            <% } %>
        </div>
    </section>

    <footer>
        <div class="container">
            <p>&copy; 2025 Goal Archive</p>
        </div>
    </footer>
</body>
</html>
