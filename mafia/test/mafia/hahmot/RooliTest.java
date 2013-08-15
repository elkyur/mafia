/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.hahmot;

import mafia.kyvyt.Buff;
import mafia.kyvyt.BuffinTyyppi;
import mafia.kyvyt.Kyky;
import mafia.kyvyt.NormiKyky;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Elkyur
 */
public class RooliTest {

    private Rooli rooli;

    public RooliTest() {
    }

    @Before
    public void setUp() {
        this.rooli = new Rooli("Mafia");

    }

    @Test
    public void testTarkistaOnkoKykyKaytossa() {
        BuffinTyyppi a = new BuffinTyyppi("a");
        Buff b = new Buff("lol", 0, a);
        Kyky z = new NormiKyky("a", b, true);
        this.rooli.LaitaLisaaKykyja(z);
        assertTrue(this.rooli.TarkistaOnkoKykyKaytossa(z));


    }

    @Test
    public void testTarkistaOnkoKykyKaytossaVaikkaSemmoistaEiLisaiskaa() {
        BuffinTyyppi a = new BuffinTyyppi("a");
        Buff b = new Buff("lol", 0, a);
        Kyky z = new NormiKyky("a", b, true);

        assertTrue(!this.rooli.TarkistaOnkoKykyKaytossa(z));


    }

    @Test
    public void testPalautaNimi() {
        assertEquals(this.rooli.PalautaNimi(), "Mafia");

    }
}
