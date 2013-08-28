/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface.pikkuObjektit;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import mafia.kyvyt.Atribuutti;

/**
 *
 * @author Elkyur
 */
public class KykyjenListaus {
    
    private JLabel label;
    private JPanel mainPanel;
    private ArrayList<Atribuutti> atributit;
    private ArrayList<String> atriibuuttienNimet;
    private JList KykyjenListaus;
    
    public KykyjenListaus()
    {
    
    this.atriibuuttienNimet = new ArrayList<String>();
    this.KykyjenListaus = new JList();
    this.KykyjenListaus.setVisibleRowCount(12);
    this.label = new JLabel("Kyvyt");
    this.mainPanel = new JPanel();
    this.mainPanel.setLayout(new BorderLayout());
    this.mainPanel.add(this.label, BorderLayout.NORTH);
    this.mainPanel.add(new JScrollPane(this.KykyjenListaus), BorderLayout.CENTER);
    
    
    }
    
    public void Lataa(ArrayList<Atribuutti> atribuutit)
    {
    this.atributit = atribuutit;
    Update();
    }
    
    public JPanel palautaMainPanelli()
    {
    return this.mainPanel;
    }
    
    public void Update()
    {
     this.atriibuuttienNimet.clear();
     for(Atribuutti atr: this.atributit)   
     {
     String k = atr.palautaKyky().palautaNimi();
     this.atriibuuttienNimet.add(k);
     }
     this.KykyjenListaus.setListData(this.atriibuuttienNimet.toArray());
     this.KykyjenListaus.validate();
     this.KykyjenListaus.repaint();
     
    }
}
