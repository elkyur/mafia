/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import mafia.kyvyt.Buff;
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

    /**
     * 
     * @param nimi
     * @param tekstirajapinta
     */
    public Peli(String nimi, TekstiRajapinta tekstirajapinta) {
     
        this.tekstirajapinta = tekstirajapinta;
    }

    /**
     * 
     * @param aanestysSysteemi
     */
    public void asetaAanestysSysteemi(AanestysSysteemi aanestysSysteemi) {
        this.aanestysSysteemi = aanestysSysteemi;
    }

    /**
     * 
     * @param faasit
     */
    public void asetaFaasit(ArrayList<Faasi> faasit) {
        this.faasiArray = faasit;
    }

    /**
     * 
     * @param pelaajat
     */
    public void asetaPelaajat(ArrayList<Pelattava> pelaajat) {
        this.PelissaMukana = pelaajat;

    }

    /**
     * 
     */
    public void Run() {
        int i = 0;
        while (true) {
            System.out.println("Koittaa Faasi numero: " + i );
            this.faasiArray.get(i % this.faasiArray.size()).Run(this.aanestysSysteemi, this.tekstirajapinta);
            BuffitVanhetuvat();
            if (!tarkistaJatkuukoPeli()) {
                this.tekstirajapinta.JulistaVoittaja(this.PelissaMukana.get(0));
                break;
            }
            i++;
        }


    }

    /**
     * 
     * @return
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
    public void BuffitVanhetuvat() {
        for (Pelattava pelattava : this.PelissaMukana) {
            for (Hahmo hahmo : pelattava.getTeam()) {
                hahmo.BuffitVanhetuvat();
                

            }

        }
    }
}
