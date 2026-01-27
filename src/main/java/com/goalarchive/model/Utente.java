package com.goalarchive.model;

import java.io.Serializable;
import java.util.Date;

public class Utente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String email;
    private String nome;
    private String cognome;
    private String username;
    private Date dataNascita;
    private String password;
    private String ruolo;

    public Utente() {}

    public Utente(String email, String nome, String cognome, String username, 
                  Date dataNascita, String password, String ruolo) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.dataNascita = dataNascita;
        this.password = password;
        this.ruolo = ruolo;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public Date getDataNascita() { return dataNascita; }
    public void setDataNascita(Date dataNascita) { this.dataNascita = dataNascita; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getRuolo() { return ruolo; }
    public void setRuolo(String ruolo) { this.ruolo = ruolo; }
}
