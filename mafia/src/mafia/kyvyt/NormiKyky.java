/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.kyvyt;

import java.util.ArrayList;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;

/**
 *
 * @author Elkyur
 */
public class NormiKyky implements Kyky {

    private String name;
    private Buff buffi;
    private ArrayList<Buff> Tarvittavat;
    private boolean onRequest;
    private int usageTime;

    public NormiKyky(String name, Buff buffi, boolean heti) {
        this.name = name;
        this.buffi = buffi;
        this.Tarvittavat = new ArrayList<Buff>();
        this.onRequest = heti;
        this.usageTime = -1;
    }
    
    public void asetaUsageTime(int i)
    {
    this.usageTime = i;
    }

    public void asetaTarvittavat(ArrayList<Buff> buffilista) {
        this.Tarvittavat = buffilista;
    }

  

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String Toiminnallisuus(Hahmo castaava, Hahmo vastaanottava) {

        if (this.Tarvittavat.isEmpty()) {
            return Toimi(castaava, vastaanottava);
        } else {
            for (Buff buffi : this.Tarvittavat) {
                if (!this.buffi.PalautaKokoHomma().contains(buffi)) {
                    return "";
                }
            }
            return Toimi(castaava, vastaanottava);

        }
    }

    public String Toimi(Hahmo castaava, Hahmo vastaanottava) {

        return "";
    }

   
  //  @Override
 //   public boolean equals(Kyky kyky) {
 //       if (this.name == kyky.getName() && this.buffi.equals(kyky.getBuffi())) {
 //           return true;
 //       }
        
 //       return false;
 //   }
   

    @Override
    public Buff getBuffi() {
        return this.buffi;
    }

    @Override
    public boolean returnHeti() {
        return this.onRequest;
    }

    @Override
    public boolean returnOnRequest() {
        return this.onRequest;
    }

    public void asetaOnRequest(boolean k) {
        this.onRequest = k;
    }

    @Override
    public int UsageTimes() {
        return this.usageTime;
    }

   
}
