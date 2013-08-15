/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.userinterface;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import mafia.kyvyt.Atribuutti;
import mafia.kyvyt.Kyky;
import mafia.peli.Faasi;
import mafia.peli.YhdenAsianLuokkia.AanestysSysteemi;

/**
 *
 * Tämä luokka kantaa vastuun tekstikäyttöliittymästä. 
 */
public class TekstiRajapinta {

    private Scanner lukija;

    /**
     * 
     * @param lukija
     */
    public TekstiRajapinta(Scanner lukija) {
        this.lukija = lukija;
    }

    /**
     * 
     * @param hahmot
     * @return
     */
    public Hahmo ValitsePelaaja(ArrayList<Hahmo> hahmot) {
        while (true) {
            System.out.print("Valitse kohteesi: ");
            String k = lukija.nextLine();
            for (Hahmo hahmo : hahmot) {
                if (hahmo.getOmistajanNimi().equals(k)) {
                    System.out.println("Valittu: " + k);
                    return hahmo;
                }

            }
            System.out.println("ErrorCode 01: Invalid player name");

            if (k.equals("exit")) {
                return null;
            }
        }

    }

    /**
     * 
     */
    public void ListaaMahdollisuudet() {
        System.out.println("Olet admini, voit tehdä seuraavia asioita: ");
        System.out.println("[0] Poistu Consolesta");
        System.out.println("[1] Listaa Tiimit");
        System.out.println("[2] Poista Pelaaja Manuaalisesti");
        System.out.println("[3] Manuaalisesti uudellen järjestä tiimit");
        System.out.println("[4] Aloita aanestys");
        System.out.println("[5] Listaa faasin kyvyt");


    }

    /**
     * 
     * @param faasi
     * @param pelattavat
     */
    public void Console(Faasi faasi, ArrayList<Pelattava> pelattavat) {
        ListaaMahdollisuudet();
        String vastaus = "";
        while (true) {
            vastaus = lukija.nextLine();
            if (vastaus.equals("0")) {
                break;
            } else if (vastaus.equals("1")) {
                printtaaTiimit(pelattavat);
            } else if (vastaus.equals("5")) {
                ListaaKyvyt(faasi.palautaKokoHomma());
            }
        }

    }

    /**
     * 
     * @param systeemi
     * @return
     */
    public Hahmo Aanestys(AanestysSysteemi systeemi) {
        System.out.println("Äänestys on aloitettu");
        while (true) {
            String k = lukija.nextLine();
            if (k.equals("exit")) {
                return null;
            } else if (k.equals("aanesta")) {
            } else if (k.equals("manualInput")) {
                return ValitsePelaaja(systeemi.palautaMukanaOlevat());

            }

        }
    }

    /**
     * 
     * @param pelattavat
     */
    public void printtaaTiimit(ArrayList<Pelattava> pelattavat) {
        if ((pelattavat == null) || (pelattavat.isEmpty())) {
            System.out.println("Tällä hetkellä pelissä ei ole tiimejä");
        } else {
            System.out.println("Pelissä on tällä hetkellä mukana seuraavat: ");
            for (Pelattava pelattava : pelattavat) {
                System.out.println(pelattava.getNimi());
            }

        }

    }

    /**
     * 
     * @return
     */
    public boolean varmistus() {
        System.out.println("Oletko varma, että haluat tehdä tämän? Vastaa: y");
        String k = this.lukija.nextLine();
        if (k.equals("y")) {
            return true;
        }
        return false;

    }

    /**
     * 
     * @param pelattava
     */
    public void JulistaVoittaja(Pelattava pelattava) {
        System.out.println("Pelin voitti: " + pelattava.getNimi());

    }

    /**
     * 
     * @param k
     */
    public void TulostaViesti(String k) {
        System.out.println(k);
    }

    /**
     * 
     * @param hahmot
     */
    public void JulistaKuolleiksi(ArrayList<Hahmo> hahmot) {
        System.out.println("Kuolivat seuraavat pelaajat:");
        for (Hahmo hahmo : hahmot) {
            System.out.println(hahmo.getOmistajanNimi() + " : " + hahmo.getNimi());

        }

    }

    /**
     * 
     * @param atribuutit
     */
    public void ListaaKyvyt(ArrayList<Atribuutti> atribuutit) {
        System.out.println("Seuraavat kyvyt ovat käytettävissä tässä faasissa:");
        for (Atribuutti atr : atribuutit) {
            System.out.println(atr.palautaKyky().palautaNimi());

        }

    }

    /**
     * 
     * @param hahmot
     */
    public void HerataPelaajat(ArrayList<Hahmo> hahmot) {
        System.out.println("Herata seuraavat: ");
        for (Hahmo hahmo : hahmot) {
            System.out.println(hahmo.getOmistajanNimi());
        }
    }
}
