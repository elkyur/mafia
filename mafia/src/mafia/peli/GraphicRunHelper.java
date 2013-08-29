/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import GraphicInterface.PanelliManageri;
import java.util.ArrayList;
import java.util.HashMap;
import mafia.hahmot.*;
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
    private HashMap<Pelattava, ArrayList<Hahmo>> alkuTilanne;
    private ArrayList<Rooli> WinnersOnZero;

     /**
     *
     * Tämä on peli tapainen luokka , mutta suuniteltu graafiselle käyttöliittymälle
     */
    
    public GraphicRunHelper(PanelliManageri paneeli, BuffienHallitsija buffmanager) {
        this.paneeli = paneeli;
        this.PhaseNumber = 0;
        this.OperationalNumber = 0;
        this.buffmanager = buffmanager;
        this.alkuTilanne = new HashMap<Pelattava, ArrayList<Hahmo>>();
        this.WinnersOnZero = new ArrayList<Rooli>();
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
     * Palauttaa faasin joka on tällä hetkellä menossa
     */
    
    public Faasi returnOnGoingFaasi()
    {
    return this.onRunning;
    }
    /**
     *
     * asettaa viittauksen pelajiin
     */
    public void asetaPelaajat(ArrayList<Pelattava> pelaajat) {
        this.PelissaMukana = pelaajat;
        this.alkuTilanne.clear();
        asetaKloonit();


    }
    
        /**
     *
     * Palauttaa seuraavan faasinö
     */

    public void getNext() {

        int j = this.PhaseNumber % this.faasiArray.size();

        this.onRunning = this.faasiArray.get(j);
        this.onRunning.Sort();

        OperationalNumber = 0;
        this.atribuuttiLista = this.onRunning.palautaAtribuutit();

        this.onRunning.UudistaJaPaivita();
        this.PhaseNumber++;
    }

      /**
     *
     * Laittaa graafisen käyttöliittymän pyörittämään peliä
     */
    
    public void Run() {

        this.onRunning.GraphicRun(aanestyssteemi, paneeli, kirjoittaja);

    }
    
      /**
     *
     * Palauttaa seuraavan atribuutin, jos atribuutteja ei ole jäljellä joko tulee uusi faasi tai peli loppuu.
     */

    public boolean ExtraRun() {

        if (OperationalNumber >= this.atribuuttiLista.size()) {


            getNext();
            return false;
        }

        onGoingAtribuutti = this.atribuuttiLista.get(OperationalNumber);

        OperationalNumber++;

        return true;

    }
    
    /**
     *
     * Palautetaan Kuolleet ja samalla puhdistetaan ne buffmanagerista
     */

    public ArrayList<Hahmo> hankiKuolleet() {
        this.kuolleet = (ArrayList<Hahmo>) this.buffmanager.palautaKuolleet().clone();



        this.buffmanager.PuhdistetaanKuolleet();
        this.buffmanager.BuffitVanhetuvat();
        return this.kuolleet;
    }
      /**
     *
     * CastausMetodi joka menee suoraan buffmanageriin , jossa se hoidetaan
     */

    public String Cast(Hahmo cast, Hahmo vastaanottaja, Kyky kyky) {
        return this.buffmanager.TekeeTaikaa(cast, kyky, vastaanottaja);

    }
    
        /**
     *
     * Palauttaa TämänHetkisen faasin
     */


    public Atribuutti palautaOnGoing() {
        return this.onGoingAtribuutti;
    }
    
        /**
     *
     * Palauttaa Pelattavat
     */


    public ArrayList<Pelattava> pelattavat() {
        return this.PelissaMukana;

    }
    
       /**
     *
     * Tarkistaa jatkuuko peli
     */

    public boolean tarkistaJatkuukoPeli() {
        if (this.pelattavat().size() <= 1) {
            return false;
        } else {
            return true;
        }

    }
    
     /**
     *
     * Kun ladataan uudet pelaajat, tämä metodi asettaa sille kloonin jotta myöhemmin voitaisiin löytää pelaajat jotka voittivat kyseisen pelin
     */

    public void asetaKloonit() {
        for (Pelattava e : this.PelissaMukana) {
            ArrayList<Hahmo> iterating = (ArrayList<Hahmo>) e.getTeam().clone();
            this.alkuTilanne.put(e, iterating);


        }

    }
    
         /**
     *
     * Tarkistetaan kuka voitti jos on nolla pelaaja jäljellä , esimerkiksi Hullu on perusmafioosossa sen tapainen rooli
     */

    public ArrayList<Hahmo> CheckWinnersOnZero() {
        ArrayList<Hahmo> voittaja = new ArrayList<Hahmo>();
        for (ArrayList<Hahmo> hahmot : alkuTilanne.values()) {
            for (Hahmo hahmo : hahmot) {
                if (this.WinnersOnZero.contains(hahmo.palautaRooli())) {
                    voittaja.add(hahmo);
                }
            }
        }


        return voittaja;
    }
        /**
     *
     * Asettaa mitkä roolit voittavat jos mikään pelaaja ei jää henkiin
     */
    
    public void asetaWinnersOnZero(ArrayList<Rooli> Champs) {
        this.WinnersOnZero = Champs;

    }
    
    /**
     *
     * Palauttaa voittajat jatko käsittelyyn 
     */

    public ArrayList<Hahmo> julistaVoittaja() {

        if (this.PelissaMukana.isEmpty()) {
            return CheckWinnersOnZero();
        }

        Pelattava pelattava = this.PelissaMukana.get(0);

        ArrayList<Hahmo> voittajat = alkuTilanne.get(pelattava);

        return voittajat;


    }
      /**
     *
     * Palauttaa tiimin nimen joka voitti
     */
    
    public Pelattava palautaVoittoTiiminNimi() {
        if (!this.PelissaMukana.isEmpty()) {
            return this.PelissaMukana.get(0);
        }
        return null;
    }
    
     /**
     *
     * Pisteyttää jokaisen hahmon, antaa pelin lopussa jokaiselle pelatun pelin lisään ja voittajille lisää voittajien lukumäärän.
     */

    public void Pisteyta() {
        for (ArrayList<Hahmo> hahmot : alkuTilanne.values()) {
            for (Hahmo hahmo : hahmot) {

                Statistiikka stat = hahmo.palautaOmistaja().palautaStatistiikka();
                stat.LisaaPeli();
            }

        }
        for (Hahmo hah : julistaVoittaja()) {
            Statistiikka stat = hah.palautaOmistaja().palautaStatistiikka();
            stat.LisaaVoitto();
        }
    }
}
