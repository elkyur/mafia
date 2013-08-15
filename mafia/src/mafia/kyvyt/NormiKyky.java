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
 * Tämä luokka vastaa tavallistista kyvyistä, jossa lähetetään pelkästään buffi hahmolle.
 */
public class NormiKyky extends Kyky {
    
    /**
     * 
     * @param name
     * @param buffi
     * @param heti
     */
    public NormiKyky(String name, Buff buffi, boolean heti) {
        super(name, buffi, heti);
    }
    
    /**
     * 
     * @param
     * @param
     * @return
     */
    @Override
    public String Toiminnallisuus(Hahmo castaava, Hahmo vastaanottava) {
        if (super.palautaTarvittavat() == null)
        {
        return Toimi(castaava, vastaanottava);
        }
        else if (super.palautaTarvittavat().isEmpty()) {
            return Toimi(castaava, vastaanottava);
        } else {
            for (Buff buffi : super.palautaTarvittavat()) {
                if (!super.getBuffi().PalautaKokoHomma().contains(buffi)) {
                    return "";
                }
            }
            return Toimi(castaava, vastaanottava);
            
        }
    }
    
    /**
     * 
     * @param castaava
     * @param vastaanottava
     * @return
     */
    public String Toimi(Hahmo castaava, Hahmo vastaanottava) {
        
        vastaanottava.lisaaBuffi(super.palautaBuffi());
        return "";
    }
    
    //  @Override
    //   public boolean equals(Kyky kyky) {
    //       if (this.name == kyky.getName() && this.buffi.equals(kyky.getBuffi())) {
    //           return true;
    //       }
    //       return false;
    //   }
}
