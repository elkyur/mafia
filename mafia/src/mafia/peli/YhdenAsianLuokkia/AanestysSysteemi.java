/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.YhdenAsianLuokkia;

import java.util.ArrayList;
import java.util.HashMap;
import mafia.hahmot.Hahmo;

/**
 *
 * This System let players find solution who to choose.
 */
public class AanestysSysteemi {

    private HashMap<Hahmo, ArrayList<Hahmo>> Aanestys;
    private ArrayList<Hahmo> Aanestaneet;
    private ArrayList<Hahmo> AanestyksessaMukana;

    public AanestysSysteemi(ArrayList<Hahmo> AanestyksessaMukana) {
        this.Aanestys = new HashMap<Hahmo, ArrayList<Hahmo>>();
        this.Aanestaneet = new ArrayList<Hahmo>();
        this.AanestyksessaMukana = AanestyksessaMukana;


    }

    public Hahmo Aanesta(Hahmo hyokkaaja, Hahmo puolustaja) {
        if (this.Aanestaneet.contains(hyokkaaja)) {
            return null;
        }
        if (!this.AanestyksessaMukana.contains(hyokkaaja)) {
            return null;
        }
        this.Aanestaneet.add(hyokkaaja);
        if (this.Aanestys.containsKey(puolustaja)) {
            this.Aanestys.get(puolustaja).add(hyokkaaja);

        } else {
            ArrayList<Hahmo> hyokkaajat = new ArrayList<Hahmo>();
            hyokkaajat.add(hyokkaaja);
            this.Aanestys.put(puolustaja, hyokkaajat);

        }
        if (2 * this.Aanestys.get(puolustaja).size() > this.AanestyksessaMukana.size()) {
            return puolustaja;
        } else {

            return null;

        }
    }

    public boolean UnAanesta(Hahmo hyokkaaja, Hahmo puolustaja) {
        if (!this.Aanestaneet.contains(hyokkaaja)) {
            return false;
        }

        if (this.Aanestys.containsKey(puolustaja)) {
            if (this.Aanestys.get(puolustaja).contains(hyokkaaja)) {
                this.Aanestys.get(puolustaja).remove(hyokkaaja);
            }
            if (this.Aanestys.get(puolustaja).isEmpty()) {
                this.Aanestys.remove(puolustaja);
            }
            return true;
        }
        return false;
    }

    public String AanestysLaskelmat() {
        String k = "";
        for (Hahmo hahmo : this.Aanestys.keySet()) {
            k = k + hahmo.getOmistajanNimi() + ": " + this.Aanestys.get(hahmo) + '\n';

        }
        return k;
    }

    public int YhteensaMukana() {
        return this.Aanestaneet.size();
    }
}
