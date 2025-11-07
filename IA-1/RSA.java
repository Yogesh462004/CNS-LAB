import java.math.BigInteger;
import java.util.*;

public class RSA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first prime number p: ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter second prime number q: ");
        BigInteger q = sc.nextBigInteger();

        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        System.out.println("n (p*q): " + n);
        System.out.println("phi(n): " + phi);

        System.out.print("Enter public exponent e (coprime with phi): ");
        BigInteger e = sc.nextBigInteger();

        BigInteger d = e.modInverse(phi);

        System.out.println("Public Key: (" + e + ", " + n + ")");
        System.out.println("Private Key: (" + d + ", " + n + ")");

        sc.nextLine();

        System.out.print("\nEnter plaintext (string or number): ");
        String input = sc.nextLine();

        if (input.matches("\\d+")) {
            BigInteger msg = new BigInteger(input);
            BigInteger cipher = msg.modPow(e, n);
            BigInteger decrypted = cipher.modPow(d, n);

            System.out.println("\nEncrypted: " + cipher);
            System.out.println("Decrypted: " + decrypted);
        } else {
            input = input.toUpperCase().replaceAll("[^A-Z]", "");
            System.out.println("\nPlaintext (converted to numbers):");

            BigInteger[] numericText = new BigInteger[input.length()];
            for (int i = 0; i < input.length(); i++) {
                int num = input.charAt(i) - 'A';
                numericText[i] = BigInteger.valueOf(num);
                System.out.print(num + " ");
            }

            System.out.println("\n\nEncryption:");

            BigInteger[] cipherText = new BigInteger[input.length()];
            for (int i = 0; i < input.length(); i++) {
                cipherText[i] = numericText[i].modPow(e, n);
                System.out.print(cipherText[i] + " ");
            }

            System.out.println("\n\nDecryption:");

            StringBuilder decrypted = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                BigInteger plain = cipherText[i].modPow(d, n);
                int val = plain.intValue();
                decrypted.append((char) (val + 'A'));
                System.out.print(val + " ");
            }

            System.out.println("\n\nDecrypted Text: " + decrypted.toString());
        }
    }
}
