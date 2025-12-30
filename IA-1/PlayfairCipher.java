import java.io.*;
import java.util.*;

public class PlayFair {

    static char[][] generateMatrix(String key) {
        key = key.toUpperCase().replace('J', 'I');
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[26];
        used['J' - 'A'] = true;

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (ch < 'A' || ch > 'Z') continue;
            int idx = ch - 'A';
            if (!used[idx]) {
                used[idx] = true;
                sb.append(ch);
            }
        }

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            int idx = ch - 'A';
            if (!used[idx]) {
                used[idx] = true;
                sb.append(ch);
            }
        }

        char[][] matrix = new char[5][5];
        int k = 0;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                matrix[i][j] = sb.charAt(k++);

        return matrix;
    }

    static String prepareText(String text) {
        text = text.toUpperCase().replace('J', 'I');
        StringBuilder sb = new StringBuilder();

        for (char ch : text.toCharArray())
            if (ch >= 'A' && ch <= 'Z')
                sb.append(ch);

        StringBuilder prepared = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            prepared.append(ch);
            if (i + 1 < sb.length() && sb.charAt(i + 1) == ch)
                prepared.append('X');
        }

        if (prepared.length() % 2 == 1)
            prepared.append('X');

        return prepared.toString();
    }

    static int[] pos(char[][] matrix, char ch) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == ch)
                    return new int[]{i, j};
        return null;
    }

    static String processPair(char[][] matrix, char a, char b, boolean encrypt) {
        int[] pa = pos(matrix, a);
        int[] pb = pos(matrix, b);

        int ra = pa[0], ca = pa[1];
        int rb = pb[0], cb = pb[1];

        if (ra == rb) {
            int shift = encrypt ? 1 : 4;
            ca = (ca + shift) % 5;
            cb = (cb + shift) % 5;
        } else if (ca == cb) {
            int shift = encrypt ? 1 : 4;
            ra = (ra + shift) % 5;
            rb = (rb + shift) % 5;
        } else {
            int temp = ca;
            ca = cb;
            cb = temp;
        }

        return "" + matrix[ra][ca] + matrix[rb][cb];
    }

    static String encrypt(char[][] matrix, String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2)
            sb.append(processPair(matrix, text.charAt(i), text.charAt(i + 1), true));
        return sb.toString();
    }

    static String decrypt(char[][] matrix, String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2)
            sb.append(processPair(matrix, text.charAt(i), text.charAt(i + 1), false));
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));

        String plaintext = br.readLine();
        String key = br.readLine();

        char[][] matrix = generateMatrix(key);
        String prepared = prepareText(plaintext);
        String cipher = encrypt(matrix, prepared);
        String decrypted = decrypt(matrix, cipher);

        pw.println("Playfair Cipher Matrix:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                pw.print(matrix[i][j] + " ");
            pw.println();
        }

        pw.println("\nPlain Text: " + plaintext);
        pw.println("Prepared Text: " + prepared);
        pw.println("Encrypted Text: " + cipher);
        pw.println("Decrypted Text: " + decrypted);

        br.close();
        pw.close();
    }
}
