/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.YhdenAsianLuokkia;

import java.util.ArrayList;
import java.util.Random;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;

/**
 *
 * Tässä luokassa jaetaan Hahmot pelaajille. 
 */
public class Sekoittaja {

    /**
     * 
     * @param pelattavat
     * @param pelaajat
     * @return
     */
    public boolean SekoitaKayttenTiimeja(ArrayList<Pelattava> pelattavat, ArrayList<Pelaaja> pelaajat) {
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        for (Pelattava pelattva : pelattavat) {
            for (Hahmo hahmo : pelattva.getTeam()) {
                hahmot.add(hahmo);
            }
        }
        return Sekoita(hahmot, pelaajat);

    }

    /**
     * 
     * @param hahmot
     * @param pelaajat
     * @return
     */
    public boolean Sekoita(ArrayList<Hahmo> hahmot, ArrayList<Pelaaja> pelaajat) {
        if (hahmot.size() != pelaajat.size()) {
            return false;
        }
        ArrayList<Hahmo> hahmote = (ArrayList<Hahmo>) hahmot.clone();
        ArrayList<Pelaaja> pelaajate = (ArrayList<Pelaaja>) pelaajat.clone();
        Random generator = new Random();

        for (int i = hahmot.size(); i > 0; i--) {

            int a = generator.nextInt(i);
            int b = generator.nextInt(i);
            hahmote.get(a).asetaOmistaja(pelaajate.get(b));
            hahmote.remove(a);
            pelaajate.remove(b);

        }
        return true;




    }
}
