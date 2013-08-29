/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.YhdenAsianLuokkia;

import java.util.ArrayList;
import mafia.hahmot.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Elkyur
 */
public class MiscTest {

    Rooli mafia;
    Rooli kansalainen;
    Rooli hullu;
    Rooli hilleri;
    Rooli poliisi;
    ArrayList<Hahmo> hahmot;
    Hahmo Mafia01;
    Hahmo Mafia02;
    Hahmo Kansa01;
    Hahmo Kansa02;
    Hahmo Poliisi;
    Hahmo Heaalaaja;
    Hahmo Kansa03;
    Hahmo Hullu;
    ArrayList<Pelaaja> pelaajat;
    Tiimi mafiat;
    Tiimi kansat;
    ArrayList<Pelattava> pelattavat;
    Misc misc;

    public MiscTest() {
    }

    @Before
    public void setUp() {
        this.misc = new Misc();
        this.pelattavat = new ArrayList<Pelattava>();
        mafiat = new Tiimi("mafiat");
        kansat = new Tiimi("kansat");
        mafia = new Rooli("Mafia");
        kansalainen = new Rooli("Kansalainen");
        hullu = new Rooli("Hullu");
        hilleri = new Rooli("heaalaja");
        poliisi = new Rooli("Poliisi");
        hahmot = new ArrayList<Hahmo>();
        Mafia01 = new Hahmo(mafia);
        Mafia02 = new Hahmo(mafia);
        Kansa01 = new Hahmo(kansalainen);
        Kansa02 = new Hahmo(kansalainen);
        Poliisi = new Hahmo(poliisi);
        Heaalaaja = new Hahmo(hilleri);
        Kansa03 = new Hahmo(kansalainen);
        Hullu = new Hahmo(hullu);

        pelattavat.add(mafiat);
        pelattavat.add(kansat);
        pelattavat.add(Hullu);
        hahmot.add(Mafia01);
        hahmot.add(Mafia02);
        hahmot.add(Kansa01);
        hahmot.add(Kansa02);
        hahmot.add(Poliisi);
        hahmot.add(Heaalaaja);
        hahmot.add(Kansa03);
        hahmot.add(Hullu);

        pelaajat = new ArrayList<Pelaaja>();
        pelaajat.add(new Pelaaja("lolcat"));
        pelaajat.add(new Pelaaja("abc"));
        pelaajat.add(new Pelaaja("guest"));
        pelaajat.add(new Pelaaja("pro"));
        pelaajat.add(new Pelaaja("noob"));
        pelaajat.add(new Pelaaja("helllord"));
        pelaajat.add(new Pelaaja("heaven"));
        pelaajat.add(new Pelaaja("random"));

        Mafia01.asetaOmistaja(pelaajat.get(0));
        Mafia02.asetaOmistaja(pelaajat.get(1));
        Kansa01.asetaOmistaja(pelaajat.get(2));
        Kansa02.asetaOmistaja(pelaajat.get(3));
        Poliisi.asetaOmistaja(pelaajat.get(4));
        Heaalaaja.asetaOmistaja(pelaajat.get(5));
        Kansa03.asetaOmistaja(pelaajat.get(6));
        Hullu.asetaOmistaja(pelaajat.get(7));

    }

    @Test
    public void testMuutos() {

        boolean k = true;
        ArrayList<Hahmo> hahmote = misc.Muutos(pelattavat);

        for (Hahmo hah : this.hahmot) {
            if (!hahmote.contains(hah)) {
                k = false;
            }

        }
        assertTrue(k);


    }

    @Test
    public void testEtsiMafia() {
        boolean k = true;

        ArrayList<Hahmo> hahmote = misc.Etsi(hahmot, mafia);

        if (!hahmote.contains(Mafia01)) {
            k = false;
        }
        if (!hahmote.contains(Mafia02)) {
            k = false;
        }

        assertTrue(k);

    }

    @Test
    public void testListanLisaaminenListaan() {
        Hahmo hahmo = new Hahmo(mafia);

        ArrayList<Hahmo> yksiAlkioinenlista = new ArrayList<Hahmo>();
        yksiAlkioinenlista.add(hahmo);

        misc.ListanLisaaminenListaan(hahmot, yksiAlkioinenlista);

        assertTrue(hahmot.contains(hahmo));

    }

    @Test
    public void testMuunnaPelaajiksi() {
        boolean k = true;
        ArrayList<Pelaaja> pel = misc.muunnaPelaajiksi(hahmot);
        for (Pelaaja pelaaja : pel) {
            if (!pelaajat.contains(pelaaja)) {
                k = false;
            }
        }

        assertTrue(k);
    }

    @Test
    public void testEtsiPelaajienJoukossa() {
        Hahmo hahmo = misc.EtsiPelaajienJoukossa(pelaajat.get(0), hahmot);
        assertTrue(hahmo.equals(Mafia01));
    }
       @Test
    public void testEtsiPelaajienJoukossaJosEiKummiskaanLoydy() {
        Hahmo hahmo = misc.EtsiPelaajienJoukossa(new Pelaaja("lol"), hahmot);
        assertTrue(hahmo.equals(null));
    }
}

   
