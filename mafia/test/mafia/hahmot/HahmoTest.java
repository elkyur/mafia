/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.hahmot;

import java.util.ArrayList;
import mafia.kyvyt.Buff;
import mafia.kyvyt.BuffinTyyppi;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Elkyur
 */
public class HahmoTest {

    private Hahmo hahmo;

    public HahmoTest() {
    }

    @Before
    public void setUp() {

        this.hahmo = new Hahmo(new Rooli("mafia"));

    }

    @Test
    public void testLisaaViesti() {
        this.hahmo.LisaaViesti("toimiikohan");
        assertEquals("toimiikohan", this.hahmo.PalautaViestit().get(0));

    }

    @Test
    public void testTyhjennaViestit() {
        this.hahmo.LisaaViesti("toimiikohan");
        this.hahmo.LisaaViesti("toimiikohantama");
        this.hahmo.TyhjennaViestit();
        this.hahmo.LisaaViesti("UusViesti");
        assertEquals("UusViesti", this.hahmo.PalautaViestit().get(0));
    }

    @Test
    public void alussaHahmonOnOltavaElossa() {
        assertTrue(this.hahmo.elossa());

    }

    @Test
    public void KunHahmolleLaitetaanTilaNollaNiinSeMyosKuolee() {
        this.hahmo.Vaihtatilaa(0);
        assertTrue(!this.hahmo.elossa());


    }

    @Test
    public void testAsetaOmistaja() {
        Pelaaja pelaaja = new Pelaaja("OmistajaYksi");
        this.hahmo.asetaOmistaja(pelaaja);
        assertEquals(this.hahmo.palautaOmistaja(), pelaaja);

    }

    @Test
    public void testGetNimi() {
        assertEquals(this.hahmo.getNimi(), "mafia");
    }

    @Test
    public void testGetOmistajanNimi() {
        Pelaaja pelaaja = new Pelaaja("OmistajaYksi");
        this.hahmo.asetaOmistaja(pelaaja);
        assertEquals(this.hahmo.getOmistajanNimi(), "OmistajaYksi");
    }

    @Test
    public void testGetWorksAsTeam() {
        assertTrue(this.hahmo.getWorksAsTeam());
    }

    @Test
    public void testGetTeam() {
        assertEquals(this.hahmo.getTeam().get(0), this.hahmo);
    }

    @Test
    public void testLisaaBuffi() {
        BuffinTyyppi type = new BuffinTyyppi("l1");
        Buff buffi = new Buff("lol", 0, type);
        this.hahmo.lisaaBuffi(buffi);
        assertEquals(buffi, this.hahmo.ListaaBuffit().get(0));
    }

    @Test
    public void testTyhjennaBuffit() {
        BuffinTyyppi type = new BuffinTyyppi("l1");
        Buff buffi = new Buff("lol", 0, type);
        this.hahmo.lisaaBuffi(buffi);
        this.hahmo.tyhjennaBuffit();
        BuffinTyyppi type2 = new BuffinTyyppi("l33");
        Buff buffi2 = new Buff("loal", 0, type2);
        this.hahmo.lisaaBuffi(buffi2);
        assertEquals(buffi2, this.hahmo.ListaaBuffit().get(0));
        
        
    }
    @Test
    public void PoistabuffiToimiiEliReturnTrue() {
        BuffinTyyppi type = new BuffinTyyppi("l1");
        Buff buffi = new Buff("lol", 0, type);
        this.hahmo.lisaaBuffi(buffi);
       
        assertEquals(this.hahmo.PoistaBuffi(buffi), true);
    }
    @Test
    public void VaaranObjektinPoistaminenEiAiheutaVirhetta() {
        BuffinTyyppi type = new BuffinTyyppi("l1");
        Buff buffi = new Buff("lol", 0, type);
        this.hahmo.lisaaBuffi(buffi);
        BuffinTyyppi type2 = new BuffinTyyppi("l33");
        Buff buffi2 = new Buff("loal", 0, type2);
       
        assertEquals(this.hahmo.PoistaBuffi(buffi2), false);
    }
    
    @Test
    public void testPoistaBuffiToimii() {
        BuffinTyyppi type = new BuffinTyyppi("l1");
        Buff buffi = new Buff("lol", 0, type);
        this.hahmo.lisaaBuffi(buffi);
        this.hahmo.PoistaBuffi(buffi);
        BuffinTyyppi type2 = new BuffinTyyppi("l33");
        Buff buffi2 = new Buff("loal", 0, type2);
        this.hahmo.lisaaBuffi(buffi2);
        assertEquals(buffi2, this.hahmo.ListaaBuffit().get(0));
    }

    @Test
    public void testAsetaTiimi() {
        Tiimi tiimi = new Tiimi("pahat mafiat");
        this.hahmo.asetaTiimi(tiimi);
        assertEquals(tiimi, this.hahmo.PalautaTiimi());
    }

  
    

   
    @Test
    public void testReturnElamienLkm() {
        this.hahmo.Vaihtatilaa(4);
        assertEquals(4, this.hahmo.returnElamienLkm());
        
    }

    @Test
    public void testPalautaRooli() {
        assertEquals("mafia", this.hahmo.palautaRooli().PalautaNimi());
    }

    @Test
    public void BuffinJonkaExpireRateOnNollaHaviaaKunExpairataan() {
        BuffinTyyppi type = new BuffinTyyppi("l1");
        Buff buffi = new Buff("lol", 0, type);
        this.hahmo.lisaaBuffi(buffi);
        this.hahmo.BuffitVanhetuvat();
        assertEquals(this.hahmo.PoistaBuffi(buffi), false);
        
    }
     @Test
    public void BuffiJokaEiPystyExpairamaanEiMyosExpairaa() {
        BuffinTyyppi type = new BuffinTyyppi("l1");
        Buff buffi = new Buff("lol", -1, type);
        this.hahmo.lisaaBuffi(buffi);
        this.hahmo.BuffitVanhetuvat();
        this.hahmo.BuffitVanhetuvat();
        this.hahmo.BuffitVanhetuvat();
        assertEquals(this.hahmo.PoistaBuffi(buffi), true);
        
    }
     @Test
    public void TuplaExpairaaminenToimii() {
        BuffinTyyppi type = new BuffinTyyppi("l1");
        Buff buffi = new Buff("lol", 1, type);
        this.hahmo.lisaaBuffi(buffi);
        this.hahmo.BuffitVanhetuvat();
        this.hahmo.BuffitVanhetuvat();
      
        assertEquals(this.hahmo.PoistaBuffi(buffi), false);

    }
}
