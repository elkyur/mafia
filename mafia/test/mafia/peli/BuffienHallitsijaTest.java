/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import java.util.ArrayList;
import java.util.HashMap;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import mafia.hahmot.Rooli;
import mafia.hahmot.Tiimi;
import mafia.kyvyt.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Elkyur
 */
public class BuffienHallitsijaTest {

    private BuffienHallitsija hallitsija;
    private Hahmo castaaja;
    private Hahmo vastaanottaja;
    private Hahmo kolmas;
    private Rooli kansa;
    private Rooli mafia;
    private ArrayList<Pelattava> pelattavat;

    public BuffienHallitsijaTest() {
    }

    @Before
    public void setUp() {
        this.mafia = new Rooli("mafia");
        this.kansa = new Rooli("kansa");
        this.castaaja = new Hahmo(this.mafia);
        this.vastaanottaja = new Hahmo(this.kansa);
        this.kolmas = new Hahmo(this.kansa);
        Tiimi tiimi = new Tiimi("kansalaiset");
        tiimi.LisaaPelaaja(this.vastaanottaja);
        tiimi.LisaaPelaaja(this.kolmas);
        this.pelattavat = new ArrayList<Pelattava>();
        pelattavat.add(tiimi);
        pelattavat.add(this.castaaja);
        this.hallitsija = new BuffienHallitsija(this.pelattavat);





    }

    @Test
    public void TappaminenToimii() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        this.hallitsija.asetaTappo(tappo);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.vastaanottaja);
        this.hallitsija.palautaKuolleet();
        assertEquals(this.hallitsija.palautaKuolleet().contains(this.vastaanottaja), true);
    }

    @Test
    public void VaaraTyyppiEiKummiskaanKuolee() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        this.hallitsija.asetaTappo(tappo);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.vastaanottaja);
        this.hallitsija.palautaKuolleet();
        assertEquals(this.hallitsija.palautaKuolleet().contains(this.kolmas), false);
    }

    @Test
    public void testCheckForCategoryBuffitToimiiko() {
        BuffinTyyppi Muutosesto = new BuffinTyyppi("estaminen");
        Buff healausbuffi = new Buff("healaus", 0, Muutosesto);
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky tappokyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        healausbuffi.lisaaErikoisViittaus(tappokyky);
        this.kolmas.lisaaBuffi(healausbuffi);

        assertEquals(this.hallitsija.checkForCategoryBuffit(this.kolmas, tappokyky, Muutosesto), false);

    }

    @Test
    public void josTargetettuaPelaajaaHealattiinHanEiKuole() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        BuffinTyyppi Muutosesto = new BuffinTyyppi("estaminen");
        Buff healausbuffi = new Buff("healaus", 0, Muutosesto);
        NormiKyky healauskyky = new NormiKyky("Lääkäri healaa", healausbuffi, true);
        NormiKyky tappokyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        healausbuffi.lisaaErikoisViittaus(tappokyky);
        this.hallitsija.asetaTappo(tappo);
        this.hallitsija.asetaMuutosEsto(Muutosesto);

        this.hallitsija.TekeeTaikaa(this.kolmas, healauskyky, this.vastaanottaja);
        this.hallitsija.TekeeTaikaa(this.castaaja, tappokyky, this.vastaanottaja);

        assertEquals(this.hallitsija.palautaKuolleet().contains(this.vastaanottaja), false);

    }

    @Test
    public void josTargetettuaPelaajaaHealattiinHanEiKuoleMuttaJosEiOlluErikoisViittaustaNiinKummiskiinKuolee() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        BuffinTyyppi Muutosesto = new BuffinTyyppi("estaminen");
        Buff healausbuffi = new Buff("healaus", 0, Muutosesto);
        NormiKyky healauskyky = new NormiKyky("Lääkäri healaa", healausbuffi, true);
        NormiKyky tappokyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        this.hallitsija.asetaTappo(tappo);
        this.hallitsija.asetaMuutosEsto(Muutosesto);
        this.hallitsija.TekeeTaikaa(this.kolmas, healauskyky, this.vastaanottaja);
        this.hallitsija.TekeeTaikaa(this.castaaja, tappokyky, this.vastaanottaja);

        assertEquals(this.hallitsija.palautaKuolleet().contains(this.vastaanottaja), true);

    }

    @Test
    public void josCastaajaaEstettiinTekemastaTappoKykyaHanEiMyoskaanKykeneSiihen() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        BuffinTyyppi castesto = new BuffinTyyppi("estaminen");
        Buff estobuffi = new Buff("esto", 0, castesto);
        NormiKyky estokyky = new NormiKyky("Lääkäri healaa", estobuffi, true);
        NormiKyky tappokyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        estobuffi.lisaaErikoisViittaus(tappokyky);
        this.hallitsija.asetaTappo(tappo);
        this.hallitsija.asetaLaukaisuEsto(castesto);

        this.hallitsija.TekeeTaikaa(this.kolmas, estokyky, this.castaaja);
        this.hallitsija.TekeeTaikaa(this.castaaja, tappokyky, this.vastaanottaja);

        assertEquals(this.hallitsija.palautaKuolleet().contains(this.vastaanottaja), false);

    }

    @Test
    public void ScannaaminenToimii() {
        BuffinTyyppi scanni = new BuffinTyyppi("poliisiScannaa");
        Buff scannibuffi = new Buff("scanni", 0, scanni);
        Skannaus kyky = new Skannaus("Mafia tappaa", scannibuffi);
        ArrayList<Rooli> roolit = new ArrayList<Rooli>();
        roolit.add(this.kansa);
        kyky.Lisaa(roolit, "kansalaiset");


        assertEquals(this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.vastaanottaja), "Scannauksen tulos: kansalaiset");
    }

    @Test
    public void ScannaaminenEiToimiJosPelaajaOnEstetty() {
        BuffinTyyppi castesto = new BuffinTyyppi("estaminen");
        Buff estobuffi = new Buff("esto", 0, castesto);
        NormiKyky estokyky = new NormiKyky("Lääkäri healaa", estobuffi, true);
        this.hallitsija.asetaLaukaisuEsto(castesto);

        BuffinTyyppi scanni = new BuffinTyyppi("poliisiScannaa");
        Buff scannibuffi = new Buff("scanni", 0, scanni);
        Skannaus kyky = new Skannaus("Mafia tappaa", scannibuffi);
        ArrayList<Rooli> roolit = new ArrayList<Rooli>();
        roolit.add(this.kansa);
        kyky.Lisaa(roolit, "kansalaiset");
        estobuffi.lisaaErikoisViittaus(kyky);

        this.hallitsija.TekeeTaikaa(this.kolmas, estokyky, this.castaaja);
        assertEquals(this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.vastaanottaja), null);
    }

    @Test
    public void TappaminenToimiiMyosDoubleKillina() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        BuffinTyyppi suhde = new BuffinTyyppi("multikill");
        Buff suhdbuffi = new Buff("suhde", -1, suhde);
        suhdbuffi.lisaaErikoisViittaus(this.kolmas);
        this.hallitsija.asetaSuhde(suhde);
        this.hallitsija.asetaTappo(tappo);
        this.vastaanottaja.lisaaBuffi(suhdbuffi);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.vastaanottaja);
        this.hallitsija.palautaKuolleet();
        assertEquals(this.hallitsija.palautaKuolleet().contains(this.kolmas), true);
    }

    @Test
    public void testCheckForKosto() {
        BuffinTyyppi KostoBuffi = new BuffinTyyppi("kosto");
        this.hallitsija.asetaKosto(KostoBuffi);
        Buff buffi = new Buff("kosto", 0, KostoBuffi);
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky tappor = new NormiKyky("tappo", tappobuffi, true);
        buffi.lisaaErikoisViittaus(tappor);
        this.kolmas.lisaaBuffi(buffi);
        assertEquals(this.hallitsija.checkForKosto(this.kolmas).contains(tappor), true);
    }

    @Test
    public void testCheckForKostoJosSellaistaEiOlekkaan() {
        BuffinTyyppi KostoBuffi = new BuffinTyyppi("kosto");
        this.hallitsija.asetaKosto(KostoBuffi);
        assertEquals(this.hallitsija.checkForKosto(this.kolmas), null);
    }

    @Test
    public void testPalautaKostoBuffiinKykynevat() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        this.hallitsija.asetaTappo(tappo);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.vastaanottaja);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.kolmas);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.castaaja);
        BuffinTyyppi KostoBuffi = new BuffinTyyppi("kosto");
        this.hallitsija.asetaKosto(KostoBuffi);
        Buff buffi = new Buff("kosto", 0, KostoBuffi);
        buffi.lisaaErikoisViittaus(tappo);
        this.kolmas.lisaaBuffi(buffi);
        this.castaaja.lisaaBuffi(buffi);
        assertEquals(this.hallitsija.tarkistettavat().keySet().contains(this.kolmas), true);
    }

    @Test
    public void testPalautaKostoBuffinKykenevvatEiKuitenkaaSisallaTurhia() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        this.hallitsija.asetaTappo(tappo);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.vastaanottaja);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.kolmas);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.castaaja);
        BuffinTyyppi KostoBuffi = new BuffinTyyppi("kosto");
        this.hallitsija.asetaKosto(KostoBuffi);
        Buff buffi = new Buff("kosto", 0, KostoBuffi);
        buffi.lisaaErikoisViittaus(tappo);
        this.kolmas.lisaaBuffi(buffi);
        this.vastaanottaja.lisaaBuffi(buffi);
        assertEquals(this.hallitsija.tarkistettavat().keySet().contains(this.castaaja), false);
    }

    @Test
    public void testPalautaKostoBuffiinKykynevatOsa2() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        this.hallitsija.asetaTappo(tappo);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.vastaanottaja);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.kolmas);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.castaaja);
        BuffinTyyppi KostoBuffi = new BuffinTyyppi("kosto");
        this.hallitsija.asetaKosto(KostoBuffi);
        Buff buffi = new Buff("kosto", 0, KostoBuffi);
        buffi.lisaaErikoisViittaus(kyky);
        this.kolmas.lisaaBuffi(buffi);
        this.castaaja.lisaaBuffi(buffi);
        assertEquals(this.hallitsija.tarkistettavat().get(this.kolmas).contains(kyky), true);
    }

    @Test
    public void testKuolemassa() {
        this.hallitsija.Kuolemassa(this.castaaja);
        assertEquals(this.hallitsija.palautaKuolleet().contains(this.castaaja), true);
    }

    @Test
    public void testPuhdistetaanKuolleet() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        this.hallitsija.asetaTappo(tappo);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.vastaanottaja);
        this.hallitsija.PuhdistetaanKuolleet();
        assertEquals(this.hallitsija.palautaKuolleet().contains(this.vastaanottaja), false);
    }

    @Test
    public void testPuhdistetaanKuolleetlisatestaus() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        this.hallitsija.asetaTappo(tappo);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.vastaanottaja);
        this.hallitsija.PuhdistetaanKuolleet();
        boolean k = true;
        for (Pelattava pelattava : this.pelattavat) {
            for (Hahmo hahmo : pelattava.getTeam()) {
                if (hahmo.equals(this.vastaanottaja)) {
                    k = false;
                }
            }

        }
        assertEquals(k, true);

    }
     @Test
    public void MuutJaaKuitenkinHenkiin() {
        BuffinTyyppi tappo = new BuffinTyyppi("pelaajan poistaminen");
        Buff tappobuffi = new Buff("tappo", 0, tappo);
        NormiKyky kyky = new NormiKyky("Mafia tappaa", tappobuffi, true);
        this.hallitsija.asetaTappo(tappo);
        this.hallitsija.TekeeTaikaa(this.castaaja, kyky, this.vastaanottaja);
        this.hallitsija.PuhdistetaanKuolleet();
        boolean k = true;
        for (Pelattava pelattava : this.pelattavat) {
            for (Hahmo hahmo : pelattava.getTeam()) {
                if (hahmo.equals(this.kolmas)) {
                    k = false;
                }
            }

        }
        assertEquals(k, false);

    }
}
