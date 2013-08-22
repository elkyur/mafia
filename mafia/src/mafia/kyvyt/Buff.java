/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import java.util.ArrayList;
import mafia.hahmot.Pelattava;

/**
 *
 * Tämä luokka vastaan Buffeista eli effekteistä hahmoihin, jotka voivat rajoittaa, suojata, estää tai tappaa hahmon.
 */
public class Buff {

    private String nimi;
    private String MessageToVictim;
    private String MessageToAdmin;
    private int ExpireRate;
    private BuffinTyyppi tyyppi;
    private ArrayList<Object> erikoisViittauksia;

    /**
     * 
     * @param nimi
     * @param ExpireRate
     * @param tyyppi
     */
    public Buff(String nimi, int ExpireRate, BuffinTyyppi tyyppi) {
        this.nimi = nimi;
        this.ExpireRate = ExpireRate;
        this.tyyppi = tyyppi;
 
        this.erikoisViittauksia = new ArrayList<Object>();

    }

    /**
     * 
     * Palauttaa nimen
     */
    public String getNimi() {
        return this.nimi;
    }


    /**
     *  
     * 
     * Asettaa viestit hahmolle ja pelinjohtajalle
     */
    public void setaddMessages(String MessageToUser, String MessageToAdmin) {
        this.MessageToVictim = MessageToUser;
        this.MessageToAdmin = MessageToAdmin;

    }

    /**
     * 
     * Palauttaa viesitn;
     */
    public String returnMessageToUser() {
        return this.MessageToVictim;

    }


    /**
     * 
     * palauttaa pelinjohtajalle tarkoitetun viestin
     */
    public String returnMessageToAdmin() {

        return this.MessageToAdmin;
    }

    /**
     * 
     * Palauttaa expireraten eli paljon buffila on elinaikaa jäljellä
     */
    public int returnExpireRate() {
        return this.ExpireRate;
    }

    /**
     * 
     * Riippuen buffityypistä lisää vittauksia. Esimerkiksi kostobuffissa viittauksena on kyvyt joita Hahmo voi käyttää heti kuoleman jälkeen.
     * 
     */
    public Object lisaaErikoisViittaus(Object objekti) {
        return this.erikoisViittauksia.add(objekti);
    }

    /**
     * 
     * Palauttaa erikoisviittaukset
     */
    public ArrayList<Object> PalautaKokoHomma() {
        return this.erikoisViittauksia;
    }

    /**
     * 
     * 
     * Palauttaa mitä tyyppiä buffi on 
     */
    public BuffinTyyppi returnBuffinTyyppi() {
        return this.tyyppi;

    }
    
    /**
     * Vähentää elinikää
     */
    public void Expire()
    {
    this.ExpireRate--;
    }
}
