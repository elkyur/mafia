/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import mafia.hahmot.Pelattava;
import mafia.kyvyt.Kyky;
import java.util.*;

/**
 *
 * @author Elkyur
 */
public class Faasi {

    private String nimi;
    boolean day;
    private HashMap<Pelattava, Kyky> SkillzAtRightTime;

    public Faasi(String nimi) {
        this.nimi = nimi;

    }

    public void setDay(boolean day) {
        this.day = day;
    }

    public boolean palautaDay() {
        return this.day;

    }

    public HashMap<Pelattava, Kyky> ReturnHash() {
        return this.SkillzAtRightTime;
    }
    
    public void Lisaa(Pelattava pelattava, Kyky kyky)
    {
    this.SkillzAtRightTime.put(pelattava, kyky);    
        
    }
        
}
