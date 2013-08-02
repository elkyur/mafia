/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.hahmot;

import java.util.ArrayList;
import mafia.kyvyt.Buff;
import mafia.kyvyt.Kyky;

/**
 *
 * @author Elkyur
 */
public class Hahmo implements Pelattava {

    private String nimi;
    private boolean elossa;
    private Pelaaja omistaja;
    private ArrayList<Buff> buffit;
    private Tiimi team;
    private ArrayList<Kyky> kyvyt;

    public Hahmo(String nimi) {
        this.nimi = nimi;
        this.elossa = true;
        this.buffit = new ArrayList<Buff>();
        this.team = null;
        this.kyvyt = new ArrayList<Kyky>();
    }

    public void Vaihtatilaa(boolean tila) {
        this.elossa = tila;
    }

    public void asetaOmistaja(Pelaaja pelaaja) {
        this.omistaja = pelaaja;
    }

    public Pelaaja palautaOmistaja() {
        return this.omistaja;
    }

    @Override
    public String getNimi() {
        return this.nimi;
    }

    @Override
    public boolean getWorksAsTeam() {
        return true;
    }

    @Override
    public ArrayList<Hahmo> getTeam() {
        ArrayList<Hahmo> hahmo = new ArrayList<Hahmo>();
        hahmo.add(this);
        return hahmo;
    }

    public void lisaaBuffi(Buff buff) {
        this.buffit.add(buff);
    }

    public ArrayList<Buff> ListaaBuffit() {
        return this.buffit;
    }

    public void tyhjennaBuffit() {
        this.buffit.clear();
    }

    public boolean PoistaBuffi(Buff buffi) {
        if (this.buffit.contains(buffi)) {
            this.buffit.remove(buffi);
            return true;
        }
        return false;
    }

    public void asetaTiimi(Tiimi team) {
        this.team = team;
    }

    public Tiimi PalautaTiimi() {

        return this.team;

    }

    @Override
    public boolean elossa() {
        return this.elossa;
    }

    public void LaitaLisaaKykyja(Kyky z) {
        this.kyvyt.add(z);

    }

    public boolean TarkistaOnkoKykyKaytossa(Kyky e) {
        if (this.kyvyt.contains(e)) {
            return true;

        }
        return false;
    }
}
