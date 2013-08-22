/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.ReadWriting;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import mafia.hahmot.Pelaaja;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Elkyur
 */
public class LoaderTest {
    
    private Kirjoittaja writer;
    private Loader loader;
    
    public LoaderTest() {
    }

  
    @Before
    public void setUp() {
     this.writer = new Kirjoittaja();
     this.loader = new Loader();
    }

    @Test
    public void testSetBuffiLukija() throws Exception {
   File file = new File("gamedata/structures/Pelaajat.txt");
    writer.asetaPelaajat(file);
    this.loader.setPelaajat(file);
    Pelaaja pelaaja = new Pelaaja("CodingMachine");
    ArrayList<Pelaaja> pelaajat = this.loader.palautaPelaajat();
    
    
    }

  



  

  
}
