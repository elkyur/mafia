/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import java.util.ArrayList;
import java.util.HashMap;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Rooli;

/**
 *
 * @author Elkyur
 */
public class Skannaus implements Kyky {

    private String nimi;
    private PerusBuffi buffi;
    private HashMap<ArrayList<Rooli>, Integer> Skannaukset;

    public Skannaus(String nimi, PerusBuffi buffi) {

        this.nimi = nimi;
        this.buffi = buffi;
        this.Skannaukset = new HashMap<ArrayList<Rooli>, Integer>();

    }

    public void Lisaa(ArrayList<Rooli> roolit, int i) {
        this.Skannaukset.put(roolit, i);
    }

    @Override
    public String getName() {
        return this.nimi;
    }

    @Override
    public Object Toiminnallisuus(ArrayList<Hahmo> castaaja, Hahmo vastaanottava) {
        
        vastaanottava.lisaaBuffi(this.buffi);
        
        for (ArrayList<Rooli> rooli : this.Skannaukset.keySet()) {
            if (rooli.contains(vastaanottava.palautaRooli())) {
                return this.Skannaukset.get(vastaanottava.palautaRooli());
            }
            
        }

        return 0;
    }

    @Override
    public boolean equals(Kyky kyky) {
        if (this.nimi == kyky.getName() && this.buffi.equals(kyky.getBuffi())) {
            return true;
        }
        return false;
    }

    @Override
    public PerusBuffi getBuffi() {
        return this.buffi;
    }

    @Override
    public boolean returnHeti() {
        return true;
    }
}
