package com.goalarchive.model;

import java.io.Serializable;

public class Calciatore implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idCalciatore;
    private String nome;
    private String cognome;
    private String ruolo;
    private String nazionalita;

    public Calciatore() {}

    public Calciatore(int idCalciatore, String nome, String cognome, String ruolo, String nazionalita) {
        this.idCalciatore = idCalciatore;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
        this.nazionalita = nazionalita;
    }

    // Getter e Setter
    public int getIdCalciatore() { return idCalciatore; }
    public void setIdCalciatore(int idCalciatore) { this.idCalciatore = idCalciatore; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    
    public String getRuolo() { return ruolo; }
    public void setRuolo(String ruolo) { this.ruolo = ruolo; }
    
    public String getNazionalita() { return nazionalita; }
    public void setNazionalita(String nazionalita) { this.nazionalita = nazionalita; }
}
