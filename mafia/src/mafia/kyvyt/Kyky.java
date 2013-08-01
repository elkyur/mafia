/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;

/**
 *
 * @author Elkyur
 */
public interface Kyky {
    
    String getName();
    void Toiminnallisuus(Pelattava castaaja, Hahmo vastaanottava, Buff buff);
}
