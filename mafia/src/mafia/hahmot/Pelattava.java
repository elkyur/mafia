/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.hahmot;

import java.util.ArrayList;

/**
 *
 * @author Elkyur
 */
public interface Pelattava {
    
    String getNimi();
    boolean getWorksAsTeam();
    ArrayList<Hahmo> getTeam();
    boolean elossa();
}
