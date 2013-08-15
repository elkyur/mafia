/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

/**
 *
 * Kertoo mitä tyyppiä Buffi on. 
 */
public class BuffinTyyppi {

    private String tyyppi;
    private Class TietoTyyppi;
    private int Prioriteetti;

    /**
     * 
     * @param tyyppi
     * @param Prioriteetti
     */
    public BuffinTyyppi(String tyyppi, int Prioriteetti) {
        this.Prioriteetti = Prioriteetti;
        this.tyyppi = tyyppi;


    }

    /**
     * 
     * @param tyyppi
     * @param TietoTyyppi
     * @param Prioriteetti
     */
    public BuffinTyyppi(String tyyppi, Class TietoTyyppi, int Prioriteetti) {
        this.Prioriteetti = Prioriteetti;
        this.tyyppi = tyyppi;
        this.TietoTyyppi = TietoTyyppi;

    }

    /**
     * 
     * @param k
     */
    public BuffinTyyppi(String k) {
        this.tyyppi = k;
        this.TietoTyyppi = null;
    }

    /**
     * 
     * Palauttaa tietotyypin missä erikoisviittaukset säilytetään
     */
    public Class returnTyyppi() {
        return this.TietoTyyppi;
    }

    /**
     * 
     * Palauttaa sortauksessa käytetettävää prioriteettia. Katso yönrakenne: https://github.com/elkyur/mafia/wiki/Faasin-eteneminen
     */
    public int palautaPrioriteetti() {
        return this.Prioriteetti;
    }
}
