package GraphicInterface.pikkuObjektit;


import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelattava;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elkyur
 */
public class PelattavienListaus {
     
    private JList lista;
    private JPanel mainPanel;
    private ArrayList<Pelattava> pelattavt;
    private ArrayList<String> listaNimia;
    private JLabel label;
    
    public PelattavienListaus(ArrayList<Pelattava> pelattavat)
    {
     this.pelattavt = pelattavat;
     this.listaNimia = new ArrayList<String>();
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

    this.listaNimia.clear();
      for (Pelattava pel : pelattavt) {
            for (Hahmo hahmo : pel.getTeam()) {
                String k = hahmo.getNimi();
                k = k + ", " + pel.getNimi();
                k = k + ": " + hahmo.getOmistajanNimi();

                 listaNimia.add(k);
                // this.KyseisetHahmotValittuina.set(i, hahmo);
                
            }
        }
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
