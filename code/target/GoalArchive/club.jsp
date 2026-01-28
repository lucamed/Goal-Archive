<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ include file="/utils.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Club - Goal Archive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/header.jsp" />

<section class="content-section">
    <div class="container">
        <h2>Seleziona una Nazione</h2>
        <p class="subtitle">Esplora i club calcistici divisi per nazione</p>

        <% if (request.getAttribute("errore") != null) { %>
        <div class="alert alert-error">
            <%= request.getAttribute("errore") %>
        </div>
        <% } %>

        <div class="nazioni-grid">
            <%
                @SuppressWarnings("unchecked")
                List<String> nazioni = (List<String>) request.getAttribute("nazioni");
                if (nazioni != null && !nazioni.isEmpty()) {
                    for (String nazione : nazioni) {
                        String logoPath = getLogoNazione(nazione);
            %>
            <a href="${pageContext.request.contextPath}/club?nazione=<%= nazione %>" class="nazione-card">
                <div class="nazione-logo">
                    <img src="${pageContext.request.contextPath}/<%= logoPath %>"
                         alt="<%= nazione %>"
                         onerror="this.src='${pageContext.request.contextPath}/img/nazioni/default.png'">
                </div>
                <h3><%= nazione %></h3>
            </a>
            <%
                }
            } else {
            %>
            <p class="no-data">Nessuna nazione disponibile.</p>
            <% } %>
        </div>
    </div>
</section>

<jsp:include page="/footer.jsp" />
</body>
</html>
