/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import GraphicInterface.PanelliManageri;
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
    private PanelliManageri manageri;
    private AanestysSysteemi aanestysSysteemi;
    private LogWriter logikiroittaja;
    private boolean graafisenKayttaminen;
    private boolean logWrittaa;

    /**
     *
     * @param nimi
     * @param tekstirajapinta
     */
    
    
    public Peli(String nimi, TekstiRajapinta tekstirajapinta, LogWriter kirjoittaja) {

        this.tekstirajapinta = tekstirajapinta;
        this.logikiroittaja = kirjoittaja;
        this.graafisenKayttaminen = false;

    }

    public Peli(String nimi, PanelliManageri paneeli, LogWriter kirjoittaja) {

        this.graafisenKayttaminen = true;
        this.manageri = paneeli;
        this.logikiroittaja = kirjoittaja;
   

    }
    
    public void logWritingSet()
    {
    
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
     * käynnistää pelin
     */
    public void Run() throws FileNotFoundException, UnsupportedEncodingException {
        this.logikiroittaja.LogWriterInit(null);
         this.logikiroittaja.Alkup(this.PelissaMukana);
        int i = 0;
        while (true) {
           this.logikiroittaja.AloitaFaasi(this.faasiArray.get(i % this.faasiArray.size()));
           // Vain ja vain testausta varten
            System.out.println("Koittaa Faasi numero: " + i);
            if (this.graafisenKayttaminen == true) {
                this.faasiArray.get(i % this.faasiArray.size()).GraphicRun(this.aanestysSysteemi, this.manageri, this.logikiroittaja);
            } else {
                this.faasiArray.get(i % this.faasiArray.size()).Run(this.aanestysSysteemi, this.tekstirajapinta, this.logikiroittaja);
            }
            if (!tarkistaJatkuukoPeli()) {
                this.logikiroittaja.Write("Pelin voitti: " + this.PelissaMukana.get(0).getNimi());
                this.tekstirajapinta.JulistaVoittaja(this.PelissaMukana.get(0));
                break;
            }
            i++;
        }


    }
    
    public void GraphicRun()
    {
    
    
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

    public ArrayList<Pelattava> PalautaPelaajat() {
        return this.PelissaMukana;
    }
    /**
     *
     */
}
