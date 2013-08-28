/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import mafia.hahmot.Pelaaja;
import mafia.peli.ReadWriting.PelinRakentaja;

/**
 *
 * @author Elkyur
 */
public class KaynnistajaLuokka {
    
    private JPanel GameChooser, ButtonPanel;
    private JButton BasicGame, CustomGame;
    private PelinRakentaja rakentaja;
    private ArrayList<Pelaaja> pelaajat;
    private valintaBlokki uusiBlokki;
    
    public KaynnistajaLuokka(PelinRakentaja rakentaja) throws FileNotFoundException
    {    
        ConfigaaButtonit();
        this.rakentaja = rakentaja;
        this.GameChooser = new JPanel();
        this.GameChooser.setLayout(new BorderLayout());
        this.GameChooser.add(this.ButtonPanel, BorderLayout.SOUTH);
        rakentaja.LataaPelaajat();
        this.pelaajat = rakentaja.PalautaPelaajat();
        this.uusiBlokki = new valintaBlokki("Valitse Näistä", 100, 12);
        this.uusiBlokki.lataa(pelaajat);
        JPanel panel = this.uusiBlokki.palautaBlokki();
        this.GameChooser.add(panel, BorderLayout.CENTER);
           
    
    }
    
    public void ConfigaaButtonit()
    {
    
        this.ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.X_AXIS));
        this.BasicGame = new JButton("Perus Mafiooso");
     //   this.BasicGame.addActionListener(this.action);
        this.CustomGame = new JButton("Custom peli");
     //   this.CustomGame.addActionListener(this.action);
    //    this.LukitseKyky = this.PelinSisainen.palautaPaaButtoni();
     //   this.LukitseKyky.addActionListener(this.action);

        this.ButtonPanel.add(BasicGame);
        this.ButtonPanel.add(CustomGame);
    }
    
    public JPanel returnMainPanel()
    {
    return this.GameChooser;
    }
    public JButton returnBasicGame()
    {
    return this.BasicGame;
    }
    public JButton returnCustomGame()
    {
    return this.CustomGame;
    }
}
