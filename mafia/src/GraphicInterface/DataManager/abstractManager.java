/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface.DataManager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import mafia.peli.TietokantaHallinta.TietokantaManageri;

/**
 *
 * Yleinen tietokanta luonti paneeeli, jossa voi lukee tietokantoja ja luoda uusi objecteja 
 */
public abstract class abstractManager {

    private JButton Add, Kill;
    private JLabel header;
    private JPanel mainpanel;
    private JPanel underpanel;
    private TietokantaManageri rakentaja;
    private TietokantaALiRajapinta luonti;
    private ArrayList<String> rivit;
    private ArrayList<Object> objectit;
    private JPanel leftPanel;
    private JButton push;
    private JList lista;
    private JScrollPane scrollauspaneeli;
    private Listener kuuntelija;

    /**
 *
 * Taas configurataan kaikki screenille
 */
    
    public abstractManager(TietokantaManageri rakentaja, TietokantaALiRajapinta luonti, String k) {
         this.kuuntelija = new Listener();
        initLeftPanel();
        this.luonti = luonti;
       
        this.rivit = new ArrayList<String>();
        this.rakentaja = rakentaja;
        this.mainpanel = new JPanel();
        this.underpanel = new JPanel();
        this.header = new JLabel("Tietokanta: " + k);

        this.mainpanel.setLayout(new BorderLayout());
        this.mainpanel.add(this.header, BorderLayout.NORTH);

        underpanel.setLayout(new BoxLayout(underpanel, BoxLayout.X_AXIS));
        underpanel.add(leftPanel);
        underpanel.add(luonti.palautaMainPanel());

        mainpanel.add(underpanel, BorderLayout.CENTER);

        push = new JButton("Tallenna muutokset tietokantaan");
        push.addActionListener(this.kuuntelija);
        mainpanel.add(push, BorderLayout.SOUTH);

        Add = this.luonti.palautaFunktionaalinenNappula();

        Add.addActionListener(this.kuuntelija);






    }

    public TietokantaManageri palautaTietokantaManageri() {
        return this.rakentaja;
    }

      /**
 *
 * Initta vasemman paneelin, eli siis paneelin jossa voi selata objecteja ja poistaa niita 
 */
    
    public void initLeftPanel() {
        this.leftPanel = new JPanel();
        this.lista = new JList();
        this.lista.setVisibleRowCount(12);
        this.lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.scrollauspaneeli = new JScrollPane(this.lista);
        Kill = new JButton("Poista");
        Kill.addActionListener(this.kuuntelija);
        this.leftPanel.setLayout(new BoxLayout(this.leftPanel, BoxLayout.Y_AXIS));
        this.leftPanel.add(scrollauspaneeli);
        this.leftPanel.add(Kill);


    }
    
     

    public ArrayList<String> palautaRivit() {
        return rivit;

    }

    public ArrayList<Object> palautaObjectit() {
        return this.objectit;
    }

      /**
 *
 * Kaantaa minkä tahansa ArrayList objectin , Arraylist Stringiin 
 */
    public abstract void Kaanna(ArrayList<Object> objectit);
    /**
 *
 * Kirjoittaa tiedostoon 
 */
    
    public abstract void KirjoitaTiedostoon();
    
  //  public abstract void PoistaTietoKannasta();

    public void Lataa(ArrayList<Object> object) {
        objectit = object;
        update();
    }
    
    /**
 *
 * Päivittää
 */

    public void update() {
        Kaanna(objectit);
        this.lista.setListData(this.rivit.toArray());
        this.mainpanel.validate();
        this.mainpanel.repaint();

    }
    
      /**
 *
 * Palauttaa antiTrollaus viestin 
 */

    public void antiTroll() {
        JOptionPane.showMessageDialog(new JPanel(), "Et valinnu mitään ja silti painoit sitä nappulaa?");
    }
    
       /**
 *
 * Palauttaa antiTrollaus viestin 
 */
     public void antiLolcat() {
        JOptionPane.showMessageDialog(new JPanel(), "Tähän saa syöttää vain numeroita. Suosittelen ohjeiden lukemista");
    }
     
     
    
    public JPanel palautaMain()
    {
    return this.mainpanel;
    }

         /**
 *
 * Taas actionit. 
 */
    
    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Kill) {
             System.out.println("päästiin tähä , vihdoinki :)");
                if (lista.getSelectedValue() != null) {
                    Object object = objectit.get(lista.getSelectedIndex());
                    objectit.remove(object);
                    update();
                } else {
                    antiTroll();
                }

            } else if (e.getSource() == Add) {
                 
                if (luonti.OnkoSallittu()) {
                    Object object = luonti.palautaObjekti();
                    objectit.add(object);
                    update();
                }
                else
                {
                 antiLolcat();
                }


            } else if (e.getSource() == push) {
                KirjoitaTiedostoon();
                JOptionPane.showMessageDialog(new JPanel(), "Muutokset puskettu tiedostoihin []");
            }


        }
    }

}
