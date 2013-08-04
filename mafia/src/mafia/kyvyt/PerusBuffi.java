/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import mafia.hahmot.Pelattava;

/**
 *
 * @author Elkyur
 */
public class PerusBuffi {

    private String nimi;
    private String type;
    private String MessageToUser;
    private String MessageToAdmin;
    private int ExpireRate;
    private boolean automatic;
    private Pelattava lahettaja;

    public PerusBuffi(String nimi, String type, int ExpireRate) {
        this.nimi = nimi;
        this.ExpireRate = ExpireRate;
        this.automatic = true;

    }
    
    public void asetaLahettaja(Pelattava lahettaja)
    {
    this.lahettaja = lahettaja;
    }

    public String getNimi() {
        return this.nimi;
    }

    public void SetType(String typez) {
        this.type = typez;
    }

    public String returnType() {
        return this.type;
    }

    public void setMessages(String MessageToUser, String MessageToAdmin) {
        this.MessageToUser = MessageToUser;
        this.MessageToAdmin = MessageToAdmin;

    }
    public String returnMessageToUser()
    {
        return this.MessageToUser;
        
    }
    public String returnMessageToAdmin()
    {
        
        return this.MessageToAdmin;
    }
    
    public int returnExpireRate()
    {
    return this.ExpireRate;
    }
    public boolean getExpireDown()
    {
    return true;
    }
}
