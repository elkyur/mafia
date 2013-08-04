/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import java.util.ArrayList;

/**
 *
 * @author Elkyur
 */
public interface Kyky {
    
    String getName();
    Object Toiminnallisuus(ArrayList<Hahmo> castaaja, Hahmo vastaanottava);
    boolean equals(Kyky kyky);
    PerusBuffi getBuffi();
    boolean returnHeti();
    
}
