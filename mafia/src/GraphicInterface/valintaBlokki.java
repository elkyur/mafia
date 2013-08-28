/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelaaja;

/**
 *
 * @author Elkyur
 */
public final class valintaBlokki {

    private JList left, right;
    private JPanel Mainpanel, Leftpanel, Rightpanel, Buttonpanel, FusionPanel;
    private JButton Lisaa;
    private JButton Tyhjenna;
    private ArrayList<Pelaaja> pelaajat;
    private ArrayList<Pelaaja> valitutPelaajat;
    private JLabel label;
    private String nimi;
    private ArrayList<String> uudet, valitutuudet;
    private int selectionlimit;
    private Listener listen;
    private ArrayList<Hahmo> hahmot;
    private int size;
    private boolean way;

    public valintaBlokki(String nimi, int selectionlimit, int size) {



        this.size = size;
        // this.pelaajat = pelaaja;
        this.listen = new Listener();
        this.nimi = nimi;
        this.uudet = new ArrayList<String>();
        this.valitutuudet = new ArrayList<String>();
        this.selectionlimit = selectionlimit;
        Configaa();
        this.valitutPelaajat = new ArrayList<Pelaaja>();
        this.way = true;


    }

    public void Convert() {
        for (Hahmo hah : this.hahmot) {
            this.pelaajat.add(hah.palautaOmistaja());
        }
    }

    public void asetaPelaajat(ArrayList<Pelaaja> pelaaja) {
        this.pelaajat = pelaaja;
    }

    public void Configaa() {

        //    InitLeftString();
        this.left = new JList();
        this.right = new JList();

        this.Lisaa = new JButton("Lisaa");
        this.Lisaa.addActionListener(this.listen);
        this.Tyhjenna = new JButton("Tyhjenna");
        this.Tyhjenna.addActionListener(this.listen);
        this.Buttonpanel = new JPanel();
        this.Buttonpanel.setLayout(new BoxLayout(this.Buttonpanel, BoxLayout.Y_AXIS));
        this.Buttonpanel.add(this.Lisaa);
        this.Buttonpanel.add(this.Tyhjenna);
        this.Leftpanel = new JPanel();
        this.Rightpanel = new JPanel();
        this.FusionPanel = new JPanel();
        this.FusionPanel.add(new JScrollPane(left));
        this.FusionPanel.add(new JScrollPane(right));
        this.FusionPanel.add(this.Buttonpanel);
        this.Mainpanel = new JPanel();
        this.Mainpanel.setLayout(new BorderLayout());
        this.label = new JLabel(this.nimi);
        this.Mainpanel.add(this.label, BorderLayout.NORTH);
        this.Mainpanel.add(this.FusionPanel, BorderLayout.CENTER);
        this.FusionPanel.setLayout(new BoxLayout(this.FusionPanel, BoxLayout.X_AXIS));





        left.setVisibleRowCount(size);

        left.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //   left.setListData(this.uudet.toArray());



        right.setVisibleRowCount(size);

    }

    public void lataa(ArrayList<Pelaaja> pel) {
        this.pelaajat = pel;
        InitLeftString();
        left.setListData(this.uudet.toArray());
        this.left.validate();
        left.repaint();

    }

    public void DisplayData() {

        this.FusionPanel.validate();

    }

    public ArrayList<Pelaaja> palautavalitut() {
        return this.valitutPelaajat;
    }

    public JPanel palautaBlokki() {
        return this.Mainpanel;
    }

    public void InitLeftString() {

        {
            this.uudet.clear();
            for (int i = 0; i < this.pelaajat.size(); i++) {
                this.uudet.add(this.pelaajat.get(i).PalautaNimi());
            }

        }


    }

    public void InitRightString() {

        {
            this.valitutuudet.clear();
            for (int i = 0; i < this.valitutPelaajat.size(); i++) {
                this.valitutuudet.add(this.valitutPelaajat.get(i).PalautaNimi());
            }
        }
    }

    public void PrinttaaValitut() {
        for (Pelaaja pelaaja : valitutPelaajat) {
            System.out.println("Valittu: " + pelaaja.PalautaNimi());
        }
    }
    
    public void Clear()
    {
    
     this.valitutPelaajat.clear();
     InitRightString();
      this.Rightpanel.validate();
      this.Rightpanel.repaint();
    
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Lisaa) {
                int j = 0;

                if (valitutPelaajat.size() < selectionlimit) {

                    j = left.getSelectedIndex();
                    if (!valitutPelaajat.contains(pelaajat.get(j))) {
                        valitutPelaajat.add(pelaajat.get(j));

                        InitRightString();

                        right.setListData(valitutuudet.toArray());

                        right.validate();
                        right.repaint();

                    }
                }

            } else if (e.getSource() == Tyhjenna) {
                valitutPelaajat.clear();

                InitRightString();
                right.setListData(valitutuudet.toArray());
                Mainpanel.validate();

            }
        }
    }
}
