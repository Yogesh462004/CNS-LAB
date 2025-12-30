import java.io.*;

public class HillCipher2x2File {

    static int[][] key = new int[2][2];

    static String encrypt(String text) {
        text = text.replaceAll("\\s", "").toUpperCase();
        if (text.length() % 2 != 0)
            text += "X"; // padding

        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int[] p = {
                text.charAt(i) - 'A',
                text.charAt(i + 1) - 'A'
            };

            for (int row = 0; row < 2; row++) {
                int val = 0;
                for (int col = 0; col < 2; col++)
                    val += key[row][col] * p[col];

                cipher.append((char) ((val % 26) + 'A'));
            }
        }
        return cipher.toString();
    }

    static int determinant(int[][] m) {
        int det = (m[0][0] * m[1][1] - m[0][1] * m[1][0]);
        return ((det % 26) + 26) % 26;
    }

    static int modInverse(int det) {
        for (int i = 1; i < 26; i++)
            if ((det * i) % 26 == 1)
                return i;
        return -1;
    }

    static String decrypt(String cipher) {

        int det = determinant(key);
        int invDet = modInverse(det);

        int[][] invKey = new int[2][2];

        invKey[0][0] =  key[1][1];
        invKey[0][1] = -key[0][1];
        invKey[1][0] = -key[1][0];
        invKey[1][1] =  key[0][0];

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                invKey[i][j] = ((invKey[i][j] * invDet) % 26 + 26) % 26;

        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < cipher.length(); i += 2) {
            int[] c = {
                cipher.charAt(i) - 'A',
                cipher.charAt(i + 1) - 'A'
            };

            for (int row = 0; row < 2; row++) {
                int val = 0;
                for (int col = 0; col < 2; col++)
                    val += invKey[row][col] * c[col];

                plain.append((char) ((val % 26) + 'A'));
            }
        }
        return plain.toString();
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));

        // Read key matrix
        String[] row1 = br.readLine().split(" ");
        String[] row2 = br.readLine().split(" ");

        key[0][0] = Integer.parseInt(row1[0]);
        key[0][1] = Integer.parseInt(row1[1]);
        key[1][0] = Integer.parseInt(row2[0]);
        key[1][1] = Integer.parseInt(row2[1]);

        // Read plaintext
        String plaintext = br.readLine();

        String cipher = encrypt(plaintext);
        String decrypted = decrypt(cipher);

        pw.println("Hill Cipher (2x2)");
        pw.println("Key Matrix:");
        pw.println(key[0][0] + " " + key[0][1]);
        pw.println(key[1][0] + " " + key[1][1]);
        pw.println();
        pw.println("Plain Text: " + plaintext);
        pw.println("Encrypted Text: " + cipher);
        pw.println("Decrypted Text: " + decrypted);

        br.close();
        pw.close();
    }
}
