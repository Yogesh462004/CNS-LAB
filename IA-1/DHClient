import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class DHClient {

    static long power(long base, long exp, long mod) {
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

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter private key of Client (xb): ");
        long xb = sc.nextLong();

        Socket s = new Socket("localhost", 5000);

        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        long p = in.readLong();
        long g = in.readLong();
        long ya = in.readLong();

        long yb = power(g, xb, p);
        out.writeLong(yb);

        long kb = power(ya, xb, p);

        System.out.println("Public key received from Server (ya): " + ya);
        System.out.println("Shared Secret Key (Client): " + kb);

        s.close();
        sc.close();
    }
}
