/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia;

/**
 *
 * @author Elkyur
 */
public class Hahmo {

    private String nimi;
    boolean elossa;
    Tiimi team;

    public Hahmo(String nimi) {
        this.nimi = nimi;
        this.elossa = true;
        this.team = null;
    }
    
    public void Asetatiimi(Tiimi team)
    {
    this.team =  team;
    
    }

    public void Vaihtatilaa(boolean tila) {
        this.elossa = tila;
    }
    

    public String PalautaNimi() {
        return this.nimi;
    }
    
    public Tiimi PalautaTiimi()
    {
    return this.team;
    }
}
