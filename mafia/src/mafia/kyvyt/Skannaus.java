/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import java.util.ArrayList;
import java.util.HashMap;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Rooli;

/**
 *
 * @author Elkyur
 */
public class Skannaus implements Kyky {

    private String nimi;
    private Buff buffi;
    private HashMap<ArrayList<Rooli>, String> Skannaukset;

    public Skannaus(String nimi, Buff buffi) {

        this.nimi = nimi;
        this.buffi = buffi;
        this.Skannaukset = new HashMap<ArrayList<Rooli>, String>();

    }

    public void Lisaa(ArrayList<Rooli> roolit, String i) {
        this.Skannaukset.put(roolit, i);
    }

    @Override
    public String getName() {
        return this.nimi;
    }

    @Override
    public String Toiminnallisuus(Hahmo castaaja, Hahmo vastaanottavaa) {

        String viesti = "Scannauksen tulos: ";
        Hahmo vastaanottava = vastaanottavaa;

        vastaanottava.lisaaBuffi(this.buffi);

        for (ArrayList<Rooli> rooli : this.Skannaukset.keySet()) {
            if (rooli.contains(vastaanottava.palautaRooli())) {
                viesti = viesti + " " + this.Skannaukset.get(vastaanottava.palautaRooli());
            }

        }
        viesti = viesti + "Muu";
        return viesti;
    }

    @Override
    public boolean equals(Kyky kyky) {
        if (this.nimi == kyky.getName() && this.buffi.equals(kyky.getBuffi())) {
            return true;
        }
        return false;
    }

    @Override
    public Buff getBuffi() {
        return this.buffi;
    }

    @Override
    public boolean returnHeti() {
        return true;
    }

    @Override
    public boolean returnOnRequest() {
       return false;
    }

    @Override
    public int UsageTimes() {
        return -1;
    }
}
