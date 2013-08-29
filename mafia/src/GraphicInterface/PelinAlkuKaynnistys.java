/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import GraphicInterface.pikkuObjektit.ValitsijaBlokki;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import mafia.hahmot.Pelaaja;
import mafia.peli.TietokantaHallinta.TietokantaManageri;

/**
 *
 * Tämä luokka vastaa pelin käynnistämisestä, eli kun halutaan aloittaa uusi peli tässä luokassa valitaan mitkä pelaajat siihen osallistuu
 */
public class PelinAlkuKaynnistys {
    
    private JPanel GameChooser, ButtonPanel;
    private JButton BasicGame, CustomGame;
    private TietokantaManageri rakentaja;
    private ArrayList<Pelaaja> pelaajat;
    private ValitsijaBlokki uusiBlokki;
    
/**
 *
 * Laittaaa alkuArvot
 */
    
    public PelinAlkuKaynnistys(TietokantaManageri rakentaja) throws FileNotFoundException
    {    
        ConfigaaButtonit();
        this.rakentaja = rakentaja;
        this.GameChooser = new JPanel();
        this.GameChooser.setLayout(new BorderLayout());
        this.GameChooser.add(this.ButtonPanel, BorderLayout.SOUTH);
       // rakentaja.LataaPelaajat();
        this.pelaajat = rakentaja.PalautaPelaajat();
        this.uusiBlokki = new ValitsijaBlokki("Valitse Näistä", 100, 12);
        this.uusiBlokki.lataa(pelaajat);
        JPanel panel = this.uusiBlokki.palautaBlokki();
        this.GameChooser.add(panel, BorderLayout.CENTER);
           
    
    }
    /**
 *
 * Configaa nappulat
 */
    
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
    public ArrayList<Pelaaja> palautaPelaajat()
    {
    return this.uusiBlokki.palautavalitut();
    }
       /**
 *
 * Päivittää
 */
    
    public void ReLoad() throws FileNotFoundException
    {
   // rakentaja.LataaPelaajat();
    
    this.pelaajat = rakentaja.PalautaPelaajat();
    this.uusiBlokki.lataa(pelaajat);
    }
}
