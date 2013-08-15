/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.hahmot;

import java.util.ArrayList;
import mafia.kyvyt.Kyky;

/**
 *
 * Tämä luokka vastaa Roolista. Jokaisella Hahmolla on jokin Rooli. 
 */
public class Rooli {

    private String nimi;
    private ArrayList<Kyky> MahdollisetKyvyt;

    /**
     * 
     * Asetetaan Roolille nimet
     */
    public Rooli(String nimi) {
        this.nimi = nimi;
        this.MahdollisetKyvyt = new ArrayList<Kyky>();

    }

    /**
     * 
     * Asetetaan mahdolliset kyvyt roolille
     */
    public void LaitaLisaaKykyja(Kyky z) {
        this.MahdollisetKyvyt.add(z);

    }

    /**
     * 
     * Tarkistetaan onko kyky mahdollisien kykyjen listalla
     * 
     */
    public boolean TarkistaOnkoKykyKaytossa(Kyky e) {
        if (this.MahdollisetKyvyt.contains(e)) {
            return true;

        }
        return false;
    }
    /**
     * 
     * Palautetaan Roolin nimi
     */
    public String PalautaNimi()
    {
    return this.nimi;
    }
}
