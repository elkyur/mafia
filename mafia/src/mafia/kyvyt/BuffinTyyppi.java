/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

/**
 *
 * @author Elkyur
 */
public class BuffinTyyppi {

    private String tyyppi;
    private Class TietoTyyppi;
    private int Prioriteetti;

    public BuffinTyyppi(String tyyppi, int Prioriteetti) {
        this.Prioriteetti = Prioriteetti;
        this.tyyppi = tyyppi;


    }

    public BuffinTyyppi(String tyyppi, Class TietoTyyppi, int Prioriteetti) {
        this.Prioriteetti = Prioriteetti;
        this.tyyppi = tyyppi;
        this.TietoTyyppi = TietoTyyppi;

    }

    public BuffinTyyppi(String k) {
        this.tyyppi = k;
        this.TietoTyyppi = null;
    }

    public Class returnTyyppi() {
        return this.TietoTyyppi;
    }

    public int palautaPrioriteetti() {
        return this.Prioriteetti;
    }
}
