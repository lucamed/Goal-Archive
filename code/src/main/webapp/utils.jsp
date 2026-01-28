<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!

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


  public String getLogoClub(String nomeClub) {
    return "img/club/" + normalizzaNome(nomeClub) + ".png";
  }


  public String getLogoNazione(String nomeNazione) {
    return "img/nazioni/" + normalizzaNome(nomeNazione) + ".png";
  }
%>
