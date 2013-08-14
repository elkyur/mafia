/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import mafia.hahmot.Pelattava;
import mafia.kyvyt.Kyky;
import java.util.*;
import mafia.hahmot.Hahmo;
import mafia.peli.YhdenAsianLuokkia.AanestysSysteemi;
import mafia.peli.YhdenAsianLuokkia.Misc;
import mafia.peli.YhdenAsianLuokkia.SortKyky;
import mafia.userinterface.TekstiRajapinta;

/**
 *
 * @author Elkyur
 */
public class Faasi {

    private String nimi;
    boolean paiva;
    private HashMap<Kyky, ArrayList<Hahmo>> KykyJaKayttaja;
    String ViestiAlussa;
    String ViestiLopussa;
    private BuffienHallitsija buffiHallitsija;
    private ArrayList<Pelattava> pelattavat;
    private Misc muutosProsessi;
    private Comparator comp;

    public Faasi(String nimi, BuffienHallitsija buffiHallitsija, ArrayList<Pelattava> pelattavat) {
        this.comp = new SortKyky();
        this.nimi = nimi;
        this.KykyJaKayttaja = new HashMap<Kyky, ArrayList<Hahmo>>();
        this.ViestiAlussa = "";
        this.ViestiLopussa = "";
        this.buffiHallitsija = buffiHallitsija;
        this.pelattavat = pelattavat;
        this.muutosProsessi = new Misc();
    }

    public void asetaViestit(String alku, String loppu) {
        this.ViestiAlussa = alku;
        this.ViestiLopussa = loppu;
    }

    public void setDay(boolean day) {
        this.paiva = day;
    }

    public boolean palautaDay() {
        return this.paiva;

    }

    public void Lisaa(Kyky kyky, ArrayList<Hahmo> hahmot) {

        // System.out.println("Yritys lisätä");

        if (this.KykyJaKayttaja.containsKey(kyky)) {
            ArrayList<Hahmo> hahmote = this.KykyJaKayttaja.get(kyky);
            this.muutosProsessi.ListanLisaaminenListaan(hahmote, hahmot);
        } else {
            ArrayList<Hahmo> hahmot2 = new ArrayList<Hahmo>();
            this.muutosProsessi.ListanLisaaminenListaan(hahmot2, hahmot);
            this.KykyJaKayttaja.put(kyky, hahmot2);
        }

    }

    public void Lisaa(Kyky kyky, Pelattava pelattava) {
        if (this.KykyJaKayttaja.containsKey(kyky)) {
            ArrayList<Hahmo> hahmot = this.KykyJaKayttaja.get(kyky);
            LisametodiLisaykseen(hahmot, pelattava);
        } else {
            ArrayList<Hahmo> hahmot2 = new ArrayList<Hahmo>();
            LisametodiLisaykseen(hahmot2, pelattava);
            this.KykyJaKayttaja.put(kyky, hahmot2);
        }
    }

    private void LisametodiLisaykseen(ArrayList<Hahmo> lista, Pelattava pelattava) {
        for (Hahmo hahmo : pelattava.getTeam()) {
            lista.add(hahmo);
        }
    }

    public ArrayList<Hahmo> PalautaHahmot(Kyky kyky) {

        return this.PalautaHahmot(kyky);
    }

    public String PalautaNimi() {
        return this.nimi;
    }

    public HashMap<Kyky, ArrayList<Hahmo>> palautaKokoHomma() {

        return this.KykyJaKayttaja;
    }

    public void UudistaJaPaivita() {

        for (Kyky kyky : this.KykyJaKayttaja.keySet()) {



            if (kyky.UsageTimes() != 0) {



                for (Hahmo hahmo : this.KykyJaKayttaja.get(kyky)) {

                    if (!hahmo.elossa()) {
                        System.out.println("case This");
                        this.KykyJaKayttaja.get(kyky).remove(hahmo);
                    }
                    if (this.KykyJaKayttaja.get(kyky).isEmpty()) {
                        this.KykyJaKayttaja.remove(kyky);
                    }
                }

            } else {
                this.KykyJaKayttaja.remove(kyky);
            }
        }
    }

    public void Run(AanestysSysteemi aanestysSysteemi, TekstiRajapinta tekstirajapinta) {
        UudistaJaPaivita();
        tekstirajapinta.Console(this, this.pelattavat);
        List<Kyky> kyvyt = new ArrayList<Kyky>(this.KykyJaKayttaja.keySet());
        Collections.sort(kyvyt, this.comp);
        for (Kyky kyky : kyvyt) {
            tekstirajapinta.HerataPelaajat(this.KykyJaKayttaja.get(kyky));
            System.out.println(kyky.getName());
            Castaaminen(kyky, tekstirajapinta, aanestysSysteemi);

        }

        kostot(tekstirajapinta);
        tekstirajapinta.JulistaKuolleiksi(this.buffiHallitsija.palautaKuolleet());
        this.buffiHallitsija.PuhdistetaanKuolleet();



    }

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

    public void Castaaminen(Kyky kyky, TekstiRajapinta tekstirajapinta, AanestysSysteemi aanestysSysteemi) {
        Hahmo hyokkaaja = null;
        Hahmo target = null;
        if (this.KykyJaKayttaja.get(kyky).size() > 1) {
            System.out.println("CASTAAJIA OVER 1");
            tekstirajapinta.TulostaViesti("Valitaan kyvyn suorittaja");
            hyokkaaja = tekstirajapinta.ValitsePelaaja(this.KykyJaKayttaja.get(kyky));
        } else {
            hyokkaaja = this.KykyJaKayttaja.get(kyky).get(0);
        }
        tekstirajapinta.TulostaViesti("Valitaan targetti");
        if (this.KykyJaKayttaja.get(kyky).size() > 1) {
            aanestysSysteemi.asetaAanestysOikeutetut(this.KykyJaKayttaja.get(kyky));
            target = tekstirajapinta.Aanestys(aanestysSysteemi);
        } else {
            target = tekstirajapinta.ValitsePelaaja(this.muutosProsessi.Muutos(this.pelattavat));
        }
        String k = this.buffiHallitsija.TekeeTaikaa(hyokkaaja, kyky, target);
        if (k == null) {
        } else if (!k.isEmpty()) {
            tekstirajapinta.TulostaViesti(k);
        }
    }

    public void Castaaminen(Hahmo hahmo, Kyky kyky, TekstiRajapinta tekstirajapinta) {
        Hahmo target = tekstirajapinta.ValitsePelaaja(this.muutosProsessi.Muutos(this.pelattavat));
        this.buffiHallitsija.TekeeTaikaa(hahmo, kyky, target);

    }
}
