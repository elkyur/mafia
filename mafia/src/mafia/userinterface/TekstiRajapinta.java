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
import mafia.kyvyt.Kyky;
import mafia.peli.Faasi;
import mafia.peli.YhdenAsianLuokkia.AanestysSysteemi;

/**
 *
 * @author Elkyur
 */
public class TekstiRajapinta {

    private Scanner lukija;

    public TekstiRajapinta(Scanner lukija) {
        this.lukija = lukija;
    }

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

    public void ListaaMahdollisuudet() {
        System.out.println("Olet admini, voit tehdä seuraavia asioita: ");
        System.out.println("[0] Poistu Consolesta");
        System.out.println("[1] Listaa Tiimit");
        System.out.println("[2] Poista Pelaaja Manuaalisesti");
        System.out.println("[3] Manuaalisesti uudellen järjestä tiimit");
        System.out.println("[4] Aloita aanestys");
        System.out.println("[5] Listaa faasin kyvyt");


    }

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
                ListaaKyvyt(faasi.palautaKokoHomma().keySet());
            }
        }

    }

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

    public boolean varmistus() {
        System.out.println("Oletko varma, että haluat tehdä tämän? Vastaa: y");
        String k = this.lukija.nextLine();
        if (k.equals("y")) {
            return true;
        }
        return false;

    }

    public void JulistaVoittaja(Pelattava pelattava) {
        System.out.println("Pelin voitti: " + pelattava.getNimi());

    }

    public void TulostaViesti(String k) {
        System.out.println(k);
    }

    public void JulistaKuolleiksi(ArrayList<Hahmo> hahmot) {
        System.out.println("Kuolivat seuraavat pelaajat:");
        for (Hahmo hahmo : hahmot) {
            System.out.println(hahmo.getOmistajanNimi() + " : " + hahmo.getNimi());

        }

    }

    public void ListaaKyvyt(Set<Kyky> kyvyt) {
        System.out.println("Seuraavat kyvyt ovat käytettävissä tässä faasissa:");
        for (Kyky kyky : kyvyt) {
            System.out.println(kyky.getName());

        }

    }

    public void HerataPelaajat(ArrayList<Hahmo> hahmot) {
        System.out.println("Herata seuraavat: ");
        for (Hahmo hahmo : hahmot) {
            System.out.println(hahmo.getOmistajanNimi());
        }
    }
}
