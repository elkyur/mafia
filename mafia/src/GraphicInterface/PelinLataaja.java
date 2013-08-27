/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface;

import GraphicInterface.pikkuObjektit.KykyjenListaus;
import GraphicInterface.pikkuObjektit.PelattavienListaus;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mafia.peli.Faasi;

/**
 *
 * @author Elkyur
 */
public class PelinLataaja {
    
    JPanel mainpanel;
    JPanel Underpanel;
    JPanel pelattavienlistaus;
    JPanel FaasiKyvyt;
    PelattavienListaus pel;
    JButton SiirryFaasin;
    JLabel label;
    KykyjenListaus listaus;
    Faasi onRunningFaasi;
    
    
    public PelinLataaja(PelattavienListaus pel)
    {
    this.listaus = new KykyjenListaus();
    FaasiKyvyt = this.listaus.palautaMainPanelli();
    this.label = new JLabel("faasi");
    this.pel = pel;
    this.mainpanel = new JPanel();
    this.Underpanel = new JPanel();
    this.Underpanel.setLayout(new BoxLayout(this.mainpanel, BoxLayout.X_AXIS));
    this.SiirryFaasin = new JButton("Aloita Faasi");
    this.mainpanel.setLayout(new BorderLayout());
    this.mainpanel.add(this.label, BorderLayout.NORTH);
    this.mainpanel.add(this.Underpanel, BorderLayout.CENTER);
    this.mainpanel.add(this.SiirryFaasin, BorderLayout.SOUTH);
    pelattavienlistaus = this.pel.palautaPanelli();
    this.Underpanel.add(this.pelattavienlistaus);
    this.Underpanel.add(FaasiKyvyt);
   
    }
    
    public void UpdateFaasi(Faasi faasi)
    {
     this.onRunningFaasi = faasi;
     this.pel.Update();
     this.listaus.Lataa(faasi.palautaAtribuutit());
     this.mainpanel.validate();
     this.mainpanel.repaint();
     
    }
    
    public void updateLabel(String k)
    {
    this.label.setText(k);
    }
    
    public JButton palautaNappula()
    {
    return this.SiirryFaasin;
    }
    
    public JPanel palautaMainPanel()
    {
    return this.mainpanel;
    }
    
    
    
}
