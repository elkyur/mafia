/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.ValmiiksAsetetut;

import mafia.peli.YhdenAsianLuokkia.Sekoittaja;
import java.util.ArrayList;
import mafia.hahmot.*;

/**
 *
 * @author Elkyur
 */
public class PerusMafiooso {

    private ArrayList<Pelaaja> pelaajat;
    private ArrayList<Pelattava> pelattavat;
    private Sekoittaja sekoittaja;

    public PerusMafiooso(ArrayList<Pelaaja> pelaajat, Sekoittaja sekoittaja) {
        this.pelaajat = pelaajat;
        this.sekoittaja = sekoittaja;

    }

    public void Run() {
        RooliInit();





    }

    private void RooliInit() {

        Rooli mafia = new Rooli("Mafia");
        Rooli kansalainen = new Rooli("Kansalainen");
        Rooli hullu = new Rooli("Hullu");
        Rooli hilleri = new Rooli("heaalaja");
        Rooli poliisi = new Rooli("Poliisi");

        int pelaajienlkm = this.pelaajat.size();
        int mafioidenlkm = this.pelaajat.size() / 3;
        Tiimi Mafiat = new Tiimi("Pahat mafiat");
        Tiimi Kansanpuolellaoleavat = new Tiimi("Hyvät kansalaiset");
        this.pelattavat.add(Mafiat);
        this.pelattavat.add(Kansanpuolellaoleavat);
        for (int i = 1; i <= mafioidenlkm; i++) {
            Hahmo mufia = new Hahmo(mafia);
            Mafiat.LisaaPelaaja(mufia);
        }
        this.pelattavat.add(new Hahmo(hullu));
        Kansanpuolellaoleavat.LisaaPelaaja(new Hahmo(hilleri));
        Kansanpuolellaoleavat.LisaaPelaaja(new Hahmo(poliisi));
        for (int i = 1; i <= pelaajienlkm - mafioidenlkm - 3; i++) {
            Kansanpuolellaoleavat.LisaaPelaaja(new Hahmo(hilleri));
        }


    }
}
