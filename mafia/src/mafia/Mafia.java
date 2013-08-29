/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia;


import GraphicInterface.PanelliManageri;
import GraphicInterface.GraphicInterfaceCore;
import GraphicInterface.pelinStartti.PeliStarttaajaKomentoRivilla;
import Settings.Settings;
import java.io.*;
import java.util.*;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Rooli;
import mafia.kyvyt.Buff;
import mafia.kyvyt.BuffinTyyppi;
import mafia.kyvyt.Kyky;
import mafia.kyvyt.NormiKyky;
import mafia.peli.YhdenAsianLuokkia.Sekoittaja;
import mafia.peli.YhdenAsianLuokkia.SortKyky;
import java.lang.Object;
import mafia.hahmot.*;
import mafia.kyvyt.*;
import mafia.peli.Logit.LogWriter;
import mafia.peli.TietokantaHallinta.Kirjoittaja;
import mafia.peli.TietokantaHallinta.Loader;
import mafia.peli.TietokantaHallinta.PeliKaynnistaja;
import mafia.peli.TietokantaHallinta.TietokantaManageri;
import mafia.peli.ValmiiksAsetetut.AlkuperainenMafiooso;
import mafia.userinterface.TekstiRajapinta;

/**
 *
 * @author Elkyur
 */
public class Mafia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
      // RectangleProgram rectObj = new RectangleProgram();
//       IdeaTest();
        
      //  WriterTest3();
      PeliStarttaajaKomentoRivilla rivi = new PeliStarttaajaKomentoRivilla();
      rivi.Run();
     
        //  KokeilelArrayListisa();
       //KokeillePelia();
       // LogWrtierTest();
        //FileTest();
        // WriterTest();
     //  ReadingTest();
       // KirjoittajaTestOsa2();
    }

    /**
     *
     */
    
    
    
   
    
   
}
