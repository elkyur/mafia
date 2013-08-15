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
 * Tämä luokka vastaa Scannaus tyyppisitä kyvyistä.
 */
public class Skannaus extends Kyky {

    private HashMap<ArrayList<Rooli>, String> Skannaukset;

    /**
     *
     * @param nimi
     * @param buffi
     */
    public Skannaus(String nimi, Buff buffi) {

        super(nimi, buffi, true);
        this.Skannaukset = new HashMap<ArrayList<Rooli>, String>();

    }

    /**
     *
     * Lisaa HashMappiin rooli ja sen arvo minkä se palauttaa skannauksen
     * aikana.
     *
     */
    public void Lisaa(ArrayList<Rooli> roolit, String i) {
        this.Skannaukset.put(roolit, i);
    }

    /**
     *
     * @param castaaja
     * @param vastaanottavaa
     * @return
     */
    @Override
    public String Toiminnallisuus(Hahmo castaaja, Hahmo vastaanottavaa) {

        String viesti = "Scannauksen tulos: ";
        Hahmo vastaanottava = vastaanottavaa;

        vastaanottava.lisaaBuffi(super.palautaBuffi());

        for (ArrayList<Rooli> rooli : this.Skannaukset.keySet()) {
            if (rooli.contains(vastaanottava.palautaRooli())) {
                String ka = this.Skannaukset.get(rooli);
                viesti = viesti + ka;
                return viesti;
            }

        }
        viesti = viesti + "Muu";
        return viesti;
    }
    //   @Override
    //   public boolean equals(Kyky kyky) {
    //       if (this.nimi == kyky.getName() && this.buffi.equals(kyky.getBuffi())) {
    //           return true;
    //       }
    //       return false;
    //   }
}
