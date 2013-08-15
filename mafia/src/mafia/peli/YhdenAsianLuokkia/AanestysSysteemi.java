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
 * Jos kykyä castaavia on monta. On pidettävä äänestys hahmojen välillä. Tämä luokka kantaa vastuun siitä. 
 */
public class AanestysSysteemi {

    private HashMap<Hahmo, ArrayList<Hahmo>> Aanestys;
    private ArrayList<Hahmo> Aanestaneet;
    private ArrayList<Hahmo> AanestyksessaMukana;
    private ArrayList<Hahmo> AanestysOikeutetut;

    /**
     * 
     * @param AanestyksessaMukana
     */
    public AanestysSysteemi(ArrayList<Hahmo> AanestyksessaMukana) {
        this.Aanestys = new HashMap<Hahmo, ArrayList<Hahmo>>();
        this.Aanestaneet = new ArrayList<Hahmo>();
        this.AanestyksessaMukana = AanestyksessaMukana;
        this.AanestysOikeutetut = this.AanestyksessaMukana;

    }

    /**
     * 
     * @param mukanaolevat
     */
    public void asetaAanestysOikeutetut(ArrayList<Hahmo> mukanaolevat) {
        this.AanestysOikeutetut = mukanaolevat;
    }
    
    /**
     * 
     */
    public void Reset()
    {
    this.AanestysOikeutetut = this.AanestyksessaMukana;
    }

    /**
     * 
     * @param hyokkaaja
     * @param puolustaja
     * @return
     */
    public Hahmo Aanesta(Hahmo hyokkaaja, Hahmo puolustaja) {
        if (this.Aanestaneet.contains(hyokkaaja)) {
            return null;
        }
        if (!this.AanestysOikeutetut.contains(hyokkaaja)) {
            return null;
        }
        if (!this.AanestyksessaMukana.contains(puolustaja)) {
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
        if (2 * this.Aanestys.get(puolustaja).size() > this.AanestysOikeutetut.size()) {
            return puolustaja;
        } else {

            return null;

        }
    }

    /**
     * 
     * @param hyokkaaja
     * @param puolustaja
     * @return
     */
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

    /**
     * 
     * @return
     */
    public String AanestysLaskelmat() {
        String k = "";
        for (Hahmo hahmo : this.Aanestys.keySet()) {
            k = k + hahmo.getOmistajanNimi() + ": " + this.Aanestys.get(hahmo) + '\n';

        }
        return k;
    }

    /**
     * 
     * @return
     */
    public int YhteensaMukana() {
        return this.Aanestaneet.size();
    }

    /**
     * 
     * @return
     */
    public ArrayList<Hahmo> palautaMukanaOlevat() {
        return this.AanestyksessaMukana;
    }
}
