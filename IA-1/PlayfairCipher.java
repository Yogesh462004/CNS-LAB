import java.util.*;
public class PlayfairCipher{
static char[][] generateMatrix(String key){
 key=key.toUpperCase().replace('J','I');
 boolean used[]=new boolean[26];
 used['J'-'A']=true;
 StringBuilder sb=new StringBuilder();
 for(char ch:key.toCharArray()){
   if(ch<'A'||ch>'Z')continue;
   int index=ch-'A';
   if(!used[index]){
   used[index]=true;
   sb.append(ch);
   }
 }
 for(char c='A';c<='Z';c++){
   int index=c-'A';
   if(!used[index]){
   used[index]=true;
   sb.append(c);
   }
 }
 char [][]matrix=new char[5][5];
 int k=0;
 for(int r=0;r<5;r++){
 for(int c=0;c<5;c++){
 matrix[r][c]=sb.charAt(k++);

 }
 }
return matrix;
}
static String preparePlainText(String text){
text=text.toUpperCase().replace('J','I');
StringBuilder sb=new StringBuilder();
for(char ch:text.toCharArray()){
if(ch>='A' && ch<='Z') sb.append(ch);

}
StringBuilder prepared=new StringBuilder();
for(int i=0;i<sb.length();i++){
char a=sb.charAt(i);
prepared.append(a);
if(i+1<sb.length()&&sb.charAt(i+1)==a) prepared.append('X');

}
if(prepared.length()%2==1)prepared.append('X');
return prepared.toString();
}
static int[] findPos(char [][] matrix,char c1){
for(int r=0;r<5;r++){
for(int c=0;c<5;c++){
if(matrix[r][c]==c1) return new int[]{r,c};
}

}
return null;
}
static String processPair(char[][] matrix,char a,char b,boolean encrypt){
int [] pa=findPos(matrix,a);
int [] pb=findPos(matrix,b);
int ra=pa[0],ca=pa[1];
int rb=pb[0],cb=pb[1];
if(ra==rb) {
int s=encrypt?1:4;
ca=(ca+s)%5;
cb=(cb+s)%5;
}else if(ca==cb){
int s=encrypt?1:4;
ra=(ra+s)%5;
rb=(rb+s)%5;
}else{
int temp=ca;ca=cb;cb=temp;
}
return ""+matrix[ra][ca]+matrix[rb][cb];
}
static String encrypt(char [][] matrix,String prepared){
StringBuilder out=new StringBuilder();
for(int i=0;i<prepared.length();i+=2){
char a=prepared.charAt(i);
char b=prepared.charAt(i+1);
out.append(processPair(matrix,a,b,true));
}
return out.toString();
}
static String decrypt(char[][] matrix,String cipher){
StringBuilder out=new StringBuilder();
for(int i=0;i<cipher.length();i+=2){
char a=cipher.charAt(i);
char b=cipher.charAt(i+1);
out.append(processPair(matrix,a,b,false));
}
StringBuilder decrypted=out.toString();
decrypted = decrypted.replaceAll("(?<=.)(X)(?=\\1)", "");
	     // Remove trailing X if it was added as padding
	     if (decrypted.endsWith("X")) {
	         decrypted = decrypted.substring(0, decrypted.length() - 1);
	     }

	     return decrypted;
}
public static void main(String[]a){
Scanner sc=new Scanner(System.in);
System.out.println("Enter text to encrypt");
String text=sc.nextLine();
String key="MONARCHY";
char[][]matrix=generateMatrix(key);
String prepared=preparePlainText(text);
String cipher=encrypt(matrix,prepared);
String decrypted=decrypt(matrix,cipher);
for(int i=0;i<5;i++){
for(int j=0;j<5;j++){
System.out.print(matrix[i][j]+" ");

}
System.out.println();
}
System.out.println(cipher);
System.out.println(decrypted);
}
}
