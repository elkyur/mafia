/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.peli.Logit.LogWriter;

/**
 *
 * Tämä luokka on luotu korvaamaan @Faasi olevan raskaan HashMap rakenteen.
 * Tämän luokan tarkoituksena on määritellä mitä kykyjä ja mitkä hahmot voivat
 * käyttää kyseistä kykyä faasin aikana.
 */
public class Atribuutti {

    private Kyky kyky;
    private ArrayList<Hahmo> hahmot;
    private boolean withVoteSystem;
  

    /**
     *
     * @param kyky
     * @param hahmot
     */
    public Atribuutti(Kyky kyky, ArrayList<Hahmo> hahmot) {
        this.kyky = kyky;
        this.hahmot = hahmot;
        if (this.hahmot.size() > 1) {
            this.withVoteSystem = true;
        } else {
            this.withVoteSystem = false;
        }

    }
    
   

    /**
     *
     * Palauttaa hahmot
     */
    public void setWithVoting(boolean k)
    {
    this.withVoteSystem = k;
    }
    public boolean returnWithVoting()
    {
    return this.withVoteSystem;
    }
    
    public ArrayList<Hahmo> palautaHahmot() {
        return this.hahmot;
    }

    /**
     *
     * Palauttaa kyvyn
     */
    public Kyky palautaKyky() {
        return this.kyky;
    }
}
