/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;

/**
 *
 * @author Elkyur
 */
public class PerusKyky implements Kyky {
    
    private String name;
    private Buff buffi;
    
    public PerusKyky(String name, Buff  buffi) {
        this.name = name;
        this.buffi = buffi;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void Toiminnallisuus(ArrayList<Hahmo> castaava, Hahmo vastaanottava) {
        vastaanottava.lisaaBuffi(this.buffi);
        
    }
    
   
    @Override
    public boolean equals(Kyky kyky)
    {
    if (this.name == kyky.getName() && this.buffi.equals(kyky.getBuffi()))
    {
    return true;
    }
        
    return false;    
    }

    @Override
    public Buff getBuffi() {
        return this.buffi;
    }
}
