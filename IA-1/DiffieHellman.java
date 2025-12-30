
import java.util.Scanner;

public class DiffieHellman {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long p,g;
        long xa,xb;
        long ya,yb;
        long ka,kb;
        System.out.println("Enter prime number(p):");
        p=sc.nextLong();
        System.out.println("Enter primitive root of  number(p):");
        g=sc.nextLong();
        System.out.println("Enter private key of user A(xa):");
        xa=sc.nextLong();
        System.out.println("Enter private key of user B(xb):");
        xb=sc.nextLong();

        ya=modPow(g,xa,p);
        yb=modPow(g,xb,p);

        System.out.println("\nPublic key of user A(ya):"+ya);
        System.out.println("\nPublic key of user B(yb):"+yb);
        ka=modPow(yb,xa,p);
        kb=modPow(ya,xb,p);

        System.out.println("\nSecret key of user A(ka):"+ka);
        System.out.println("\nSecret key of user B(kb):"+kb);
        if(ka==kb) System.out.println("\nKey exchange successful");
        else System.out.println("\nKey exchange failed");

    }
    private static long modPow(long base, long exp, long mod) {
        long result=1;
        base=base%mod ;
        while(exp>0){
            if(exp%2==1)result=(result*base)%mod;
            exp=exp/2;
            base=(base*base)%mod;
        }
        return result;
    }
}
