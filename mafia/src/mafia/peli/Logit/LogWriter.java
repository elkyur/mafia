/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.Logit;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import mafia.kyvyt.Kyky;
import mafia.peli.Faasi;

/**
 *
 * Tämä luokkaa kirjoittaa logeja mitä pelissä on tapahtunu , tällä hetkellä vain testaamista varten. 
 * Kostokykyjä ei viellä lisätä logeihin.
 */
public class LogWriter {

    private PrintWriter writer;

    public LogWriter() {
    }
     /**
     *
     * asettaa alkuparametrit logikirjoittajalle
     */
    
    public void LogWriterInit(String a) throws FileNotFoundException, UnsupportedEncodingException {
        if (a == null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH mm");
            Date date = new Date();
            String k = dateFormat.format(date);
            this.writer = new PrintWriter("gamedata/gamelogs/" + k + ".txt", "UTF-8");
        } else {
            this.writer = new PrintWriter("gamedata/gamelogs/" + a + ".txt", "UTF-8");
        }


    }
    /**
     *
     * kirjoittaa tiedostoon
     */

    public void Write(String k) {
        this.writer.println(k);
        this.writer.flush();

    }
    /**
     *
     * Listaa pelissa olevat hahmot tiedostoon
     */

    public void Alkup(ArrayList<Pelattava> hahmot) {
        this.writer.println("Pelissä olivat mukana seuraavat");
        for (Pelattava pela : hahmot) {
            if (pela.getTeam().size() > 1) {
                this.writer.println(pela.getNimi());
            }
            for (Hahmo hah : pela.getTeam()) {
                this.writer.println(hah.getOmistajanNimi() + " : " + hah.getNimi());
            }
            this.writer.println("---");
            this.writer.flush();
        }

    }
     /**
     *
     * aloittaa faasin logien kirjoittamisen
     */

    public void AloitaFaasi(Faasi faasi) {
        this.writer.println(faasi.PalautaNimi());
        this.writer.flush();

    }
    
      /**
     *
     * Kun kyky castataan tämä vie sen logeihin. 
     */

    public void KykyCasti(ArrayList<Hahmo> hahmot, Kyky kyky, Hahmo targetettava) {
        boolean k = true;
        for (Hahmo yksiCastaajista : hahmot) {
            if (k == true) {
                this.writer.print(yksiCastaajista.getOmistajanNimi());
                k = false;
            } else {
                this.writer.print(", " + yksiCastaajista.getOmistajanNimi());
            }

        }
        this.writer.print(": ");
        this.writer.print(kyky.palautaNimi());
        this.writer.print(" :");
        this.writer.println(targetettava.getOmistajanNimi());
        this.writer.flush();

    }
}
