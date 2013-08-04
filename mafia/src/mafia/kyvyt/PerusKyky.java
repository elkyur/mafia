/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;

/**
 *
 * @author Elkyur
 */
public class PerusKyky implements Kyky {

    private String name;
    private PerusBuffi buffi;
    private boolean heti;

    public PerusKyky(String name, PerusBuffi buffi, boolean heti) {
        this.name = name;
        this.buffi = buffi;
        this.heti = heti;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object Toiminnallisuus(ArrayList<Hahmo> castaava, Hahmo vastaanottava) {
        vastaanottava.lisaaBuffi(this.buffi);
        return null;

    }

    @Override
    public boolean equals(Kyky kyky) {
        if (this.name == kyky.getName() && this.buffi.equals(kyky.getBuffi())) {
            return true;
        }

        return false;
    }

    @Override
    public PerusBuffi getBuffi() {
        return this.buffi;
    }

    @Override
    public boolean returnHeti() {
      return this.heti;
    }
    
}
