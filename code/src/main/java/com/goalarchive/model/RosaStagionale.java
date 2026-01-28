package com.goalarchive.model;

import java.io.Serializable;

public class RosaStagionale implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idRosaStagionale;
    private int idClub;
    private int idCalciatore;
    private String stagione;
    private int presenze;
    private int gol;
    

    private String nomeCalciatore;
    private String cognomeCalciatore;
    private String ruoloCalciatore;
    private String nazionalitaCalciatore;

    public RosaStagionale() {}


    public int getIdRosaStagionale() { return idRosaStagionale; }
    public void setIdRosaStagionale(int idRosaStagionale) { this.idRosaStagionale = idRosaStagionale; }
    
    public int getIdClub() { return idClub; }
    public void setIdClub(int idClub) { this.idClub = idClub; }
    
    public int getIdCalciatore() { return idCalciatore; }
    public void setIdCalciatore(int idCalciatore) { this.idCalciatore = idCalciatore; }
    
    public String getStagione() { return stagione; }
    public void setStagione(String stagione) { this.stagione = stagione; }
    
    public int getPresenze() { return presenze; }
    public void setPresenze(int presenze) { this.presenze = presenze; }
    
    public int getGol() { return gol; }
    public void setGol(int gol) { this.gol = gol; }
    
    public String getNomeCalciatore() { return nomeCalciatore; }
    public void setNomeCalciatore(String nomeCalciatore) { this.nomeCalciatore = nomeCalciatore; }
    
    public String getCognomeCalciatore() { return cognomeCalciatore; }
    public void setCognomeCalciatore(String cognomeCalciatore) { this.cognomeCalciatore = cognomeCalciatore; }
    
    public String getRuoloCalciatore() { return ruoloCalciatore; }
    public void setRuoloCalciatore(String ruoloCalciatore) { this.ruoloCalciatore = ruoloCalciatore; }
    
    public String getNazionalitaCalciatore() { return nazionalitaCalciatore; }
    public void setNazionalitaCalciatore(String nazionalitaCalciatore) { this.nazionalitaCalciatore = nazionalitaCalciatore; }
}
