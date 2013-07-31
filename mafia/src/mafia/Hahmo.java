/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia;

/**
 *
 * @author Elkyur
 */
public class Hahmo {

    private String nimi;
    boolean elossa;

    public Hahmo(String nimi) {
        this.nimi = nimi;
        this.elossa = true;
    }

    public void Vaihtatilaa(boolean tila) {
        this.elossa = tila;
    }

    public String PalautaNimi() {
        return this.nimi;
    }
}
