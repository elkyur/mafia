/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface.pikkuObjektit;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * Configaa infopaneliin jossa tällä hetkellä vain lukee ohjelman luoja. 
 */
public class InfoPanelli {
    
    JPanel main;
    JLabel label1 ,label2;
    
    public InfoPanelli()
    {
    this.main = new JPanel();
    this.main.setLayout(new FlowLayout());
    label1 = new JLabel("This APP Was created by: ");
    label2 = new JLabel("Elkyur");
    this.main.add(label1);
    this.main.add(label2);
    
    }
    
    public JPanel palautaPanelli()
    {
    return main;
    }
    
}
