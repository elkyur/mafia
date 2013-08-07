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
    private ArrayList<Hahmo> NightAanestys;
    private ArrayList<Hahmo> AanestyksessaMukana;
    private boolean YoAanestysMukana;

    public AanestysSysteemi(ArrayList<Hahmo> AanestyksessaMukana, boolean YoAanestysMukana) {
        this.Aanestys = new HashMap<Hahmo, ArrayList<Hahmo>>();
        this.AanestyksessaMukana = AanestyksessaMukana;
        this.NightAanestys = new ArrayList<Hahmo>();
        this.YoAanestysMukana = YoAanestysMukana;
    }

    public Hahmo Aanesta(Hahmo hyokkaaja, Hahmo puolustaja) {
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

    public void UnAanesta(Hahmo hyokkaaja, Hahmo puolustaja) {
        if (this.Aanestys.containsKey(puolustaja)) {
            if (this.Aanestys.get(puolustaja).contains(hyokkaaja)) {
                this.Aanestys.remove(hyokkaaja);

            }
        }
    }
   
}
