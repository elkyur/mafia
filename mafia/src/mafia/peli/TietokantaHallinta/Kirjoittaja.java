/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.TietokantaHallinta;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Rooli;
import mafia.hahmot.Tiimi;
import mafia.kyvyt.Buff;
import mafia.kyvyt.Kyky;

/**
 *
 * Koska käyttäjän on työlästä asettaa peliin joka kerta samat asiat uudestaan,
 * tämä luokka auttaa tietojen tallentamisessa
 */
public class Kirjoittaja {
    
    private File pelaajat, hahmot, buffit, kyvyt, faasit, tiimit, temp;
    private FileWriter pelaajatwriter, hahmowriter, buffitwriter, kyvytwriter, faasitwriter, tiimitwriter, tempWriter;
    
    public Kirjoittaja() {
    }

    /**
     *
     * Asettaa tietokannat 
     *
     */
    public void asetaTietokannat(File pelaajat, File hahmot, File buffit, File kyvyt, File faasit, File tiimit) throws IOException {
        this.buffit = buffit;
        this.buffitwriter = new FileWriter(this.buffit, true);
        this.pelaajat = pelaajat;
        this.pelaajatwriter = new FileWriter(this.pelaajat, true);
        this.kyvyt = kyvyt;
        this.kyvytwriter = new FileWriter(this.kyvyt, true);
        this.faasit = faasit;
        this.faasitwriter = new FileWriter(this.faasit, true);
        this.hahmot = hahmot;
        this.hahmowriter = new FileWriter(this.hahmot, true);
        this.tiimit = tiimit;
        this.tiimitwriter = new FileWriter(this.tiimit, true);
    }
       /**
     *
     * Asettaa pelaaja tiedoston erikseen
     *
     */
    
    public void asetaPelaajat(File file) throws IOException {
        this.pelaajat = file;
        this.pelaajatwriter = new FileWriter(this.pelaajat, true);
    }
     /**
     *
     * Asettaa rooli tiedoston erikseen
     *
     */
    
    public void asetaRooli(File file) throws IOException {
        this.hahmot = file;
        this.hahmowriter = new FileWriter(this.hahmot, true);
    }
    
    /**
     *
     * Kun lisataan pelaaja tämä metodi castataan, se kirjoittaa pelaajan nimen tiedoston loppuun
     *
     */
    
    public void LisaaPelaaja(Pelaaja pelaaja, Boolean variable) throws IOException {
        FileWriter fw = this.pelaajatwriter;

       
        if (variable) {
            fw = this.tempWriter;
        }
        
        if (pelaaja.palautaStatistiikka() == null) {
            fw.write(pelaaja.PalautaNimi() + ",0,0" + "\n");
        } else {
            fw.write(pelaaja.PalautaNimi() + "," + pelaaja.palautaStatistiikka().palautaPelinLkm() + "," + pelaaja.palautaStatistiikka().palautaVoittojenLkm() + "\n");
            
        }
        fw.append(System.getProperty("line.separator"));
        
        fw.flush();
        //    fw.close();
    }
       /**
     *
     * Kirjoittaa roolin tiedostoon kyseissä muodossa
     *
     */
    
    public void LisaaRooli(Rooli rooli, Tiimi team, Boolean variable) throws IOException {
        FileWriter fw = this.hahmowriter;
        fw.write(rooli.PalautaNimi() + "," + team.getNimi());
        fw.append(System.getProperty("line.separator"));
        fw.flush();
        fw.close();
        
    }
    
    /**
     *
     * Kirjoittaa buffin tiedoston kyseisessä muodossa
     *
     */
    
    public void LisaaBuffi(Buff buff, Boolean variable) throws IOException {
        FileWriter fw = this.buffitwriter;
        String k = buff.getNimi() + ",";
        k = k + buff.returnExpireRate() + ",";
        k = k + buff.returnBuffinTyyppi().palautaTyyppi() + ",";
        k = k + buff.returnMessageToAdmin() + ",";
        k = k + buff.returnMessageToUser();
        fw.write(k);
        fw.append(System.getProperty("line.separator"));
        fw.flush();
        fw.close();
    }
       /**
     *
     *Lisää kyvyn tiedostoon kyseissä muodossa
     *
     */
    
    public void LisaaKyky(Kyky kyky, Boolean variable) throws IOException {
        FileWriter fw = this.kyvytwriter;
        String k = kyky.palautaNimi() + ",";
        k = k + kyky.getClass().getName() + ",";
        k = k + kyky.palautaBuffi() + ",";
        k = k + kyky.palautaHeti();
        fw.write(k);
        fw.append(System.getProperty("line.separator"));
        fw.flush();
        fw.close();
        
    }
      /**
     *
     *Muokkaa pelaajat tiedostossa, ensin poistaa kaikki ja kirjoittaa uudet käyttäen hyväkseen LisaaPelaaja() metodia
     *
     */
    
    public void MofifyPelaajat(ArrayList<Pelaaja> pel) throws IOException {
        //  InitTempWriter();
        
        
        pelaajatwriter.close();
        pelaajatwriter = new FileWriter(this.pelaajat, false);
        
        for (Pelaaja pelaaja : pel) {
            LisaaPelaaja(pelaaja, false);
            
        }
        pelaajatwriter.close();
        pelaajatwriter = pelaajatwriter = new FileWriter(this.pelaajat, true);
        //   extramethod();
    }
}
