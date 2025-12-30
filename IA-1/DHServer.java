import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class DHServer {

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

        System.out.print("Enter prime number (p): ");
        long p = sc.nextLong();

        System.out.print("Enter primitive root (g): ");
        long g = sc.nextLong();

        System.out.print("Enter private key of Server (xa): ");
        long xa = sc.nextLong();

        long ya = power(g, xa, p);

        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server waiting for client...");
        Socket s = ss.accept();

        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        out.writeLong(p);
        out.writeLong(g);
        out.writeLong(ya);

        long yb = in.readLong();

        long ka = power(yb, xa, p);

        System.out.println("Public key received from Client (yb): " + yb);
        System.out.println("Shared Secret Key (Server): " + ka);

        s.close();
        ss.close();
        sc.close();
    }
}
