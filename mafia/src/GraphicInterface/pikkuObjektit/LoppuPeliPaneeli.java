/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface.pikkuObjektit;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import mafia.peli.YhdenAsianLuokkia.Misc;

/**
 *
 * Kun peli Loppuu tämä paneeli laitetaan päälle. Tässä paneelissa palautetaan pelin voittaneet. 
 */
public class LoppuPeliPaneeli {

    private JPanel panelli;
    private JScrollPane scrollausjuttu;
    private JLabel label;
    private Misc misc;
    private JList lista;
    private ArrayList<String> addausLista;

    public LoppuPeliPaneeli() {
        this.panelli = new JPanel();
        panelli.setLayout(new BorderLayout());
        this.addausLista = new ArrayList<String>();
        this.misc = new Misc();
        this.lista = new JList();

        this.label = new JLabel("Pelin Voittivat:");
        this.scrollausjuttu = new JScrollPane(this.lista);
        this.panelli.add(this.label, BorderLayout.NORTH);
        this.panelli.add(this.scrollausjuttu, BorderLayout.CENTER);
    }

    public JPanel palautaPanelli() {
        return panelli;
    }

    public void Update(ArrayList<Hahmo> hahmot) {

       
        misc.MuutaHahmotStringTyypiksi(this.addausLista, hahmot);
        this.lista.setListData(this.addausLista.toArray());
        this.panelli.validate();
        this.panelli.repaint();

    }


}
