import java.io.*;

public class CeaserCipherII {
    public static String encrypt(String text, int s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char ch = (char) ((c - base + s) % 26 + base);
                sb.append(ch);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String decrypt(String encrypted, int s) {
        return encrypt(encrypted, 26 - (s % 26));
    }

    public static void main(String[] args) {
        int shift = 3;
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        BufferedReader br = null;
        PrintWriter out = null;

        try {
            br = new BufferedReader(new FileReader(inputFile));
            StringBuffer text = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append("\n");
            }

            String encrypted = encrypt(text.toString(), shift);
            String decrypted = decrypt(encrypted, shift);

            out = new PrintWriter(new FileWriter(outputFile));
            out.println("Original Text:");
            out.println(text.toString());
            out.println();
            out.println("Encrypted Text:");
            out.println(encrypted);
            out.println();
            out.println("Decrypted Text:");
            out.println(decrypted);

            System.out.println("Encryption and decryption completed.");
            System.out.println("Read from: " + inputFile);
            System.out.println("Written to: " + outputFile);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
                if (out != null) out.close();
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }
}
