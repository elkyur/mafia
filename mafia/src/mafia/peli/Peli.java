/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import java.util.ArrayList;
import mafia.hahmot.Pelattava;

/**
 *
 *
 */
public class Peli {

    private String nimi;
    private ArrayList<Faasi> faasiArray;
    private ArrayList<Pelattava> Pelaajat;

    public Peli(String nimi) {
        this.nimi = nimi;
    }

    public void asetaFaasit(ArrayList<Faasi> faasit) {
        this.faasiArray = faasit;
    }

    public void asetaPelaajat(ArrayList<Pelattava> pelaajat) {
        this.Pelaajat = pelaajat;

    }
    
    
    
    
    
}
