<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione - Goal Archive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/header.jsp" />


<section class="form-section">
        <div class="container">
            <div class="form-box">
                <h2>Registrazione</h2>
                <p class="subtitle">Crea il tuo account per accedere a tutte le funzionalità</p>
                

                <% if (request.getAttribute("errore") != null) { %>
                    <div class="alert alert-error">
                        <%= request.getAttribute("errore") %>
                    </div>
                <% } %>
                

                <form action="${pageContext.request.contextPath}/registraDati" method="post">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="nome">Nome *</label>
                            <input type="text" id="nome" name="nome" required>
                        </div>
                        <div class="form-group">
                            <label for="cognome">Cognome *</label>
                            <input type="text" id="cognome" name="cognome" required>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="nomeUtente">Nome Utente *</label>
                        <input type="text" id="nomeUtente" name="nomeUtente" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="dataNascita">Data di Nascita *</label>
                        <input type="date" id="dataNascita" name="dataNascita" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Email *</label>
                        <input type="email" id="email" name="email" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="password">Password *</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="domandaSicurezza">Domanda di Sicurezza *</label>
                        <input type="text" id="domandaSicurezza" name="domandaSicurezza" 
                               placeholder="Es: Qual è il nome del tuo migliore amico?" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="rispostaSicurezza">Risposta di Sicurezza *</label>
                        <input type="text" id="rispostaSicurezza" name="rispostaSicurezza" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="squadraCuore">Squadra del Cuore (opzionale)</label>
                        <input type="text" id="squadraCuore" name="squadraCuore" 
                               placeholder="Es: Napoli">
                    </div>
                    
                    <button type="submit" class="btn btn-primary">Registrati</button>
                </form>
                
                <p class="form-footer">
                    Hai già un account? <a href="${pageContext.request.contextPath}/login">Accedi qui</a>
                </p>
            </div>
        </div>
    </section>

<jsp:include page="/footer.jsp" />

</body>
</html>
