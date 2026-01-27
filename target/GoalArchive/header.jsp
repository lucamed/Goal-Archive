<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goalarchive.model.Utente" %>
<header>
    <div class="container">
        <div class="logo">
            <a href="${pageContext.request.contextPath}/home.jsp" class="logo-link">
                <h1>Goal Archive</h1>
            </a>
        </div>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/home.jsp">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/club">Club</a></li>
                <li><a href="${pageContext.request.contextPath}/approfondimenti">Approfondimenti</a></li>

                <%
                    Utente utente = (Utente) session.getAttribute("utente");
                    if (utente != null) {
                        if ("admin".equals(utente.getRuolo())) {

                %>
                <li class="dropdown">
                    <a href="#" class="user-menu">🔧 <%= utente.getUsername() %> (Admin)</a>
                    <div class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/admin/aggiungiTrofeo">Aggiungi Trofeo</a>
                        <a href="${pageContext.request.contextPath}/logout">Logout</a>
                    </div>
                </li>
                <%
                } else {

                %>
                <li class="dropdown">
                    <a href="#" class="user-menu">👤 <%= utente.getUsername() %></a>
                    <div class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
                        <a href="${pageContext.request.contextPath}/logout">Logout</a>
                    </div>
                </li>
                <%
                    }
                } else {
                %>
                <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                <li><a href="${pageContext.request.contextPath}/registrazione" class="btn-registrati">Registrati</a></li>
                <% } %>
            </ul>
        </nav>
    </div>
</header>
