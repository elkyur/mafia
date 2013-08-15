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
    String getNimi();
    /**
     * 
     * Palauttaa toimiiko tiimina
     */
    boolean getWorksAsTeam();
    /**
     * 
     * Palauttaa koko tiimin
     */
    ArrayList<Hahmo> getTeam();
    /**
     * 
     * Palauttaa onko vielä elossa
     */
    boolean elossa();
}
