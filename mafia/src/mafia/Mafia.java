/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia;


import GraphicInterface.JPanerManager;
import GraphicInterface.GraphicInterfaceCore;
import java.io.*;
import java.util.*;
import mafia.hahmot.Hahmo;
import mafia.hahmot.Pelaaja;
import mafia.hahmot.Rooli;
import mafia.kyvyt.Buff;
import mafia.kyvyt.BuffinTyyppi;
import mafia.kyvyt.Kyky;
import mafia.kyvyt.NormiKyky;
import mafia.peli.YhdenAsianLuokkia.Sekoittaja;
import mafia.peli.YhdenAsianLuokkia.SortKyky;
import java.lang.Object;
import mafia.hahmot.Tiimi;
import mafia.kyvyt.*;
import mafia.peli.Logit.LogWriter;
import mafia.peli.ReadWriting.Kirjoittaja;
import mafia.peli.ReadWriting.Loader;
import mafia.peli.ReadWriting.PeliKaynnistaja;
import mafia.peli.ReadWriting.PelinRakentaja;
import mafia.peli.ValmiiksAsetetut.AlkuperainenMafiooso;
import mafia.userinterface.TekstiRajapinta;

/**
 *
 * @author Elkyur
 */
public class Mafia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
      // RectangleProgram rectObj = new RectangleProgram();
       
        GraphicsTest();
     
        //  KokeilelArrayListisa();
       //KokeillePelia();
       // LogWrtierTest();
        //FileTest();
        // WriterTest();
     //  ReadingTest();
       // KirjoittajaTestOsa2();
    }

    /**
     *
     */
    
    public static void GraphicsTest() throws IOException
    {
        PelinRakentaja rakentaja = new PelinRakentaja();
        rakentaja.asetaParamaterit();
        PeliKaynnistaja kaynnistaja = new PeliKaynnistaja();
        JPanerManager manager = new JPanerManager(rakentaja, kaynnistaja);
        
      //  manager.LoadAll();
        GraphicInterfaceCore valikko = new GraphicInterfaceCore(manager);
    
    
    }
    
    public static void KokeilleYhtasuuruutta() {

        BuffinTyyppi tyyppi = new BuffinTyyppi("k");
        Buff buffi = new Buff("salama", 0, tyyppi);
        NormiKyky murhata = new NormiKyky("123", buffi, false);
        NormiKyky murhatar = new NormiKyky("1233", buffi, false);
        if (murhata.equals(murhatar)) {
            System.out.println("Se onnistui");
        } else {
            System.out.println("ei");
        }
    }

    /**
     *
     */
    public static void KokeilleSekoittajaa() {
        Rooli mafia = new Rooli("Mafia");
        Rooli kansalainen = new Rooli("Kansalainen");
        Rooli hullu = new Rooli("Hullu");
        Rooli hilleri = new Rooli("heaalaja");
        Rooli poliisi = new Rooli("Poliisi");
        Sekoittaja sekoittaja = new Sekoittaja();
        ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
        Hahmo Mafia01 = new Hahmo(mafia);
        Hahmo Mafia02 = new Hahmo(mafia);
        Hahmo Kansa01 = new Hahmo(kansalainen);
        Hahmo Kansa02 = new Hahmo(kansalainen);
        Hahmo Poliisi = new Hahmo(poliisi);
        Hahmo Heaalaaja = new Hahmo(hilleri);
        Hahmo Kansa03 = new Hahmo(kansalainen);
        Hahmo Hullu = new Hahmo(hullu);

        hahmot.add(Mafia01);
        hahmot.add(Mafia02);
        hahmot.add(Kansa01);
        hahmot.add(Kansa02);
        hahmot.add(Poliisi);
        hahmot.add(Heaalaaja);
        hahmot.add(Kansa03);
        hahmot.add(Hullu);

        ArrayList<Pelaaja> pelaajat = new ArrayList<Pelaaja>();
        pelaajat.add(new Pelaaja("lolcat"));
        pelaajat.add(new Pelaaja("abc"));
        pelaajat.add(new Pelaaja("guest"));
        pelaajat.add(new Pelaaja("pro"));
        pelaajat.add(new Pelaaja("noob"));
        pelaajat.add(new Pelaaja("helllord"));
        pelaajat.add(new Pelaaja("heaven"));
        pelaajat.add(new Pelaaja("random"));

        sekoittaja.Sekoita(hahmot, pelaajat);
        sekoittaja.Sekoita(hahmot, pelaajat);


        for (Hahmo hahmo : hahmot) {
            System.out.println("Pelaaja: " + hahmo.palautaOmistaja().PalautaNimi() + " hahmo: " + hahmo.getNimi());
        }
    }

    /**
     *
     */
    public static void SortausKokeilu() {

        Comparator comp = new SortKyky();


        TreeMap<Kyky, Pelaaja> mappi = new TreeMap<Kyky, Pelaaja>(comp);

        BuffinTyyppi tyyppi0 = new BuffinTyyppi("tuli", 1);
        BuffinTyyppi tyyppi1 = new BuffinTyyppi("vesi", 1);
        BuffinTyyppi tyyppi2 = new BuffinTyyppi("salama", 2);

        Buff buffi0 = new Buff("tuli", 0, tyyppi0);
        Buff buffi1 = new Buff("tuli", 0, tyyppi1);
        Buff buffi2 = new Buff("tuli", 0, tyyppi2);

        Pelaaja pelaaja1 = new Pelaaja("p1");
        Pelaaja pelaaja2 = new Pelaaja("p2");
        Pelaaja pelaaja3 = new Pelaaja("p3");

        Kyky kyky1 = new NormiKyky("", buffi0, true);
        Kyky kyky2 = new NormiKyky("", buffi1, true);
        Kyky kyky3 = new NormiKyky("", buffi2, true);

        mappi.put(kyky3, pelaaja3);
        mappi.put(kyky2, pelaaja2);
        mappi.put(kyky1, pelaaja1);



        for (Kyky kyky : mappi.keySet()) {
            System.out.println(kyky.getBuffi().returnBuffinTyyppi().palautaPrioriteetti());
        }



    }
    /**
     *
     */
    public static void kokeilleToimiikoCastaaminenOikein() {
        ArrayList<Object> objektilista = new ArrayList<Object>();
        objektilista.add(new Pelaaja("p1"));
        objektilista.add(new Pelaaja("p2"));
        objektilista.add(new Pelaaja("p3"));



    }
    // Vihdoinkin!

    /**
     *
     */
    public static void KokeillePelia() throws FileNotFoundException, UnsupportedEncodingException {
        Scanner lukija = new Scanner(System.in);
        TekstiRajapinta rajapinta = new TekstiRajapinta(lukija);
        Sekoittaja sekoittaja = new Sekoittaja();
        ArrayList<Pelaaja> pelaajat = new ArrayList<Pelaaja>();

        for (int i = 1; i <= 8; i++) {
            pelaajat.add(new Pelaaja("player" + i));
        }

        AlkuperainenMafiooso mafiooso = new AlkuperainenMafiooso(pelaajat, sekoittaja, rajapinta);
        mafiooso.Run();

    }

    public static void KokeilelArrayListisa() {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("yksi");
        lista.add("kaksi");
        lista.add("kolme");
        lista.add("nelja");
        ListaaElementit(lista);
        lista.remove(1);
        System.out.println(lista.get(1));


    }

    public static void ListaaElementit(ArrayList<String> listaus) {
        for (int i = 0; i < listaus.size(); i++) {
            System.out.println(listaus.get(i));
        }

    }
    
    public static void LogWrtierTest() throws FileNotFoundException, UnsupportedEncodingException
    {
    LogWriter kirjoittaja = new LogWriter();
    kirjoittaja.LogWriterInit(null);
    kirjoittaja.Write("testaus");
    kirjoittaja.Write("testaus");
    kirjoittaja.Write("testaus");
    kirjoittaja.Write("testaus");
    kirjoittaja.Write("testaus");
    kirjoittaja.Write("testaus");
    
    }
    public static void FileTest() throws FileNotFoundException, IOException
    {
   
 //   FileOutputStream is = new FileOutputStream(tiedosto);
 //   OutputStreamWriter osw = new OutputStreamWriter(is);    
 //    Writer w = new BufferedWriter(osw);
 //    w.write("toimiiko");
 //    w.flush();
      File tiedosto = new File("gamedata/structures/faasit.txt");  
     FileWriter fw = new FileWriter(tiedosto,true);
     fw.append("lisaa");
     fw.close();
     
     
    
    }
    
    public static void WriterTest() throws IOException
    {
    Kirjoittaja kirjoittaja = new Kirjoittaja();
    kirjoittaja.asetaPelaajat(new File("gamedata/structures/Pelaajat.txt"));
    kirjoittaja.LisaaPelaaja(new Pelaaja("pikachu"));
    
    }
    
    public static void ReadingTest() throws FileNotFoundException {
        Loader loader = new Loader();
        loader.setPelaajat(new File("gamedata/structures/Pelaajat.txt"));
        ArrayList<Pelaaja> pelaajat = loader.palautaPelaajat();
        for (Pelaaja pelaaja : pelaajat) {
            System.out.println(pelaaja.PalautaNimi());
            System.out.println(pelaaja.palautaStatistiikka().palautaVoittojenLkm());
        }
    
    }
    
   
    
    public static void KirjoittajaTestOsa2() throws IOException
    {
    Kirjoittaja kirjoittaja = new Kirjoittaja();
    kirjoittaja.asetaRooli(new File(Settings.Settings.roolitlocation));
    Tiimi mafia = new Tiimi("Pahat");
    Rooli mafiar = new Rooli("Mafiooso");
    kirjoittaja.LisaaRooli(mafiar, mafia);
    }
}
