/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * Tämä luokka vastaa pelivalikosta
 */
public class gamemenu extends JFrame {
    
    private JButton Aloitus, Tietokanta, Statistiikka, Lopetus, vanhatpelit;
    
    public gamemenu()
    {
    super.setTitle("Mafia peli: Valikko");
    Container pane = getContentPane();
    pane.setLayout(new GridLayout(5,1));
    
    Aloitus = new JButton("Uusi Peli");
    Tietokanta = new JButton("Lisaa Hahmoja");
    Statistiikka = new JButton("Pelaajien voimatasot");
    vanhatpelit = new JButton("Selaa vanhoja peleja");
    Lopetus = new JButton("Poistu");
    
    
    setSize(Settings.Settings.windowWidth, Settings.Settings.windowHeight);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    pane.add(Aloitus);
    pane.add(Tietokanta);
    pane.add(Statistiikka);
    pane.add(vanhatpelit);
    pane.add(Lopetus);
    }
    
    
    
}
