/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Rooli;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Elkyur
 */
public class BuffTest {
    
    private Buff buffi;
    private BuffinTyyppi tyyppi;
    
    public BuffTest() {
        
    }


    
    @Before
    public void setUp() {
        this.tyyppi = new BuffinTyyppi("UusiTyyppi");
        this.buffi = new Buff("tappo", 0 , this.tyyppi);
       
        
    }

    @Test
    public void testGetNimi() {
      assertEquals(this.buffi.getNimi(), "tappo");
    }



    @Test
    public void testReturnMessageToUser() {
       this.buffi.setaddMessages("user", "admin");
       assertEquals(this.buffi.returnMessageToUser(), "user");
    }

   

    @Test
    public void testReturnMessageToAdmin() {
        this.buffi.setaddMessages("user", "admin");
       assertEquals(this.buffi.returnMessageToAdmin(), "admin");
     
    }

    @Test
    public void testReturnExpireRate() {
      assertEquals(this.buffi.returnExpireRate(), 0);
    }

    @Test
    public void testLisaaErikoisViittaus() {
      Hahmo hahmo = new Hahmo(new Rooli("mafria"));
      this.buffi.lisaaErikoisViittaus(hahmo);
      assertEquals(this.buffi.PalautaKokoHomma().contains(hahmo), true);
    }

   

    @Test
    public void testReturnBuffinTyyppi() {
     
    }

    @Test
    public void testExpire() {
       
    }
}
