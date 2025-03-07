import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int[] alpha = new int[26];

    public static void main(String[] args) throws Exception {
        char[] temp = br.readLine().toCharArray();
        for (char c : temp) {
            alpha[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            sb.append(alpha[i]).append(" ");
        }

        System.out.println(sb);
    }

    
}
