package com.goalarchive.model;

import java.io.Serializable;

public class Competizione implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idCompetizione;
    private String nome;
    private String tipo;
    private String nazione;

    public Competizione() {}

    public int getIdCompetizione() { return idCompetizione; }
    public void setIdCompetizione(int idCompetizione) { this.idCompetizione = idCompetizione; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public String getNazione() { return nazione; }
    public void setNazione(String nazione) { this.nazione = nazione; }
}
