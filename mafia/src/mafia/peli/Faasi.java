/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import GraphicInterface.JPanerManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import mafia.kyvyt.Atribuutti;
import mafia.kyvyt.Kyky;
import mafia.peli.Logit.LogWriter;
import mafia.peli.YhdenAsianLuokkia.AanestysSysteemi;
import mafia.peli.YhdenAsianLuokkia.Misc;
import mafia.peli.YhdenAsianLuokkia.SortKyky;
import mafia.userinterface.TekstiRajapinta;

/**
 *
 * Kantaa vastuun pelissä esintyyvistä faaseista. Esimerkiksi Yö ja Päivä ovat
 * faaseja.
 */
public class Faasi {

    private String nimi;
    private ArrayList<Atribuutti> KykyJaKayttaja;
    private String ViestiAlussa;
    private String ViestiLopussa;
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
    public Faasi(String nimi, BuffienHallitsija buffiHallitsija) {
        this.comp = new SortKyky();
        this.nimi = nimi;
        this.KykyJaKayttaja = new ArrayList<Atribuutti>();
        this.ViestiAlussa = "";
        this.ViestiLopussa = "";
        this.buffiHallitsija = buffiHallitsija;
        this.muutosProsessi = new Misc();
        this.pelattavat = buffiHallitsija.Palautetaanpelattavat();
    }

    /**
     *
     * asettaa faasin alku ja loppu viestit
     *
     */
    public void asetaViestit(String alku, String loppu) {
        this.ViestiAlussa = alku;
        this.ViestiLopussa = loppu;
    }

    /**
     *
     *
     * Palauttaa atribuutin joka sisaltaa kyseisen kyvyn
     */
    public Atribuutti Sisaltaa(Kyky kyky) {
        for (Atribuutti atr : this.KykyJaKayttaja) {
            if (atr.palautaKyky().equals(kyky)) {
                return atr;
            }
        }
        return null;
    }

    /**
     *
     * Lisää kyvyn ja listan hahmoja jotka voivat käyttää kyseistä kykyä
     * (yhtäaikaa)
     *
     */
    public void Lisaa(Kyky kyky, ArrayList<Hahmo> hahmot) {

        // System.out.println("Yritys lisätä");

        Atribuutti atr = Sisaltaa(kyky);

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
     *
     * Palauttaa hahmot jotka voivat käyttää kyseistä kykyä
     */
    public ArrayList<Hahmo> PalautaHahmot(Kyky kyky) {

        for (Atribuutti atr : this.KykyJaKayttaja) {
            if (atr.palautaKyky().equals(kyky)) {
                return atr.palautaHahmot();
            }

        }
        return null;
    }

    /**
     *
     * Palauttaa nimen
     */
    public String PalautaNimi() {
        return this.nimi;
    }

    /**
     *
     * Palauuttaa atribuutit
     */
    public ArrayList<Atribuutti> palautaAtribuutit() {

        return this.KykyJaKayttaja;
    }

    /**
     * Faasin alussa uudistaa ja paivittaa mitkä kyvyt ovat vielä käytettävissä
     * tässä faasissa
     */
    public void UudistaJaPaivita() {
        for (int i = 0; i < this.KykyJaKayttaja.size(); i++) {
            Atribuutti atr = this.KykyJaKayttaja.get(i);
            if (atr.palautaKyky().KayttoKerrat() != 0) {
                UudistaJaPaivitaLisaOsa(atr);

            } else {
                this.KykyJaKayttaja.remove(atr);
            }
        }


    }

    /**
     *
     * Lisametodi UudistaJaPaivita Metodille
     */
    public void UudistaJaPaivitaLisaOsa(Atribuutti atr) {

        ArrayList<Hahmo> PoistettavatHahmot = new ArrayList<Hahmo>();


        for (Hahmo hahmo : atr.palautaHahmot()) {
            if (!hahmo.elossa()) {
                PoistettavatHahmot.add(hahmo);

            }

        }
        for (Hahmo hahmor : PoistettavatHahmot) {
            atr.palautaHahmot().remove(hahmor);
        }
        if (atr.palautaHahmot().isEmpty()) {
            this.KykyJaKayttaja.remove(atr);
        }
        if (atr.palautaHahmot().size() == 1) {
            atr.setWithVoting(false);
        }

    }

    /**
     *
     *
     * Käynnistää faasin tekstikäyttöliittymällä
     */
    public void Run(AanestysSysteemi aanestysSysteemi, TekstiRajapinta tekstirajapinta, LogWriter kirjoittaja) {
        UudistaJaPaivita();
        tekstirajapinta.Console(this, this.pelattavat);

        Collections.sort(this.KykyJaKayttaja, this.comp);
        for (Atribuutti atr : this.KykyJaKayttaja) {
            if (atr.palautaKyky().palautaHeti()) {
                tekstirajapinta.HerataPelaajat(atr.palautaHahmot());
                System.out.println(atr.palautaKyky().palautaNimi());
                Castaaminen(atr, tekstirajapinta, aanestysSysteemi, kirjoittaja);
            }
        }

        kostot(tekstirajapinta);
        tekstirajapinta.JulistaKuolleiksi(this.buffiHallitsija.palautaKuolleet());
        this.buffiHallitsija.PuhdistetaanKuolleet();
        this.buffiHallitsija.BuffitVanhetuvat();


    }
    
       /**
     *
     *
     * Käynnistää faasin graafisella liittymällä
     */
    
    public void GraphicRun(AanestysSysteemi aanestysSysteemi, JPanerManager manageri, LogWriter kirjoittaja) {
        UudistaJaPaivita();
        manageri.Console(this, this.pelattavat);

        

    }
    
    public void GraphicRunLisaOsa()
    {
    Collections.sort(this.KykyJaKayttaja, this.comp);
        for (Atribuutti atr : this.KykyJaKayttaja) {
            if (atr.palautaKyky().palautaHeti()) {
               // tekstirajapinta.HerataPelaajat(atr.palautaHahmot());
               // System.out.println(atr.palautaKyky().palautaNimi());
               // Castaaminen(atr, tekstirajapinta, aanestysSysteemi, kirjoittaja);
            }
        }

       // kostot(tekstirajapinta);
       // tekstirajapinta.JulistaKuolleiksi(this.buffiHallitsija.palautaKuolleet());
        this.buffiHallitsija.PuhdistetaanKuolleet();
        this.buffiHallitsija.BuffitVanhetuvat();

    
    }

    /**
     *
     * Hoitaa kostokyvyt
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
                            Castaaminene(hahmo, kyky, tekstirajapinta);
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * Castaa jonkun kyvyn, etsimällä hyokkaajan ja targetin
     *
     *
     */
    public void Castaaminen(Atribuutti atr, TekstiRajapinta tekstirajapinta, AanestysSysteemi aanestysSysteemi, LogWriter kirjoittaja) {

        Hahmo hyokkaaja = LoydaHyokkaaja(atr, tekstirajapinta);
        Hahmo target = LoydaTargetti(tekstirajapinta, atr, aanestysSysteemi);
        kirjoittaja.KykyCasti(atr.palautaHahmot(), atr.palautaKyky(), target);
        String k = this.buffiHallitsija.TekeeTaikaa(hyokkaaja, atr.palautaKyky(), target);
        if (k == null) {
        } else if (!k.isEmpty()) {
            tekstirajapinta.TulostaViesti(k);
        }
    }

    /**
     *
     *
     * Yksinkertaisempi metodi castaamista varten, jota kostometodi käyttää
     *
     */
    public void Castaaminene(Hahmo hahmo, Kyky kyky, TekstiRajapinta tekstirajapinta) {
        Hahmo target = tekstirajapinta.ValitsePelaaja(this.muutosProsessi.Muutos(this.pelattavat));
        this.buffiHallitsija.TekeeTaikaa(hahmo, kyky, target);

    }

    /**
     *
     * Jos castaamis kandidaatteja on enemmän kuin yksi, tämä metodi auttaa
     * löytämään kyvyn suorittajan
     */
    public Hahmo LoydaHyokkaaja(Atribuutti atr, TekstiRajapinta tekstirajapinta) {
        Hahmo hyokkaaja;
        if (atr.palautaHahmot().size() > 1) {
            tekstirajapinta.TulostaViesti("Valitaan kyvyn suorittaja");
            hyokkaaja = tekstirajapinta.ValitsePelaaja(atr.palautaHahmot());
        } else {
            hyokkaaja = atr.palautaHahmot().get(0);
        }
        return hyokkaaja;
    }

    /**
     *
     * Tämä metodi auttaa löytämään targetin
     */
    public Hahmo LoydaTargetti(TekstiRajapinta tekstirajapinta, Atribuutti atr, AanestysSysteemi aanestysSysteemi) {
        Hahmo target;
        tekstirajapinta.TulostaViesti("Valitaan targetti");
        if (atr.returnWithVoting()) {
            aanestysSysteemi.asetaAanestysOikeutetut(atr.palautaHahmot());
            target = tekstirajapinta.Aanestys(aanestysSysteemi);
        } else {
            target = tekstirajapinta.ValitsePelaaja(this.muutosProsessi.Muutos(this.pelattavat));
        }
        return target;
    }

    public int LaskePelattavat() {
        int i = 0;
        for (Pelattava pelattava : this.pelattavat) {
            for (Hahmo hahmo : pelattava.getTeam()) {
                i++;
            }
        }
        return i;
    }
}
