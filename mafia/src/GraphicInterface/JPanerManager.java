/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Pelattava;
import mafia.kyvyt.Atribuutti;
import mafia.peli.Faasi;
import mafia.peli.GraphicRunHelper;
import mafia.peli.ReadWriting.PeliKaynnistaja;
import mafia.peli.ReadWriting.PelinRakentaja;

/**
 *
 * @author Elkyur
 *
 */
public final class JPanerManager {

    private JButton aloitaFaasi;
    private GameStartingMenuAction action;
    private JPanel GameChooser, info, UnderGameChooser, ButtonPanel, InsideTheGame, ButtonPanelInsideTheGame, OtherStuffInsideTheGame;
    private JList leftlist, rightlist, hahmoList, kykyList;
    private JButton Valinta, poistu, LisaaUusi, CustomGame, BasicGame;
    private PelinRakentaja rakentaja;
    private ArrayList<Pelaaja> pelaajat;
    private String[] pelaajaVector;
    private ArrayList<Pelaaja> selected;
    private Container pane;
    private ArrayList<Hahmo> KyseisetHahmotValittuina;
    private PeliKaynnistaja kaynnistaja;
    private GraphicRunHelper Peli;
    private valintaBlokki uusiBlokki;

    public JPanerManager(PelinRakentaja rakentaja, PeliKaynnistaja kaynnistaja) throws FileNotFoundException {

        this.rakentaja = rakentaja;
        this.hahmoList = new JList();
        this.kykyList = new JList();
        this.kaynnistaja = kaynnistaja;
        this.kaynnistaja.asetaPaaneeliManager(this);
        this.selected = new ArrayList<Pelaaja>();

        this.action = new GameStartingMenuAction();
        LoadAll();
        KokeilleJotakinUutta();
    }

    public void setContainer(Container pane) {
        this.pane = pane;
    }

    public void LoadGameChooser() throws FileNotFoundException {
        ConfigaaButtonit();

        this.GameChooser = new JPanel();
        this.UnderGameChooser = new JPanel();
        this.GameChooser.setLayout(new BorderLayout());
        this.GameChooser.add(this.UnderGameChooser, BorderLayout.CENTER);
        this.GameChooser.add(this.ButtonPanel, BorderLayout.SOUTH);




        rakentaja.LataaPelaajat();
        this.pelaajat = rakentaja.PalautaPelaajat();
        this.pelaajaVector = new String[this.pelaajat.size()];
        InitPelaajaVector();

        rightlist = new JList();
        rightlist.setVisibleRowCount(10);
        leftlist = new JList(pelaajaVector);
        leftlist.setVisibleRowCount(10);
        leftlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        Valinta = new JButton("Valitaan");
        Valinta.addActionListener(this.action);

        this.uusiBlokki = new valintaBlokki("Valitse Näistä", 100, this.pelaajat);
        JPanel panel = this.uusiBlokki.palautaBlokki();
        // Adding stuff

        UnderGameChooser.add(new JScrollPane(leftlist));
        UnderGameChooser.add(new JScrollPane(rightlist));
        UnderGameChooser.add(Valinta);
        UnderGameChooser.add(panel);

    }
    
    public void KokeilleJotakinUutta()
    {
   
    
    }

    private void ConfigaaButtonit() {

        this.ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.X_AXIS));
        this.BasicGame = new JButton("Perus Mafiooso");
        this.BasicGame.addActionListener(this.action);
        this.CustomGame = new JButton("Custom peli");
        this.CustomGame.addActionListener(this.action);
        this.ButtonPanel.add(BasicGame);
        this.ButtonPanel.add(CustomGame);


    }

    public JPanel returnGameChooser() {
        return this.GameChooser;
    }

    private void InitPelaajaVector() {

        for (int i = 0; i < this.pelaajat.size(); i++) {
            pelaajaVector[i] = pelaajat.get(i).PalautaNimi();
        }

    }

    public void pelirakentajainit(PelinRakentaja pelinrakentaja) {
        this.rakentaja = pelinrakentaja;
    }

    public void LoadInfo() {
        this.info = new JPanel();
        this.info.setLayout(new GridLayout(1, 2));
        JLabel label1 = new JLabel("This APP Was created by: ");
        JLabel label2 = new JLabel("Elkyur");
        this.info.add(label1);
        this.info.add(label2);


    }

    public JPanel PalautaInfo() {
        return this.info;
    }

    public void LoadAll() throws FileNotFoundException {
        LoadInfo();
        LoadGameChooser();
        ConfigurateInGame();
    }

    public void ConfigurateInGame() {
        this.InsideTheGame = new JPanel();
        this.InsideTheGame.setLayout(new FlowLayout());
        JScrollPane scrollauspaneeli = new JScrollPane(this.hahmoList);
        this.InsideTheGame.add(scrollauspaneeli);
        JScrollPane scrollauspnaeelli2 = new JScrollPane(this.kykyList);
        this.InsideTheGame.add(scrollauspnaeelli2);
        hahmoList.setVisibleRowCount(15);
        kykyList.setVisibleRowCount(15);
        
        this.aloitaFaasi = new JButton("Aloita faasi");
        this.aloitaFaasi.addActionListener(this.action);
        this.KyseisetHahmotValittuina = new ArrayList<Hahmo>();
        this.InsideTheGame.add(this.aloitaFaasi);
        this.InsideTheGame.setVisible(false);
    }

    public void Console(Faasi faasi, ArrayList<Pelattava> pelattavat) {


        String Taulukko[] = new String[faasi.LaskePelattavat()];
        String KykyTaulukko[] = new String[faasi.palautaAtribuutit().size()];
        int i = 0;
        for (Pelattava pel : pelattavat) {
            for (Hahmo hahmo : pel.getTeam()) {
                String k = hahmo.getNimi();
                k = k + ", " + pel.getNimi();
                k = k + ": " + hahmo.getOmistajanNimi();
                Taulukko[i] = k;
                // this.KyseisetHahmotValittuina.set(i, hahmo);
                i++;
            }
        }
        int j = 0;
        for (Atribuutti atr : faasi.palautaAtribuutit())
        {
        String k = atr.palautaKyky().palautaNimi();
        KykyTaulukko[j] = k;
        j++;
        }
        
        this.hahmoList.setListData(Taulukko);
        this.kykyList.setListData(KykyTaulukko);
        // this.InsideTheGame.setVisible(true);
        pane.validate();
        pane.repaint();


    }

    private class GameStartingMenuAction implements ActionListener {

        private ArrayList<Pelaaja> pelaajate;

        public GameStartingMenuAction() {
            this.pelaajate = new ArrayList<Pelaaja>();
        }

        @Override
        public void actionPerformed(ActionEvent e) {


            // pelaajate.clear();
             //  selected.clear();
               
            if (e.getSource() == Valinta) {
                rightlist.setListData(leftlist.getSelectedValues());
                int[] indexit = leftlist.getSelectedIndices();

                for (int i = 0; i < indexit.length; i++) {
                    selected.add(pelaajat.get(indexit[i]));
                }


            } else if (e.getSource() == BasicGame) {


                if (selected.size() > 7) {
                    pane.removeAll();
                    pane.add(InsideTheGame);
                    InsideTheGame.setVisible(true);
                    pane.validate();
                    pane.repaint();
                    kaynnistaja.asetaPelaajat(selected);
                    try {
                        Peli = kaynnistaja.LaitaePerusMafioosoPaalle();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(JPanerManager.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(JPanerManager.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Peli.Run();
                    //   String k = scan.nextLine();


                }
            }
            
            else if (e.getSource() == aloitaFaasi)
            {
            pane.removeAll();
            pane.validate();
            pane.repaint();
            
            }
        }
        
        
    }
}
