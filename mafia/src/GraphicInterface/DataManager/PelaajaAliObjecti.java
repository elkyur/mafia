/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicInterface.DataManager;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Statistiikka;

/**
 *
 * Pelaaja tietokannan objecti joka implementaa CreatingMenu
 */
public class PelaajaAliObjecti implements TietokantaALiRajapinta {

    private JPanel main;
    private JPanel fillingPanel;
    private JTextField nimi, pelinlukumaara, voittojenlukumaara;
    private JLabel nimilabel, pelilkmlabel, voittolkmlabel;
    private JLabel header;
    private JButton lisaysNappula;

    /**
     *
     * Configaa objectin
     */
    public PelaajaAliObjecti() {
        this.lisaysNappula = new JButton("Lisää");
        this.header = new JLabel("Luo Uusi Pelajaa");
        this.main = new JPanel();
        this.main.setLayout(new BorderLayout());
        this.fillingPanel = new JPanel();
        this.fillingPanel.setLayout(new GridLayout(3, 2));
        this.nimi = new JTextField(15);
        this.pelilkmlabel = new JLabel("Syötä pelinlukumäärä");
        this.nimilabel = new JLabel("Syötä pelaajan nimi");
        this.voittolkmlabel = new JLabel("syötä voittojen lukumäärä");
        this.pelinlukumaara = new JTextField(5);
        this.voittojenlukumaara = new JTextField(5);
        this.fillingPanel.add(nimilabel);
        this.fillingPanel.add(nimi);
        this.fillingPanel.add(pelilkmlabel);
        this.fillingPanel.add(pelinlukumaara);
        this.fillingPanel.add(voittolkmlabel);
        this.fillingPanel.add(voittojenlukumaara);
        this.main.add(this.fillingPanel, BorderLayout.CENTER);
        this.main.add(this.header, BorderLayout.NORTH);
        this.main.add(this.lisaysNappula, BorderLayout.SOUTH);


    }

    @Override
    public JPanel palautaMainPanel() {
        return this.main;
    }

    @Override
    public JButton palautaFunktionaalinenNappula() {
        return this.lisaysNappula;
    }

    @Override
    public boolean OnkoSallittu() {
        String a = nimi.getText();
        String i = this.pelinlukumaara.getText();
        String j = this.voittojenlukumaara.getText();

        int p = 0;
        int q = 0;

        try {
            p = Integer.parseInt(i);

        } catch (Exception e) {
            return false;
        }

        try {
            q = Integer.parseInt(i);

        } catch (Exception e) {
            return false;
        }




        return valuesAllright(p, q);
    }
    
       /**
     *
     * Tarkistaa että valuet on oikealla rangella
     */

    public boolean valuesAllright(int i, int j) {
        if ((i > 0) && (j > 0) && (i < 10000) && (j < 10000)) {
            return true;
        } else {
            return false;
        }

    }
    
         /**
     *
     * Palauttaa objectin eli siis Pelaajan. Luo sen ja sitten palauttaa 
     */


    @Override
    public Object palautaObjekti() {
        String a = nimi.getText();
        String i = this.pelinlukumaara.getText();
        String j = this.voittojenlukumaara.getText();

        int p = Integer.parseInt(i);
        int q = Integer.parseInt(j);

        Pelaaja pelaaja = new Pelaaja(a);
        Statistiikka stat = new Statistiikka(p, q);
        pelaaja.setStatistics(stat);
        return pelaaja;
    }
    
   
}
