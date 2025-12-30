
import java.net.*;
import java.io.*;
import java.util.*;

public class DHClient {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Client Private Key (xb): ");
        long xb = sc.nextLong();

        Socket s = new Socket("localhost", 5000);

        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        // Receive p, g and server public key
        long p = in.readLong();
        long g = in.readLong();
        long ya = in.readLong();

        // Client public key
        long yb = modPow(g, xb, p);

        // Send client public key
        out.writeLong(yb);

        // Compute shared key
        long kb = modPow(ya, xb, p);

        System.out.println("Public key received from server: " + ya);
        System.out.println("Shared secret key: " + kb);

        s.close();
        sc.close();
    }

    private static long modPow(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;

        while (exp > 0) {
            if (exp % 2 == 1)
                result = (result * base) % mod;

            exp = exp / 2;
            base = (base * base) % mod;
        }
        return result;
    }
}
