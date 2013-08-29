/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.TietokantaHallinta;

import Settings.Settings;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Pelattava;
import mafia.kyvyt.Buff;
import mafia.kyvyt.BuffinTyyppi;
import mafia.peli.BuffienHallitsija;

/**
 *
 * Tarkoituksena on luoda luokka joka käyttäjän syötteestä rakentaisi pelin
 * käyttämällä tallennettuja objekteja tekstitiedostosta.
 */
public class TietokantaManageri {

    private ArrayList<BuffinTyyppi> tyypit;
    private ArrayList<Pelattava> viittausTiimeihin;
    private ArrayList<Buff> buffit;
    private BuffienHallitsija hallitsija;
    private ArrayList<Pelaaja> pelaajat;
    private Kirjoittaja kir;
    private Loader load;
    private Settings settings;

    public TietokantaManageri(Settings settings) {
        this.kir = new Kirjoittaja();
        this.load = new Loader();
        this.viittausTiimeihin = new ArrayList<Pelattava>();
        this.hallitsija = new BuffienHallitsija(this.viittausTiimeihin);
        this.settings = settings;
    }

    public Kirjoittaja palautaKirjoittaja() {
        return this.kir;
    }

    public Loader palautaLoaderi() {
        return load;
    }

    public void asetaPelaajat(ArrayList<Pelaaja> pelaajat) {
        this.pelaajat = pelaajat;
    }

    public void asetaParamaterit() throws IOException {
        File pelaajata = new File(this.settings.PelaajaLocation);

        kir.asetaPelaajat(pelaajata);
        load.asetaPelaajaTietoKanta(pelaajata);


    }

    /**
     *
     * Configaa buffityyypit
     */
    public void ConfigurateBuffiTyyppit() {
        HashMap<String, BuffinTyyppi> kartta = this.load.buffityypit();
        this.hallitsija.asetaSuhde(Config(kartta.get("SUHDEBUFFI")));
        this.hallitsija.asetaKosto(Config(kartta.get("KOSTOBUFFI")));
        this.hallitsija.asetaTappo(Config(kartta.get("TAPPOBUFFI")));
        this.hallitsija.asetaMuutosEsto(Config(kartta.get("MUUTOSESTO")));
        this.hallitsija.asetaLaukaisuEsto(Config(kartta.get("LAUKAISUESTO")));


    }

    /**
     *
     *
     * Configaa buffityypit
     *
     */
    public BuffinTyyppi Config(BuffinTyyppi tyyppi) {
        this.tyypit.add(tyyppi);
        return tyyppi;
    }

    /**
     *
     *
     * Asettaa ohjelman kannalta funktionaaliset pelaajat
     *
     */
    public void LataaPelaajat() throws FileNotFoundException {
        this.pelaajat = this.load.palautaPelaajat();
    }

    /**
     *
     *
     * Palauttaa pelaajat
     *
     */
    public ArrayList<Pelaaja> PalautaPelaajat() {
        if (this.pelaajat != null) {
            return this.pelaajat;
        } else {
            ArrayList<Pelaaja> pelaajatt = new ArrayList<Pelaaja>();
            return pelaajatt;
        }
    }

    /**
     * Kirjoittaa tiedoston pelaajat
     */
    public void UudistaPelaajat() throws IOException {


        this.kir.MofifyPelaajat(pelaajat);

        /**
         *
         *
         * Kirjoittaa tiedoston pelaajat
         *
         */
    }

    public void UudistaPelaajat(ArrayList<Pelaaja> pel) throws IOException {

        this.kir.MofifyPelaajat(pelaajat);


    }
}
