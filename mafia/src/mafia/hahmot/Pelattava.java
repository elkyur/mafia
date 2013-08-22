/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.hahmot;

import java.util.ArrayList;

/**
 *
 * Tämä rajapinta vastaa Tiimeistä. Hahmo voi olla yksin tiimissä, jolloin sen luokka on Hahmo tai monta hahmoa voi olla yhdessä tiimissä, jolloin luokkana on Tiimi
 */
public interface Pelattava {
    
    /**
     * 
     * Palauttaa nimen
     */
    public String getNimi();
    /**
     * 
     * Palauttaa toimiiko tiimina
     */
    public boolean getWorksAsTeam();
    /**
     * 
     * Palauttaa koko tiimin
     */
    public ArrayList<Hahmo> getTeam();
    /**
     * 
     * Palauttaa onko vielä elossa
     */
    public boolean elossa();
    
    
    public boolean poistaTiimista(Hahmo hahmo);
}
