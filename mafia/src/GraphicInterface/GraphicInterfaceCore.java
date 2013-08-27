/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * Tämä luokka vastaa pelivalikosta
 */
public class GraphicInterfaceCore extends JFrame {

    // private JButton Aloitus, Tietokanta, Statistiikka, Lopetus, vanhatpelit;
    private JMenuBar Valikko;
    private JMenu file, edit, info;
    private JMenuItem UusiPeli, Poistu, LisaaBuffi, LisaaKyky, LisaaRooli, LiitaRooliinKyky, lisaaFaasi, ConfigFaasinKyvyt, LisaaPelaaja, ListaaInfo;
    private JPanel Gamechooser, infopanelli;
    private JPanel Alkupanelli;
    private JPanerManager manager;
    public Container pane;

    public GraphicInterfaceCore(JPanerManager manager) {
         this.manager = manager;
         
         ConfigurateMenu();
         ImportStuff();
       

        // this.Alkupanelli = Gamechooser;

        super.setJMenuBar(Valikko);
        super.setTitle("Mafia peli: Valikko");
        this.pane = getContentPane();
        
        this.manager.setContainer(pane);

        //JPanel panel = new JPanel();
        // panel.setLayout(new FlowLayout());

        pane.setLayout(new FlowLayout());
        pane.add(Alkupanelli);

        MenuListener MenuListener = new MenuListener();
        this.UusiPeli.addActionListener(MenuListener);
        this.ListaaInfo.addActionListener(MenuListener);
        this.Poistu.addActionListener(MenuListener);
        


        setSize(Settings.Settings.windowWidth, Settings.Settings.windowHeight);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //pack();

        
    }

    private void ImportStuff() {
        this.Gamechooser = this.manager.returnGameChooser();
        
        this.Alkupanelli = new JPanel();
        this.infopanelli = this.manager.PalautaInfo();

    }

    private void ConfigurateMenu() {
        Valikko = new JMenuBar();

        file = new JMenu("File");
        edit = new JMenu("Aseta");
        info = new JMenu("info");
        Valikko.add(file);
        Valikko.add(edit);
        Valikko.add(info);
        ListaaInfo = new JMenuItem("Listaa info");
        UusiPeli = new JMenuItem("Aloita Uusi Peli");
        Poistu = new JMenuItem("Poistu");
        LisaaBuffi = new JMenuItem("Lisaa Buffi");
        LisaaKyky = new JMenuItem("Lisaa Kyky");
        LisaaRooli = new JMenuItem("Lisaa Rooli");
        LiitaRooliinKyky = new JMenuItem(" Liita Roolin kyky");
        lisaaFaasi = new JMenuItem("Lisaa Faasi");
        ConfigFaasinKyvyt = new JMenuItem("Configaa Faasin kyvyt");
        LisaaPelaaja = new JMenuItem("Lisaa Pelaaja");
        file.add(UusiPeli);
        file.add(Poistu);
        edit.add(LisaaBuffi);
        edit.add(LisaaKyky);
        edit.add(LisaaRooli);
        edit.add(LiitaRooliinKyky);
        edit.add(LisaaPelaaja);
        info.add(ListaaInfo);


    }
    
    public void Refresh()
    {
    validate();
    repaint();
    }

    private class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == UusiPeli) {
                pane.removeAll();
                pane.add(Gamechooser);
                Refresh();

            }
            else if(e.getSource() == ListaaInfo)
            {
                pane.removeAll();
                pane.add(infopanelli);
                Refresh();
            }
            else if (e.getSource() == Poistu )
            {
            setVisible(false);
            dispose();
            System.exit(0); 
            }
        }
    }
}
