package com.goalarchive.model;

import java.io.Serializable;
import java.util.List;

public class Dashboard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private UtenteRegistrato utente;
    private List<Preferito> preferitiClub;
    private List<Preferito> preferitiCalciatori;
    private int totalePreferiti;

    public Dashboard() {}


    public UtenteRegistrato getUtente() { return utente; }
    public void setUtente(UtenteRegistrato utente) { this.utente = utente; }
    
    public List<Preferito> getPreferitiClub() { return preferitiClub; }
    public void setPreferitiClub(List<Preferito> preferitiClub) { this.preferitiClub = preferitiClub; }
    
    public List<Preferito> getPreferitiCalciatori() { return preferitiCalciatori; }
    public void setPreferitiCalciatori(List<Preferito> preferitiCalciatori) { this.preferitiCalciatori = preferitiCalciatori; }
    
    public int getTotalePreferiti() { return totalePreferiti; }
    public void setTotalePreferiti(int totalePreferiti) { this.totalePreferiti = totalePreferiti; }
}
