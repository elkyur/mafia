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
 * @author Elkyur
 */
public class Sekoittaja {

    public void SekoitaKayttenTiimeja(ArrayList<Pelattava> pelattavat, ArrayList<Pelaaja> pelaajat) {
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        for (Pelattava pelattva : pelattavat) {
            for (Hahmo hahmo : pelattva.getTeam()) {
                hahmot.add(hahmo);
            }
        }
        Sekoita(hahmot, pelaajat);

    }

    public void Sekoita(ArrayList<Hahmo> hahmot, ArrayList<Pelaaja> pelaajat) {
        if (hahmot.size() != pelaajat.size()) {
            return;
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





    }
}
