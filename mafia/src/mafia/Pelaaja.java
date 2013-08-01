/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia;

/**
 *
 * @author Elkyur
 */
public class Pelaaja {

    private String nimi;
    private String salasana;


    public Pelaaja(String nimi) {
        this(nimi, "");

    }

    public Pelaaja(String nimi, String salasana) {
        this.nimi = nimi;
        this.salasana = salasana;
     

    }

    public String PalautaNimi() {
        return this.nimi;
    }
}
