/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import java.util.ArrayList;
import mafia.hahmot.Pelattava;
import mafia.userinterface.TextInterface;

/**
 *
 *
 */
public class Peli {

    private String nimi;
    private ArrayList<Faasi> faasiArray;
    private ArrayList<Pelattava> PelissaMukana;
    private TextInterface tekstirajapinta;
    

    public Peli(String nimi, TextInterface tekstirajapinta) {
        this.nimi = nimi;
        this.tekstirajapinta = tekstirajapinta;
    }

    public void asetaFaasit(ArrayList<Faasi> faasit) {
        this.faasiArray = faasit;
    }

    public void asetaPelaajat(ArrayList<Pelattava> pelaajat) {
        this.PelissaMukana = pelaajat;

    }

    public void Run() {
        
        
    }

    public boolean tarkistaJatkuukoPeli() {
        if (this.PelissaMukana.size() <= 1) {
            return true;
        }
        return false;
    }
}
