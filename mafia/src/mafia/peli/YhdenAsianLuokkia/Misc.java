/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.YhdenAsianLuokkia;

import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Pelattava;
import mafia.hahmot.Rooli;

/**
 *
 * Tässä luokassa on pientä toiminnalisuutta, Kuten Pelattavien muuttaminen
 * Hahmoiksi.
 */
public class Misc {

    /**
     *
     * @param pelattavat
     * @return
     */
    public ArrayList<Hahmo> Muutos(ArrayList<Pelattava> pelattavat) {
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        for (Pelattava pelattva : pelattavat) {
            for (Hahmo hahmo : pelattva.getTeam()) {
                hahmot.add(hahmo);
            }
        }
        return hahmot;

    }

    /**
     *
     * @param etsittavat
     * @param rooli
     * @return
     */
    public ArrayList<Hahmo> Etsi(ArrayList<Hahmo> etsittavat, Rooli rooli) {
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        for (Hahmo hahmo : etsittavat) {
            if (hahmo.palautaRooli().equals(rooli)) {
                hahmot.add(hahmo);
            }
        }

        return hahmot;
    }

    /**
     *
     * @param kohde
     * @param mistakopioidaan
     */
    public void ListanLisaaminenListaan(ArrayList<Hahmo> kohde, ArrayList<Hahmo> mistakopioidaan) {
        for (Hahmo hahmo : mistakopioidaan) {
            kohde.add(hahmo);
        }


    }

    public ArrayList<Pelaaja> muunnaPelaajiksi(ArrayList<Hahmo> hah) {
        ArrayList<Pelaaja> pelaaja = new ArrayList<Pelaaja>();
        for (Hahmo h : hah) {
            pelaaja.add(h.palautaOmistaja());
        }
        return pelaaja;
    }

    public ArrayList<Pelaaja> ultimaattinenMuutos(ArrayList<Pelattava> pel) {

        ArrayList<Hahmo> hah = Muutos(pel);
        return muunnaPelaajiksi(hah);
    }

    public Hahmo EtsiPelaajienJoukossa(Pelaaja pel, ArrayList<Hahmo> hah) {
        for (Hahmo h : hah) {
            if (h.palautaOmistaja().equals(pel)) {
                return h;
            }

        }
        return null;
    }
}
