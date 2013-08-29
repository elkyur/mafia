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
     * Muuttaa Pelattavat hahmoksi 
     *
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
     
              /**
     * Etsii Roolin hahmojen seasta
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
     * Lisataan lista listaan
     * 
     */
    
    public void ListanLisaaminenListaan(ArrayList<Hahmo> kohde, ArrayList<Hahmo> mistakopioidaan) {
        for (Hahmo hahmo : mistakopioidaan) {
            kohde.add(hahmo);
        }


    }
      /**
     * Muuten hahmot pelaajiksi
     */

    public ArrayList<Pelaaja> muunnaPelaajiksi(ArrayList<Hahmo> hah) {
        ArrayList<Pelaaja> pelaaja = new ArrayList<Pelaaja>();
        for (Hahmo h : hah) {
            pelaaja.add(h.palautaOmistaja());
        }
        return pelaaja;
    }
    
     /**
     * muutetaan Pelattavat pelaajiksi
     */

    public ArrayList<Pelaaja> ultimaattinenMuutos(ArrayList<Pelattava> pel) {

        ArrayList<Hahmo> hah = Muutos(pel);
        return muunnaPelaajiksi(hah);
    }
    
       /**
     * Etsii pelaajan hahmojen joukossa
     */

    public Hahmo EtsiPelaajienJoukossa(Pelaaja pel, ArrayList<Hahmo> hah) {
        for (Hahmo h : hah) {
            if (h.palautaOmistaja().equals(pel)) {
                return h;
            }

        }
        return null;
    }
 /**
     * Muuttaa Pelattavan String tyyppiseksi
     */

    
    public void MuutaPelattavaStringTyypiksi(ArrayList<String> tahakopioidaan, ArrayList<Pelattava> pelattavt) {
        tahakopioidaan.clear();
        for (Pelattava pel : pelattavt) {
            for (Hahmo hahmo : pel.getTeam()) {
                String k = hahmo.getNimi();
                k = k + ", " + pel.getNimi();
                k = k + ": " + hahmo.getOmistajanNimi();

                tahakopioidaan.add(k);
                // this.KyseisetHahmotValittuina.set(i, hahmo);

            }
        }

    }
    
     /**
     * Muuttaa Hahmon string tyyppiseksi
     */

    public void MuutaHahmotStringTyypiksi(ArrayList<String> tahakopioidaan, ArrayList<Hahmo> hahmot) {
        tahakopioidaan.clear();

        for (Hahmo hahmo : hahmot) {
            String k = hahmo.getNimi();
            k = k + ": " + hahmo.getOmistajanNimi();

            tahakopioidaan.add(k);
            // this.KyseisetHahmotValittuina.set(i, hahmo);

        }
    
}
}
