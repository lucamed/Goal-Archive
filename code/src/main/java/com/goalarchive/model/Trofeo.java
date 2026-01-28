package com.goalarchive.model;

import java.io.Serializable;

public class Trofeo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idTrofeo;
    private int idClub;
    private int anno;
    private int idCompetizione;
    

    private String nomeCompetizione;
    private String tipoCompetizione;

    public Trofeo() {}


    public int getIdTrofeo() { return idTrofeo; }
    public void setIdTrofeo(int idTrofeo) { this.idTrofeo = idTrofeo; }
    
    public int getIdClub() { return idClub; }
    public void setIdClub(int idClub) { this.idClub = idClub; }
    
    public int getAnno() { return anno; }
    public void setAnno(int anno) { this.anno = anno; }
    
    public int getIdCompetizione() { return idCompetizione; }
    public void setIdCompetizione(int idCompetizione) { this.idCompetizione = idCompetizione; }
    
    public String getNomeCompetizione() { return nomeCompetizione; }
    public void setNomeCompetizione(String nomeCompetizione) { this.nomeCompetizione = nomeCompetizione; }
    
    public String getTipoCompetizione() { return tipoCompetizione; }
    public void setTipoCompetizione(String tipoCompetizione) { this.tipoCompetizione = tipoCompetizione; }
}
