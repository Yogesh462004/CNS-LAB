package com.yp.arrays;
import java.math.BigInteger;
import java.util.*;
public class Example02{


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter first prime number p:");
        BigInteger p=sc.nextBigInteger();
        System.out.println("Enter second prime number q:");
        BigInteger q=sc.nextBigInteger();
        BigInteger n=p.multiply(q);
        BigInteger phi=(p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        System.out.println("n (p*q):"+n);
        System.out.println("phi(n):"+phi);
        System.out.println("Enter public exponent key e:");
        BigInteger e=sc.nextBigInteger();
        BigInteger d=e.modInverse(phi);
        System.out.println("Public key:(" +e+ "," +n+ ")");
        System.out.println("Private key:(" +d+ "," +n+ ")");
        sc.nextLine();
        System.out.println("\n Enter Plaintext(number or string):");
        String input=sc.nextLine();
        if(input.matches("\\d+")){
            BigInteger msg=new BigInteger(input);
            BigInteger cipher=msg.modPow(e,n);
            BigInteger decrypted=cipher.modPow(d,n);
            System.out.println("\n Encrypted:"+cipher);
            System.out.println("\n Decrypted:"+decrypted);

        }else{
            input=input.toUpperCase().replaceAll("[^A-Z]","");
            System.out.println("\nPlain Text converted to numbers:");
            BigInteger [] numericText=new BigInteger[input.length()];
            for(int i=0;i<input.length();i++){
                numericText[i]=BigInteger.valueOf(input.charAt(i)-'A');
                System.out.print(numericText[i].intValue()+" ");
            }

            BigInteger cipher[]=new BigInteger[input.length()];
            StringBuilder sb=new StringBuilder();
            System.out.println("\nEncrypted Message:");
            for(int i=0;i<input.length();i++){
                cipher[i]=numericText[i].modPow(e,n);
                System.out.print(cipher[i].intValue()+" ");
                sb.append((char)(cipher[i].intValue()+'A'));
            }

            System.out.println();
            BigInteger decrypt[]=new BigInteger[input.length()];
            System.out.println("\ndecrypted Message:");

            StringBuilder sb1=new StringBuilder();
            for(int i=0;i<input.length();i++){
                decrypt[i]=cipher[i].modPow(d,n);
                System.out.print(decrypt[i].intValue()+" ");
                sb1.append((char)(decrypt[i].intValue()+'A'));
            }
            System.out.println("\n"+sb1);
        }



    }
}
