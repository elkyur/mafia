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
    String Toiminnallisuus(Hahmo castaaja, Hahmo vastaanottava);
    Buff getBuffi();
    boolean returnHeti();
    boolean returnOnRequest();
    int UsageTimes();

    
    
    
    
}
