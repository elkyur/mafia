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

    public BuffinTyyppi(String tyyppi, Class TietoTyyppi) {
        this.tyyppi = tyyppi;
        this.TietoTyyppi = TietoTyyppi;

    }

    public BuffinTyyppi(String k) {
        this.tyyppi = k;
        this.TietoTyyppi = null;
    }
    
    public Class returnTyyppi()
    {
    return this.TietoTyyppi;
    }
}
