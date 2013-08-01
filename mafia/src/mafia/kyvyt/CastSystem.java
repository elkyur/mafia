/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import mafia.hahmot.Pelattava;

/**
 *
 * @author Elkyur
 */
public class CastSystem implements Kyky {

    private String name;
    private Buff buff;

    public CastSystem(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void Toiminnallisuus(Pelattava castaaja, Pelattava vastaanottava) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
