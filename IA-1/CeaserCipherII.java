import java.io.*;

public class CeaserCipherII {

    public static String encrypt(String text, int s) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
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

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));

        String text = br.readLine();          // Plain text
        int s = Integer.parseInt(br.readLine()); // Shift value

        String encrypted = encrypt(text, s);
        String decrypted = decrypt(encrypted, s);

        pw.println("Plain Text: " + text);
        pw.println("Shift Value: " + s);
        pw.println("Encrypted Text: " + encrypted);
        pw.println("Decrypted Text: " + decrypted);

        br.close();
        pw.close();
    }
}
