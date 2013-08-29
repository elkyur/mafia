/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface.DataManager;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * Rajapinta kaikille abstractManagerin aliobjecteille, jossa siis voi luoda
 * uuden tietokanta objectin.
 */
public interface TietokantaALiRajapinta {

    /**
     *
     * Palauta paneeli
     */
    public JPanel palautaMainPanel();

    /**
     *
     * Palauta funtionaalinen nappula
     */
    public JButton palautaFunktionaalinenNappula();

    /**
     *
     * Tarkistaa onko nappulaa sallittua painaa, esimerkiks jos se antaa null niin ei ole
     */
    public boolean OnkoSallittu();
     /**
     *
     * Palauttaa luodun objectin
     */

    public Object palautaObjekti();

   
}
