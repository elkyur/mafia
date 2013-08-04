/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.hahmot;

import java.util.ArrayList;
import mafia.kyvyt.Kyky;

/**
 *
 * @author Elkyur
 */
public class Rooli {

    private String nimi;
    private ArrayList<Kyky> MahdollisetKyvyt;

    public Rooli(String nimi) {
        this.nimi = nimi;
        this.MahdollisetKyvyt = new ArrayList<Kyky>();

    }

    public void LaitaLisaaKykyja(Kyky z) {
        this.MahdollisetKyvyt.add(z);

    }

    public boolean TarkistaOnkoKykyKaytossa(Kyky e) {
        if (this.MahdollisetKyvyt.contains(e)) {
            return true;

        }
        return false;
    }
    public String PalautaNimi()
    {
    return this.nimi;
    }
}
