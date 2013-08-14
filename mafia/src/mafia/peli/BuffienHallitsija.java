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
import mafia.userinterface.TekstiRajapinta;

/**
 *
 * @author Elkyur
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
    // HashMap<PerusBuffi, ArrayList<Kyky>> Estot;

    public BuffienHallitsija(ArrayList<Pelattava> ViittausTiimeihin) {
        this.ViittausTiimeihin = ViittausTiimeihin;
        this.KuolemassaOlevatPelaajat = new ArrayList<Hahmo>();
        this.KostoKandidaatit = new ArrayList<Hahmo>();

    }

    public void asetaTappo(BuffinTyyppi tyyppi) {
        this.TappoBuffit = tyyppi;
    }

    public void asetaSuhde(BuffinTyyppi tyyppi) {
        this.SuhdeBuffit = tyyppi;
    }

    public void asetaLaukaisuEsto(BuffinTyyppi tyyppi) {
        this.LaukaisuEstot = tyyppi;
    }

    public void asetaMuutosEsto(BuffinTyyppi tyyppi) {
        this.MuutosEstot = tyyppi;
    }

    public void asetaKosto(BuffinTyyppi tyyppi) {
        this.KostoBuffit = tyyppi;
    }

    public String TekeeTaikaa(Hahmo hahmo, Kyky kyky, Hahmo vastaanottava) {

        if (checkForCategoryBuffit(hahmo, kyky, this.LaukaisuEstot)) {

            if (checkForCategoryBuffit(vastaanottava, kyky, this.MuutosEstot)) {

                if (kyky.getBuffi().returnBuffinTyyppi().equals(this.TappoBuffit)) {
                    Kuolemassa(vastaanottava);
                }

                String k = kyky.Toiminnallisuus(hahmo, vastaanottava);
                if (k != "") {
                    return k;
                }
            }
        }
        return null;
    }

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
            this.KostoKandidaatit.remove(hahmor);
        }
        if (KokoHomma.isEmpty()) {
            return null;
        }

        return KokoHomma;

    }

    public void tyhjennaTargetutPelaajat() {
        this.KuolemassaOlevatPelaajat.clear();
    }

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

    public void Kuolemassa(Hahmo hahmo) {
        if (this.KuolemassaOlevatPelaajat.contains(hahmo)) {
            return;
        }

        if (hahmo.returnElamienLkm() == 1) {
            hahmo.Vaihtatilaa(0);
            this.KuolemassaOlevatPelaajat.add(hahmo);
            this.KostoKandidaatit.add(hahmo);
            for (Buff buffi : hahmo.ListaaBuffit()) {
                if (buffi.returnBuffinTyyppi().equals(this.SuhdeBuffit)) {
                    for (Object objekti : buffi.PalautaKokoHomma()) {
                        Hahmo hahmoa = (Hahmo) objekti;
                        Kuolemassa(hahmoa);
                    }
                }

            }
        } else {
            hahmo.Vaihtatilaa(hahmo.returnElamienLkm() - 1);

        }
    }

    public ArrayList<Object> checkForKosto(Hahmo hahmo) {
        for (Buff buffi : hahmo.ListaaBuffit()) {
            if (buffi.returnBuffinTyyppi().equals(this.KostoBuffit)) {
                return buffi.PalautaKokoHomma();

            }
        }
        return null;

    }

    public void PuhdistetaanKuolleet() {
        for (Hahmo hahmo : this.KuolemassaOlevatPelaajat) {
            etsiJaPoista(hahmo);
        }

    }

    public void etsiJaPoista(Hahmo hahmo) {
        for (Pelattava pelattava : this.ViittausTiimeihin) {
            {
                if (pelattava.getTeam().contains(hahmo)) {
                    pelattava.getTeam().remove(hahmo);
                }

            }
        }
    }

    public ArrayList<Hahmo> palautaKuolleet() {
        return this.KuolemassaOlevatPelaajat;
    }
}
