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
            <div class="trofeo-card" style="position: relative; overflow: hidden; min-height: 60px;">
                <!-- Contenuto principale -->
                <div class="trofeo-content-main" style="position: relative; z-index: 1; pointer-events: none;">
                    <div class="trofeo-competizione">
                        <strong><%= trofeo.getNomeCompetizione() %></strong>
                    </div>
                    <span class="anno-badge"><%= trofeo.getAnno() %></span>
                </div>

                <!-- Overlay admin -->
                <% if (isAdmin) { %>
                <div class="trofeo-overlay" style="position: absolute; top: 0; left: 0; right: 0; bottom: 0; background: linear-gradient(135deg, rgba(26, 26, 46, 0.96) 0%, rgba(22, 33, 62, 0.96) 100%); display: none; align-items: center; justify-content: center; gap: 12px; z-index: 999; padding: 10px; backdrop-filter: blur(3px);">
                    <a href="${pageContext.request.contextPath}/admin/modificaTrofeo?idClub=<%= trofeo.getIdClub() %>&anno=<%= trofeo.getAnno() %>&idCompetizione=<%= trofeo.getIdCompetizione() %>"
                       style="padding: 10px 18px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; text-decoration: none; border-radius: 8px; font-weight: 700; font-size: 13px; box-shadow: 0 4px 12px rgba(102, 126, 234, 0.4); transition: all 0.3s; pointer-events: auto;"
                       onmouseover="this.style.transform='translateY(-3px) scale(1.05)'; this.style.boxShadow='0 6px 20px rgba(102, 126, 234, 0.6)';"
                       onmouseout="this.style.transform='translateY(0) scale(1)'; this.style.boxShadow='0 4px 12px rgba(102, 126, 234, 0.4)';">
                        ✏️ Modifica
                    </a>

                    <button type="button"
                            onclick="confermaEliminazione(<%= trofeo.getIdClub() %>, <%= trofeo.getAnno() %>, <%= trofeo.getIdCompetizione() %>, '<%= trofeo.getNomeCompetizione().replace("'", "\\'") %>')"
                            style="padding: 10px 18px; background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%); color: white; border: none; border-radius: 8px; cursor: pointer; font-weight: 700; font-size: 13px; box-shadow: 0 4px 12px rgba(245, 87, 108, 0.4); transition: all 0.3s; pointer-events: auto;"
                            onmouseover="this.style.transform='translateY(-3px) scale(1.05)'; this.style.boxShadow='0 6px 20px rgba(245, 87, 108, 0.6)';"
                            onmouseout="this.style.transform='translateY(0) scale(1)'; this.style.boxShadow='0 4px 12px rgba(245, 87, 108, 0.4)';">
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

<!-- Modal per conferma eliminazione -->
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
    console.log('🔧 Admin detected - Attivazione overlay trofei');

    document.addEventListener('DOMContentLoaded', function() {
        const cards = document.querySelectorAll('.trofeo-card');
        console.log('📊 Trovate ' + cards.length + ' card trofei');

        cards.forEach((card, index) => {
            const overlay = card.querySelector('.trofeo-overlay');

            if (overlay) {
                console.log('✅ Overlay trovato per card ' + index);

                card.addEventListener('mouseenter', function() {
                    console.log('🖱️ Mouse ENTRATO su card ' + index);
                    overlay.style.display = 'flex';
                });

                card.addEventListener('mouseleave', function() {
                    console.log('🖱️ Mouse USCITO da card ' + index);
                    overlay.style.display = 'none';
                });
            } else {
                console.error('❌ ATTENZIONE: Overlay NON trovato per card ' + index);
            }
        });
    });

    function confermaEliminazione(idClub, anno, idCompetizione, nomeCompetizione) {
        console.log('🗑️ Richiesta eliminazione:', nomeCompetizione);
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
