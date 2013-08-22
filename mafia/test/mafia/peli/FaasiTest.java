/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import mafia.hahmot.Rooli;
import mafia.kyvyt.*;
import mafia.peli.YhdenAsianLuokkia.AanestysSysteemi;
import mafia.userinterface.TekstiRajapinta;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Elkyur
 */
public class FaasiTest {
    
    private Faasi faasi;
    private ArrayList<Pelattava> pelattavat;
    private Rooli Alkup;
    
    
    public FaasiTest() {
        
        
    }


    @Before
    public void setUp() {
        this.pelattavat = new ArrayList<Pelattava>();
        BuffienHallitsija hallitsija = new BuffienHallitsija(this.pelattavat);
        this.faasi = new Faasi("test", hallitsija);
        this.Alkup = new Rooli("mafia");
    }
      @Test
    public void  AluksiAtribuuttiListaPitaisiOllaTyhja() {
         assertEquals(this.faasi.palautaAtribuutit().isEmpty(), true);
        
    }
    
    @Test
    public void  UudenTaianLisaaminenPitaisiToimia() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        Hahmo Esiintyva = new Hahmo(this.Alkup);
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        hahmot.add(Esiintyva);
        this.faasi.Lisaa(kyky, hahmot);
        assertEquals(this.faasi.palautaAtribuutit().get(0).palautaKyky().equals(kyky), true);
        
    }
    @Test
    public void  EiPitaisiSyntyaUuttaListaa() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        Hahmo Esiintyva = new Hahmo(this.Alkup);
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        hahmot.add(Esiintyva);
        ArrayList<Hahmo> lisaHahmot = new ArrayList<Hahmo>();
        Hahmo Esiintyva2 = new Hahmo(this.Alkup);
        lisaHahmot.add(Esiintyva2);
        this.faasi.Lisaa(kyky, hahmot);
        this.faasi.Lisaa(kyky, lisaHahmot);
        assertEquals(this.faasi.palautaAtribuutit().size(), 1);
        
    }
     @Test
    public void  UuteenHahmonLisaaminenPitaisiToimia() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        Hahmo Esiintyva = new Hahmo(this.Alkup);
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        hahmot.add(Esiintyva);
        ArrayList<Hahmo> lisaHahmot = new ArrayList<Hahmo>();
        Hahmo Esiintyva2 = new Hahmo(this.Alkup);
        lisaHahmot.add(Esiintyva2);
        this.faasi.Lisaa(kyky, hahmot);
        this.faasi.Lisaa(kyky, lisaHahmot);
        assertEquals(this.faasi.palautaAtribuutit().get(0).palautaHahmot().contains(Esiintyva2), true);
        
    }
     @Test
    public void KahdenKyvynLisaaminenPitaisiToimia() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        NormiKyky kyky2 = new NormiKyky("Hullu tappaa", tappobuffi, true);
        Hahmo Esiintyva = new Hahmo(this.Alkup);
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        hahmot.add(Esiintyva);
        ArrayList<Hahmo> lisaHahmot = new ArrayList<Hahmo>();
        Hahmo Esiintyva2 = new Hahmo(this.Alkup);
        lisaHahmot.add(Esiintyva2);
        this.faasi.Lisaa(kyky, hahmot);
        this.faasi.Lisaa(kyky2, lisaHahmot);
        assertEquals(this.faasi.palautaAtribuutit().size(), 2);
        
    }
      @Test
    public void  HahmojenPalauttaminenPitaisiToimiaOikein() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        Hahmo Esiintyva = new Hahmo(this.Alkup);
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        hahmot.add(Esiintyva);
        ArrayList<Hahmo> lisaHahmot = new ArrayList<Hahmo>();
        Hahmo Esiintyva2 = new Hahmo(this.Alkup);
        lisaHahmot.add(Esiintyva2);
        this.faasi.Lisaa(kyky, hahmot);
        this.faasi.Lisaa(kyky, lisaHahmot);
        assertEquals(this.faasi.PalautaHahmot(kyky).contains(Esiintyva2), true);
        
    }
      
        @Test
    public void  UudistamisPaivittaminenToimiiOikein() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        Hahmo Esiintyva = new Hahmo(this.Alkup);
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        hahmot.add(Esiintyva);
        ArrayList<Hahmo> lisaHahmot = new ArrayList<Hahmo>();
        Hahmo Esiintyva2 = new Hahmo(this.Alkup);
        lisaHahmot.add(Esiintyva2);
        this.faasi.Lisaa(kyky, hahmot);
        this.faasi.Lisaa(kyky, lisaHahmot);
        Esiintyva2.Vaihtatilaa(0);
        this.faasi.UudistaJaPaivita();
        assertEquals(this.faasi.PalautaHahmot(kyky).contains(Esiintyva2), false);
        
    }
             @Test
    public void  UudistamisPaivittaminenKunKaikkiKyvynCastaajatOvatKuolleitaMetodinEiPitaisiEnaanToimia() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        Hahmo Esiintyva = new Hahmo(this.Alkup);
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        hahmot.add(Esiintyva);
        ArrayList<Hahmo> lisaHahmot = new ArrayList<Hahmo>();
        Hahmo Esiintyva2 = new Hahmo(this.Alkup);
        lisaHahmot.add(Esiintyva2);
        this.faasi.Lisaa(kyky, hahmot);
        this.faasi.Lisaa(kyky, lisaHahmot);
        Esiintyva2.Vaihtatilaa(0);
        Esiintyva.Vaihtatilaa(0);
        this.faasi.UudistaJaPaivita();
        assertEquals(this.faasi.PalautaHahmot(kyky), null);
        
    }
    @Test
    public void testAsetaViestit() {
       
    }

    @Test
    public void testSetDay() {
    
    }

    @Test
    public void testPalautaDay() {
       
    }

    @Test
    public void testContains() {
   
    }

  

    @Test
    public void testPalautaHahmot() {
        
    }

    @Test
    public void testPalautaNimi() {
        
    }

    @Test
    public void testPalautaKokoHomma() {
       
    }

    @Test
    public void testUudistaJaPaivita() {
        
    }

    @Test
    public void testRun() {
       
    }

    @Test
    public void testKostot() {
       
    }

    @Test
    public void testCastaaminen_3args_1() {
       
    }

    @Test
    public void testCastaaminen_3args_2() {
     
    }
}
