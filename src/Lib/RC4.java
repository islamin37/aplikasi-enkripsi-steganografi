/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lib;

/**
 *
 * @author marti
 */
public class RC4 {

    private static int[] S = new int[256];
    private static int[] T = new int[256];

    public static String Encryption_Decryption(String plaintext, String key) {
        int length = plaintext.length();
        String output, k;
        int xor;
        StringBuilder out = new StringBuilder();
        int[] arrKey = Toint(key);
        initialization(arrKey, key);
        k = Keygeneration(length);
        plaintext = convertTextToBinary(plaintext);
        k = convertTextToBinary(k);
        int[] arrKey2 = Toint(k);
        int[] arrPlaitext = Toint(plaintext);

        for (int i = 0; i < arrPlaitext.length; i++) {
            xor = arrKey2[i] ^ arrPlaitext[i];
            out.append(xor);
        }
        output = out.toString();
        output = convertFromBinaryToText(output);

        return output;
    }

    public static void initialization(int[] arrkey, String Key) {
        int temp;
        for (int i = 0; i < 256; i++) {
            S[i] = (int) i;
            T[i] = arrkey[i % Key.length()];  //repeat the key to the length 256
        }
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + T[i]) % 256;
            //swap
            temp = S[j];
            S[j] = S[i];
            S[i] = temp;
        }
    }

    public static String Keygeneration(int length) {
        int i = 0, j = 0, t, k;
        StringBuilder key = new StringBuilder();
        int temp;
        for (int counter = 0; counter < length; counter++) {
            i = (i + 1) % 256;
            j = (j + S[i]) % 256;
            //swap
            temp = S[i];
            S[i] = S[j];
            S[j] = temp;
            t = ((S[i] + S[j]) % 256);
            k = S[t];
            key.append(k);
        }
        return key.toString();
    }

    //prepare input
    public static String Tostring(int[] input, int length) {
        StringBuilder s = new StringBuilder();
        char[] str = new char[length];
        for (int i = 0; i < length; i++) {
            str[i] = (char) input[i];
            s.append(str[i]);
        }
        return s.toString();
    }

    public static int[] Toint(String input) {
        int[] arr = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            arr[i] = (int) input.charAt(i);
        }
        return arr;
    }

    private static String convertTextToBinary(String input) {
        // String in=Tostring(input, input.length);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {

            int chAscii = (int) input.charAt(i);
            for (int j = 0; j < (8 - Integer.toBinaryString(chAscii).length()); j++) {
                output.append("0");
            }
            output.append(Integer.toBinaryString(chAscii));
        }
        return output.toString();
    }

    private static String convertFromBinaryToText(String input) {
        StringBuilder output = new StringBuilder();
        char c;
        String part;
        for (int i = 0; i < input.length(); i += 8) {
            part = input.substring(i, i + 8);
            c = (char) Integer.parseInt(part, 2);
            output.append(c);
        }
        return output.toString();
    }
}