import java.util.*;

public class PlayFair {

    static char[][] generateMatrix(String key) {
        key = key.toUpperCase().replace('J', 'I');
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[26];
        used['J' - 'A'] = true;

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (ch < 'A' || ch > 'Z') continue;  
            int idx = ch - 'A';
            if (!used[idx]) {
                used[idx] = true;
                sb.append(ch);
            }
        }
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            int idx = ch - 'A';
            if (!used[idx]) {
                used[idx] = true;
                sb.append(ch);
            }
        }
        char[][] matrix = new char[5][5];
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = sb.charAt(k++);
            }
        }
        return matrix;
    }

    static String prepareText(String text) {
        text = text.toUpperCase().replace('J', 'I');
        StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') sb.append(ch);
        }

        StringBuilder prepared = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            prepared.append(ch);
            if (i + 1 < sb.length() && sb.charAt(i + 1) == ch)
                prepared.append('X');
        }
        if (prepared.length() % 2 == 1)
            prepared.append('X');
        return prepared.toString();
    }

    static int[] pos(char[][] matrix, char ch) {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (matrix[r][c] == ch)
                    return new int[]{r, c};
            }
        }
        return null;
    }

    static String processPair(char[][] matrix, char a, char b, boolean encrypt) {
        int[] pa = pos(matrix, a);
        int[] pb = pos(matrix, b);
        int ra = pa[0], ca = pa[1];
        int rb = pb[0], cb = pb[1];

        if (ra == rb) {
            int s = encrypt ? 1 : 4;
            ca = (ca + s) % 5;
            cb = (cb + s) % 5;
        } else if (ca == cb) {
            int s = encrypt ? 1 : 4;
            ra = (ra + s) % 5;
            rb = (rb + s) % 5;
        } else {
            int temp = ca;
            ca = cb;
            cb = temp;
        }

        return "" + matrix[ra][ca] + matrix[rb][cb];
    }

    static String encrypt(char[][] matrix, String prepared) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < prepared.length(); i += 2) {
            char a = prepared.charAt(i);
            char b = prepared.charAt(i + 1);
            sb.append(processPair(matrix, a, b, true));
        }
        return sb.toString();
    }

    static String decrypt(char[][] matrix, String prepared) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < prepared.length(); i += 2) {
            char a = prepared.charAt(i);
            char b = prepared.charAt(i + 1);
            sb.append(processPair(matrix, a, b, false));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to encrypt:");
        String text = sc.nextLine();

        String key = "MONARCHY";
        char[][] matrix = generateMatrix(key);
        String prepared = prepareText(text);
        String cipher = encrypt(matrix, prepared);
        String decrypted = decrypt(matrix, cipher);

        System.out.println("\nMatrix:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nPrepared Text: " + prepared);
        System.out.println("Encrypted Text: " + cipher);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
