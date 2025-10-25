import java.util.*;
public class dHillCipher {

	
	public static void main(String []args) {
		Scanner sc = new Scanner(System.in);

        System.out.println("Enter plaintext:");
        String text = sc.nextLine();

        System.out.println("Enter matrix size (2 or 3):");
        int n = sc.nextInt();
        
        int[][]key=new int[n][n];
        System.out.println("Enter "+n+" elements per row for "+n+" times");
        for(int i=0;i<n;i++) {
        	for(int j=0;j<n;j++) {
        		key[i][j]=sc.nextInt();
        	}
        }
        String encrypted = encrypt(text, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("\nOriginal Text  : " + text.toUpperCase());
        System.out.println("Encrypted Text : " + encrypted);
        System.out.println("Decrypted Text : " + decrypted);

	}

	private static String decrypt(String encrypted, int[][] key) {
		return null;
	}

	private static String encrypt(String text, int[][] key) {
		text=text.toUpperCase().replaceAll("[^A-Z]","");
         int n=key.length;
         while(text.length()%n!=0) {
        	 text+="X";
         }
         StringBuilder cipherText = new StringBuilder();

        for(int i=0;i<text.length();i+=n) {
        	int []p=new int[n];
        	for(int j=0;j<n;j++) {
        		p[j]=text.charAt(i+j)-'A';
        	}
        	int [] c=new int[n];
        	for(int x=0;x<n;x++) {
        		for(int y=0;y<n;y++) {
        			c[x]+=key[x][y]*p[y];
        		}
        		c[x]%=26;
        		cipherText.append((char)(c[x]+'A'));
        	}
        }
		return cipherText.toString();
	}
}
