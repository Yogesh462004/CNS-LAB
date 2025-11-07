import java.util.*;
public class VigenereCipher {

static  String encrypt(String text,String key){
    key=key.toUpperCase();
    int keyIndex=0;
    StringBuilder sb=new StringBuilder();
    for(char c:text.toCharArray()){
        if(Character.isLetter(c)){
            char base=Character.isUpperCase(c)?'A':'a';
            char shift = (char) (key.charAt(keyIndex % key.length()) - 'A');
            char ch=(char)((c-base+shift+26)%26+base);
            sb.append(ch);
            keyIndex++;
        }else sb.append(c);
    }

    return sb.toString();
}
    static  String decrypt(String text,String key){
        key=key.toUpperCase();
        int keyIndex=0;
        StringBuilder sb=new StringBuilder();
        for(char c:text.toCharArray()){
            if(Character.isLetter(c)){
                char base=Character.isUpperCase(c)?'A':'a';
                char shift = (char) (key.charAt(keyIndex % key.length()) - 'A');
                char ch=(char)((c-base-shift+26)%26+base);
                sb.append(ch);
                keyIndex++;
            }else sb.append(c);
        }

        return sb.toString();
    }

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

}
