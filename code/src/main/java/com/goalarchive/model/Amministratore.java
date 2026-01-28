package com.goalarchive.model;

import java.util.Date;

public class Amministratore extends Utente {
    private int livelloAccesso;
    private String domandaSicurezza;
    private String rispostaSicurezza;

    public Amministratore() {
        super();
    }

    public Amministratore(String email, String nome, String cognome, String username,
                         Date dataNascita, String password, int livelloAccesso,
                         String domandaSicurezza, String rispostaSicurezza) {
        super(email, nome, cognome, username, dataNascita, password, "admin");
        this.livelloAccesso = livelloAccesso;
        this.domandaSicurezza = domandaSicurezza;
        this.rispostaSicurezza = rispostaSicurezza;
    }

    public int getLivelloAccesso() { return livelloAccesso; }
    public void setLivelloAccesso(int livelloAccesso) { this.livelloAccesso = livelloAccesso; }
    
    public String getDomandaSicurezza() { return domandaSicurezza; }
    public void setDomandaSicurezza(String domandaSicurezza) { this.domandaSicurezza = domandaSicurezza; }
    
    public String getRispostaSicurezza() { return rispostaSicurezza; }
    public void setRispostaSicurezza(String rispostaSicurezza) { this.rispostaSicurezza = rispostaSicurezza; }
}
