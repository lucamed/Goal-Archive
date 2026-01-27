<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Goal Archive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/header.jsp" />


<section class="form-section">
        <div class="container">
            <div class="form-box">
                <h2>Login</h2>
                <p class="subtitle">Accedi al tuo account</p>
                
                <!-- Messaggi (come da UC2 - RAD pag. 13) -->
                <% if (request.getAttribute("messaggio") != null) { %>
                    <div class="alert alert-success">
                        <%= request.getAttribute("messaggio") %>
                    </div>
                <% } %>
                
                <% if (request.getAttribute("errore") != null) { %>
                    <div class="alert alert-error">
                        <%= request.getAttribute("errore") %>
                    </div>
                <% } %>
                
                <!-- Form login (come da Mock-up RAD pag. 37) -->
                <form action="${pageContext.request.contextPath}/autenticazione" method="post">
                    <div class="form-group">
                        <label for="emailOrUsername">Email o Nome Utente *</label>
                        <input type="text" id="emailOrUsername" name="emailOrUsername" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="password">Password *</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
                
                <p class="form-footer">
                    <a href="${pageContext.request.contextPath}/recuperaPassword">Password dimenticata?</a>
                </p>
                
                <p class="form-footer">
                    Non hai un account? <a href="${pageContext.request.contextPath}/registrazione">Registrati qui</a>
                </p>
            </div>
        </div>
    </section>

<jsp:include page="/footer.jsp" />

</body>
</html>
