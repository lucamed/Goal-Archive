<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!
  /**
   * Normalizza il nome per creare il path del logo
   * Es: "AC Milan" → "ac-milan"
   */
  public String normalizzaNome(String nome) {
    if (nome == null || nome.isEmpty()) {
      return "default";
    }
    return nome.toLowerCase()
            .replace(" ", "-")
            .replace(".", "")
            .replace("'", "")
            .replaceAll("[^a-z0-9-]", "");
  }

  /**
   * Genera il path completo del logo club
   */
  public String getLogoClub(String nomeClub) {
    return "img/club/" + normalizzaNome(nomeClub) + ".png";
  }

  /**
   * Genera il path completo del logo nazione
   */
  public String getLogoNazione(String nomeNazione) {
    return "img/nazioni/" + normalizzaNome(nomeNazione) + ".png";
  }
%>
