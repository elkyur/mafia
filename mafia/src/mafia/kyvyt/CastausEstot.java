/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import java.util.ArrayList;
import mafia.hahmot.Hahmo;

/**
 *
 * @author Elkyur
 */
public class CastausEstot implements Kyky {

    private ArrayList<Kyky> Estetyt;
    private boolean kaikki;
    private String nimi;
    
    public CastausEstot()
    {
    this.Estetyt = new ArrayList<Kyky>();
    
    }
    
    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object Toiminnallisuus(ArrayList<Hahmo> castaaja, Hahmo vastaanottava) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equals(Kyky kyky) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PerusBuffi getBuffi() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean returnHeti() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
