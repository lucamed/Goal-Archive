<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goalarchive.model.*" %>
<%@ page import="java.util.List" %>
<%@ include file="/utils.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <% Club club = (Club) request.getAttribute("club"); %>
    <title>Palmarès - <%= club != null ? club.getNome() : "Club" %> - Goal Archive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>

        .trofeo-card {
            position: relative;
            overflow: hidden;
        }

        .trofeo-overlay {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: rgba(0, 0, 0, 0.9);
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
            opacity: 0;
            transition: opacity 0.3s ease;
            z-index: 10;
        }

        .trofeo-card:hover .trofeo-overlay {
            opacity: 1;
        }

        .btn-overlay {
            padding: 8px 16px;
            border: none;
            border-radius: 5px;
            font-size: 13px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            color: white;
            display: flex;
            align-items: center;
            gap: 5px;
        }

        .btn-edit {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        }

        .btn-edit:hover {
            transform: scale(1.05);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }

        .btn-delete {
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
        }

        .btn-delete:hover {
            transform: scale(1.05);
            box-shadow: 0 5px 15px rgba(245, 87, 108, 0.4);
        }

        /* Modal */
        .modal {
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.6);
            animation: fadeIn 0.3s;
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        .modal-content {
            background-color: #fefefe;
            margin: 10% auto;
            padding: 30px;
            border-radius: 10px;
            width: 90%;
            max-width: 500px;
            position: relative;
            animation: slideDown 0.3s;
        }

        @keyframes slideDown {
            from { transform: translateY(-50px); opacity: 0; }
            to { transform: translateY(0); opacity: 1; }
        }

        .modal-content h2 {
            margin-top: 0;
            color: #f5576c;
        }

        .close {
            position: absolute;
            right: 20px;
            top: 20px;
            color: #aaa;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }

        .close:hover {
            color: #000;
        }

        .modal-buttons {
            display: flex;
            gap: 10px;
            justify-content: flex-end;
            margin-top: 20px;
        }

        .btn-danger {
            background: #f5576c;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-weight: 600;
        }

        .btn-danger:hover {
            background: #d43f51;
        }

        .btn-secondary {
            background: #6c757d;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn-secondary:hover {
            background: #5a6268;
        }

        .form-group {
            margin: 20px 0;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #333;
        }

        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-family: inherit;
            font-size: 14px;
            resize: vertical;
            box-sizing: border-box;
        }

        .form-group textarea:focus {
            outline: none;
            border-color: #007bff;
            box-shadow: 0 0 0 3px rgba(0,123,255,0.1);
        }
    </style>
</head>
<body>
<jsp:include page="/header.jsp" />

<%
    Utente utente = (Utente) session.getAttribute("utente");
    boolean isAdmin = (utente != null && "admin".equals(utente.getRuolo()));

    if (club != null) {
        String logoPath = getLogoClub(club.getNome());
%>
<section class="club-header">
    <div class="container">
        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/club">Club</a> →
            <a href="${pageContext.request.contextPath}/club?nazione=<%= club.getNazione() %>"><%= club.getNazione() %></a> →
            <a href="${pageContext.request.contextPath}/dettaglioClub?idClub=<%= club.getIdClub() %>"><%= club.getNome() %></a> →
            Palmarès
        </div>

        <div class="club-info">
            <div class="club-badge">
                <img src="${pageContext.request.contextPath}/<%= logoPath %>"
                     alt="<%= club.getNome() %>"
                     onerror="this.src='${pageContext.request.contextPath}/img/club/default.png'">
            </div>
            <div>
                <h1><%= club.getNome() %></h1>
                <div class="club-meta">
                    <span>🏆 <%= club.getCampionato() %></span>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="club-navigation">
    <div class="container">
        <div class="tab-menu">
            <a href="${pageContext.request.contextPath}/dettaglioClub?idClub=<%= club.getIdClub() %>">Info</a>
            <a href="${pageContext.request.contextPath}/archivioRose?idClub=<%= club.getIdClub() %>">Archivio Rose</a>
            <a href="${pageContext.request.contextPath}/palmares?idClub=<%= club.getIdClub() %>" class="tab-active">Palmarès</a>
        </div>
    </div>
</section>

<section class="content-section">
    <div class="container">
        <%
            Integer trofeiNazionali = (Integer) request.getAttribute("trofeiNazionali");
            Integer trofeiInternazionali = (Integer) request.getAttribute("trofeiInternazionali");

            if (trofeiNazionali != null || trofeiInternazionali != null) {
        %>
        <div class="palmares-summary">
            <h2>Riepilogo Trofei</h2>
            <div class="trofei-count">
                <div class="count-card">
                    <span class="count-number"><%= trofeiNazionali != null ? trofeiNazionali : 0 %></span>
                    <span class="count-label">Trofei Nazionali</span>
                </div>
                <div class="count-card">
                    <span class="count-number"><%= trofeiInternazionali != null ? trofeiInternazionali : 0 %></span>
                    <span class="count-label">Trofei Internazionali</span>
                </div>
            </div>
        </div>
        <% } %>

        <%
            @SuppressWarnings("unchecked")
            List<Trofeo> trofei = (List<Trofeo>) request.getAttribute("trofei");

            if (trofei != null && !trofei.isEmpty()) {
                String tipoCorrente = "";

                for (Trofeo trofeo : trofei) {
                    if (!tipoCorrente.equals(trofeo.getTipoCompetizione())) {
                        if (!tipoCorrente.isEmpty()) {
        %>
    </div>
    </div>
    <%
        }
        tipoCorrente = trofeo.getTipoCompetizione();
        String iconaTipo = tipoCorrente.equals("Internazionale") ? "🌍" : "🏠";
    %>
    <div class="trofei-section">
        <h3 class="trofei-header"><%= iconaTipo %> Trofei <%= tipoCorrente %>i</h3>
        <div class="trofei-list">
            <%
                }
            %>
            <div class="trofeo-card">
                <div class="trofeo-competizione">
                    <strong><%= trofeo.getNomeCompetizione() %></strong>
                </div>
                <span class="anno-badge"><%= trofeo.getAnno() %></span>

                <!-- Overlay per admin -->
                <% if (isAdmin) { %>
                <div class="trofeo-overlay">
                    <a href="${pageContext.request.contextPath}/admin/modificaTrofeo?idClub=<%= trofeo.getIdClub() %>&anno=<%= trofeo.getAnno() %>&idCompetizione=<%= trofeo.getIdCompetizione() %>"
                       class="btn-overlay btn-edit" title="Modifica anno">
                        ✏️ Modifica
                    </a>

                    <button type="button"
                            class="btn-overlay btn-delete"
                            onclick="confermaEliminazione(<%= trofeo.getIdClub() %>, <%= trofeo.getAnno() %>, <%= trofeo.getIdCompetizione() %>, '<%= trofeo.getNomeCompetizione().replace("'", "\\'") %>')"
                            title="Elimina trofeo">
                        🗑️ Elimina
                    </button>
                </div>
                <% } %>
            </div>
            <%
                }
            %>
        </div>
    </div>
    <%
    } else {
    %>
    <p class="no-data">Nessun trofeo disponibile per questo club.</p>
    <% } %>
    </div>
</section>
<% } else { %>
<section class="content-section">
    <div class="container">
        <div class="alert alert-error">Club non trovato.</div>
    </div>
</section>
<% } %>


<% if (isAdmin) { %>
<div id="deleteModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="chiudiModal()">&times;</span>
        <h2>⚠️ Conferma Eliminazione</h2>
        <p id="deleteMessage"></p>
        <form method="post" action="${pageContext.request.contextPath}/admin/eliminaTrofeo" id="deleteForm">
            <input type="hidden" name="idClub" id="deleteIdClub">
            <input type="hidden" name="anno" id="deleteAnno">
            <input type="hidden" name="idCompetizione" id="deleteIdCompetizione">

            <div class="form-group">
                <label for="motivo">Motivo dell'eliminazione: *</label>
                <textarea id="motivo" name="motivo" rows="3" required
                          placeholder="Spiega il motivo dell'eliminazione..."></textarea>
            </div>

            <div class="modal-buttons">
                <button type="submit" class="btn btn-danger">Conferma Eliminazione</button>
                <button type="button" class="btn btn-secondary" onclick="chiudiModal()">Annulla</button>
            </div>
        </form>
    </div>
</div>

<script>
    function confermaEliminazione(idClub, anno, idCompetizione, nomeCompetizione) {
        document.getElementById('deleteIdClub').value = idClub;
        document.getElementById('deleteAnno').value = anno;
        document.getElementById('deleteIdCompetizione').value = idCompetizione;

        document.getElementById('deleteMessage').innerHTML =
            'Sei sicuro di voler eliminare il trofeo <strong>' + nomeCompetizione +
            ' (' + anno + ')</strong>?<br><br>Questa azione non può essere annullata.';

        document.getElementById('deleteModal').style.display = 'block';
    }

    function chiudiModal() {
        document.getElementById('deleteModal').style.display = 'none';
        document.getElementById('deleteForm').reset();
    }

    window.onclick = function(event) {
        let modal = document.getElementById('deleteModal');
        if (event.target == modal) {
            chiudiModal();
        }
    }
</script>
<% } %>

<jsp:include page="/footer.jsp" />
</body>
</html>
