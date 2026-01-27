package com.goalarchive.model;

import java.util.Date;

public class UtenteRegistrato extends Utente {
    private Date dataRegistrazione;
    private String domandaSicurezza;
    private String rispostaSicurezza;
    private String squadraCuore;

    public UtenteRegistrato() {
        super();
    }

    public UtenteRegistrato(String email, String nome, String cognome, String username,
                           Date dataNascita, String password, String domandaSicurezza,
                           String rispostaSicurezza, String squadraCuore) {
        super(email, nome, cognome, username, dataNascita, password, "utente");
        this.domandaSicurezza = domandaSicurezza;
        this.rispostaSicurezza = rispostaSicurezza;
        this.squadraCuore = squadraCuore;
        this.dataRegistrazione = new Date();
    }

    public Date getDataRegistrazione() { return dataRegistrazione; }
    public void setDataRegistrazione(Date dataRegistrazione) { this.dataRegistrazione = dataRegistrazione; }
    
    public String getDomandaSicurezza() { return domandaSicurezza; }
    public void setDomandaSicurezza(String domandaSicurezza) { this.domandaSicurezza = domandaSicurezza; }
    
    public String getRispostaSicurezza() { return rispostaSicurezza; }
    public void setRispostaSicurezza(String rispostaSicurezza) { this.rispostaSicurezza = rispostaSicurezza; }
    
    public String getSquadraCuore() { return squadraCuore; }
    public void setSquadraCuore(String squadraCuore) { this.squadraCuore = squadraCuore; }
}
