/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.YhdenAsianLuokkia;

import java.util.ArrayList;
import mafia.hahmot.*;
import mafia.kyvyt.Kyky;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Elkyur
 */
public class SekoittajaTest {

    private Sekoittaja mixer;
    private ArrayList<Pelaaja> pelaajat;
    private ArrayList<Hahmo> hahmot;
    private Rooli mafia;
    private Rooli Kansalainen;

    public SekoittajaTest() {
    }

    @Before
    public void setUp() {
        this.mixer = new Sekoittaja();
        this.pelaajat = new ArrayList<Pelaaja>();
        this.hahmot = new ArrayList<Hahmo>();
        this.mafia = new Rooli("Mafia");
        this.Kansalainen = new Rooli("Kansalainen");


    }

    @Test
    public void testSekoitaTyhjallaHahmolla() {
        this.pelaajat.add(new Pelaaja("pekka"));

        assertTrue(!this.mixer.Sekoita(this.hahmot, this.pelaajat));
    }

    @Test
    public void testSekoitaTyhjallaPelaajalla() {
        this.hahmot.add(new Hahmo(this.mafia));

        assertEquals(false, this.mixer.Sekoita(this.hahmot, this.pelaajat));
    }

    @Test
    public void testHahmojaEnemmaKuinPelaajia() {
        this.pelaajat.add(new Pelaaja("pekka"));
        this.hahmot.add(new Hahmo(this.mafia));
        this.hahmot.add(new Hahmo(this.Kansalainen));

        assertEquals(false, this.mixer.Sekoita(this.hahmot, this.pelaajat));
    }

    @Test
    public void testPelaajiaEnemmanKuinHahmoja() {
        this.pelaajat.add(new Pelaaja("pekka"));
        this.pelaajat.add(new Pelaaja("olli"));
        this.hahmot.add(new Hahmo(this.mafia));

        assertEquals(false, this.mixer.Sekoita(this.hahmot, this.pelaajat));
    }

    @Test
    public void PelaajiaJaHahmojaSamanVerran() {
        this.pelaajat.add(new Pelaaja("pekka"));
        this.pelaajat.add(new Pelaaja("olli"));
        this.hahmot.add(new Hahmo(this.mafia));
        this.hahmot.add(new Hahmo(this.Kansalainen));

        assertEquals(true, this.mixer.Sekoita(this.hahmot, this.pelaajat));
    }

    @Test
    public void MetodiToimiiOikeasti() {
        this.pelaajat.add(new Pelaaja("pekka"));
        this.pelaajat.add(new Pelaaja("olli"));
        this.hahmot.add(new Hahmo(this.mafia));
        this.hahmot.add(new Hahmo(this.Kansalainen));
        this.mixer.Sekoita(this.hahmot, this.pelaajat);
        assertTrue(this.hahmot.get(0).palautaOmistaja().equals(this.pelaajat.get(0)) || this.hahmot.get(0).palautaOmistaja().equals(this.pelaajat.get(1)));
        
    }

    public void MetodiToimiiOikeastiVaikkaSinneLaittaisikinSamanAsianTiimeina() {
        this.pelaajat.add(new Pelaaja("p1"));
        this.pelaajat.add(new Pelaaja("p2"));
        this.pelaajat.add(new Pelaaja("p3"));
        this.hahmot.add(new Hahmo(this.mafia));
        this.hahmot.add(new Hahmo(this.mafia));
        this.hahmot.add(new Hahmo(this.Kansalainen));
        Tiimi tiimi = new Tiimi("Mafiaa");
        Tiimi kansa = new Tiimi("KansanPuolellaOlevat");
        tiimi.LisaaPelaaja(this.hahmot.get(0));
        tiimi.LisaaPelaaja(this.hahmot.get(1));
        tiimi.LisaaPelaaja(this.hahmot.get(2));
        this.mixer.Sekoita(this.hahmot, this.pelaajat);
        assertTrue(this.hahmot.get(0).palautaOmistaja().equals(this.pelaajat.get(0)) || this.hahmot.get(0).palautaOmistaja().equals(this.pelaajat.get(1)) || this.hahmot.get(0).palautaOmistaja().equals(this.pelaajat.get(2)));

    }

   
    /**
     * Test of Sekoita method, of class Sekoittaja.
     */
}
