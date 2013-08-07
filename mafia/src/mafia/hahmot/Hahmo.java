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

    private int elaminelkm;
    private Pelaaja omistaja;
    private ArrayList<Buff> buffit;
    private Tiimi team;
    private Rooli rooli;
    private ArrayList<String> ViestitHahmolle;

    public Hahmo(Rooli rooli) {
        this.elaminelkm = 1;
        this.buffit = new ArrayList<Buff>();
        this.team = null;
        this.rooli = rooli;
        this.ViestitHahmolle = new ArrayList<String>();

    }

    public void TyhjennaViestit() {
        this.ViestitHahmolle.clear();

    }

    public void LisaaViesti(String k) {
        this.ViestitHahmolle.add(k);

    }

    public ArrayList<String> PalautaViestit() {
        return this.ViestitHahmolle;
    }

    public void Vaihtatilaa(int tila) {
        this.elaminelkm = tila;
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
        if (this.elaminelkm > 0 ){
        return true;
        }
        else{
        return false;
        }
    }
    
    public int returnElamienLkm()
    {
    return this.elaminelkm;
    }

    public Rooli palautaRooli() {
        return this.rooli;

    }
}
