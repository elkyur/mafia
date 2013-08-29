/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import GraphicInterface.DataManager.PelaajaAliObjecti;
import GraphicInterface.DataManager.PelaajaTietokantaManageri;
import GraphicInterface.DataManager.abstractManager;
import GraphicInterface.pikkuObjektit.InfoPanelli;
import GraphicInterface.pikkuObjektit.messagePanel;
import GraphicInterface.pikkuObjektit.PelattavienListaus;
import GraphicInterface.pikkuObjektit.TheEndGamePanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import mafia.peli.TietokantaHallinta.PeliKaynnistaja;
import mafia.peli.TietokantaHallinta.TietokantaManageri;

/**
 *
 * Tämä luokka on PaneeliManageri se vastaa koko graafisen käyttöiittymän
 * luokkarakenteesta
 *
 */
public final class PanelliManageri {

    private JButton aloitaFaasi;
    private GameStartingMenuAction action;
    private JPanel GameChooser, info, InsideTheGame;
    private ArrayList<Pelattava> pelattavat;
    private JButton CustomGame, BasicGame, LukitseKyky;
    private TietokantaManageri rakentaja;
    private Container pane;
    private ArrayList<Hahmo> KyseisetHahmotValittuina;
    private PeliKaynnistaja kaynnistaja;
    private GraphicRunHelper Peli;
    private PelinLataaja pelilataaja;
    private PelattavienListaus pelattavienlistaus, kakkoslisti;
    private PelinSisainen PelinSisainen;
    private messagePanel panelli;
    private KaynnistajaLuokka kaynnistajaLuokka;
    private JPanel iterating;
    private TheEndGamePanel peliLoppu;
    private PelaajaAliObjecti pelaajatietokantalsiaosa;
    private abstractManager pelaajaTietokanta;
    private JPanel PelaajaTietokantaPanelli;

    /**
     *
     * asettaa Alkuarvoja
     *
     */
    public PanelliManageri(TietokantaManageri rakentaja, PeliKaynnistaja kaynnistaja) throws FileNotFoundException {

        panelli = new messagePanel();
        this.pelattavat = new ArrayList<Pelattava>();
        this.pelattavienlistaus = new PelattavienListaus(this.pelattavat);
        this.kakkoslisti = new PelattavienListaus(this.pelattavat);
        this.pelilataaja = new PelinLataaja(this.pelattavienlistaus);
        this.PelinSisainen = new PelinSisainen(this.kakkoslisti);

        this.rakentaja = rakentaja;
        this.kaynnistaja = kaynnistaja;
        this.kaynnistaja.asetaPaaneeliManager(this);

        this.action = new GameStartingMenuAction();

        LoadInfo();
        LoadGameChooser();
        ConfigurateInGame();
        this.peliLoppu = new TheEndGamePanel();
        this.iterating = GameChooser;
        InitDatabaseStructures();

    }

    /**
     *
     * asentaa Tietokantaan liittyvat Structuurit
     *
     */
    public void InitDatabaseStructures() {
        pelaajatietokantalsiaosa = new PelaajaAliObjecti();
        pelaajaTietokanta = new PelaajaTietokantaManageri(rakentaja, pelaajatietokantalsiaosa, "pelaaja");
        ArrayList<Object> objectit = convertToObject();
        pelaajaTietokanta.Lataa(objectit);
        this.PelaajaTietokantaPanelli = pelaajaTietokanta.palautaMain();


    }

    /**
     *
     * Palauttaa Pelaajatietokanta paneelin
     *
     */
    public JPanel palautaPelaajaTietokanta() {
        return PelaajaTietokantaPanelli;
    }

    /**
     *
     * convertaa Objectiksi
     *
     */
    public ArrayList<Object> convertToObject() {
        ArrayList<Pelaaja> pelaajat = rakentaja.PalautaPelaajat();
        ArrayList<Object> objectit = new ArrayList<Object>();
        for (Pelaaja p : pelaajat) {
            objectit.add(p);
        }
        return objectit;
    }

    /**
     *
     * palauttaa Iteroivan JPanel (Pelin vaihteen)
     *
     */
    public JPanel returnIterating() {
        return this.iterating;
    }

    /**
     *
     * asettaa PääPaneelin
     *
     */
    public void setContainer(Container pane) {
        this.pane = pane;
    }

    /**
     *
     * Loadaa tarvittavat elementit PeliLataajaan
     *
     */
    public void LoadGameChooser() throws FileNotFoundException {

        this.LukitseKyky = this.PelinSisainen.palautaPaaButtoni();
        this.LukitseKyky.addActionListener(this.action);

        this.kaynnistajaLuokka = new KaynnistajaLuokka(this.rakentaja);
        this.GameChooser = this.kaynnistajaLuokka.returnMainPanel();

        this.BasicGame = this.kaynnistajaLuokka.returnBasicGame();
        this.BasicGame.addActionListener(this.action);
        this.CustomGame = this.kaynnistajaLuokka.returnCustomGame();
        this.CustomGame.addActionListener(this.action);



    }

    /**
     *
     * Lopettaa pelin
     *
     */
    public void FinalizeGame() throws FileNotFoundException {
        peliResetoitu();
        reLoadGameChooser();
        this.iterating = this.GameChooser;

    }

    /**
     *
     * Uudistaa peliKäynnistäjän (Lisää sinne Pelaajia etc...)
     *
     */
    public void reLoadGameChooser() throws FileNotFoundException {
        this.kaynnistajaLuokka.ReLoad();

    }

    /**
     *
     * Palauttaa peliValitsijan
     *
     */
    public JPanel returnGameChooser() {
        return this.GameChooser;
    }

    /**
     *
     * Inittaa pelirakentajan
     *
     */
    public void pelirakentajainit(TietokantaManageri pelinrakentaja) {
        this.rakentaja = pelinrakentaja;
    }

    /**
     *
     * Loadaa infon
     *
     */
    public void LoadInfo() {
        InfoPanelli panel = new InfoPanelli();
        this.info = panel.palautaPanelli();


    }

    /**
     *
     * Palauttaa infon
     *
     */
    public JPanel PalautaInfo() {
        return this.info;
    }

    /**
     *
     * configuroi pelinsisäisiä paneeleja
     *
     */
    public void ConfigurateInGame() {
        this.InsideTheGame = this.pelilataaja.palautaMainPanel();
        this.aloitaFaasi = this.pelilataaja.palautaNappula();
        this.aloitaFaasi.addActionListener(this.action);
        // this.InsideTheGame.setVisible(false);
    }
    
     /**
     *
     * Käynnistää Consolen eli Itse Pelin
     *
     */

    public void Console(Faasi faasi, ArrayList<Pelattava> pelattavat) {

        this.pelilataaja.UpdateFaasi(faasi, pelattavat);
        // this.InsideTheGame.setVisible(true);
        pane.validate();
        pane.repaint();

    }
    
      /**
     *
     * Palautttaa viestin et pelit on pelattu
     *
     */

    public void peliResetoitu() {
        JOptionPane.showMessageDialog(new JPanel(), "Peli Päättyi (en ota kantaa siihen miten)");
    }
    
     /**
     *
     * Tämä metodi vastaa paneelin uudistamisesta 
     *
     */

     public void asetaPanelliin(JPanel panel) {
            pane.removeAll();
            pane.add(panel);
            pane.validate();
            pane.repaint();

        }
     
          /**
     *
     * Tämä luokka vastaa paneelin actioneista 
     *
     */
     
    private class GameStartingMenuAction implements ActionListener {

       
    

        @Override
        public void actionPerformed(ActionEvent e) {


            // pelaajate.clear();
            //  selected.clear();


            if (e.getSource() == BasicGame) {

                ArrayList<Pelaaja> valittujenlista = kaynnistajaLuokka.palautaPelaajat();

                if (valittujenlista.size() > 7) {

                    iterating = InsideTheGame;
                    asetaPanelliin(iterating);
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

                } else {
                    antiTroll();
                }

            } else if (e.getSource() == aloitaFaasi) {



                Peli.ExtraRun();
                Atribuutti atr = Peli.palautaOnGoing();
                PelinSisainen.update(Peli.pelattavat(), atr);

                iterating = PelinSisainen.palautaPaneelli();
                asetaPanelliin(iterating);


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
                        if (Peli.ExtraRun() == false) {
                            try {
                                try {
                                    CaseNoExtraRunLeft();
                                } catch (IOException ex) {
                                    Logger.getLogger(PanelliManageri.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } catch (HeadlessException ex) {
                                Logger.getLogger(PanelliManageri.class.getName()).log(Level.SEVERE, null, ex);
                            }


                        } else {


                            Atribuutti atra = Peli.palautaOnGoing();
                            PelinSisainen.update(Peli.pelattavat(), atra);

                            iterating = PelinSisainen.palautaPaneelli();
                            asetaPanelliin(iterating);

                        }
                    }
                } else {
                    antiTroll();
                }

            } else if (e.getSource() == CustomGame) {
                test();
            }
        }
        
          /**
     *
     * Checkaa onko castaaja tai targetettava null palauttaa virheilmoituksen
     *
     */

        public boolean Checker(Hahmo castaaja, Hahmo targetettava) {

            if (castaaja == null) {
                return false;
            }
            if (targetettava == null) {
                return false;
            }
            return true;

        }
        
         /**
     *
     * Jos PeliPaattyy tai kyvytt loppuvat tämä metodi hoitaa asiat
     *
     */

        public void CaseNoExtraRunLeft() throws HeadlessException, FileNotFoundException, IOException {
            // Tämmönen tilanne on nyt koittanu

            ArrayList<Hahmo> kuolemassaOlevat = Peli.hankiKuolleet();

            JPanel panel = panelli.returnPanelEvenWithPelaajat(kuolemassaOlevat);
            panel.validate();
            pane.repaint();
            JOptionPane.showMessageDialog(panel, "Seuraavat pelaajat kuolevat");

            if (Peli.tarkistaJatkuukoPeli() == false) {

                ArrayList<Hahmo> voittaja = Peli.julistaVoittaja();
                peliLoppu.Update(voittaja);
                asetaPanelliin(peliLoppu.palautaPanelli());
                FinalizeGame();
                Peli.Pisteyta();
                rakentaja.UudistaPelaajat();
                return;
                //   JOptionPane.showMessageDialog(new JPanel(), "Peli Pääättyyyi");


            }

            pane.removeAll();
            pane.add(InsideTheGame);
            InsideTheGame.setVisible(true);
            pane.validate();
            pane.repaint();
            Peli.Run();
        }

        public void antiTroll() {
            JOptionPane.showMessageDialog(new JPanel(), "Sori, mut näin ei voi tehdä");
        }

        public void test() {
            Object[] options = {"Yes, please",
                "No, thanks",
                "No eggs, no ham!"};
            int n = JOptionPane.showOptionDialog(new JFrame(),
                    "Would you like some green eggs to go "
                    + "with that ham?",
                    "A Silly Question",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);

        }
    }
}
