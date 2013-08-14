/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.YhdenAsianLuokkia;

import java.util.Comparator;
import mafia.kyvyt.Kyky;

/**
 *
 * @author Elkyur
 */
public class SortKyky implements Comparator<Kyky> {

    @Override
    public int compare(Kyky o1, Kyky o2) {

        int i = o2.getBuffi().returnBuffinTyyppi().palautaPrioriteetti();
        int j = o1.getBuffi().returnBuffinTyyppi().palautaPrioriteetti();

        if (o1.equals(o2)) {
            return 0;
        }

        else if (i == j) {
            return 0;

        } else {
            return i-j;
        }




    }
}
