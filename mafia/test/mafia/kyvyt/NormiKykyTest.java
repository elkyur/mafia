/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

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
public class NormiKykyTest {
    
    private Hahmo hyokkaaja;
    private Hahmo puolustaja;
    private NormiKyky kyky;
    private Buff buffi;
 
    
    public NormiKykyTest() {
        
        
        
    }
    
       @Before
    public void setUp() {

        this.hyokkaaja = new Hahmo(new Rooli("mafia"));
        this.puolustaja = new Hahmo(new Rooli("kansalainen"));
        BuffinTyyppi hyokkaava = new BuffinTyyppi("tappo");
        Buff tappo = new Buff("tappo", 0, hyokkaava);
        this.buffi = tappo;
        this.kyky = new NormiKyky("abc", this.buffi, true);
        
       
        

    }

  

    @Test
    public void testToiminnallisuus() {
       this.kyky.Toiminnallisuus(this.hyokkaaja, this.puolustaja);
       assertEquals(this.puolustaja.ListaaBuffit().get(0), this.buffi);
       
    }

   
}
