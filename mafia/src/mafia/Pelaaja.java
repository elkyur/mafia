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
    private Hahmo hahmo;

    public Pelaaja(String nimi, Hahmo hahmo) {
        this(nimi, "", hahmo);

    }

    public Pelaaja(String nimi, String salasana, Hahmo hahmo) {
        this.nimi = nimi;
        this.salasana = salasana;
        this.hahmo = hahmo;

    }

    public String PalautaNimi() {
        return this.nimi;
    }
}
