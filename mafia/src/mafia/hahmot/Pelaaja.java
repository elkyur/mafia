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
     * @return
     */
    public String PalautaNimi() {
        return this.nimi;
    }
    
    public void setStatistics(Statistiikka stat)
    {
    this.statistics = stat;
    }
    public Statistiikka palautaStatistiikka()
    {
    return this.statistics;
    }

    
}
