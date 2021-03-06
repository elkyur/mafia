/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.TietokantaHallinta;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Statistiikka;
import mafia.kyvyt.Buff;
import mafia.kyvyt.BuffinTyyppi;

/**
 *
 * Tämä luokka lukee tallennettuja asioita ja vie ne peliin.
 */
public class Loader {
    
    private File pelaajat, hahmot, buffit, kyvyt, faasit, tiimit, buffityypit;
    private Scanner buffityyppilukija, pelaajaLukija, RooliLukija, KykyLukija, FaasiLukija, TiimiLukija, buffilukija;

    
    public Loader() {
        
        
        
    }
    
      /**
     *
     *Asettaa tietokaknnat
     *
     */
    
        public void asetaPelaajaTietoKanta(File pelaaja) throws FileNotFoundException
        {
        this.pelaajat = pelaaja;
        this.pelaajaLukija = new Scanner(pelaajat);
        
        }
    
       public void asetaTietokannat(File pelaajat, File hahmot, File buffit, File kyvyt, File faasit, File tiimit) throws IOException {
        this.buffit = buffit;
        this.buffilukija = new Scanner(buffit);
        this.pelaajat = pelaajat;
        this.pelaajaLukija = new Scanner(pelaajat);
        this.kyvyt = kyvyt;
        this.KykyLukija = new Scanner(kyvyt);
        this.faasit = faasit;
        this.FaasiLukija = new Scanner(faasit);
        this.hahmot = hahmot;
        this.RooliLukija = new Scanner(hahmot);
        this.tiimit = tiimit;
        this.TiimiLukija = new Scanner(tiimit);
    }
       
         /**
     *
     *Asettaa buffilukijan 
     *
     */
    
    public void setBuffiLukija(File file) throws FileNotFoundException
    {
    this.buffityypit = file;
    this.buffityyppilukija = new Scanner(file);
    
    }
     /**
     *
     *Asettaa pelaajalukutiedoston
     *
     */

    public void setPelaajat(File file) throws FileNotFoundException {
        this.pelaajat = file;
        this.pelaajaLukija = new Scanner(file);
    }
    
        /**
     *
     *
     * Palauttaa pelaajat tiedostosta
     *
     */
    
    public ArrayList<Pelaaja> palautaPelaajat() throws FileNotFoundException {
        
       System.out.println("lukemisKerta");
        
        ArrayList<Pelaaja> pelattavat = new ArrayList<Pelaaja>();
        while (this.pelaajaLukija.hasNextLine()) {
            String[] iterating;
            iterating = this.pelaajaLukija.nextLine().split(",");
            if (iterating.length == 3) {
                int i = Integer.parseInt(iterating[1]);
                int j = Integer.parseInt(iterating[2]);
                Statistiikka stat = new Statistiikka(i, j);
                Pelaaja pelaaja = new Pelaaja(iterating[0]);
                pelaaja.setStatistics(stat);
                pelattavat.add(pelaaja);
            }
            
        }
       this.pelaajaLukija.reset();
       this.pelaajaLukija = new Scanner(pelaajat);
        
     
        return pelattavat;
    }
    
       /**
     *
     *
     * Palauttaa buffityypit tiedostosta
     *
     */
    
  
    
    public HashMap<String, BuffinTyyppi> buffityypit()
    {
        
   HashMap<String, BuffinTyyppi> tyyppi = new HashMap<String, BuffinTyyppi>();
      while(this.buffityyppilukija.hasNextLine())
      {
      String[] iterating;
      iterating = this.buffityyppilukija.nextLine().split(":");
      if (iterating.length == 2)
      {
      String k = iterating[2].trim();
      BuffinTyyppi tyyppir = new BuffinTyyppi(k);
      tyyppi.put(k, tyyppir);
      }
      
      }
    
    /**
     *
     *
     * palautta buffit
     *
     */
        
    return tyyppi;
    }
    
    public ArrayList<Buff> palautaBuffit()
    {
    return null;
    }
    
    
}
