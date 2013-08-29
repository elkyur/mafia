/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface.pikkuObjektit;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;
import mafia.hahmot.Hahmo;

/**
 *
 * Paneeli jota ei ole vielä aktivoitu.
 */
public class messagePanel {

    private JPanel kuolleidenPanelli;
    private JList lista;
    private ArrayList<String> listData;
    private JLabel labeli;
    private JButton nappula;

    public messagePanel() {
        JFrame frame = new JFrame();
        frame.setVisible(false);
        frame.setLayout(new BorderLayout());
        this.labeli = new JLabel("Seuraavat pelaajat kuolivat:");
        frame.add(this.labeli, BorderLayout.NORTH);
      //  this.nappula = new JButton("Ookoo");
        this.kuolleidenPanelli = new JPanel();
        this.kuolleidenPanelli.setLayout(new FlowLayout());

        this.listData = new ArrayList<String>();
        this.lista = new JList(this.listData.toArray());
        this.lista.setVisibleRowCount(5);
        this.kuolleidenPanelli.add(new JScrollPane(this.lista));
        
        this.nappula = new JButton("buttoni");
        
        frame.add(this.kuolleidenPanelli, BorderLayout.CENTER);
       



    }

    public JPanel returnThePanel(String k) {
        JPanel main = new JPanel();
        main.setLayout(new FlowLayout());
        JLabel label = new JLabel(k);
        label.setFont(new Font("Courier New", Font.ITALIC, 20));
        main.add(label);
        return main;


    }

    public JPanel returnThePanel(ArrayList<String> a) {
        return null;
    }

    public JPanel returnPanelEvenWithPelaajat(ArrayList<Hahmo> riseDead) {

        
       
        listaUpdate(riseDead);

        this.kuolleidenPanelli.repaint();
        this.kuolleidenPanelli.validate();
        return this.kuolleidenPanelli;
    }

    public void listaUpdate(ArrayList<Hahmo> riseDead) {
        
        this.listData.clear();

        for (Hahmo hah : riseDead) {
            this.listData.add(hah.getOmistajanNimi());

        }

        this.lista.setListData(this.listData.toArray());
        
        this.lista.repaint();
        this.lista.validate();
    }
}
