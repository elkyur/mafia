/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.YhdenAsianLuokkia;

import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import mafia.hahmot.Rooli;

/**
 *
 * @author Elkyur
 */
public class Misc {

    public ArrayList<Hahmo> Muutos(ArrayList<Pelattava> pelattavat) {
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        for (Pelattava pelattva : pelattavat) {
            for (Hahmo hahmo : pelattva.getTeam()) {
                hahmot.add(hahmo);
            }
        }
        return hahmot;

    }

    public ArrayList<Hahmo> Etsi(ArrayList<Hahmo> etsittavat, Rooli rooli) {
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        for (Hahmo hahmo : etsittavat) {
            if (hahmo.palautaRooli().equals(rooli)) {
                hahmot.add(hahmo);
            }
        }

        return hahmot;
    }

    public void ListanLisaaminenListaan(ArrayList<Hahmo> kohde, ArrayList<Hahmo> mistakopioidaan) {
        for (Hahmo hahmo : mistakopioidaan) {
            kohde.add(hahmo);
        }

    }
}
