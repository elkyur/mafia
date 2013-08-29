/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import GraphicInterface.pikkuObjektit.KykyjenListaus;
import GraphicInterface.pikkuObjektit.PelattavienListaus;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mafia.hahmot.Pelattava;
import mafia.peli.Faasi;

/**
 *
 * Pelin Lataaja , vastaa faasin alku paneelista, josta näkee kaikki faasin kyvyt ja hahmot
 */
public class FaasinAlku {
    
    private JPanel mainpanel;
    private JPanel Underpanel;
    private JPanel pelattavienlistaus;
    private JPanel FaasiKyvyt;
    private PelattavienListaus pel;
    private JButton SiirryFaasin;
    private JLabel label;
    private KykyjenListaus listaus;
    private Faasi onRunningFaasi;
    
    
/**
 *
 * Käynnistää pelinlataajan
 */
    
    public FaasinAlku(PelattavienListaus pel)
    {
    this.listaus = new KykyjenListaus();
    FaasiKyvyt = this.listaus.palautaMainPanelli();
    this.label = new JLabel("faasi");
    this.label.setFont(new Font("Serif", Font.PLAIN, 20));
    this.pel = pel;
    this.mainpanel = new JPanel();
    this.Underpanel = new JPanel();
    this.Underpanel.setLayout(new BoxLayout(this.Underpanel, BoxLayout.X_AXIS));
    this.SiirryFaasin = new JButton("Aloita Faasi");
    this.mainpanel.setLayout(new BorderLayout());
    this.mainpanel.add(this.label, BorderLayout.NORTH);
    this.mainpanel.add(this.Underpanel, BorderLayout.CENTER);
    this.mainpanel.add(this.SiirryFaasin, BorderLayout.SOUTH);
    pelattavienlistaus = this.pel.palautaPanelli();
    this.Underpanel.add(this.pelattavienlistaus);
    this.Underpanel.add(FaasiKyvyt);
   
    }
    
    /**
 *
 * Päivittää faasin
 */
    
    public void UpdateFaasi(Faasi faasi, ArrayList<Pelattava> pel)
    {
     this.label.setText(faasi.PalautaNimi());
     this.onRunningFaasi = faasi;
     this.pel.Lataa(pel);
     this.listaus.Lataa(faasi.palautaAtribuutit());
     
     this.mainpanel.validate();
     this.mainpanel.repaint();
    
     
    }
    
       /**
 *
 * Päivittää otsikon 
 */
    
    public void updateLabel(String k)
    {
    this.label.setText(k);
    }
    
       /**
 *
 * Palauttaa nappulan
 */
    
    public JButton palautaNappula()
    {
    return this.SiirryFaasin;
    }
    
    public JPanel palautaMainPanel()
    {
    return this.mainpanel;
    }
    

    
    
    
}
