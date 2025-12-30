
import java.net.*;
import java.io.*;
import java.util.*;

public class DHServer {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        long p, g, x, ya, yb, ka;

        System.out.print("Enter Prime number (p): ");
        p = sc.nextLong();

        System.out.print("Enter Primitive root (g): ");
        g = sc.nextLong();

        System.out.print("Enter Private Key (x): ");
        x = sc.nextLong();

        // Server public key
        ya = modPow(g, x, p);

        ServerSocket ss = new ServerSocket(5000);
        System.out.println("\nServer waiting for client...");
        Socket s = ss.accept();

        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        DataInputStream in = new DataInputStream(s.getInputStream());

        // Send p, g and public key to client
        out.writeLong(p);
        out.writeLong(g);
        out.writeLong(ya);

        // Receive client's public key
        yb = in.readLong();

        // Compute shared key
        ka = modPow(yb, x, p);

        System.out.println("Public key received from client: " + yb);
        System.out.println("Shared secret key: " + ka);

        s.close();
        ss.close();
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
