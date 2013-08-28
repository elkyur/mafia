/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import GraphicInterface.pikkuObjektit.messagePanel;
import GraphicInterface.pikkuObjektit.PelattavienListaus;
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
public final class PanelliManageri {

    private JButton aloitaFaasi;
    private GameStartingMenuAction action;
    private JPanel GameChooser, info, ButtonPanel, InsideTheGame, ButtonPanelInsideTheGame, OtherStuffInsideTheGame;
    private ArrayList<Pelattava> pelattavat;
    private JButton poistu, LisaaUusi, CustomGame, BasicGame, LukitseKyky;
    private PelinRakentaja rakentaja;
    private ArrayList<Pelaaja> pelaajat;
    private String[] pelaajaVector;
    private ArrayList<Pelaaja> selected;
    private Container pane;
    private ArrayList<Hahmo> KyseisetHahmotValittuina;
    private PeliKaynnistaja kaynnistaja;
    private GraphicRunHelper Peli;
    private valintaBlokki uusiBlokki;
    private PelinLataaja pelilataaja;
    private PelattavienListaus pelattavienlistaus, kakkoslisti;
    private ItsInTheGame PelinSisainen;
    private messagePanel panelli;
    private KaynnistajaLuokka kaynnistajaLuoakka;

    public PanelliManageri(PelinRakentaja rakentaja, PeliKaynnistaja kaynnistaja) throws FileNotFoundException {

        panelli = new messagePanel();
        this.pelattavat = new ArrayList<Pelattava>();
        this.pelattavienlistaus = new PelattavienListaus(this.pelattavat);
        this.kakkoslisti = new PelattavienListaus(this.pelattavat);
        this.pelilataaja = new PelinLataaja(this.pelattavienlistaus);
        this.PelinSisainen = new ItsInTheGame(this.kakkoslisti);

        this.rakentaja = rakentaja;
        this.kaynnistaja = kaynnistaja;
        this.kaynnistaja.asetaPaaneeliManager(this);
        this.selected = new ArrayList<Pelaaja>();

        this.action = new GameStartingMenuAction();
        LoadAll();

    }

    public void setContainer(Container pane) {
        this.pane = pane;
    }

    public void LoadGameChooser() throws FileNotFoundException {
        ConfigaaButtonit();
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

    private void ConfigaaButtonit() {

        this.ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.X_AXIS));
        this.BasicGame = new JButton("Perus Mafiooso");
        this.BasicGame.addActionListener(this.action);
        this.CustomGame = new JButton("Custom peli");
        this.CustomGame.addActionListener(this.action);
        this.LukitseKyky = this.PelinSisainen.palautaPaaButtoni();
        this.LukitseKyky.addActionListener(this.action);

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
        this.InsideTheGame = this.pelilataaja.palautaMainPanel();
        this.aloitaFaasi = this.pelilataaja.palautaNappula();
        this.aloitaFaasi.addActionListener(this.action);
        // this.InsideTheGame.setVisible(false);
    }

    public void Console(Faasi faasi, ArrayList<Pelattava> pelattavat) {

        this.pelilataaja.UpdateFaasi(faasi, pelattavat);
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


            if (e.getSource() == BasicGame) {

                ArrayList<Pelaaja> valittujenlista = uusiBlokki.palautavalitut();

                if (valittujenlista.size() > 7) {

                    pane.removeAll();
                    pane.add(InsideTheGame);
                    InsideTheGame.setVisible(true);
                    pane.validate();
                    pane.repaint();
                    kaynnistaja.asetaPelaajat(valittujenlista);
                    try {
                        Peli = kaynnistaja.LaitaePerusMafioosoPaalle();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(PanelliManageri.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(PanelliManageri.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Peli.Run();
                    //   String k = scan.nextLine();

                }

            } else if (e.getSource() == aloitaFaasi) {


                pane.removeAll();
                Peli.ExtraRun();
                Atribuutti atr = Peli.palautaOnGoing();
                PelinSisainen.update(Peli.pelattavat(), atr);
                pane.add(PelinSisainen.palautaPaneelli());
                pane.validate();
                pane.repaint();


            } else if (e.getSource() == LukitseKyky) {

                // Step 1. Checkata ox tarpeeks Pelajii
                Hahmo castaaja = PelinSisainen.palautaHyokkaaja(Peli.pelattavat());
                Hahmo targetettava = PelinSisainen.palautaPuolustaja(Peli.pelattavat());
                Atribuutti atr = Peli.palautaOnGoing();

                boolean k = Checker(castaaja, targetettava);

                if (k == true) {

                    String a = Peli.Cast(castaaja, targetettava, atr.palautaKyky());
                    if (a != null) {
                        if (!a.isEmpty()) {
                            JOptionPane.showMessageDialog(new JPanel(), a);
                        }
                    }

                    {
                        if (Peli.ExtraRun() == false) {
                            CaseNoExtraRunLeft();


                        } else {


                            pane.removeAll();
                            Atribuutti atra = Peli.palautaOnGoing();
                            PelinSisainen.update(Peli.pelattavat(), atra);
                            pane.add(PelinSisainen.palautaPaneelli());
                            pane.validate();
                            pane.repaint();


                        }
                    }
                }
                else
                {
                antiTroll();
                }

            }
        }

        public boolean Checker(Hahmo castaaja, Hahmo targetettava) {
            
            if (castaaja == null) {
                return false;
            }
            if (targetettava == null) {
                return false;
            }
            return true;

        }

        public void CaseNoExtraRunLeft() throws HeadlessException {
            // Tämmönen tilanne on nyt koittanu

            ArrayList<Hahmo> kuolemassaOlevat = Peli.hankiKuolleet();

            JPanel panel = panelli.returnPanelEvenWithPelaajat(kuolemassaOlevat);
            panel.validate();
            pane.repaint();
            JOptionPane.showMessageDialog(panel, "Seuraavat pelaajat kuolevat");

            if (Peli.tarkistaJatkuukoPeli() == false) {

                String zwwaq = Peli.julistaVoittaja();
                JOptionPane.showMessageDialog(new JPanel(), "PeliPääättyyyi");


            }


            pane.removeAll();
            pane.add(InsideTheGame);
            InsideTheGame.setVisible(true);
            pane.validate();
            pane.repaint();
            Peli.Run();
        }

        public void antiTroll() {
            JOptionPane.showMessageDialog(new JPanel(), "Älä edes yritä, sinulla ei ole mitään mahdollisuuksia kaataa tätä ohjelmaa");
        }
        
    }
}
