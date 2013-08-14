/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia;

import java.util.*;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Rooli;
import mafia.kyvyt.Buff;
import mafia.kyvyt.BuffinTyyppi;
import mafia.kyvyt.Kyky;
import mafia.kyvyt.NormiKyky;
import mafia.peli.YhdenAsianLuokkia.Sekoittaja;
import mafia.peli.YhdenAsianLuokkia.SortKyky;
import java.lang.Object;
import mafia.peli.ValmiiksAsetetut.AlkuperainenMafiooso;
import mafia.userinterface.TekstiRajapinta;

/**
 *
 * @author Elkyur
 */
public class Mafia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        KokeillePelia();

    }

    public static void KokeilleYhtasuuruutta() {

        BuffinTyyppi tyyppi = new BuffinTyyppi("k");
        Buff buffi = new Buff("salama", 0, tyyppi);
        NormiKyky murhata = new NormiKyky("123", buffi, false);
        NormiKyky murhatar = new NormiKyky("1233", buffi, false);
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

    public static void SortausKokeilu() {

        Comparator comp = new SortKyky();


        TreeMap<Kyky, Pelaaja> mappi = new TreeMap<Kyky, Pelaaja>(comp);

        BuffinTyyppi tyyppi0 = new BuffinTyyppi("tuli", 1);
        BuffinTyyppi tyyppi1 = new BuffinTyyppi("vesi", 1);
        BuffinTyyppi tyyppi2 = new BuffinTyyppi("salama", 2);

        Buff buffi0 = new Buff("tuli", 0, tyyppi0);
        Buff buffi1 = new Buff("tuli", 0, tyyppi1);
        Buff buffi2 = new Buff("tuli", 0, tyyppi2);

        Pelaaja pelaaja1 = new Pelaaja("p1");
        Pelaaja pelaaja2 = new Pelaaja("p2");
        Pelaaja pelaaja3 = new Pelaaja("p3");

        Kyky kyky1 = new NormiKyky("", buffi0, true);
        Kyky kyky2 = new NormiKyky("", buffi1, true);
        Kyky kyky3 = new NormiKyky("", buffi2, true);

        mappi.put(kyky3, pelaaja3);
        mappi.put(kyky2, pelaaja2);
        mappi.put(kyky1, pelaaja1);



        for (Kyky kyky : mappi.keySet()) {
            System.out.println(kyky.getBuffi().returnBuffinTyyppi().palautaPrioriteetti());
        }



    }

    public static void kokeilleToimiikoCastaaminenOikein() {
        ArrayList<Object> objektilista = new ArrayList<Object>();
        objektilista.add(new Pelaaja("p1"));
        objektilista.add(new Pelaaja("p2"));
        objektilista.add(new Pelaaja("p3"));



    }
    // Vihdoinkin!

    public static void KokeillePelia() {
        Scanner lukija = new Scanner(System.in);
        TekstiRajapinta rajapinta = new TekstiRajapinta(lukija);
        Sekoittaja sekoittaja = new Sekoittaja();
        ArrayList<Pelaaja> pelaajat = new ArrayList<Pelaaja>();

        for (int i = 1; i <= 8; i++) {
            pelaajat.add(new Pelaaja("player" + i));
        }

        AlkuperainenMafiooso mafiooso = new AlkuperainenMafiooso(pelaajat, sekoittaja, rajapinta);
        mafiooso.Run();

    }
}
