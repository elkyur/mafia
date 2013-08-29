/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import GraphicInterface.pikkuObjektit.messagePanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * Tämä luokka vastaa pelivalikosta ja sen toiminnalisuudesta
 */
public class GraphicInterfaceCore extends JFrame {

    // private JButton Aloitus, Tietokanta, Statistiikka, Lopetus, vanhatpelit;
    private JMenuBar Valikko;
    private JMenu file, edit, info;
    private JMenuItem UusiPeli, Poistu, LisaaBuffi, LisaaKyky, LisaaRooli, LiitaRooliinKyky, lisaaFaasi, ConfigFaasinKyvyt, LisaaPelaaja, ListaaInfo, PoistuPelista;
    private JPanel Gamechooser, infopanelli;
    private JPanel Alkupanelli;
    private PanelliManageri manager;
    private Container pane;
    private JPanel Iterating;
    private JPanel pelaajatietokantapanelli;
 

    /**
     * Asettaa alkuarvot
     */
    

    public GraphicInterfaceCore(PanelliManageri manager) {
         
        
        LahtoPanelli();
        
         this.manager = manager;
         ConfigurateMenu();
         ImportStuff();
       

        super.setJMenuBar(Valikko);
        super.setTitle("Mafia peli: Valikko");
        this.pane = getContentPane();
        
        this.manager.setContainer(pane);


        pane.setLayout(new FlowLayout());
        pane.add(Alkupanelli);

        MenuListener MenuListener = new MenuListener();
        this.UusiPeli.addActionListener(MenuListener);
        this.ListaaInfo.addActionListener(MenuListener);
        this.Poistu.addActionListener(MenuListener);
        this.PoistuPelista.addActionListener(MenuListener);
        this.LisaaPelaaja.addActionListener(MenuListener);


        setSize(Settings.Settings.windowWidth, Settings.Settings.windowHeight);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //pack();

        
    }
    
     /**
     * asettaa LahtoPaneelin
     */
    
    
    public void LahtoPanelli()
    {
     this.Alkupanelli = new JPanel();
     this.Alkupanelli.setLayout(new BoxLayout(this.Alkupanelli, BoxLayout.Y_AXIS));
     JLabel label = new JLabel("Tervetuloa käyttämään mafiooso appia");
     JLabel label2 = new JLabel("Voit käynnistää pelin valikosta: File: Uusi Peli");
      JLabel label3 = new JLabel("Voit keskeyttää pelin valikosta: File : Keskeytä Peli");
      JLabel label4 = new JLabel("Voit myös lisätä uusia pelaajia tai poistaa ja kattoo niiden statistiikkaa: Aseta Valikosta");
      label.setFont(new Font("Courier New", Font.PLAIN , 20));
      label2.setFont(new Font("Courier New", Font.PLAIN , 20));
      label3.setFont(new Font("Courier New", Font.PLAIN , 20));
      label4.setFont(new Font("Courier New", Font.PLAIN , 15));
      this.Alkupanelli.add(label);
      this.Alkupanelli.add(label2);
      this.Alkupanelli.add(label3);
      this.Alkupanelli.add(label4);
    } 
    
         /**
     * Hankkii GameChoosersita tarvittavat
     */

    private void ImportStuff() {
        this.Gamechooser = this.manager.returnGameChooser();
        
       
       
        this.infopanelli = this.manager.PalautaInfo();
        this.pelaajatietokantapanelli = this.manager.palautaPelaajaTietokanta();
    }
    
        /**
     * Configaa valikon
     */
    

    private void ConfigurateMenu() {
        Valikko = new JMenuBar();
        
        file = new JMenu("File");
        edit = new JMenu("Aseta");
        info = new JMenu("info");
        Valikko.add(file);
        Valikko.add(edit);
        Valikko.add(info);
        ListaaInfo = new JMenuItem("Listaa info");
        UusiPeli = new JMenuItem("Siirry Peliin");
        Poistu = new JMenuItem("Poistu");
        LisaaBuffi = new JMenuItem("Lisaa Buffi");
        LisaaKyky = new JMenuItem("Lisaa Kyky");
        LisaaRooli = new JMenuItem("Lisaa Rooli");
        LiitaRooliinKyky = new JMenuItem(" Liita Roolin kyky");
        lisaaFaasi = new JMenuItem("Lisaa Faasi");
        ConfigFaasinKyvyt = new JMenuItem("Configaa Faasin kyvyt");
        LisaaPelaaja = new JMenuItem("Lisaa Pelaaja");
        PoistuPelista = new JMenuItem("Poistu nykyisestä pelistä");
        
        file.add(UusiPeli);
        file.add(PoistuPelista);
        file.add(Poistu);
        edit.add(LisaaBuffi);
        edit.add(LisaaKyky);
        edit.add(LisaaRooli);
        edit.add(LiitaRooliinKyky);
        edit.add(LisaaPelaaja);
        info.add(ListaaInfo);


    }
    
         /**
     * Refreshaa koko paneelin
     */
    
    public void Refresh()
    {
    validate();
    repaint();
    }
    
       /**
     * Asettaa paneelin ja refreshaa 
     */
    
    
    public void asetaPanelli(JPanel panel)
    {
    pane.removeAll();
    pane.add(panel);
    Refresh();
    }
         /**
     * Tämä luokka vastaa valikon toiminnalisuudesta
     */


    private class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == UusiPeli) {
                try {
                    manager.reLoadGameChooser();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GraphicInterfaceCore.class.getName()).log(Level.SEVERE, null, ex);
                }
             asetaPanelli(manager.returnIterating());
             
            }
            else if(e.getSource() == ListaaInfo)
            {
            asetaPanelli(infopanelli);
            }
            else if (e.getSource() == Poistu )
            {
            setVisible(false);
            dispose();
            System.exit(0); 
            }
            else if (e.getSource() == PoistuPelista )
            {
                try {
                    manager.FinalizeGame();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GraphicInterfaceCore.class.getName()).log(Level.SEVERE, null, ex);
                }
            asetaPanelli(manager.returnIterating());
            }
            else if (e.getSource() == LisaaPelaaja )
            {
            asetaPanelli(pelaajatietokantapanelli);
            }
        }
    }
}
