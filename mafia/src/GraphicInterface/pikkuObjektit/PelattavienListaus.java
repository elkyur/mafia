package GraphicInterface.pikkuObjektit;


import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;
import mafia.peli.YhdenAsianLuokkia.Misc;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Listaa Pelissa olevat pelattavat 
 */
public class PelattavienListaus {
     
    private JList lista;
    private JPanel mainPanel;
    private ArrayList<Pelattava> pelattavt;
    private ArrayList<String> listaNimia;
    private JLabel label;
    private Misc misc;
    
    public PelattavienListaus(ArrayList<Pelattava> pelattavat)
    {
     this.pelattavt = pelattavat;
     this.listaNimia = new ArrayList<String>();
     this.misc = new Misc();
     Init();
    
    }
    
    
    public void Init()
    {
     this.lista= new JList();
     this.lista.setVisibleRowCount(12);
     this.mainPanel = new JPanel();
     this.mainPanel.setLayout(new BorderLayout());
     this.label = new JLabel("Pelissä mukana olevat:");
     this.mainPanel.add(this.label, BorderLayout.NORTH);
     this.mainPanel.add(new JScrollPane(this.lista), BorderLayout.CENTER);
     
     
    }
    
    public void Update()
    {

      misc.MuutaPelattavaStringTyypiksi(listaNimia, pelattavt);
      this.lista.setListData(listaNimia.toArray());
      this.lista.validate();
      this.lista.repaint();
    
    }
    
    public void Lataa(ArrayList<Pelattava> pelattava)
    {
    this.pelattavt = pelattava;
    Update();
    }
    
    public JPanel palautaPanelli()
    {
      return this.mainPanel;  
      
    }
    
}
