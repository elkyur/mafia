/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.userinterface;

import java.util.ArrayList;
import java.util.Scanner;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import mafia.peli.Faasi;

/**
 *
 * @author Elkyur
 */
public class TextInterface {

    private Scanner lukija;

    public TextInterface(Scanner lukija) {
        this.lukija = lukija;
    }

    public Hahmo ValitsePelaaja(ArrayList<Hahmo> hahmot) {
        while (true) {
            System.out.print("Valitse kohteesi: ");
            String k = lukija.nextLine();
            for (Hahmo hahmo : hahmot) {
                if (hahmo.getNimi().equals(k)) {
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

    }

    public void Console(Faasi faasi, ArrayList<Pelattava> pelattavat) {
        ListaaMahdollisuudet();
        String vastaus = "";
        while (true) {
            vastaus = lukija.nextLine();
            if (vastaus.equals("0")) {
                break;
            }
            else if (vastaus.equals("1"))
            {
            printtaaTiimit(pelattavat);
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
}
