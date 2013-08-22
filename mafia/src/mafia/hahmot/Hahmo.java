/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.hahmot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import mafia.kyvyt.Buff;
import mafia.kyvyt.Kyky;

/**
 * Tämä luokka kantaa vastuun Hahmosta
 * @author Elkyur
 */
public class Hahmo implements Pelattava {

    private int elaminelkm;
    private Pelaaja omistaja;
    private HashMap<Buff, Integer> buffit;
    private Tiimi team;
    private Rooli rooli;
    private ArrayList<String> ViestitHahmolle;

    /**
     * 
     * @param rooli
     */
    public Hahmo(Rooli rooli) {
        this.elaminelkm = 1;
        this.buffit = new HashMap<Buff, Integer>();
        this.team = null;
        this.rooli = rooli;
        this.ViestitHahmolle = new ArrayList<String>();

    }

    /**
     * Tyhjentää hahmolle tulevat viestit pelin aikana
     */
    public void TyhjennaViestit() {
        this.ViestitHahmolle.clear();

    }

    /**
     * 
     * Lisaa Kayttajalle viestin
     */
    public void LisaaViesti(String k) {
        this.ViestitHahmolle.add(k);

    }

    /**
     * 
     * Palauttaa kaikki viestit
     */
    public ArrayList<String> PalautaViestit() {
        return this.ViestitHahmolle;
    }

    /**
     * 
     * Vaihtaa hahmon elamien lukumaaran
     */
    public void Vaihtatilaa(int tila) {
        this.elaminelkm = tila;
    }

    /**
     * 
     * Asettaa hahmolle omistajan
     */
    public void asetaOmistaja(Pelaaja pelaaja) {
        this.omistaja = pelaaja;
    }

    /**
     * 
     * Palauttaa omistajan
     */
    public Pelaaja palautaOmistaja() {
        return this.omistaja;
    }

    /**
     * 
     * Palauttaa hahmon roolin nimen
     */
    @Override
    public String getNimi() {
        return this.rooli.PalautaNimi();
    }

    /**
     * 
     * Palauttaa omistajan nimen
     */
    public String getOmistajanNimi() {
        return this.omistaja.PalautaNimi();
    }

    /**
     * 
     * Palauttaa toimiiko tiimissä. Tapauksessa että tiimissä on vain yksi hahmo palauttaa true.
     */
    @Override
    public boolean getWorksAsTeam() {
        return true;
    }

    /**
     * 
     * Palauttaa koko tiimin 
     */
    @Override
    public ArrayList<Hahmo> getTeam() {
        ArrayList<Hahmo> hahmo = new ArrayList<Hahmo>();
        hahmo.add(this);
        return hahmo;
    }

    /**
     * 
     * Lisaa hahmolle buffin
     */
    public void lisaaBuffi(Buff buff) {
        if (this.buffit.containsKey(buff)) {
            this.buffit.remove(buff);
            this.buffit.put(buff, buff.returnExpireRate());

        }
        this.buffit.put(buff, buff.returnExpireRate());
    }

    /**
     * 
     * Listaa kaikki aktiiviset buffit jotka ovat hahmolla
     */
    public ArrayList<Buff> ListaaBuffit() {
        ArrayList<Buff> baffit = new ArrayList<Buff>(this.buffit.keySet());
        return baffit;
    }

    /**
     * Tyjentaa kaikki hahmon buffit
     */
    public void tyhjennaBuffit() {
        this.buffit.clear();
    }

    /**
     * 
     * Poistaa buffin , palauttaa true jos sellainen oli olemassa.
     *
     */
    public boolean PoistaBuffi(Buff buffi) {
        if (this.buffit.containsKey(buffi)) {
            this.buffit.remove(buffi);
            return true;
        }
        return false;
    }

    /**
     * 
     * Asettaa hahmolle tiimin
     */
    public void asetaTiimi(Tiimi team) {
        this.team = team;
    }

    /**
     * 
     * Palauttaa hahmon tiimin
     */
    public Tiimi PalautaTiimi() {

        return this.team;

    }

    /**
     * 
     * Palauttaa onko hahmo elossa
     */
    @Override
    public boolean elossa() {
        if (this.elaminelkm > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * Palauttaa hahmon elamien lukumäärän
     */
    public int returnElamienLkm() {
        return this.elaminelkm;
    }

    /**
     * 
     * Palauttaa hahmon roolin
     */
    public Rooli palautaRooli() {
        return this.rooli;

    }

    /**
     * Vanhentaa jokaista buffia yhdellä yksiköllä, kun buffin elinikä on enään 0, poistaa sen
     */
    public void BuffitVanhetuvat() {
        for (Buff buffi : this.ListaaBuffit()) {
            if (this.buffit.get(buffi) == 0) {
                this.buffit.remove(buffi);
            } else {
                if (this.buffit.get(buffi) > 0) {
                    int a = this.buffit.get(buffi);
                    this.buffit.remove(buffi);
                    this.buffit.put(buffi, a - 1);
                }

            }

        }

    }

    @Override
    public boolean poistaTiimista(Hahmo hahmo) {
        if(hahmo.equals(this))
        {
        return true;
        }
        return false;
    }
}
