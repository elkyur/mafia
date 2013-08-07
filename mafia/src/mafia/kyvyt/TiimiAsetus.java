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
 * @author Elkyur
 */
public class TiimiAsetus implements Kyky {

    private Tiimi tiimi;
    private String nimi;
    private Buff buffi;
    private int usageTime;

    public TiimiAsetus(String nimi, Tiimi tiimi, Buff buffi) {
        this.nimi = nimi;
        this.tiimi = tiimi;
        this.buffi = buffi;

    }

    public Tiimi palautaTiimi() {

        return this.tiimi;
    }

    public void asetaUsageTime(int j) {
        this.usageTime = j;
    }

    @Override
    public String getName() {
        return this.nimi;
    }

    @Override
    public String Toiminnallisuus(Hahmo castaaja, Hahmo vastaanottava) {
        tiimi.LisaaPelaaja(vastaanottava);
        vastaanottava.lisaaBuffi(this.buffi);
        return "";


    }

    @Override
    public boolean equals(Kyky kyky) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Buff getBuffi() {
        return this.buffi;
    }

    @Override
    public boolean returnHeti() {
        return true;
    }

    @Override
    public boolean returnOnRequest() {
        return true;
    }

    @Override
    public int UsageTimes() {
        return this.usageTime;
    }
}
