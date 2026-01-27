<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goalarchive.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aggiungi Trofeo - Goal Archive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script>
        function caricaClub() {
            var nazione = document.getElementById("nazione").value;
            if (nazione) {
                fetch('${pageContext.request.contextPath}/club?nazione=' + nazione)
                    .then(response => response.text())
                    .then(html => {
                        var parser = new DOMParser();
                        var doc = parser.parseFromString(html, 'text/html');
                        var clubSelect = document.getElementById("idClub");
                        clubSelect.innerHTML = '<option value="">Seleziona club...</option>';

                        // Parsing semplificato - in produzione usare JSON API
                        var links = doc.querySelectorAll('.club-card');
                        links.forEach(link => {
                            var href = link.getAttribute('href');
                            var idClub = href.split('idClub=')[1];
                            var nome = link.querySelector('h3').textContent;
                            var option = document.createElement('option');
                            option.value = idClub;
                            option.textContent = nome;
                            clubSelect.appendChild(option);
                        });
                    });
            }
        }
    </script>
</head>
<body>
<jsp:include page="/header.jsp" />

<section class="admin-header">
    <div class="container">
        <h1>🏆 Aggiungi Trofeo</h1>
        <p>Inserisci un nuovo trofeo nel palmarès di un club</p>
    </div>
</section>

<section class="form-section">
    <div class="container">
        <div class="form-box">
            <div class="breadcrumb">
                <a href="${pageContext.request.contextPath}/admin/dashboard">← Dashboard Admin</a>
            </div>

            <% if (request.getParameter("successo") != null) { %>
            <div class="alert alert-success">
                Trofeo aggiunto con successo!
            </div>
            <% } %>

            <% if (request.getAttribute("errore") != null) { %>
            <div class="alert alert-error">
                <%= request.getAttribute("errore") %>
            </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/admin/aggiungiTrofeo" method="post">

                <!-- Selezione Nazione -->
                <div class="form-group">
                    <label for="nazione">Nazione *</label>
                    <select id="nazione" name="nazione" onchange="caricaClub()" required>
                        <option value="">Seleziona nazione...</option>
                        <%
                            List<String> nazioni = (List<String>) request.getAttribute("nazioni");
                            if (nazioni != null) {
                                for (String nazione : nazioni) {
                        %>
                        <option value="<%= nazione %>"><%= nazione %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <!-- Selezione Club -->
                <div class="form-group">
                    <label for="idClub">Club *</label>
                    <select id="idClub" name="idClub" required>
                        <option value="">Prima seleziona una nazione...</option>
                    </select>
                </div>

                <!-- Anno -->
                <div class="form-group">
                    <label for="anno">Anno *</label>
                    <input type="number" id="anno" name="anno" min="1900" max="2100" placeholder="Es: 2007" required>
                </div>

                <!-- Selezione Competizione -->
                <div class="form-group">
                    <label for="idCompetizione">Competizione *</label>
                    <select id="idCompetizione" name="idCompetizione" required>
                        <option value="">Seleziona competizione...</option>
                        <%
                            List<Competizione> competizioni = (List<Competizione>) request.getAttribute("competizioni");
                            if (competizioni != null) {
                                String tipoCorrente = "";
                                for (Competizione comp : competizioni) {
                                    if (!tipoCorrente.equals(comp.getTipo())) {
                                        if (!tipoCorrente.isEmpty()) {
                        %>
                        </optgroup>
                        <%
                            }
                            tipoCorrente = comp.getTipo();
                        %>
                        <optgroup label="<%= tipoCorrente %>">
                            <%
                                }
                            %>
                            <option value="<%= comp.getIdCompetizione() %>"><%= comp.getNome() %></option>
                            <%
                                }
                                if (!tipoCorrente.isEmpty()) {
                            %>
                        </optgroup>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <!-- CAMPO MOTIVO -->
                <div class="form-group">
                    <label for="motivo">Motivo della modifica *</label>
                    <textarea id="motivo" name="motivo" rows="4" required
                              placeholder="Es: Integrazione dato mancante nel palmares. Verifica su fonti ufficiali."></textarea>
                    <small style="color: #666; font-size: 0.9em; display: block; margin-top: 5px;">
                        Spiega brevemente il motivo dell'aggiunta (obbligatorio per il log)
                    </small>
                </div>

                <button type="submit" class="btn btn-primary">Aggiungi Trofeo</button>
            </form>
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
