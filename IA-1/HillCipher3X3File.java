import java.io.*;

public class HillCipher3x3 {

    static int[][] key = new int[3][3];

    // Encrypt
    static String encrypt(String text) {
        text = text.replaceAll("\\s", "").toUpperCase();

        while (text.length() % 3 != 0)
            text += "X"; // padding

        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i += 3) {
            int[] p = {
                text.charAt(i) - 'A',
                text.charAt(i + 1) - 'A',
                text.charAt(i + 2) - 'A'
            };

            for (int row = 0; row < 3; row++) {
                int val = 0;
                for (int col = 0; col < 3; col++)
                    val += key[row][col] * p[col];

                cipher.append((char) ((val % 26) + 'A'));
            }
        }
        return cipher.toString();
    }

    // Determinant
    static int determinant(int[][] m) {
        int det =
              m[0][0] * (m[1][1] * m[2][2] - m[1][2] * m[2][1])
            - m[0][1] * (m[1][0] * m[2][2] - m[1][2] * m[2][0])
            + m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]);
        return ((det % 26) + 26) % 26;
    }

    static int modInverse(int det) {
        for (int i = 1; i < 26; i++)
            if ((det * i) % 26 == 1)
                return i;
        return -1;
    }

    // Decrypt
    static String decrypt(String cipher) {

        int det = determinant(key);
        int invDet = modInverse(det);

        int[][] invKey = new int[3][3];

        invKey[0][0] =  (key[1][1]*key[2][2] - key[1][2]*key[2][1]);
        invKey[0][1] = -(key[0][1]*key[2][2] - key[0][2]*key[2][1]);
        invKey[0][2] =  (key[0][1]*key[1][2] - key[0][2]*key[1][1]);

        invKey[1][0] = -(key[1][0]*key[2][2] - key[1][2]*key[2][0]);
        invKey[1][1] =  (key[0][0]*key[2][2] - key[0][2]*key[2][0]);
        invKey[1][2] = -(key[0][0]*key[1][2] - key[0][2]*key[1][0]);

        invKey[2][0] =  (key[1][0]*key[2][1] - key[1][1]*key[2][0]);
        invKey[2][1] = -(key[0][0]*key[2][1] - key[0][1]*key[2][0]);
        invKey[2][2] =  (key[0][0]*key[1][1] - key[0][1]*key[1][0]);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                invKey[i][j] = ((invKey[i][j] * invDet) % 26 + 26) % 26;

        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < cipher.length(); i += 3) {
            int[] c = {
                cipher.charAt(i) - 'A',
                cipher.charAt(i + 1) - 'A',
                cipher.charAt(i + 2) - 'A'
            };

            for (int row = 0; row < 3; row++) {
                int val = 0;
                for (int col = 0; col < 3; col++)
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
        for (int i = 0; i < 3; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < 3; j++)
                key[i][j] = Integer.parseInt(row[j]);
        }

        // Read plaintext
        String plaintext = br.readLine();

        String cipher = encrypt(plaintext);
        String decrypted = decrypt(cipher);

        pw.println("Hill Cipher (3x3)");
        pw.println("Key Matrix:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                pw.print(key[i][j] + " ");
            pw.println();
        }

        pw.println("\nPlain Text: " + plaintext);
        pw.println("Encrypted Text: " + cipher);
        pw.println("Decrypted Text: " + decrypted);

        br.close();
        pw.close();
    }
}
