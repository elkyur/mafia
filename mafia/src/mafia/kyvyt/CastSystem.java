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
public class CastSystem implements Kyky {
    
    private String name;
    
    public CastSystem(String name) {
        this.name = name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void Toiminnallisuus(Pelattava castaaja, Hahmo vastaanottava, Buff buff) {
        
        vastaanottava.lisaaBuffi(buff);
        
    }
}
