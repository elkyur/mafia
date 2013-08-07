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
import mafia.kyvyt.PerusKyky;

/**
 *
 * @author Elkyur
 */
public class BuffManager {

    private BuffinTyyppi TappoBuffit;
    private BuffinTyyppi SuhdeBuffit;
    private BuffinTyyppi LaukaisuEstot;
    private BuffinTyyppi MuutosEstot;
    private BuffinTyyppi KostoBuffit;
    private ArrayList<Hahmo> TargetetutPelaajat;
    private ArrayList<Pelattava> ViittausTiimeihin;
    // HashMap<PerusBuffi, ArrayList<Kyky>> Estot;

    public BuffManager(BuffinTyyppi TappavatBuffit) {
        this.TappoBuffit = TappavatBuffit;
        this.TargetetutPelaajat = new ArrayList<Hahmo>();


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

    public void TarkistusKierros() {
    }

    public ArrayList<Hahmo> tarkistettavat() {
        if (this.KostoBuffit == null) {
            return null;
        }

        ArrayList<Hahmo> hahmo = new ArrayList<Hahmo>();

        for (Hahmo hahmor : this.TargetetutPelaajat) {
            for (Buff buffi : hahmor.ListaaBuffit()) {
                if (buffi.returnBuffinTyyppi().equals(this.KostoBuffit)) {
                    hahmo.add(hahmor);
                }

            }
        }
        if (hahmo.isEmpty()) {
            return null;
        }

        return hahmo;

    }

    public void tyhjennaTargetutPelaajat() {
        this.TargetetutPelaajat.clear();
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
        if (hahmo.returnElamienLkm() == 1) {
            hahmo.Vaihtatilaa(0);
            this.TargetetutPelaajat.add(hahmo);
            for (Buff buffi : hahmo.ListaaBuffit()) {
                if (buffi.returnBuffinTyyppi().equals(this.TappoBuffit)) {
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

    public void InstantKill() {
    }
}
