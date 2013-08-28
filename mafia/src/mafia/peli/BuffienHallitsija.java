/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import java.util.ArrayList;
import java.util.HashMap;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import mafia.kyvyt.Kyky;
import mafia.kyvyt.Buff;
import mafia.kyvyt.BuffinTyyppi;
import mafia.kyvyt.NormiKyky;
import mafia.peli.Logit.LogWriter;
import mafia.userinterface.TekstiRajapinta;

/**
 *
 * Tämä luokka vastaa yleisestä buffien käsittelystä.
 */
public class BuffienHallitsija {

    private BuffinTyyppi TappoBuffit;
    private BuffinTyyppi SuhdeBuffit;
    private BuffinTyyppi LaukaisuEstot;
    private BuffinTyyppi MuutosEstot;
    private BuffinTyyppi KostoBuffit;
    private ArrayList<Hahmo> KuolemassaOlevatPelaajat;
    private ArrayList<Pelattava> ViittausTiimeihin;
    private ArrayList<Hahmo> KostoKandidaatit;


    /**
     *
     * @param ViittausTiimeihin
     */
    public BuffienHallitsija(ArrayList<Pelattava> ViittausTiimeihin) {
        this.ViittausTiimeihin = ViittausTiimeihin;
        this.KuolemassaOlevatPelaajat = new ArrayList<Hahmo>();
        this.KostoKandidaatit = new ArrayList<Hahmo>();
      
          

    }

    /**
     *
     * @param tyyppi
     */
    public void asetaTappo(BuffinTyyppi tyyppi) {
        this.TappoBuffit = tyyppi;
    }
    
    
 

    /**
     *
     * @param tyyppi
     */
    public void asetaSuhde(BuffinTyyppi tyyppi) {
        this.SuhdeBuffit = tyyppi;
    }

    /**
     *
     * @param tyyppi
     */
    public void asetaLaukaisuEsto(BuffinTyyppi tyyppi) {
        this.LaukaisuEstot = tyyppi;
    }

    /**
     *
     * @param tyyppi
     */
    public void asetaMuutosEsto(BuffinTyyppi tyyppi) {
        this.MuutosEstot = tyyppi;
    }

    /**
     *
     * @param tyyppi
     */
    public void asetaKosto(BuffinTyyppi tyyppi) {
        this.KostoBuffit = tyyppi;
    }

    /**
     *
     * Metodi castaataan kuin jokin Hahmo tekee kyvyn. Ensin tarkistetaan onko
     * castaavalla hahmolla laukaisuestoja Sitten tarkistetaan onko
     * vastaanottavalla muutosestoja. Jos buffi oli tappo tyyppiä ja meni läpi
     * Silloin castataan Kuolemassa() metodi
     *
     */
    public String TekeeTaikaa(Hahmo hahmo, Kyky kyky, Hahmo vastaanottava) {
        
       
        if (checkForCategoryBuffit(hahmo, kyky, this.LaukaisuEstot)) {

            if (checkForCategoryBuffit(vastaanottava, kyky, this.MuutosEstot)) {

                if (kyky.getBuffi().returnBuffinTyyppi().equals(this.TappoBuffit)) {
                    Kuolemassa(vastaanottava);
                }

                String k = kyky.Toiminnallisuus(hahmo, vastaanottava);
               
                    return k;
                
            }
        }
        return "";
    }

    /**
     *
     * Palautetaan HashMap rakenne jossa avaimena on hahmoja joilla on
     * kostokykyjä ja kostokyvyt mapattavina ArrayListinä.
     */
    public HashMap<Hahmo, ArrayList<Object>> tarkistettavat() {
        if (this.KostoBuffit == null) {
            return null;
        }
        HashMap<Hahmo, ArrayList<Object>> KokoHomma = new HashMap<Hahmo, ArrayList<Object>>();

        for (Hahmo hahmor : this.KostoKandidaatit) {
            ArrayList<Object> kyvyt = checkForKosto(hahmor);
            if (kyvyt != null) {
                KokoHomma.put(hahmor, kyvyt);
            }
        }
        this.KostoKandidaatit.clear();
        if (KokoHomma.isEmpty()) {
            return null;
        }

        return KokoHomma;

    }

    /**
     * Tyhjentaa kuolemassa olevien pelaajien listan
     */
    public void tyhjennaTargetutPelaajat() {
        this.KuolemassaOlevatPelaajat.clear();
    }

    /**
     *
     * Tarkistaa onko hahmolla esto buffeja, joissa olisi erikoisviittauksia
     * jossa olisi kyseinen kyky.
     *
     *
     * @return
     */
    public boolean checkForCategoryBuffit(Hahmo hahmo, Kyky kyky, BuffinTyyppi tyyppi) {
        for (Buff buffi : hahmo.ListaaBuffit()) {

            if (buffi.returnBuffinTyyppi().equals(tyyppi)) {
                if (buffi.PalautaKokoHomma().contains(kyky)) {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     *
     * Kun hahmo on kuolemassa castataan tämä metodi. Ensin tarkistetaan onko
     * Hahmolla lisäelämiä. Sitten asetetaan Hahmon kuolleeksi ja lisätään se
     * kuolevien hahmojen listalle. Jos Hahmolla on joitakin suhteita, kuten jos
     * Hahmo kuolee niin jokin toinen Hahmo kuolee myös. Lisätään se toinen
     * Hahmo kuolevien listalle.
     */
    public void Kuolemassa(Hahmo hahmo) {
        if (this.KuolemassaOlevatPelaajat.contains(hahmo)) {
            return;
        }

        if (hahmo.returnElamienLkm() == 1) {
            hahmo.Vaihtatilaa(0);
            this.KuolemassaOlevatPelaajat.add(hahmo);
            this.KostoKandidaatit.add(hahmo);
            SuhdeBuffit(hahmo);
        } else {
            hahmo.Vaihtatilaa(hahmo.returnElamienLkm() - 1);

        }
    }

    public void SuhdeBuffit(Hahmo hahmo) {
        for (Buff buffi : hahmo.ListaaBuffit()) {
            if (buffi.returnBuffinTyyppi().equals(this.SuhdeBuffit)) {
                for (Object objekti : buffi.PalautaKokoHomma()) {
                    Hahmo hahmoa = (Hahmo) objekti;
                    Kuolemassa(hahmoa);
                }
            }

        }
    }

    /**
     *
     * Tarkistaa onko hahmolla kosto kykyjä.
     *
     */
    public ArrayList<Object> checkForKosto(Hahmo hahmo) {
        for (Buff buffi : hahmo.ListaaBuffit()) {
            if (buffi.returnBuffinTyyppi().equals(this.KostoBuffit)) {
                return buffi.PalautaKokoHomma();

            }
        }
        return null;

    }

    /**
     * Poistetaan kuolleet pelaajat kokonaan tiimeistä
     */
    public void PuhdistetaanKuolleet() {
        for (Hahmo hahmo : this.KuolemassaOlevatPelaajat) {
            etsiJaPoista(hahmo);
        }
        this.KuolemassaOlevatPelaajat.clear();

    }

    /**
     * Etsii Pelaajan elossa olevien listalta ja tuhoaa sen.
     *
     */
    private void etsiJaPoista(Hahmo hahmo) {
        ArrayList<Pelattava> pelattavatjotkaPoistetaan = new ArrayList<Pelattava>();
        for (Pelattava pelattava : this.ViittausTiimeihin) {
            {
                if (pelattava.getTeam().contains(hahmo)) {
                    if (pelattava.poistaTiimista(hahmo)) {
                        pelattavatjotkaPoistetaan.add(pelattava);

                    }
                }

            }
           
        }
        for (Pelattava pelattavar : pelattavatjotkaPoistetaan) {
               this.ViittausTiimeihin.remove(pelattavar);
            }
    }

    /**
     *
     * Palauttaa listan jossa on kuolemassa olevat pelaajat.
     */
    public ArrayList<Hahmo> palautaKuolleet() {
        return this.KuolemassaOlevatPelaajat;
    }

    public void BuffitVanhetuvat() {
        for (Pelattava pelattava : this.ViittausTiimeihin) {
            for (Hahmo hahmo : pelattava.getTeam()) {
                hahmo.BuffitVanhetuvat();


            }

        }
    }
    
    public ArrayList<Pelattava> Palautetaanpelattavat()
    {
    return this.ViittausTiimeihin;
    }
}
