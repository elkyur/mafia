/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import java.util.ArrayList;
import mafia.hahmot.Pelattava;

/**
 *
 * @author Elkyur
 */
public class Buff {

    private String nimi;
    private ArrayList<String> MessageToVictim;
    private String MessageToAdmin;
    private int ExpireRate;
    private BuffinTyyppi tyyppi;
    private ArrayList<Object> erikoisViittauksia;

    public Buff(String nimi, int ExpireRate, BuffinTyyppi tyyppi) {
        this.nimi = nimi;
        this.ExpireRate = ExpireRate;
        this.tyyppi = tyyppi;
        this.MessageToVictim = new ArrayList<String>();
        this.erikoisViittauksia = new ArrayList<Object>();

    }

    public String getNimi() {
        return this.nimi;
    }


    public void setaddMessages(String MessageToUser, String MessageToAdmin) {
        this.MessageToVictim.add(MessageToUser);
        this.MessageToAdmin = MessageToAdmin;

    }

    public ArrayList<String> returnMessageToUser() {
        return this.MessageToVictim;

    }

    public void ClearUserMessages() {
        this.MessageToVictim.clear();
    }

    public String returnMessageToAdmin() {

        return this.MessageToAdmin;
    }

    public int returnExpireRate() {
        return this.ExpireRate;
    }

    public Object lisaaErikoisViittaus(Object objekti) {
        return this.erikoisViittauksia.add(objekti);
    }

    public ArrayList<Object> PalautaKokoHomma() {
        return this.erikoisViittauksia;
    }

    public BuffinTyyppi returnBuffinTyyppi() {
        return this.tyyppi;

    }
    
    public void Expire()
    {
    this.ExpireRate--;
    }
}
