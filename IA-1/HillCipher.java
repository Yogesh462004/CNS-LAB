import java.util.*;

public class HillCipher {
    static int modInverse(int a) {
        a = (a % 26 + 26) % 26;
        for (int i = 1; i < 26; i++)
            if ((a * i) % 26 == 1)
                return i;
        return -1;
    }

    static int det(int[][] A, int n) {
        if (n == 1) return A[0][0];
        int d = 0, s = 1;
        for (int i = 0; i < n; i++) {
            int[][] m = new int[n - 1][n - 1];
            for (int r = 1; r < n; r++) {
                int c2 = 0;
                for (int c = 0; c < n; c++)
                    if (c != i)
                        m[r - 1][c2++] = A[r][c];
            }
            d += s * A[0][i] * det(m, n - 1);
            s = -s;
        }
        return (d % 26 + 26) % 26;
    }

    static int[][] inv(int[][] A, int n) {
        if (n == 1) {
            int invNum = modInverse(A[0][0]);
            if (invNum == -1)
                throw new RuntimeException("Not invertible mod 26");
            return new int[][]{{invNum}};
        }

        int d = det(A, n), id = modInverse(d);
        if (id == -1)
            throw new RuntimeException("Not invertible mod 26");

        int[][] adj = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                int s = ((i + j) % 2 == 0) ? 1 : -1;
                int[][] m = new int[n - 1][n - 1];
                for (int r = 0; r < n; r++)
                    if (r != i)
                        for (int c = 0, cc = 0; c < n; c++)
                            if (c != j)
                                m[r < i ? r : r - 1][cc++] = A[r][c];
                adj[j][i] = (s * det(m, n - 1) * id) % 26;
                if (adj[j][i] < 0) adj[j][i] += 26;
            }
        return adj;
    }

    static int[] mul(int[][] K, int[] b) {
        int n = K.length, res[] = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                res[i] += K[i][j] * b[j];
            res[i] = (res[i] % 26 + 26) % 26;
        }
        return res;
    }

    static String process(String t, int[][] K) {
        t = t.toUpperCase().replaceAll("[^A-Z]", "");
        int n = K.length;
        while (t.length() % n != 0) t += 'X';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t.length(); i += n) {
            int[] b = new int[n];
            for (int j = 0; j < n; j++)
                b[j] = t.charAt(i + j) - 'A';
            int[] o = mul(K, b);
            for (int x : o)
                sb.append((char) (x + 'A'));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter matrix size (1-4): ");
        int n = sc.nextInt();

        int[][] K = new int[n][n];
        System.out.println("Enter key matrix (" + n + "x" + n + "):");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                K[i][j] = sc.nextInt() % 26;

        int determinant = det(K, n);
        if (modInverse(determinant) == -1) {
            System.out.println("\n Invalid key matrix! Determinant (" + determinant + ") has no inverse mod 26.");
            return;
        }

        sc.nextLine();
        System.out.print("Enter plaintext: ");
        String p = sc.nextLine();

        String e = process(p, K);
        System.out.println("\nEncrypted: " + e);

        String d = process(e, inv(K, n));
        System.out.println("Decrypted: " + d);
    }
}
