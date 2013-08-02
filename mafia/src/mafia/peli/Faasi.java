/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import mafia.hahmot.Pelattava;
import mafia.kyvyt.Kyky;
import java.util.*;
import mafia.hahmot.Hahmo;

/**
 *
 * @author Elkyur
 */
public class Faasi {

    private String nimi;
    boolean paiva;
    private HashMap<Kyky, ArrayList<Hahmo>> KykyJaKayttaja;

    public Faasi(String nimi) {
        this.nimi = nimi;
        this.KykyJaKayttaja = new HashMap<Kyky, ArrayList<Hahmo>>();

    }

    public void setDay(boolean day) {
        this.paiva = day;
    }

    public boolean palautaDay() {
        return this.paiva;

    }

    public void Lisaa(Kyky kyky, Pelattava pelattava) {
        if (this.KykyJaKayttaja.containsKey(kyky)) {
            ArrayList<Hahmo> hahmot = this.KykyJaKayttaja.get(kyky);
            LisametodiLisaykseen(hahmot, pelattava);
        } else {
            ArrayList<Hahmo> hahmot2 = new ArrayList<Hahmo>();
            LisametodiLisaykseen(hahmot2, pelattava);
        }
    }

    private void LisametodiLisaykseen(ArrayList<Hahmo> lista, Pelattava pelattava) {
        for (Hahmo hahmo : pelattava.getTeam()) {
            lista.add(hahmo);
        }


    }

    public ArrayList<Hahmo> PalautaHahmot(Kyky kyky) {

        return this.PalautaHahmot(kyky);
    }

    public String PalautaNimi() {
        return this.nimi;
    }

    public HashMap<Kyky, ArrayList<Hahmo>> palautaKokoHomma()
    {
        
    return this.KykyJaKayttaja;        
    }


    
}
