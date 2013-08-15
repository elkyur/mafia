/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.YhdenAsianLuokkia;

import java.util.Comparator;
import mafia.kyvyt.Atribuutti;
import mafia.kyvyt.Kyky;

/**
 *
 * Tässä luokassa on metodi Atribuuttien sortaamista varten.
 */
public class SortKyky implements Comparator<Atribuutti> {

    @Override
    public int compare(Atribuutti o1, Atribuutti o2) {

        int i = o2.palautaKyky().getBuffi().returnBuffinTyyppi().palautaPrioriteetti();
        int j = o1.palautaKyky().getBuffi().returnBuffinTyyppi().palautaPrioriteetti();

        return i - j;





    }
}
