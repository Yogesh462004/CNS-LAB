import java.util.*;

class E1 {
    static String encrypt(String text, int key) {
        text = text.replaceAll("\\s+", "");
        int n = text.length();
        char[][] rail = new char[key][n];
        for (char[] row : rail) Arrays.fill(row, '\n');
        boolean down = false;
        int row = 0;
        for (int i = 0; i < n; i++) {
            rail[row][i] = text.charAt(i);
            if (row == 0 || row == key - 1) down = !down;
            row += down ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < n; j++) {
                if (rail[i][j] != '\n') result.append(rail[i][j]);
            }
        }
        return result.toString();
    }

    static String decrypt(String text, int key) {
        text = text.replaceAll("\\s+", "");
        int n = text.length();
        char[][] rail = new char[key][n];
        for (char[] row : rail) Arrays.fill(row, '\n');
        boolean down = false;
        int row = 0;
        for (int i = 0; i < n; i++) {
            rail[row][i] = '*';
            if (row == 0 || row == key - 1) down = !down;
            row += down ? 1 : -1;
        }
        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < n; j++) {
                if (rail[i][j] == '*' && index < n) rail[i][j] = text.charAt(index++);
            }
        }
        StringBuilder result = new StringBuilder();
        row = 0;
        down = false;
        for (int i = 0; i < n; i++) {
            result.append(rail[row][i]);
            if (row == 0 || row == key - 1) down = !down;
            row += down ? 1 : -1;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plaintext: ");
        String text = sc.nextLine();
        int key = 2;
        String encrypted = encrypt(text, key);
        String decrypted = decrypt(encrypted, key);
        System.out.println("\nEncrypted text: " + encrypted);
        System.out.println("Decrypted text: " + decrypted);
    }
}
