package com.goalarchive.model;

import java.io.Serializable;
import java.util.Date;

public class Preferito implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idPreferito;
    private String email;
    private String tipo;
    private int idRiferimento;
    private Date dataAggiunta;
    

    private String nome;
    private String descrizione;

    public Preferito() {}


    public int getIdPreferito() { return idPreferito; }
    public void setIdPreferito(int idPreferito) { this.idPreferito = idPreferito; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public int getIdRiferimento() { return idRiferimento; }
    public void setIdRiferimento(int idRiferimento) { this.idRiferimento = idRiferimento; }
    
    public Date getDataAggiunta() { return dataAggiunta; }
    public void setDataAggiunta(Date dataAggiunta) { this.dataAggiunta = dataAggiunta; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
}
