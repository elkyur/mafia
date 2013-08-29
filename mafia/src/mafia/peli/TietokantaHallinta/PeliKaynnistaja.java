/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.peli.TietokantaHallinta;

import GraphicInterface.PanelliManageri;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Pelattava;
import mafia.hahmot.Tiimi;
import mafia.peli.Faasi;
import mafia.peli.GraphicRunHelper;
import mafia.peli.ValmiiksAsetetut.AlkuperainenMafiooso;
import mafia.peli.YhdenAsianLuokkia.Sekoittaja;

/**
 *
 * @author Elkyur
 */
public class PeliKaynnistaja {
    
    private ArrayList<Pelattava> tiimit;
    private ArrayList<Pelaaja> pelaajat;
    private ArrayList<Faasi> faasit;
    private ArrayList<Tiimi> l;
    private PanelliManageri paneeliManageri;
   
    /**
     *
     *
     * Tämä luokka on tarkoitettu pelin käynnistämiseen, ikävä kyllä se kykenee vain käynnistämään tavallisen mafiooson.
     *
     */
    
    
    public PeliKaynnistaja()
    {
    
    }
    
    public void asetaPaaneeliManager(PanelliManageri paneeli)
    {
    this.paneeliManageri = paneeli;
    }
    
    
    
    public void asetaTiimit()
    {
    
    }
    
    public void palautaPeli()
    {
    
    }
    
       /**
     *
     *
     * Asettaa pelaajat
     *
     */
    
    public void asetaPelaajat(ArrayList<Pelaaja> pelaajat)
    {
    this.pelaajat = pelaajat;
    }
    
        /**
     *
     *
     * Käynnistää mafiooson ja antaa pelistä viitteen graphic managerille
     *
     */
    
    public GraphicRunHelper LaitaePerusMafioosoPaalle() throws FileNotFoundException, UnsupportedEncodingException
    {
    Sekoittaja sekoittaja = new Sekoittaja();
    AlkuperainenMafiooso mafiooso = new AlkuperainenMafiooso(this.pelaajat, sekoittaja, this.paneeliManageri);
    mafiooso.Run();
    return mafiooso.Steal();
    
    }
    
}
