/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import GraphicInterface.PanelliManageri;
import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Pelattava;
import mafia.kyvyt.Atribuutti;
import mafia.kyvyt.Kyky;
import mafia.peli.Logit.LogWriter;
import mafia.peli.YhdenAsianLuokkia.AanestysSysteemi;

/**
 *
 * @author Elkyur
 */
public class GraphicRunHelper {

    private LogWriter kirjoittaja;
    private PanelliManageri paneeli;
    private ArrayList<Faasi> faasiArray;
    private ArrayList<Pelattava> PelissaMukana;
    private int PhaseNumber;
    private Faasi onRunning;
    private AanestysSysteemi aanestyssteemi;
    private int OperationalNumber;
    private BuffienHallitsija buffmanager;
    private ArrayList<Atribuutti> atribuuttiLista;
    private ArrayList<Hahmo> kuolleet;
    private Atribuutti onGoingAtribuutti;

    public GraphicRunHelper(PanelliManageri paneeli, BuffienHallitsija buffmanager) {
        this.paneeli = paneeli;
        this.PhaseNumber = 0;
        this.OperationalNumber = 0;
        this.buffmanager = buffmanager;
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

    public void getNext() {
        
        int j = this.PhaseNumber % this.faasiArray.size();
       
        this.onRunning = this.faasiArray.get(j);
        this.onRunning.Sort();
        
        OperationalNumber = 0;
        this.atribuuttiLista = this.onRunning.palautaAtribuutit();

        this.onRunning.UudistaJaPaivita();
        this.PhaseNumber++;
    }

    public void Run() {

        this.onRunning.GraphicRun(aanestyssteemi, paneeli, kirjoittaja);

    }

    public boolean ExtraRun() {

        if (OperationalNumber >= this.atribuuttiLista.size()) {


            getNext();
            return false;
        }

        onGoingAtribuutti = this.atribuuttiLista.get(OperationalNumber);

        OperationalNumber++;

        return true;

    }

    public ArrayList<Hahmo> hankiKuolleet() {
        this.kuolleet = this.buffmanager.palautaKuolleet();
        this.buffmanager.PuhdistetaanKuolleet();
        this.buffmanager.BuffitVanhetuvat();
        return this.kuolleet;
    }

    public String Cast(Hahmo cast, Hahmo vastaanottaja, Kyky kyky) {
        return this.buffmanager.TekeeTaikaa(cast, kyky, vastaanottaja);

    }

    public Atribuutti palautaOnGoing() {
        return this.onGoingAtribuutti;
    }

    public ArrayList<Pelattava> pelattavat() {
        return this.PelissaMukana;

    }

    public boolean tarkistaJatkuukoPeli() {
        if (this.pelattavat().size() <= 1) {
            return false;
        } else {
            return true;
        }

    }
    
    public String julistaVoittaja()
    {
    String k = this.PelissaMukana.get(0).getNimi();
    return k;
    
    }
}
