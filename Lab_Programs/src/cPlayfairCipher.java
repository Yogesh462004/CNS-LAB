import java.util.*;

public class cPlayfairCipher {
    private static char[][] matrix = new char[5][5];

	public static void main(String args[] ){
		
		  Scanner sc = new Scanner(System.in);
	        System.out.println("Enter text to encrypt:");
	        String text = sc.nextLine().trim();

	        System.out.println("Enter key:");
	        String key = sc.nextLine().trim();

	        String encrypted = encrypt(text, key);
	        String decrypted = decrypt(encrypted, key);

	        System.out.println("\nOriginal Text  : " + text);
	        System.out.println("Encrypted Text : " + encrypted);
	        System.out.println("Decrypted Text : " + decrypted);

		
	}

	private static String decrypt(String ciphertext, String key) {
		 generateMatrix(key);
	     StringBuilder plainText = new StringBuilder();
	     for (int i = 0; i < ciphertext.length(); i += 2) {
	            char a = ciphertext.charAt(i);
	            char b = ciphertext.charAt(i + 1);
	            int[] posA = findPosition(a);
	            int[] posB = findPosition(b);

	            if (posA[0] == posB[0]) { // Same row
	                plainText.append(matrix[posA[0]][(posA[1] + 4) % 5]);
	                plainText.append(matrix[posB[0]][(posB[1] + 4) % 5]);
	            } else if (posA[1] == posB[1]) { // Same column
	                plainText.append(matrix[(posA[0] + 4) % 5][posA[1]]);
	                plainText.append(matrix[(posB[0] + 4) % 5][posB[1]]);
	            } else { // Rectangle
	                plainText.append(matrix[posA[0]][posB[1]]);
	                plainText.append(matrix[posB[0]][posA[1]]);
	            }
	        }
	     
	     String decrypted = plainText.toString();

	     //  Remove X between duplicate letters
	     decrypted = decrypted.replaceAll("(?<=.)(X)(?=\\1)", "");
	     // Remove trailing X if it was added as padding
	     if (decrypted.endsWith("X")) {
	         decrypted = decrypted.substring(0, decrypted.length() - 1);
	     }

	     return decrypted;
	}

	private static String encrypt(String plaintext, String key) {
		
		generateMatrix(key);
		String text=prepareText(plaintext);
		
		StringBuilder cipherText=new StringBuilder();
		for(int i=0;i<text.length();i+=2) {
			char a=text.charAt(i);
			char b=text.charAt(i+1);
			int []posA=findPosition(a);
			int []posB=findPosition(b);
			
			if(posA[0]==posB[0]) {
				cipherText.append(matrix[posA[0]][(posA[1] + 1) % 5]);
                cipherText.append(matrix[posB[0]][(posB[1] + 1) % 5]);			
           }
			 else if (posA[1] == posB[1]) { // Same column
	                cipherText.append(matrix[(posA[0] + 1) % 5][posA[1]]);
	                cipherText.append(matrix[(posB[0] + 1) % 5][posB[1]]);
	            } else { // Rectangle
	                cipherText.append(matrix[posA[0]][posB[1]]);
	                cipherText.append(matrix[posB[0]][posA[1]]);
	            }
		}
        return cipherText.toString();

	}

	private static int[] findPosition(char c) {
        if (c == 'J') c = 'I';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }


	private static String prepareText(String text) {
		
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(text.charAt(i));
            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1)) {
                result.append('X');
            }
        }
        if (result.length() % 2 != 0) {
            result.append('X');
        }

        return result.toString();

	}

	private static void generateMatrix(String key) {
		String preparedKey=prepareKey(key);
		int index=0;
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				matrix[i][j]=preparedKey.charAt(index++);
			}
		}
		 System.out.println("\nGenerated Playfair Cipher Matrix:");
	        for (int i = 0; i < 5; i++) {
	            for (int j = 0; j < 5; j++) {
	                System.out.print(matrix[i][j] + " ");
	            }
	            System.out.println();
	        }
	}

	private static String prepareKey(String key) {
		 key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");	
		 StringBuilder result=new StringBuilder();
		Set<Character>seen=new HashSet<>();
		for(char ch:key.toCharArray()) {
			if(!seen.contains(ch)) {
				seen.add(ch);
				result.append(ch);
			}
		}
		
		for(char c='A';c<='Z';c++) {
			if(c=='J') continue;
			 if (!seen.contains(c)) {
	                seen.add(c);
	                result.append(c);
	         }
			
		}
		return result.toString();	
		
	}

}
