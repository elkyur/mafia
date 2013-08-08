/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.YhdenAsianLuokkia;

import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Rooli;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Elkyur
 */
public class AanestysSysteemiTest {

    private AanestysSysteemi systeemi;
    private Rooli Kansalainen;
    private ArrayList<Hahmo> AanestyksessaMukana;

    public AanestysSysteemiTest() {
    }

    @Before
    public void setUp() {
        this.AanestyksessaMukana = new ArrayList<Hahmo>();
        this.systeemi = new AanestysSysteemi(this.AanestyksessaMukana);
        this.Kansalainen = new Rooli("Kansalainen");
        for (int i = 1; i <= 8; i++) {
            Hahmo hahmo = new Hahmo(this.Kansalainen);
            hahmo.asetaOmistaja(new Pelaaja("Pelaaja" + i));
            this.AanestyksessaMukana.add(hahmo);

        }

    }

    @Test
    public void testAanestaminenToimii() {

        assertEquals(this.systeemi.Aanesta(this.AanestyksessaMukana.get(0), this.AanestyksessaMukana.get(1)), null);


    }

    @Test
    public void testAanestaminenToimiiLukuina() {
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(0), this.AanestyksessaMukana.get(1));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(2), this.AanestyksessaMukana.get(1));
        assertEquals(this.systeemi.YhteensaMukana(), 2);


    }

    @Test
    public void TaponPitaisiToimiaJosYliPuoletOvatAanestaneet() {
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(1), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(2), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(3), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(4), this.AanestyksessaMukana.get(0));

        assertEquals(this.systeemi.Aanesta(this.AanestyksessaMukana.get(5), this.AanestyksessaMukana.get(0)), this.AanestyksessaMukana.get(0));


    }

    @Test
    public void MuttaEiPitaisiToimiaJosVainPuoletOvatAanestaneet() {
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(1), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(2), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(3), this.AanestyksessaMukana.get(0));


        assertEquals(this.systeemi.Aanesta(this.AanestyksessaMukana.get(4), this.AanestyksessaMukana.get(0)), null);


    }

    @Test
    public void TaponEiPitaisiToimiaJosSamaJabaAanestaaMontaKertaa() {
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(1), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(2), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(3), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(4), this.AanestyksessaMukana.get(0));

        assertEquals(this.systeemi.Aanesta(this.AanestyksessaMukana.get(1), this.AanestyksessaMukana.get(0)), null);

    }

    @Test
    public void UlkoPuolisiaEiSaaPaastaaAanestamaan() {
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(1), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(2), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(3), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(4), this.AanestyksessaMukana.get(0));

        Hahmo hahmo = new Hahmo(this.Kansalainen);
        hahmo.asetaOmistaja(new Pelaaja("Ulkopuolinen"));

        assertEquals(this.systeemi.Aanesta(hahmo, this.AanestyksessaMukana.get(0)), null);

    }

    /**
     * Test of UnAanesta method, of class AanestysSysteemi.
     */
    @Test
    public void testUnAanesta() {
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(1), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(2), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(3), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(4), this.AanestyksessaMukana.get(0));
        this.systeemi.UnAanesta(this.AanestyksessaMukana.get(1), this.AanestyksessaMukana.get(0));
        assertEquals(this.systeemi.Aanesta(this.AanestyksessaMukana.get(5), this.AanestyksessaMukana.get(0)), null);
    }

    /**
     * Test of AanestysLaskelmat method, of class AanestysSysteemi.
     */

    /**
     * Test of YhteensaMukana method, of class AanestysSysteemi.
     */
    @Test
    public void testaaToimiikoLaskelmat() {
         this.systeemi.Aanesta(this.AanestyksessaMukana.get(1), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(2), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(3), this.AanestyksessaMukana.get(0));
        this.systeemi.Aanesta(this.AanestyksessaMukana.get(4), this.AanestyksessaMukana.get(0));
        this.systeemi.UnAanesta(this.AanestyksessaMukana.get(1), this.AanestyksessaMukana.get(0));
        assertEquals(this.systeemi.AanestysLaskelmat(), this.AanestyksessaMukana.get(0).getOmistajanNimi() + ": 4 \n");
        
    }
}
