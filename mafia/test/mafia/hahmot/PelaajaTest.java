/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.hahmot;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Elkyur
 */
public class PelaajaTest {
    
    private Pelaaja pelaaja;
    private Statistiikka stat;
    
    public PelaajaTest() {
        
    }

 
    @Before
    public void setUp() {
        this.stat = new Statistiikka();
        this.pelaaja = new Pelaaja("elkyur");
        this.pelaaja.setStatistics(this.stat);
    }

    @Test
    public void testPalautaNimi() {
    assertEquals(this.pelaaja.PalautaNimi(), "elkyur");
    }



    @Test
    public void testPalautaStatistiikka() {
       Statistiikka state = new Statistiikka();
       this.pelaaja.setStatistics(state);
       assertEquals(this.pelaaja.palautaStatistiikka(), state);
    }


    @Test
    public void testEqualsPitaisiToimiaJosPelaajaSamaNimiSamaStatistiikka() {
       Pelaaja pel = new Pelaaja("elkyur");
       pel.setStatistics(stat);
       assertEquals(pel.equals(this.pelaaja), true);
     
    }
     @Test
    public void testEqualsEiPitaisiToimiaJosPelaajaEriNimi() {
        Pelaaja pel = new Pelaaja("dfgsfdfg");
       pel.setStatistics(stat);
       assertEquals(this.pelaaja.equals(pel), false );
     
    }
}
