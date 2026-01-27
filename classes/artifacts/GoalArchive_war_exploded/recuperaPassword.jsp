<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recupera Password - Goal Archive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/header.jsp" />


<section class="form-section">
        <div class="container">
            <div class="form-box">
                <h2>Recupera Password</h2>
                
                <% if (request.getAttribute("errore") != null) { %>
                    <div class="alert alert-error">
                        <%= request.getAttribute("errore") %>
                    </div>
                <% } %>
                
                <% 
                Boolean verificato = (Boolean) request.getAttribute("verificato");
                String email = (String) request.getAttribute("email");
                String domanda = (String) request.getAttribute("domanda");
                
                if (verificato != null && verificato) { 
                %>
                    <!-- Step 2: Form nuova password (UC3 - RAD pag. 14) -->
                    <p class="subtitle">Imposta una nuova password</p>
                    <form action="${pageContext.request.contextPath}/verificaRecupero" method="post">
                        <input type="hidden" name="step" value="nuovaPassword">
                        <input type="hidden" name="email" value="<%= email %>">
                        
                        <div class="form-group">
                            <label for="nuovaPassword">Nuova Password *</label>
                            <input type="password" id="nuovaPassword" name="nuovaPassword" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="confermaPassword">Conferma Password *</label>
                            <input type="password" id="confermaPassword" name="confermaPassword" required>
                        </div>
                        
                        <button type="submit" class="btn btn-primary">Salva</button>
                    </form>
                <% } else { %>
                    <!-- Step 1: Form verifica email e risposta (UC3 - RAD pag. 14) -->
                    <p class="subtitle">Inserisci i tuoi dati per recuperare la password</p>
                    <form action="${pageContext.request.contextPath}/verificaRecupero" method="post">
                        <input type="hidden" name="step" value="verificaDati">
                        
                        <div class="form-group">
                            <label for="email">Email *</label>
                            <input type="email" id="email" name="email" required>
                        </div>
                        
                        <% if (domanda != null) { %>
                            <div class="alert alert-info">
                                <strong>Domanda di sicurezza:</strong> <%= domanda %>
                            </div>
                        <% } %>
                        
                        <div class="form-group">
                            <label for="rispostaSicurezza">Risposta di Sicurezza *</label>
                            <input type="text" id="rispostaSicurezza" name="rispostaSicurezza" required>
                        </div>
                        
                        <button type="submit" class="btn btn-primary">Continua</button>
                    </form>
                <% } %>
                
                <p class="form-footer">
                    <a href="${pageContext.request.contextPath}/login">Torna al Login</a>
                </p>
            </div>
        </div>
    </section>

<jsp:include page="/footer.jsp" />


</body>
</html>
