/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

/**
 *
 * @author Elkyur
 */
public class Buff {

    private String nimi;
    private String type;
    private String MessageToUser;
    private String MessageToAdmin;

    public Buff(String nimi, String type) {
        this.nimi = nimi;

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
}
