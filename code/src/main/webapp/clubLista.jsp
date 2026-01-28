<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goalarchive.model.Club" %>
<%@ page import="java.util.List" %>
<%@ include file="/utils.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Club <%= request.getAttribute("nazione") %> - Goal Archive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/header.jsp" />

<section class="content-section">
    <div class="container">
        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/club">← Torna alle Nazioni</a>
        </div>

        <h2>Club - <%= request.getAttribute("nazione") %></h2>

        <div class="club-grid">
            <%
                @SuppressWarnings("unchecked")
                List<Club> clubs = (List<Club>) request.getAttribute("clubs");
                if (clubs != null && !clubs.isEmpty()) {
                    for (Club club : clubs) {
                        String logoPath = getLogoClub(club.getNome());
            %>
            <a href="${pageContext.request.contextPath}/dettaglioClub?idClub=<%= club.getIdClub() %>" class="club-card">
                <div class="club-logo-img">
                    <img src="${pageContext.request.contextPath}/<%= logoPath %>"
                         alt="<%= club.getNome() %>"
                         onerror="this.src='${pageContext.request.contextPath}/img/club/default.png'">
                </div>
                <h3><%= club.getNome() %></h3>
                <p><%= club.getCampionato() %></p>
            </a>
            <%
                }
            } else {
            %>
            <p class="no-data">Nessun club disponibile per questa nazione.</p>
            <% } %>
        </div>
    </div>
</section>

<jsp:include page="/footer.jsp" />
</body>
</html>
