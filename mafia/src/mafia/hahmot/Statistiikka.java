/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.hahmot;

/**
 *
 * @author Elkyur
 */
public class Statistiikka {

    private int voittojenlkm;
    private int pelienlkm;

    public Statistiikka() {
        this.voittojenlkm = 0;
        this.pelienlkm = 0;
    }
     public Statistiikka(int i, int j) {
        this.voittojenlkm = j;
        this.pelienlkm = i;
    }

    public void LisaaPeli() {
        this.pelienlkm++;
    }

    public void LisaaVoitto() {
        this.voittojenlkm++;
    }
    public int palautaPelinLkm()
    {
    return this.pelienlkm;
    }
    public int palautaVoittojenLkm()
    {
    return this.voittojenlkm;
    }
}
