/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.hahmot;

/**
 *
 * Tämä luokka kantaa vastuun Pelaajasta ja sen tiedoista
 */
public class Pelaaja {

    private String nimi;
    private String salasana;
    private Statistiikka statistics;

    /**
     *
     * @param nimi
     */
    public Pelaaja(String nimi) {
        this(nimi, "");

    }

    /**
     *
     * @param nimi
     * @param salasana
     */
    public Pelaaja(String nimi, String salasana) {
        this.nimi = nimi;
        this.salasana = salasana;


    }

    /**
     *
     * Palauttaa Nimen
     */
    public String PalautaNimi() {

        return this.nimi;
    }

     /**
     *
     * Asettaa Statistiikan
     */
    
    public void setStatistics(Statistiikka stat) {
        this.statistics = stat;
    }
    
        /**
     *
     * Palauttaa Pelaajan statistiik,an
     */

    public Statistiikka palautaStatistiikka() {
        return this.statistics;
    }



    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.nimi != null ? this.nimi.hashCode() : 0);
        hash = 53 * hash + (this.statistics != null ? this.statistics.hashCode() : 0);
        return hash;
    }
    
        /**
     *
     * Asettaa Custom equals metodin
     */

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pelaaja other = (Pelaaja) obj;
        if ((this.nimi == null) ? (other.nimi != null) : !this.nimi.equals(other.nimi)) {
            return false;
        }
        if (this.statistics != other.statistics && (this.statistics == null || !this.statistics.equals(other.statistics))) {
            return false;
        }
        return true;
    }
}
