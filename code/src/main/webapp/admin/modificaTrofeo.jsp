<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goalarchive.model.*" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifica Trofeo - Goal Archive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .form-admin {
            max-width: 600px;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #333;
            font-size: 15px;
        }
        .form-control {
            width: 100%;
            padding: 12px;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            font-size: 15px;
            transition: border-color 0.3s;
            box-sizing: border-box;
        }
        .form-control:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }
        .form-control:disabled {
            background: #f5f5f5;
            color: #666;
            cursor: not-allowed;
        }
        .form-group small {
            display: block;
            margin-top: 6px;
            color: #666;
            font-size: 13px;
        }
        .form-buttons {
            display: flex;
            gap: 12px;
            margin-top: 30px;
        }
        .btn {
            padding: 12px 24px;
            border: none;
            border-radius: 8px;
            font-weight: 600;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            transition: all 0.3s;
            font-size: 15px;
        }
        .btn-primary {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            flex: 1;
        }
        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(102, 126, 234, 0.4);
        }
        .btn-secondary {
            background: #e0e0e0;
            color: #333;
            flex: 1;
        }
        .btn-secondary:hover {
            background: #d0d0d0;
            transform: translateY(-2px);
        }
        .alert {
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 20px;
            font-weight: 500;
        }
        .alert-error {
            background: #fee;
            color: #c33;
            border: 1px solid #fcc;
        }
        .breadcrumb {
            margin-bottom: 20px;
            color: #666;
            font-size: 14px;
        }
        .breadcrumb a {
            color: #667eea;
            text-decoration: none;
        }
        .breadcrumb a:hover {
            text-decoration: underline;
        }
        h1 {
            color: #333;
            margin-bottom: 30px;
            font-size: 28px;
        }
    </style>
</head>
<body>
<jsp:include page="/header.jsp" />

<%
    Trofeo trofeo = (Trofeo) request.getAttribute("trofeo");
    Club club = (Club) request.getAttribute("club");
    String errore = (String) request.getAttribute("errore");
%>

<section class="content-section">
    <div class="container">
        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard Admin</a> →
            <a href="${pageContext.request.contextPath}/palmares?idClub=<%= club.getIdClub() %>">
                Palmarès <%= club.getNome() %>
            </a> →
            Modifica Trofeo
        </div>

        <form method="post" action="${pageContext.request.contextPath}/admin/modificaTrofeo" class="form-admin">
            <h1>✏️ Modifica Trofeo</h1>

            <% if (errore != null) { %>
            <div class="alert alert-error">⚠️ <%= errore %></div>
            <% } %>

            <!-- Hidden fields per identificare il trofeo -->
            <input type="hidden" name="idClub" value="<%= trofeo.getIdClub() %>">
            <input type="hidden" name="annoVecchio" value="<%= trofeo.getAnno() %>">
            <input type="hidden" name="idCompetizione" value="<%= trofeo.getIdCompetizione() %>">

            <div class="form-group">
                <label>🏆 Club</label>
                <input type="text" value="<%= club.getNome() %>" disabled class="form-control">
            </div>

            <div class="form-group">
                <label>🏅 Competizione</label>
                <input type="text" value="<%= trofeo.getNomeCompetizione() %>" disabled class="form-control">
            </div>

            <div class="form-group">
                <label for="annoNuovo">📅 Anno di vittoria *</label>
                <input type="number"
                       id="annoNuovo"
                       name="annoNuovo"
                       value="<%= trofeo.getAnno() %>"
                       min="1850"
                       max="2100"
                       required
                       class="form-control"
                       autofocus>
                <small>Anno attuale: <strong><%= trofeo.getAnno() %></strong></small>
            </div>

            <div class="form-group">
                <label for="motivo">📝 Motivo della modifica *</label>
                <textarea id="motivo"
                          name="motivo"
                          rows="4"
                          required
                          class="form-control"
                          placeholder="Spiega perché stai modificando questo trofeo...&#10;&#10;Esempio: 'Correzione anno errato da 1985 a 1986' oppure 'Aggiornamento dati storici'"></textarea>
                <small>Questo campo è obbligatorio per tracciare le modifiche</small>
            </div>

            <div class="form-buttons">
                <button type="submit" class="btn btn-primary">💾 Salva Modifiche</button>
                <a href="${pageContext.request.contextPath}/palmares?idClub=<%= trofeo.getIdClub() %>"
                   class="btn btn-secondary">❌ Annulla</a>
            </div>
        </form>
    </div>
</section>

<jsp:include page="/footer.jsp" />
</body>
</html>
