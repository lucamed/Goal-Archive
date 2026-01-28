package com.goalarchive.model;

import java.io.Serializable;
import java.util.Date;

public class Commento implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idCommento;
    private int idTopic;
    private String email;
    private String testo;
    private Date dataPubblicazione;
    

    private String nomeUtente;

    public Commento() {}


    public int getIdCommento() { return idCommento; }
    public void setIdCommento(int idCommento) { this.idCommento = idCommento; }
    
    public int getIdTopic() { return idTopic; }
    public void setIdTopic(int idTopic) { this.idTopic = idTopic; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTesto() { return testo; }
    public void setTesto(String testo) { this.testo = testo; }
    
    public Date getDataPubblicazione() { return dataPubblicazione; }
    public void setDataPubblicazione(Date dataPubblicazione) { this.dataPubblicazione = dataPubblicazione; }
    
    public String getNomeUtente() { return nomeUtente; }
    public void setNomeUtente(String nomeUtente) { this.nomeUtente = nomeUtente; }
}
