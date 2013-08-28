/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import GraphicInterface.pikkuObjektit.PelattavienListaus;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Pelattava;
import mafia.kyvyt.Atribuutti;
import mafia.peli.YhdenAsianLuokkia.Misc;

/**
 *
 * @author Elkyur
 */
public class ItsInTheGame {

    private JPanel mainPanelwithoutTitle, miniPanel, underMainPanel, cast, recieve, titleFull;
    private PelattavienListaus listaus;
    private JButton heratysViesti;
    private JButton Castaa;
    private valintaBlokki blokkiCastaaja, blokkiTargetti;
    private ArrayList<Pelattava> kaikkiPelattavat;
    private Atribuutti atr;
    private Misc tyokalu;
    private JLabel label;

    public ItsInTheGame(PelattavienListaus listaus) {

        this.listaus = listaus;
        this.mainPanelwithoutTitle = new JPanel();
        this.mainPanelwithoutTitle.setLayout(new BoxLayout(this.mainPanelwithoutTitle, BoxLayout.X_AXIS));
        this.mainPanelwithoutTitle.add(listaus.palautaPanelli());
        this.miniPanel = new JPanel();
        this.miniPanel.setLayout(new BoxLayout(this.miniPanel, BoxLayout.Y_AXIS));
        this.underMainPanel = new JPanel();
        this.underMainPanel.setLayout(new BorderLayout());
        this.underMainPanel.add(this.miniPanel, BorderLayout.CENTER);
        this.mainPanelwithoutTitle.add(underMainPanel);
        this.heratysViesti = new JButton("Herata");
        this.Castaa = new JButton("Castaus");
        this.underMainPanel.add(this.heratysViesti, BorderLayout.NORTH);
        this.underMainPanel.add(this.Castaa, BorderLayout.SOUTH);
        this.blokkiCastaaja = new valintaBlokki("Valitse Castaaja", 1, 5);
        this.blokkiTargetti = new valintaBlokki("Valitse uhri", 1, 12);
        this.cast = blokkiCastaaja.palautaBlokki();
        this.recieve = blokkiTargetti.palautaBlokki();
        this.miniPanel.add(this.cast);
        this.miniPanel.add(this.recieve);
        this.tyokalu = new Misc();
        this.label = new JLabel("lol");
        label.setFont(new Font("Courier New", Font.PLAIN, 20));

        this.titleFull = new JPanel();
        this.titleFull.setLayout(new BorderLayout());
        this.titleFull.add(label, BorderLayout.NORTH);
        this.titleFull.add(this.mainPanelwithoutTitle, BorderLayout.CENTER);
    }

    public void update(ArrayList<Pelattava> pelattavat, Atribuutti atr) {
        ClearBoxes();
        label.setText(atr.palautaKyky().palautaNimi());
        this.atr = atr;
        this.kaikkiPelattavat = pelattavat;

        listaus.Lataa(pelattavat);

        blokkiCastaaja.lataa(tyokalu.muunnaPelaajiksi(atr.palautaHahmot()));
        blokkiTargetti.lataa(tyokalu.ultimaattinenMuutos(pelattavat));

        this.titleFull.validate();
        this.titleFull.repaint();

    }

    public Hahmo palautaHyokkaaja(ArrayList<Pelattava> viittaustiimeihin) {

         if(NullChecker(this.blokkiCastaaja))
        {
        return null;
        }
        
        ArrayList<Hahmo> hah = tyokalu.Muutos(viittaustiimeihin);
        Pelaaja valittu = blokkiCastaaja.palautavalitut().get(0);

        return tyokalu.EtsiPelaajienJoukossa(valittu, hah);

    }

    public boolean NullChecker(valintaBlokki blokki) {

        if (blokki.palautavalitut() == null) {
            return true;
        } else if (blokki.palautavalitut().isEmpty()) {
            return true;
        }
        return false;
    }

    public Hahmo palautaPuolustaja(ArrayList<Pelattava> viittaustiimeihin) {
        
        if(NullChecker(this.blokkiTargetti))
        {
        return null;
        }


        ArrayList<Hahmo> hah = tyokalu.Muutos(viittaustiimeihin);
        Pelaaja valittu = blokkiTargetti.palautavalitut().get(0);

        return tyokalu.EtsiPelaajienJoukossa(valittu, hah);

    }

    public JButton palautaPaaButtoni() {
        return this.Castaa;
    }

    public JPanel palautaPaneelli() {
        return this.titleFull;

    }

    public void ClearBoxes() {
        this.blokkiCastaaja.Clear();
        this.blokkiTargetti.Clear();
    }
}
