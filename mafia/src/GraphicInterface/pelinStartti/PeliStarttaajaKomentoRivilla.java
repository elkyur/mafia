/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface.pelinStartti;

import GraphicInterface.Valikko;
import GraphicInterface.PanelliManageri;
import Settings.Settings;
import java.io.IOException;
import java.util.Scanner;
import mafia.peli.TietokantaHallinta.PeliKaynnistaja;
import mafia.peli.TietokantaHallinta.TietokantaManageri;

/**
 *
 * Aloittaa pelin komento Rivillä jossa pelaaja valitsee tiedostoPolun. 
 */
public class PeliStarttaajaKomentoRivilla {
    
    
    public void Run() throws IOException
    {
    Scanner lukija = new Scanner(System.in);
    System.out.println("Tervetuloa käyttämään mafiooso appia, jotta voisimme alloittaa pelin on löydettävä pelaajatietokanta tiedosto");
    
    System.out.print("Tiedoston path on: ");
    String k = lukija.nextLine();
    
    GraphicRun(k);
    
    System.out.println("Ohjelma Starttaaa");
    
    
    
    
    }
    
      public static void GraphicRun(String k) throws IOException
    {
        Settings settings = new Settings(k);
        TietokantaManageri rakentaja = new TietokantaManageri(settings);
        rakentaja.asetaParamaterit();
        rakentaja.LataaPelaajat();
        PeliKaynnistaja kaynnistaja = new PeliKaynnistaja();
        PanelliManageri manager = new PanelliManageri(rakentaja, kaynnistaja);
    
      //  manager.LoadAll();
        Valikko valikko = new Valikko(manager);
    
    
    }
    
    
}
