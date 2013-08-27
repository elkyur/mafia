/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli;

import GraphicInterface.PanelliManageri;
import java.util.ArrayList;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Pelattava;
import mafia.peli.Logit.LogWriter;
import mafia.peli.YhdenAsianLuokkia.AanestysSysteemi;

/**
 *
 * @author Elkyur
 */
public class GraphicRunHelper {
    
    private LogWriter kirjoittaja;
    private PanelliManageri paneeli;
    private ArrayList<Faasi> faasiArray;
    private ArrayList<Pelattava> PelissaMukana;
    private int PhaseNumber;
    private Faasi onRunning;
    private AanestysSysteemi aanestyssteemi;
    
    public GraphicRunHelper(PanelliManageri paneeli)
    {
    this.paneeli = paneeli;    
    this.PhaseNumber = 0;
        
    }
      /**
     *
     * asettaa faasilistan
     */
    public void asetaFaasit(ArrayList<Faasi> faasit) {
        this.faasiArray = faasit;
    }

    /**
     *
     * asettaa viittauksen pelajiin
     */
    public void asetaPelaajat(ArrayList<Pelattava> pelaajat) {
        this.PelissaMukana = pelaajat;

    }
    
    public void getNext()
    {
    int j = this.PhaseNumber % this.faasiArray.size();
    this.onRunning =  this.faasiArray.get(j);
    j++;
    }
    public void Run()
    {
    this.onRunning.GraphicRun(aanestyssteemi, paneeli, kirjoittaja);
    
    }

    
}
