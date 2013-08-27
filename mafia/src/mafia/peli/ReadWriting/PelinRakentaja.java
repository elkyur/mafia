/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.ReadWriting;

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
public class PelinRakentaja {

    private ArrayList<BuffinTyyppi> tyypit;
    private ArrayList<Pelattava> viittausTiimeihin;
    private ArrayList<Buff> buffit;
    private BuffienHallitsija hallitsija;
    private ArrayList<Pelaaja> pelaajat;
    private Kirjoittaja kir;
    private Loader load;

    public PelinRakentaja() {
        this.kir = new Kirjoittaja();
        this.load = new Loader();
        this.viittausTiimeihin = new ArrayList<Pelattava>();
        this.hallitsija = new BuffienHallitsija(this.viittausTiimeihin);

    }

    public void asetaParamaterit() throws IOException {
        File pelaajat = new File(Settings.Settings.PelaajatLocation);
        File roolit = new File(Settings.Settings.roolitlocation);
        File buffit = new File(Settings.Settings.buffitlocation);
        File kyvyt = new File(Settings.Settings.kyvytlocation);
        File faasit = new File(Settings.Settings.faasitlocation);
        File tiimit = new File(Settings.Settings.Tiimitlocation);
        kir.asetaTietokannat(pelaajat, roolit, buffit, kyvyt, faasit, tiimit);
        load.asetaTietokannat(pelaajat, roolit, buffit, kyvyt, faasit, tiimit);


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

    public BuffinTyyppi Config(BuffinTyyppi tyyppi) {
        this.tyypit.add(tyyppi);
        return tyyppi;
    }

    public void LataaPelaajat() throws FileNotFoundException {
        this.pelaajat = this.load.palautaPelaajat();
    }

    public ArrayList<Pelaaja> PalautaPelaajat() {
        if (this.pelaajat != null) {
            return this.pelaajat;
        } else {
            ArrayList<Pelaaja> pelaajatt = new ArrayList<Pelaaja>();
            return pelaajatt;
        }
    }
}
