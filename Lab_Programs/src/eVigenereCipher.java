import java.util.*;
public class eVigenereCipher {
	 public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        System.out.print("Enter plaintext: ");
	        String plaintext = sc.nextLine();

	        System.out.print("Enter key: ");
	        String key = sc.nextLine();

	        String encrypted = encrypt(plaintext, key);
	        String decrypted = decrypt(encrypted, key);

	        System.out.println("\nPlaintext : " + plaintext);
	        System.out.println("Encrypted : " + encrypted);
	        System.out.println("Decrypted : " + decrypted);
	    }

	 private static String decrypt(String encrypted, String key) {
		 int keyIndex=0;
	     key = key.toUpperCase();
	     StringBuilder result = new StringBuilder();
	     for(char c:encrypted.toCharArray()) {
	    	 if(Character.isLetter(c)) {
	    		 char base=Character.isUpperCase(c)?'A':'a';
	    		 int shift=key.charAt(keyIndex%key.length())-'A';
	    		 char decryptedChar=(char)((c-base-shift+26)%26+base);
	    		 result.append(decryptedChar);
	                keyIndex++;
	            } else {
	                result.append(c); 
	            }
	        }
	        return result.toString();
	}

	 private static String encrypt(String plaintext, String key) {
		 int keyIndex=0;
	     key = key.toUpperCase();
	     StringBuilder result = new StringBuilder();
	     for(char c:plaintext.toCharArray()) {
	    	 if(Character.isLetter(c)) {
	    		 char base=Character.isUpperCase(c)?'A':'a';
	    		 int shift=key.charAt(keyIndex%key.length())-'A';
	    		 char encryptedChar=(char)((c-base+shift)%26+base);
	    		 result.append(encryptedChar);
	                keyIndex++;
	            } else {
	                result.append(c); 
	            }
	        }
	        return result.toString();
	     }
	 	 
}
