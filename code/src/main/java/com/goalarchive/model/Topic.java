package com.goalarchive.model;

import java.io.Serializable;
import java.util.Date;

public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idTopic;
    private String titolo;
    private String descrizione;
    private Date dataCreazione;
    

    private int numeroCommenti;

    public Topic() {}


    public int getIdTopic() { return idTopic; }
    public void setIdTopic(int idTopic) { this.idTopic = idTopic; }
    
    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }
    
    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    
    public Date getDataCreazione() { return dataCreazione; }
    public void setDataCreazione(Date dataCreazione) { this.dataCreazione = dataCreazione; }
    
    public int getNumeroCommenti() { return numeroCommenti; }
    public void setNumeroCommenti(int numeroCommenti) { this.numeroCommenti = numeroCommenti; }
}
