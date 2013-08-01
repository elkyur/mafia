/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.hahmot;

import mafia.hahmot.Hahmo;
import java.util.ArrayList;

/**
 *
 * @author Elkyur
 */
public class Tiimi implements Pelattava {

    private String nimi;
    boolean WorksAsTeam;
    private ArrayList<Hahmo> members;

    public Tiimi(String nimi) {
        this.nimi = nimi;
        this.WorksAsTeam = true;
        this.members = new ArrayList<Hahmo>();
    }

    public void AsetaWorkAsTeam(boolean abc) {
        this.WorksAsTeam = abc;

    }

    public void LisaaPelaaja(Hahmo hahmo) {
        this.members.add(hahmo);

    }

    public boolean poistaPelaaja(Hahmo hahmo) {
        if (this.members.contains(hahmo)) {

            this.members.remove(hahmo);
            return true;
        }
        return false;
    }

    @Override
    public String getNimi() {
        return this.nimi;
    }

    @Override
    public boolean getWorksAsTeam() {
        return this.WorksAsTeam;
    }

    @Override
    public ArrayList<Hahmo> getTeam() {
        return this.members;
    }

    @Override
    public boolean alive() {
        if (this.members.isEmpty()) {
            return false;
        }
        return true;
    }
}
