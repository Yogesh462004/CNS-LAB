import java.util.Scanner;

public class fOneTimePad {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter key: ");
        String key = sc.nextLine();

        // Clean and uppercase both plaintext and key
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.toUpperCase().replaceAll("[^A-Z]", "");

        // Check key length
        if (key.length() != plaintext.length()) {
            System.out.println("Error: Key length must equal plaintext length.");
            return;
        }

        String encrypted = encrypt(plaintext, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("\nPlaintext  : " + plaintext);
        System.out.println("Encrypted  : " + encrypted);
        System.out.println("Decrypted  : " + decrypted);
    }

    private static String encrypt(String plaintext, String key) {
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            int p = plaintext.charAt(i) - 'A';
            int k = key.charAt(i) - 'A';
            char c = (char) ((p + k) % 26 + 'A');
            cipher.append(c);
        }
        return cipher.toString();
    }

    private static String decrypt(String encrypted, String key) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < encrypted.length(); i++) {
            int c = encrypted.charAt(i) - 'A';
            int k = key.charAt(i) - 'A';
            char p = (char) ((c - k + 26) % 26 + 'A');
            plaintext.append(p);
        }
        return plaintext.toString();
    }
}
