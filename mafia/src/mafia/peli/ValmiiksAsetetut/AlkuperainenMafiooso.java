/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.ValmiiksAsetetut;

import GraphicInterface.GraphicInterfaceCore;
import GraphicInterface.PanelliManageri;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import mafia.peli.YhdenAsianLuokkia.Sekoittaja;
import java.util.ArrayList;
import mafia.hahmot.*;
import mafia.kyvyt.*;

import mafia.peli.BuffienHallitsija;
import mafia.peli.Faasi;
import mafia.peli.GraphicRunHelper;
import mafia.peli.Logit.LogWriter;
import mafia.peli.Peli;
import mafia.peli.YhdenAsianLuokkia.AanestysSysteemi;
import mafia.peli.YhdenAsianLuokkia.Misc;
import mafia.userinterface.TekstiRajapinta;

/**
 *
 * Massiivinen luokka tämän ohjelman testaamiseksi.
 */
public class AlkuperainenMafiooso {

    private ArrayList<Pelaaja> pelaajat;
    private ArrayList<Pelattava> pelattavat;
    private Sekoittaja sekoittaja;
    private BuffienHallitsija hallitsija;
    private Misc misc;
    private TekstiRajapinta rajapinta;
    private PanelliManageri GraphicManager;
    private LogWriter logit;
    private boolean way;
    private GraphicRunHelper helper;

    /**
     *
     * @param pelaajat
     * @param sekoittaja
     * @param rajapinta
     */
    public AlkuperainenMafiooso(ArrayList<Pelaaja> pelaajat, Sekoittaja sekoittaja, TekstiRajapinta rajapinta) {
        this.pelaajat = pelaajat;
        this.sekoittaja = sekoittaja;
        this.misc = new Misc();
        this.rajapinta = rajapinta;
        this.pelattavat = new ArrayList<Pelattava>();
        this.logit = new LogWriter();
        this.way = false;

    }

    public AlkuperainenMafiooso(ArrayList<Pelaaja> pelaajat, Sekoittaja sekoittaja, PanelliManageri core) {
        this.GraphicManager = core;
        this.pelattavat = new ArrayList<Pelattava>();
        this.logit = new LogWriter();
        this.pelaajat = pelaajat;
        this.sekoittaja = sekoittaja;
        this.misc = new Misc();
        this.way = true;
    }

    /**
     *
     */
    public void Run() throws FileNotFoundException, UnsupportedEncodingException {

        Rooli mafia = new Rooli("Mafia");
        Rooli kansalainen = new Rooli("Kansalainen");
        Rooli hullu = new Rooli("Hullu");
        Rooli hilleri = new Rooli("heaalaja");
        Rooli poliisi = new Rooli("Poliisi");
        RoolitInitToiminallisuus(mafia, hullu, hilleri, poliisi, kansalainen);

        this.hallitsija = new BuffienHallitsija(this.pelattavat);

        BuffinTyyppi tappo = new BuffinTyyppi("tappava", 1);
        BuffinTyyppi healaus = new BuffinTyyppi("heaalaavaa", 2);
        BuffinTyyppi PoliisiScanni = new BuffinTyyppi("Scannaus", 0);

        Buff Tappo = new Buff("Hullun tai mafian kyky tappaa", 0, tappo);
        Buff Healaus = new Buff("Lääkäri healaa", 0, healaus);


        Buff Scannaus = new Buff("Poliisi scannaa", 0, PoliisiScanni);

        Kyky YleinenTappo = new NormiKyky("Mafia tappaa", Tappo, true);
        Kyky HulluTappo = new NormiKyky("Hullu tappaa", Tappo, true);
        Kyky Lynkkays = new NormiKyky("KansaLoytaaJaTappaa", Tappo, true);
        Kyky heali = new NormiKyky("Lääkäri healaa", Healaus, true);
        Skannaus scanni = new Skannaus("Poliisi scannaa", Scannaus);
        Healaus.lisaaErikoisViittaus(YleinenTappo);
        Healaus.lisaaErikoisViittaus(HulluTappo);


        ArrayList<Rooli> yksinkretainen = new ArrayList<Rooli>();
        yksinkretainen.add(mafia);
        scanni.Lisaa(yksinkretainen, "Mafia");

        ArrayList<Faasi> faasit = new ArrayList<Faasi>();
        faasit.add(new Faasi("Yo", this.hallitsija));
        faasit.add(new Faasi("Paiva", this.hallitsija));

        faasit.get(0).Lisaa(YleinenTappo, this.misc.Etsi(this.misc.Muutos(this.pelattavat), mafia));
        faasit.get(0).Lisaa(HulluTappo, this.misc.Etsi(this.misc.Muutos(this.pelattavat), hullu));
        faasit.get(0).Lisaa(heali, this.misc.Etsi(this.misc.Muutos(this.pelattavat), hilleri));
        faasit.get(0).Lisaa(scanni, this.misc.Etsi(this.misc.Muutos(this.pelattavat), poliisi));
        faasit.get(1).Lisaa(Lynkkays, this.misc.Muutos(this.pelattavat));
        this.hallitsija.asetaTappo(tappo);
        this.hallitsija.asetaMuutosEsto(healaus);
        AanestysSysteemi aanestysysteemi = new AanestysSysteemi(this.misc.Muutos(this.pelattavat));

        if (this.way == true) {
            this.helper = new GraphicRunHelper(this.GraphicManager);
            helper.asetaFaasit(faasit);
            helper.asetaPelaajat(this.pelattavat);
            helper.getNext();
         //   helper.Run();
        } else {
            Peli peli = new Peli("PerusMafiooso", this.rajapinta, this.logit);
            peli.asetaFaasit(faasit);
            peli.asetaPelaajat(this.pelattavat);
            peli.asetaAanestysSysteemi(aanestysysteemi);
            peli.Run();
        }
        










    }

    /**
     *
     * @param mafia
     * @param hullu
     * @param hilleri
     * @param poliisi
     * @param kansalainen
     */
    
     public GraphicRunHelper Steal()
      {
        
        return this.helper;
        }
    
    public void RoolitInitToiminallisuus(Rooli mafia, Rooli hullu, Rooli hilleri, Rooli poliisi, Rooli kansalainen) {

        // pelaajien lukumaarat:
        int pelaajienlkm = this.pelaajat.size();
        int mafioidenlkm = this.pelaajat.size() / 3;


        Tiimi Mafiat = new Tiimi("Pahat mafiat");
        Tiimi Kansanpuolellaoleavat = new Tiimi("Hyvät kansalaiset");
        this.pelattavat.add(Mafiat);
        this.pelattavat.add(Kansanpuolellaoleavat);
        for (int i = 1; i <= mafioidenlkm; i++) {
            Hahmo mufia = new Hahmo(mafia);
            Mafiat.LisaaPelaaja(mufia);
        }
        this.pelattavat.add(new Hahmo(hullu));
        Kansanpuolellaoleavat.LisaaPelaaja(new Hahmo(hilleri));
        Kansanpuolellaoleavat.LisaaPelaaja(new Hahmo(poliisi));
        for (int i = 1; i <= pelaajienlkm - mafioidenlkm - 3; i++) {
            Kansanpuolellaoleavat.LisaaPelaaja(new Hahmo(kansalainen));
        }
        this.sekoittaja.SekoitaKayttenTiimeja(this.pelattavat, this.pelaajat);
    }
}
