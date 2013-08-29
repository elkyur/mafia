/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface.DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import mafia.hahmot.Pelaaja;
import mafia.peli.TietokantaHallinta.TietokantaManageri;

/**
 *
 * Tämä on Pelaajan paneeli joka extendaa yleistä graafiasta tietokanta managerii
 */
public class PelaajaTietokantaManageri extends abstractManager {

    public PelaajaTietokantaManageri(TietokantaManageri rakentaja, TietokantaALiRajapinta luonti, String k) {
        super(rakentaja, luonti, k);
    }

    @Override
    public void Kaanna(ArrayList<Object> objectit) {
        ArrayList<String> abc = super.palautaRivit();
        ArrayList<Pelaaja> pelaajat = new ArrayList<Pelaaja>();
        abc.clear();
        for (Object pelaaja : objectit) {
            Pelaaja p = (Pelaaja) pelaaja;
            pelaajat.add(p);
            abc.add(p.PalautaNimi() + ", " + p.palautaStatistiikka().palautaPelinLkm() + ", " + p.palautaStatistiikka().palautaVoittojenLkm());
        }
        super.palautaTietokantaManageri().asetaPelaajat(pelaajat);
        
    }

    @Override
    public void KirjoitaTiedostoon() {

        ArrayList<Pelaaja> pelaajat = new ArrayList<Pelaaja>();

        for (Object object : super.palautaObjectit()) {
            Pelaaja p = (Pelaaja) object;
            pelaajat.add(p);

        }
        super.palautaTietokantaManageri().asetaPelaajat(pelaajat);
        try {
            super.palautaTietokantaManageri().UudistaPelaajat();
        } catch (IOException ex) {
            Logger.getLogger(PelaajaTietokantaManageri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
