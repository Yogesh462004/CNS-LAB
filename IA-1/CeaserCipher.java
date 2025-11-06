import java.util.*;
public class CeaserCipher {
 public static String encrypt(String text,int s){
    StringBuilder sb=new StringBuilder();
    for(char c:text.toCharArray()){
        if(Character.isLetter(c)){
          char base =Character.isUpperCase(c)?'A':'a';
          char ch=(char)((c-base+s)%26+base);
          sb.append(ch);
         }else sb.append(c);
      }
     return sb.toString();
}
    public static String decrypt(String encrypted,int s){
     return encrypt(encrypted,26-(s%26));
}
  public static void main(String[]a){
      Scanner sc=new Scanner(System.in);
       int s=3;
       System.out.println("Enter text to encrypt:");
      String text=sc.nextLine();
     String encrypted=encrypt(text,s);
     String decrypted=decrypt(encrypted,s);
       System.out.println("encrypted text:"+encrypted);
      System.out.println("decrypted text:"+decrypted);
    }
}
