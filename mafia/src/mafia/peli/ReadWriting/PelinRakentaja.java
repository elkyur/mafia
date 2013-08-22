/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.ReadWriting;

import java.util.ArrayList;
import mafia.kyvyt.BuffinTyyppi;

/**
 *
 * Tarkoituksena on luoda luokka joka käyttäjän syötteestä rakentaisi pelin käyttämällä tallennettuja objekteja tekstitiedostosta.
 */
public class PelinRakentaja {
    
    private ArrayList<BuffinTyyppi> tyypit;
    
    private Kirjoittaja kir;
    private Loader load;
    public PelinRakentaja()
    {
    this.kir = new Kirjoittaja();
    this.load = new Loader();
    
    }
    
    public void ConfigurateBuffiTyyppit()
    {
    
    
    }
    
}
