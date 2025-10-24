import java.util.*;

public class bMonoalphabeticCipher {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encrypt(String text, String key) {
        key = key.toUpperCase();
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                int index = ALPHABET.indexOf(ch);
                result.append(index != -1 ? key.charAt(index) : ch);
            } else if (Character.isLowerCase(ch)) {
            	
                int index = ALPHABET.indexOf(Character.toUpperCase(ch));
                result.append(index != -1 ? Character.toLowerCase(key.charAt(index)) : ch);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, String key) {
        key = key.toUpperCase();
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                int index = key.indexOf(ch);
                result.append(index != -1 ? ALPHABET.charAt(index) : ch);
            } else if (Character.isLowerCase(ch)) {
                int index = key.indexOf(Character.toUpperCase(ch));
                result.append(index != -1 ? Character.toLowerCase(ALPHABET.charAt(index)) : ch);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    private static boolean isValidKey(String key) {
        key = key.toUpperCase();
        boolean[] seen = new boolean[26];
        for (char c : key.toCharArray()) {
            if (c < 'A' || c > 'Z') return false;
            if (seen[c - 'A']) return false;
            seen[c - 'A'] = true;
        }
        return true;
    }

    // Optional: Generate a random key
    private static String generateRandomKey() {
        List<Character> letters = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) letters.add(c);
        Collections.shuffle(letters);
        StringBuilder key = new StringBuilder();
        for (char c : letters) key.append(c);
        return key.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to encrypt:");
        String text = sc.nextLine().trim();

        System.out.println("Enter key (26 unique letters) or type 'RANDOM' to generate one:");
        String key = sc.nextLine().trim();

        if (key.equalsIgnoreCase("RANDOM")) {
            key = generateRandomKey();
            System.out.println("Generated Key: " + key);
        }

        if (key.length() != 26 || !isValidKey(key)) {
            System.out.println(" Invalid key! It must contain 26 unique A–Z letters.");
            return;
        }

        String encrypted = encrypt(text, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("\nOriginal Text  : " + text);
        System.out.println("Encrypted Text : " + encrypted);
        System.out.println("Decrypted Text : " + decrypted);
    }
}
