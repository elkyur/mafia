/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mafia;

import java.util.Random;

/**
 *
 * @author Elkyur
 */
public class Mafia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random generator = new Random();
        System.out.println(generator.nextInt(2));
        System.out.println(generator.nextInt(2));
        System.out.println(generator.nextInt(2));
        System.out.println(generator.nextInt(2));
        System.out.println(generator.nextInt(2));
    }
}
