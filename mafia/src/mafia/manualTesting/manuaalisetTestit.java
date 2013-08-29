/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia.manualTesting;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeMap;
import mafia.hahmot.*;
import mafia.kyvyt.Buff;
import mafia.kyvyt.BuffinTyyppi;
import mafia.kyvyt.Kyky;
import mafia.kyvyt.NormiKyky;
import mafia.peli.Logit.LogWriter;
import mafia.peli.TietokantaHallinta.Kirjoittaja;
import mafia.peli.TietokantaHallinta.Loader;
import mafia.peli.ValmiiksAsetetut.AlkuperainenMafiooso;
import mafia.peli.YhdenAsianLuokkia.Sekoittaja;
import mafia.peli.YhdenAsianLuokkia.SortKyky;
import mafia.userinterface.TekstiRajapinta;

/**
 *
 * @author Elkyur
 */
public class manuaalisetTestit {

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

    public static void LogWrtierTest() throws FileNotFoundException, UnsupportedEncodingException {
        LogWriter kirjoittaja = new LogWriter();
        kirjoittaja.LogWriterInit(null);
        kirjoittaja.Write("testaus");
        kirjoittaja.Write("testaus");
        kirjoittaja.Write("testaus");
        kirjoittaja.Write("testaus");
        kirjoittaja.Write("testaus");
        kirjoittaja.Write("testaus");

    }

    public static void FileTest() throws FileNotFoundException, IOException {

        //   FileOutputStream is = new FileOutputStream(tiedosto);
        //   OutputStreamWriter osw = new OutputStreamWriter(is);    
        //    Writer w = new BufferedWriter(osw);
        //    w.write("toimiiko");
        //    w.flush();
        File tiedosto = new File("gamedata/structures/faasit.txt");
        FileWriter fw = new FileWriter(tiedosto, true);
        fw.append("lisaa");
        fw.close();



    }

    public static void WriterTest() throws IOException {
        Kirjoittaja kirjoittaja = new Kirjoittaja();
        kirjoittaja.asetaPelaajat(new File("gamedata/structures/Pelaajat.txt"));
        kirjoittaja.LisaaPelaaja(new Pelaaja("Vegeta"), false);

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

    public static void KirjoittajaTestOsa2() throws IOException {
        Kirjoittaja kirjoittaja = new Kirjoittaja();
        kirjoittaja.asetaRooli(new File(Settings.Settings.roolitlocation));
        Tiimi mafia = new Tiimi("Pahat");
        Rooli mafiar = new Rooli("Mafiooso");
        kirjoittaja.LisaaRooli(mafiar, mafia, false);
    }

    public static void IdeaTest() {
        Rooli mafia = new Rooli("Mafia");
        Rooli kansalainen = new Rooli("Kansalainen");
        Rooli hullu = new Rooli("Hullu");
        Rooli hilleri = new Rooli("heaalaja");
        Rooli poliisi = new Rooli("Poliisi");
        Hahmo Mafia01 = new Hahmo(mafia);
        Hahmo Mafia02 = new Hahmo(mafia);
        Hahmo Kansa01 = new Hahmo(kansalainen);
        Hahmo Kansa02 = new Hahmo(kansalainen);
        Hahmo Poliisi = new Hahmo(poliisi);
        Hahmo Heaalaaja = new Hahmo(hilleri);
        Hahmo Kansa03 = new Hahmo(kansalainen);
        Hahmo Hullu = new Hahmo(hullu);

        Tiimi hyvat = new Tiimi("hyvät");
        Tiimi pahat = new Tiimi("pahat");

        hyvat.LisaaPelaaja(Kansa01);
        hyvat.LisaaPelaaja(Kansa02);
        pahat.LisaaPelaaja(Mafia01);
        pahat.LisaaPelaaja(Mafia02);

        ArrayList<Pelattava> pelattavat = new ArrayList<Pelattava>();

        pelattavat.add(hyvat);
        pelattavat.add(pahat);
        pelattavat.add(Hullu);



        ArrayList<Pelattava> klooni = (ArrayList<Pelattava>) pelattavat.clone();

        pelattavat.get(0).poistaTiimista(Kansa01);
        pelattavat.get(0).poistaTiimista(Kansa02);

        System.out.println(klooni.get(0).getTeam().get(0).getNimi());





    }

    public void IdeaTest2() {
        Rooli mafia = new Rooli("Mafia");
        Rooli kansalainen = new Rooli("Kansalainen");
        Rooli hullu = new Rooli("Hullu");
        Rooli hilleri = new Rooli("heaalaja");
        Rooli poliisi = new Rooli("Poliisi");
        Hahmo Mafia01 = new Hahmo(mafia);
        Hahmo Mafia02 = new Hahmo(mafia);
        Hahmo Kansa01 = new Hahmo(kansalainen);
        Hahmo Kansa02 = new Hahmo(kansalainen);
        Hahmo Poliisi = new Hahmo(poliisi);
        Hahmo Heaalaaja = new Hahmo(hilleri);
        Hahmo Kansa03 = new Hahmo(kansalainen);
        Hahmo Hullu = new Hahmo(hullu);

        Tiimi hyvat = new Tiimi("hyvät");
        Tiimi pahat = new Tiimi("pahat");

        hyvat.LisaaPelaaja(Kansa01);
        hyvat.LisaaPelaaja(Kansa02);
        pahat.LisaaPelaaja(Mafia01);
        pahat.LisaaPelaaja(Mafia02);



    }

    public static void WriterTest3() throws IOException {
        Kirjoittaja kirjoittaja = new Kirjoittaja();
        kirjoittaja.asetaPelaajat(new File("gamedata/structures/Pelaajat.txt"));
        // kirjoittaja.LisaaPelaaja(new Pelaaja("Vegeta"), false);
        ArrayList<Pelaaja> pelaajat = new ArrayList<Pelaaja>();
        pelaajat.add(new Pelaaja("Goku"));
        pelaajat.add(new Pelaaja("Gohan"));
        kirjoittaja.MofifyPelaajat(pelaajat);
    }
}
