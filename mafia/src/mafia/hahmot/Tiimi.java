/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.hahmot;

import mafia.hahmot.Hahmo;
import java.util.ArrayList;

/**
 *
 * Tämä luokka vastaa Tiimeistä 
 */
public class Tiimi implements Pelattava {

    private String nimi;
    boolean WorksAsTeam;
    private ArrayList<Hahmo> members;

    /**
     * 
     * @param nimi
     */
    public Tiimi(String nimi) {
        this.nimi = nimi;
        this.WorksAsTeam = true;
        this.members = new ArrayList<Hahmo>();
    }

    /**
     * 
     * Asettaa toimiiko tiimi tiiminä
     */
    public void AsetaWorkAsTeam(boolean abc) {
        this.WorksAsTeam = abc;

    }

    /**
     * 
     * Lisää pelaajan tiimiin
     */
    public void LisaaPelaaja(Hahmo hahmo) {
        this.members.add(hahmo);

    }

    /**
     * 
     * Poista tiimistä pelaajan, palautta true jos onnistui
     */
    public boolean poistaPelaaja(Hahmo hahmo) {
        if (this.members.contains(hahmo)) {

            this.members.remove(hahmo);
            return true;
        }
        return false;
    }

    /**
     * 
     * Palauttaa nimen
     */
    @Override
    public String getNimi() {
        return this.nimi;
    }

    /**
     * 
     * Palauttaa toimiiko tiiminä
     */
    @Override
    public boolean getWorksAsTeam() {
        return this.WorksAsTeam;
    }

    /**
     * 
     * Palauttaa koko tiimin
     */
    @Override
    public ArrayList<Hahmo> getTeam() {
        return this.members;
    }

    /**
     * 
     * Palauttaa onko tiimi viellä elsosa
     */
    @Override
    public boolean elossa() {
        if (this.members.isEmpty()) {
            return false;
        }
        return true;
    }
}
