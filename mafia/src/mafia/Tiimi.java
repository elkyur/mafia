/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia;

/**
 *
 * @author Elkyur
 */
public class Tiimi {

    private String nimi;
    boolean WorksAsTeam;

    public Tiimi(String nimi) {
        this.nimi = nimi;
        this.WorksAsTeam = true;

    }

    public void AsetaWorkAsTeam(boolean abc) {
        this.WorksAsTeam = abc;

    }
     public String PalautaNimi() {
        return this.nimi;
    }
}
