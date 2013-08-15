/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import mafia.hahmot.Pelattava;
import mafia.kyvyt.Kyky;
import java.util.*;
import mafia.hahmot.Hahmo;
import mafia.kyvyt.Atribuutti;
import mafia.peli.YhdenAsianLuokkia.AanestysSysteemi;
import mafia.peli.YhdenAsianLuokkia.Misc;
import mafia.peli.YhdenAsianLuokkia.SortKyky;
import mafia.userinterface.TekstiRajapinta;

/**
 *
 * Kantaa vastuun pelissä esintyyvistä faaseista. Esimerkiksi Yö ja Päivä ovat faaseja. 
 */
public class Faasi {

    private String nimi;
    boolean paiva;
    private ArrayList<Atribuutti> KykyJaKayttaja;
    String ViestiAlussa;
    String ViestiLopussa;
    private BuffienHallitsija buffiHallitsija;
    private ArrayList<Pelattava> pelattavat;
    private Misc muutosProsessi;
    private Comparator comp;

    /**
     * 
     * @param nimi
     * @param buffiHallitsija
     * @param pelattavat
     */
    public Faasi(String nimi, BuffienHallitsija buffiHallitsija, ArrayList<Pelattava> pelattavat) {
        this.comp = new SortKyky();
        this.nimi = nimi;
        this.KykyJaKayttaja = new ArrayList<Atribuutti>();
        this.ViestiAlussa = "";
        this.ViestiLopussa = "";
        this.buffiHallitsija = buffiHallitsija;
        this.pelattavat = pelattavat;
        this.muutosProsessi = new Misc();
    }

    /**
     * 
     * @param alku
     * @param loppu
     */
    public void asetaViestit(String alku, String loppu) {
        this.ViestiAlussa = alku;
        this.ViestiLopussa = loppu;
    }

    /**
     * 
     * @param day
     */
    public void setDay(boolean day) {
        this.paiva = day;
    }

    /**
     * 
     * @return
     */
    public boolean palautaDay() {
        return this.paiva;

    }

    /**
     * 
     * @param kyky
     * @return
     */
    public Atribuutti Contains(Kyky kyky) {
        for (Atribuutti atr : this.KykyJaKayttaja) {
            if (atr.palautaKyky().equals(kyky)) {
                return atr;
            }
        }
        return null;
    }

    /**
     * 
     * @param kyky
     * @param hahmot
     */
    public void Lisaa(Kyky kyky, ArrayList<Hahmo> hahmot) {

        // System.out.println("Yritys lisätä");

        Atribuutti atr = Contains(kyky);

        if (atr != null) {
            ArrayList<Hahmo> hahmote = atr.palautaHahmot();
            this.muutosProsessi.ListanLisaaminenListaan(hahmote, hahmot);
        } else {
            ArrayList<Hahmo> hahmote = new ArrayList<Hahmo>();
            this.muutosProsessi.ListanLisaaminenListaan(hahmote, hahmot);
            this.KykyJaKayttaja.add(new Atribuutti(kyky, hahmote));

        }

    }

    /**
     * 
     * @param kyky
     * @return
     */
    public ArrayList<Hahmo> PalautaHahmot(Kyky kyky) {

        return this.PalautaHahmot(kyky);
    }

    /**
     * 
     * @return
     */
    public String PalautaNimi() {
        return this.nimi;
    }

    /**
     * 
     * @return
     */
    public ArrayList<Atribuutti> palautaKokoHomma() {

        return this.KykyJaKayttaja;
    }

    /**
     * 
     */
    public void UudistaJaPaivita() {
        for (Atribuutti atr : this.KykyJaKayttaja) {
            if (atr.palautaKyky().KayttoKerrat() != 0) {
                for (Hahmo hahmo : atr.palautaHahmot()) {
                    if (!hahmo.elossa()) {
                        atr.palautaHahmot().remove(hahmo);

                    }
                    if (atr.palautaHahmot().isEmpty()) {
                        this.KykyJaKayttaja.remove(atr);
                    }

                }

            } else {
                this.KykyJaKayttaja.remove(atr);
            }

        }

    }

    /**
     * 
     * @param aanestysSysteemi
     * @param tekstirajapinta
     */
    public void Run(AanestysSysteemi aanestysSysteemi, TekstiRajapinta tekstirajapinta) {
        // UudistaJaPaivita();
        tekstirajapinta.Console(this, this.pelattavat);

        Collections.sort(this.KykyJaKayttaja, this.comp);
        for (Atribuutti atr : this.KykyJaKayttaja) {
            tekstirajapinta.HerataPelaajat(atr.palautaHahmot());
            System.out.println(atr.palautaKyky().palautaNimi());
            Castaaminen(atr, tekstirajapinta, aanestysSysteemi);

        }

        kostot(tekstirajapinta);
        tekstirajapinta.JulistaKuolleiksi(this.buffiHallitsija.palautaKuolleet());
        this.buffiHallitsija.PuhdistetaanKuolleet();



    }

    /**
     * 
     * @param tekstirajapinta
     */
    public void kostot(TekstiRajapinta tekstirajapinta) {
        while (true) {
            HashMap<Hahmo, ArrayList<Object>> tarkistettavatHahmot = this.buffiHallitsija.tarkistettavat();
            if (tarkistettavatHahmot == null) {
                return;
            } else {
                for (Hahmo hahmo : tarkistettavatHahmot.keySet()) {
                    {
                        for (Object object : tarkistettavatHahmot.get(hahmo)) {
                            Kyky kyky = (Kyky) object;
                            Castaaminen(hahmo, kyky, tekstirajapinta);
                        }
                    }
                }
            }
        }
    }

    /**
     * 
     * @param atr
     * @param tekstirajapinta
     * @param aanestysSysteemi
     */
    public void Castaaminen(Atribuutti atr, TekstiRajapinta tekstirajapinta, AanestysSysteemi aanestysSysteemi) {
        Hahmo hyokkaaja = null;
        Hahmo target = null;
        if (atr.palautaHahmot().size() > 1) {
            tekstirajapinta.TulostaViesti("Valitaan kyvyn suorittaja");
            hyokkaaja = tekstirajapinta.ValitsePelaaja(atr.palautaHahmot());
        } else {
            hyokkaaja = atr.palautaHahmot().get(0);
        }
        tekstirajapinta.TulostaViesti("Valitaan targetti");
        if (atr.palautaHahmot().size() > 1) {
            aanestysSysteemi.asetaAanestysOikeutetut(atr.palautaHahmot());
            target = tekstirajapinta.Aanestys(aanestysSysteemi);
        } else {
            target = tekstirajapinta.ValitsePelaaja(this.muutosProsessi.Muutos(this.pelattavat));
        }
        String k = this.buffiHallitsija.TekeeTaikaa(hyokkaaja, atr.palautaKyky(), target);
        if (k == null) {
        } else if (!k.isEmpty()) {
            tekstirajapinta.TulostaViesti(k);
        }
    }

    /**
     * 
     * @param hahmo
     * @param kyky
     * @param tekstirajapinta
     */
    public void Castaaminen(Hahmo hahmo, Kyky kyky, TekstiRajapinta tekstirajapinta) {
        Hahmo target = tekstirajapinta.ValitsePelaaja(this.muutosProsessi.Muutos(this.pelattavat));
        this.buffiHallitsija.TekeeTaikaa(hahmo, kyky, target);

    }
}
