
import java.util.Scanner;

public class ElaGamal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long p,g;
        long x;
        long y;
        long m,k,ka;
        long c1,c2,d;
        System.out.println("Enter prime number(p):");
        p=sc.nextLong();
        System.out.println("Enter primitive root of  number(p):");
        g=sc.nextLong();
        System.out.println("Enter private key(x):");
        x=sc.nextLong();
        System.out.println("Enter random integer (k):");
        k=sc.nextLong();
        y=modPow(g,x,p);
        System.out.println("Enter PlainText (m):");
        m=sc.nextLong();
        c1=modPow(g,k,p);
        ka=modPow(y,k,p);
        c2=(m*ka)%p;
        System.out.println("\nEncrypted values");
        System.out.println("c1="+c1);
        System.out.println("c2="+c2);
        ka=modPow(c1,x,p);
        d=(c2*modInverse(ka,p))%p;
        System.out.println("\nDecrypted message:"+d);
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
    private static long modInverse(long a,long p){
        return modPow(a,p-2,p);
    }
}
