/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Rooli;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Elkyur
 */
public class SkannausTest {

    private Hahmo hyokkaaja;
    private Hahmo puolustaja;
    private Hahmo hulluHahmo;
    private Buff buffi;
    private Skannaus kyky;
    private Rooli kansalainen;
    private Rooli mafia;
    private Rooli hullu;

    public SkannausTest() {
    }

    @Before
    public void setUp() {
        this.kansalainen = new Rooli("kansalainen");
        this.hullu = new Rooli("hullu");
        this.mafia = new Rooli("mafia");
        this.hulluHahmo = new Hahmo(this.hullu);
        this.hyokkaaja = new Hahmo(this.kansalainen);
        this.puolustaja = new Hahmo(this.mafia);
        BuffinTyyppi hyokkaava = new BuffinTyyppi("tappo");
        Buff tappo = new Buff("tappo", 0, hyokkaava);
        this.buffi = tappo;
        this.kyky = new Skannaus("abc", this.buffi);




    }

    @Test
    public void mafiaJaaKiinniScannauksessa() {
        ArrayList<Rooli> kiinnijaamiset = new ArrayList<Rooli>();
        kiinnijaamiset.add(this.mafia);
        this.kyky.Lisaa(kiinnijaamiset, "Mafia");
        assertEquals("Scannauksen tulos: Mafia", this.kyky.Toiminnallisuus(this.hyokkaaja, this.puolustaja));
    }
    @Test
    public void hulluEiJaaKiinniJosSeEiHaluaJaadaKiinni() {
        ArrayList<Rooli> kiinnijaamiset = new ArrayList<Rooli>();
        kiinnijaamiset.add(this.mafia);
        this.kyky.Lisaa(kiinnijaamiset, "Mafia");
        assertEquals("Scannauksen tulos: Muu", this.kyky.Toiminnallisuus(this.hyokkaaja, this.hulluHahmo));
    }
    
    @Test
    public void ToisaaltaJosHulluOnkinPaha() {
        ArrayList<Rooli> kiinnijaamiset = new ArrayList<Rooli>();
        kiinnijaamiset.add(this.mafia);
        kiinnijaamiset.add(this.hullu);
        this.kyky.Lisaa(kiinnijaamiset, "Mafia");
        assertEquals("Scannauksen tulos: Mafia", this.kyky.Toiminnallisuus(this.hyokkaaja, this.hulluHahmo));
    }
     @Test
    public void ItsensaSkannaaminenToimii() {
        ArrayList<Rooli> kiinnijaamiset = new ArrayList<Rooli>();
        kiinnijaamiset.add(this.mafia);
        kiinnijaamiset.add(this.hullu);
        this.kyky.Lisaa(kiinnijaamiset, "Mafia");
        assertEquals("Scannauksen tulos: Muu", this.kyky.Toiminnallisuus(this.hyokkaaja, this.hyokkaaja));
    }

    @Test
    public void testToiminnallisuus() {
    }
}
