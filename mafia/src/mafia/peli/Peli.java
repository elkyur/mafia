/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import mafia.kyvyt.Buff;
import mafia.peli.Logit.LogWriter;
import mafia.peli.YhdenAsianLuokkia.AanestysSysteemi;
import mafia.userinterface.TekstiRajapinta;

/**
 * Kantaa vastuun koko pelistä. 
 *
 */
public class Peli {


    private ArrayList<Faasi> faasiArray;
    private ArrayList<Pelattava> PelissaMukana;
    private TekstiRajapinta tekstirajapinta;
    private AanestysSysteemi aanestysSysteemi;
    private LogWriter logikiroittaja;

    /**
     * 
     * @param nimi
     * @param tekstirajapinta
     */
    public Peli(String nimi, TekstiRajapinta tekstirajapinta, LogWriter kirjoittaja) {
     
        this.tekstirajapinta = tekstirajapinta;
        this.logikiroittaja = kirjoittaja;
        
    }

    /**
     * 
     * asettaa äänestysysteemin
     */
    public void asetaAanestysSysteemi(AanestysSysteemi aanestysSysteemi) {
        this.aanestysSysteemi = aanestysSysteemi;
    }

    /**
     * 
     * asettaa faasilistan
     */
    public void asetaFaasit(ArrayList<Faasi> faasit) {
        this.faasiArray = faasit;
    }

    /**
     * 
     * asettaa viittauksen pelajiin
     */
    public void asetaPelaajat(ArrayList<Pelattava> pelaajat) {
        this.PelissaMukana = pelaajat;

    }

    /**
     *  käynnistää pelin
     */
    public void Run() throws FileNotFoundException, UnsupportedEncodingException {
        this.logikiroittaja.LogWriterInit(null);
        this.logikiroittaja.Alkup(this.PelissaMukana);
        int i = 0;
        while (true) {
            this.logikiroittaja.AloitaFaasi(this.faasiArray.get(i % this.faasiArray.size()));
            System.out.println("Koittaa Faasi numero: " + i );
            this.faasiArray.get(i % this.faasiArray.size()).Run(this.aanestysSysteemi, this.tekstirajapinta, this.logikiroittaja);
           
            if (!tarkistaJatkuukoPeli()) {
                this.logikiroittaja.Write("Pelin voitti: " + this.PelissaMukana.get(0).getNimi());
                this.tekstirajapinta.JulistaVoittaja(this.PelissaMukana.get(0));
                break;
            }
            i++;
        }


    }

    /**
     * 
     * Tarkistaa jatkuuko vielä peli
     */
    public boolean tarkistaJatkuukoPeli() {
        if (this.PelissaMukana.size() <= 1) {
            return false;
        }
        return true;
    }

    /**
     * 
     */
   
}
