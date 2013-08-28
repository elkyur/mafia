/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface.pikkuObjektit;

import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mafia.hahmot.Hahmo;

/**
 *
 * @author Elkyur
 */
public class messagePanel {

    public messagePanel() {
    }

    public JPanel returnThePanel(String k) {
        JPanel main = new JPanel();
        main.setLayout(new FlowLayout());
        JLabel label = new JLabel(k);
        label.setFont(new Font("Courier New", Font.ITALIC, 20));
        main.add(label);
        return main;



    }

    public JPanel returnThePanel(ArrayList<String> a) {
        return null;
    }

    public JPanel returnPanelEvenWithPelaajat(ArrayList<Hahmo> riseDead) {

         JPanel mainer = new JPanel();
         mainer.setLayout(new BoxLayout(mainer, BoxLayout.Y_AXIS));
  
       
        for (Hahmo h : riseDead) {
            
            JLabel label = new JLabel(h.getOmistajanNimi() + ": " + h.getNimi());
          //  System.out.println(h.getOmistajanNimi());
            mainer.add(label);
        }
        
       return mainer;
    }
}
