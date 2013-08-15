/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import mafia.hahmot.Tiimi;

/**
 *
 * Tämä luokka vastaa kyvyistä, jossa tiimit rakenne voi muuttua.
 */
public class TiimiAsetus extends Kyky {

    private Tiimi tiimi;

    /**
     * 
     * @param nimi
     * @param tiimi
     * @param buffi
     */
    public TiimiAsetus(String nimi, Tiimi tiimi, Buff buffi) {
        super(nimi, buffi, true);
        ;
        this.tiimi = tiimi;


    }

    /**
     * 
     * @return
     */
    public Tiimi palautaTiimi() {

        return this.tiimi;
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
