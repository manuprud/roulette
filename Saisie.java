/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exojava;

/**
 *
 * @author lagache
 */
import java.io.*;

class Saisie {

    public static String lire_String() {
        String ligne_lue = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            ligne_lue = br.readLine();
        } catch (IOException e) {
            System.err.println(e);
        }
        return ligne_lue;
    }

    public static String lire_String(String question) {
        System.out.print(question);
        return (lire_String());
    }

    public static int lire_int() {
        return Integer.parseInt(lire_String());
    }

    public static int lire_int(String question) {
        System.out.print(question);
        return Integer.parseInt(lire_String());
    }

    public static double lire_double() {
        return Double.parseDouble(lire_String());
    }

    public static double lire_double(String question) {
        System.out.print(question);
        return Double.parseDouble(lire_String());
    }

    public static char lire_char() {
        String reponse = lire_String();
        return reponse.charAt(0);
    }

    public static char lire_char(String question) {
        System.out.print(question);
        String reponse = lire_String();
        return reponse.charAt(0);
    }
}
