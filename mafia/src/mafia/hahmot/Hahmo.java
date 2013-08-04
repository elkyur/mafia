/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.hahmot;

import java.util.ArrayList;
import mafia.kyvyt.PerusBuffi;
import mafia.kyvyt.Kyky;

/**
 *
 * @author Elkyur
 */
public class Hahmo implements Pelattava {

    private boolean elossa;
    private Pelaaja omistaja;
    private ArrayList<PerusBuffi> buffit;
    private Tiimi team;
    private Rooli rooli;

    public Hahmo(Rooli rooli) {
        this.elossa = true;
        this.buffit = new ArrayList<PerusBuffi>();
        this.team = null;
        this.rooli = rooli;

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
        return this.omistaja.PalautaNimi();
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

    public void lisaaBuffi(PerusBuffi buff) {
        this.buffit.add(buff);
    }

    public ArrayList<PerusBuffi> ListaaBuffit() {
        return this.buffit;
    }

    public void tyhjennaBuffit() {
        this.buffit.clear();
    }

    public boolean PoistaBuffi(PerusBuffi buffi) {
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
    public Rooli palautaRooli()
    {
    return this.rooli;
    
    }
}
