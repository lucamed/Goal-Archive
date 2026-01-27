<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goalarchive.model.Utente" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Goal Archive - Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <!-- Header come da Mock-up RAD pag. 34 -->
    <jsp:include page="/header.jsp" />


    <!-- Sezione Hero -->
    <section class="hero">
        <div class="container">
            <h2>Archivio Digitale di Storia Calcistica</h2>
            <p>Consulta dati storici su squadre, giocatori, rose stagionali e palmarès in modo semplice e affidabile.</p>
            
            <% if (request.getParameter("logout") != null) { %>
                <div class="alert alert-success">Logout effettuato con successo!</div>
            <% } %>
            
            <% if (request.getParameter("admin") != null) { %>
                <div class="alert alert-info">Benvenuto Amministratore!</div>
            <% } %>
        </div>
    </section>

    <!-- Funzionalità principali -->
    <section class="features">
        <div class="container">
            <h3>Funzionalità Principali</h3>
            <div class="feature-grid">
                <div class="feature-card">
                    <h4>🏆 Palmarès Club</h4>
                    <p>Consulta lo storico completo dei trofei vinti dalle squadre</p>
                </div>
                <div class="feature-card">
                    <h4>👥 Rose Stagionali</h4>
                    <p>Visualizza la composizione delle squadre stagione per stagione</p>
                </div>
                <div class="feature-card">
                    <h4>💬 Approfondimenti</h4>
                    <p>Partecipa alle discussioni con altri appassionati</p>
                </div>
            </div>
        </div>
    </section>

    <jsp:include page="/footer.jsp" />

</body>
</html>
