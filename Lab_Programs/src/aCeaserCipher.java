import java.util.*;
public class aCeaserCipher {
	
	public static String encrypt(String text,int shift) {
		StringBuilder result=new StringBuilder();
		for(char c:text.toCharArray()) {
			if(Character.isUpperCase(c)) {
				char ch=(char)((c-'A'+shift)%26+'A');
				result.append(ch);
			}else if(Character.isLowerCase(c)) {
				char ch=(char)((c-'a'+shift)%26+'a');
				result.append(ch);
			}else {
				result.append(c);
			}
			
		}
		return result.toString();
	}
	public static String decrypt(String text,int shift) {
		return encrypt(text,26-(shift%26));
	}

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter text to encrypt:");
		String text=sc.nextLine();
		System.out.println("Enter shift value:");
		int shift=sc.nextInt();
		String encrypted=encrypt(text,shift);
	    String decrypted=decrypt(encrypted,shift);
	    System.out.println("original text:"+text);
	    System.out.println("Encrypted text:"+encrypted);
		System.out.println("decrypted text:"+decrypted);

	}

}
