package com.goalarchive.model;

import java.io.Serializable;

public class Club implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idClub;
    private String nome;
    private String nazione;
    private String campionato;
    private int annoFondazione;
    private String stadio;

    public Club() {}


    public int getIdClub() { return idClub; }
    public void setIdClub(int idClub) { this.idClub = idClub; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getNazione() { return nazione; }
    public void setNazione(String nazione) { this.nazione = nazione; }

    public String getCampionato() { return campionato; }
    public void setCampionato(String campionato) { this.campionato = campionato; }

    public int getAnnoFondazione() { return annoFondazione; }
    public void setAnnoFondazione(int annoFondazione) { this.annoFondazione = annoFondazione; }

    public String getStadio() { return stadio; }
    public void setStadio(String stadio) { this.stadio = stadio; }
}
