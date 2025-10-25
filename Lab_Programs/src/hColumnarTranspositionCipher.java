import java.util.Arrays;
import java.util.Scanner;

public class hColumnarTranspositionCipher {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine().replaceAll("\\s", "");

        System.out.print("Enter key: ");
        String key = sc.nextLine().replaceAll("\\s", "");

        String encrypted = encrypt(plaintext, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("\nPlaintext  : " + plaintext);
        System.out.println("Encrypted  : " + encrypted);
        System.out.println("Decrypted  : " + decrypted);
    }

    public static String encrypt(String plaintext, String key) {
        int cols = key.length();
        int rows = (int) Math.ceil((double) plaintext.length() / cols);

        char[][] matrix = new char[rows][cols];

        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (index < plaintext.length())
                    matrix[i][j] = plaintext.charAt(index++);
                else
                    matrix[i][j] = 'X';
            }
        }

        Character[] keyArray = new Character[cols];
        for (int i = 0; i < cols; i++) keyArray[i] = key.charAt(i);
        Character[] sortedKey = keyArray.clone();
        Arrays.sort(sortedKey);

        StringBuilder cipher = new StringBuilder();
        for (char ch : sortedKey) {
            int colIndex = Arrays.asList(keyArray).indexOf(ch);
            for (int i = 0; i < rows; i++) {
                cipher.append(matrix[i][colIndex]);
            }
            keyArray[colIndex] = null;
        }

        return cipher.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        int cols = key.length();
        int rows = (int) Math.ceil((double) ciphertext.length() / cols);

        char[][] matrix = new char[rows][cols];

        Character[] keyArray = new Character[cols];
        for (int i = 0; i < cols; i++) keyArray[i] = key.charAt(i);
        Character[] sortedKey = keyArray.clone();
        Arrays.sort(sortedKey);

        int index = 0;
        for (char ch : sortedKey) {
            int colIndex = Arrays.asList(keyArray).indexOf(ch);
            for (int i = 0; i < rows; i++) {
                if (index < ciphertext.length())
                    matrix[i][colIndex] = ciphertext.charAt(index++);
            }
            keyArray[colIndex] = null;
        }

        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                plaintext.append(matrix[i][j]);

        return plaintext.toString().replaceAll("X+$", "");
    }
}
