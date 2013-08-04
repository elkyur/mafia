/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia;

import java.util.ArrayList;
import java.util.Random;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Rooli;
import mafia.kyvyt.PerusBuffi;
import mafia.kyvyt.Kyky;
import mafia.kyvyt.PerusKyky;
import mafia.peli.Sekoittaja;

/**
 *
 * @author Elkyur
 */
public class Mafia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        KokeilleYhtasuuruutta();
    }

    public static void KokeilleYhtasuuruutta() {
        PerusBuffi buffi = new PerusBuffi("salama", "tappava", 0);
        PerusKyky murhata = new PerusKyky("123", buffi, false);
        PerusKyky murhatar = new PerusKyky("1233", buffi, false);
        if (murhata.equals(murhatar)) {
            System.out.println("Se onnistui");
        } else {
            System.out.println("ei");
        }
    }

    public static void KokeilleSekoittajaa() {
        Rooli mafia = new Rooli("Mafia");
        Rooli kansalainen = new Rooli("Kansalainen");
        Rooli hullu = new Rooli("Hullu");
        Rooli hilleri = new Rooli("heaalaja");
        Rooli poliisi = new Rooli("Poliisi");
        Sekoittaja sekoittaja = new Sekoittaja();
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        Hahmo Mafia01 = new Hahmo(mafia);
        Hahmo Mafia02 = new Hahmo(mafia);
        Hahmo Kansa01 = new Hahmo(kansalainen);
        Hahmo Kansa02 = new Hahmo(kansalainen);
        Hahmo Poliisi = new Hahmo(poliisi);
        Hahmo Heaalaaja = new Hahmo(hilleri);
        Hahmo Kansa03 = new Hahmo(kansalainen);
        Hahmo Hullu = new Hahmo(hullu);

        hahmot.add(Mafia01);
        hahmot.add(Mafia02);
        hahmot.add(Kansa01);
        hahmot.add(Kansa02);
        hahmot.add(Poliisi);
        hahmot.add(Heaalaaja);
        hahmot.add(Kansa03);
        hahmot.add(Hullu);

        ArrayList<Pelaaja> pelaajat = new ArrayList<Pelaaja>();
        pelaajat.add(new Pelaaja("lolcat"));
        pelaajat.add(new Pelaaja("abc"));
        pelaajat.add(new Pelaaja("guest"));
        pelaajat.add(new Pelaaja("pro"));
        pelaajat.add(new Pelaaja("noob"));
        pelaajat.add(new Pelaaja("helllord"));
        pelaajat.add(new Pelaaja("heaven"));
        pelaajat.add(new Pelaaja("random"));

        sekoittaja.Sekoita(hahmot, pelaajat);
        sekoittaja.Sekoita(hahmot, pelaajat);


        for (Hahmo hahmo : hahmot) {
            System.out.println("Pelaaja: " + hahmo.palautaOmistaja().PalautaNimi() + " hahmo: " + hahmo.getNimi());
        }
    }
}
