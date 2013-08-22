/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import mafia.hahmot.Tiimi;

/**
 *
 * Tämä luokka vastaa kyvyistä, jossa tiimit rakenne voi muuttua.
 */
public class TiimiAsetus extends Kyky {

    private ArrayList<Pelattava> viittausTiimeihin;
    private Tiimi tiimi;
    boolean ehdollinen;

    /**
     *
     * @param nimi
     * @param tiimi
     * @param buffi
     */
    public TiimiAsetus(String nimi, Tiimi tiimi, Buff buffi, ArrayList<Pelattava> viittausTiimeihin) {
        super(nimi, buffi, true);
        ;
        this.tiimi = tiimi;
        this.viittausTiimeihin = viittausTiimeihin;
       


    }

    /**
     *
     * @return
     */
    public Tiimi palautaTiimi() {

        return this.tiimi;
    }

    public Pelattava loydatiimit(Hahmo hahmo) {
        for (Pelattava pelattava : this.viittausTiimeihin) {
            for (Hahmo hahm : pelattava.getTeam()) {
                if (hahm.equals(hahmo)) {
                    return pelattava;
                }

            }
        }
        return null;
    }

  

    /**
     *
     * @param castaaja
     * @param vastaanottava
     * @return
     */
    @Override
    public String Toiminnallisuus(Hahmo castaaja, Hahmo vastaanottava) {
        tiimi.LisaaPelaaja(vastaanottava);
        vastaanottava.lisaaBuffi(super.palautaBuffi());
       
        return "";


    }
    //   @Override
    //   public boolean equals(Kyky kyky) {
    //       throw new UnsupportedOperationException("Not supported yet.");
    //   }
}
