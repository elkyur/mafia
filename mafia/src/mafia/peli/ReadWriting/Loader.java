/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.ReadWriting;

import java.io.File;
import java.io.FileNotFoundException;
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
    
    private File pelaajat;
    private File buffityypit;
    private Scanner buffityyppilukija;
    private Scanner pelaajaLukija;
    
    public Loader() {
        
        
        
    }
    
    public void setBuffiLukija(File file) throws FileNotFoundException
    {
    this.buffityypit = file;
    this.buffityyppilukija = new Scanner(file);
    
    }

    public void setPelaajat(File file) throws FileNotFoundException {
        this.pelaajat = file;
        this.pelaajaLukija = new Scanner(file);
    }
    
    public ArrayList<Pelaaja> palautaPelaajat() throws FileNotFoundException {
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
        return pelattavat;
    }
    
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
    
    
        
    return tyyppi;
    }
    
    public ArrayList<Buff> palautaBuffit()
    {
    return null;
    }
    
    
}
