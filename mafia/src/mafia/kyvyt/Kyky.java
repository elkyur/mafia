/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import java.util.ArrayList;
import mafia.hahmot.Hahmo;

/**
 *
 * Luokka joka vastaa kyvyistä
 */
public abstract class Kyky {

    private String nimi;
    private Buff buffi;
    private ArrayList<Buff> Tarvittavat;
    private boolean Active;
    private int kayttoKerta;


    /**
     * 
     * @param nimi
     * @param buffi
     * @param onRequest
     */
    public Kyky(String nimi, Buff buffi, boolean onDeath) {
        this.Active = onDeath;
        this.buffi = buffi;
        this.nimi = nimi;
        this.kayttoKerta = -1;
        

    }

    /**
     * 
     * Asettaa monta kertaa kykyä voi käyttää. 
     */
    public void asetaKayttoKerta(int i) {
        this.kayttoKerta = i;
    }

    /**
     * 
     * Aseta mitkä buffit tarvitaan kyvyn castaamiseen
     */
    public void asetaTarvittavat(ArrayList<Buff> buffilista) {
        this.Tarvittavat = buffilista;
    }

    /**
     * 
     * 
     * Abstrakti Toiminnallisuus, joka on eri kyky tyypeillä eri.
     * 
     */
    public abstract String Toiminnallisuus(Hahmo castaaja, Hahmo vastaanottava);

    /**
     * 
     * Palauttaa Buffin
     */
    public Buff getBuffi() {
        return this.buffi;
    }

    /**
     * 
     * Palauttaa variablen heti
     */
    public boolean palautaHeti() {
        return this.Active;
    }

  

    /**
     * 
     * Asettaa pitääkö kykyä käyttää heti
     */
    public void asetaOnRequest(boolean k) {
        this.Active = k;
    }

    /**
     * 
     * Palauttaa käyttökertojen lukumäärän
     */
    public int KayttoKerrat() {
        return this.kayttoKerta;
    }
    
    /**
     * 
     * Palauttaa tarvittavat buffit
     */
    public ArrayList<Buff> palautaTarvittavat()
    {
    return this.Tarvittavat;
    }
    /**
     * 
     * Palauttaa buffin minkä kyky lähettää
     */
    public Buff palautaBuffi()
    {
    return this.buffi;
    }
    /**
     * 
     * Palauttaa kyvyn nimen
     */
    public String palautaNimi()
    {
    return this.nimi;
    }


}
