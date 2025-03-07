import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_20437_문자열게임2_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static char[] W;
    static int K;
    public static void main(String[] args) throws Exception {
        int T = parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            solve();
        }
        System.out.println(sb);
    }
    
    public static void solve() throws Exception {
        W = br.readLine().toCharArray();
        K = parseInt(br.readLine());

        int small = smallSlide();

        if (small == -1) {
            sb.append(-1 + "\n");
            return;
        }

        sb.append(small + " ");

        int large = largeSlide();

        sb.append(large + "\n");
    }

    public static int smallSlide() {
        int[] firstCheck = new int[26];
        int[] check;
        int slidesize = 1;

        while (slidesize <= W.length) {
            if (K == ++firstCheck[W[slidesize - 1] - 'a']) {
                return slidesize;
            }

            check = Arrays.copyOf(firstCheck, firstCheck.length);

            for (int i = 0; i < W.length - slidesize; i++) {
                check[W[i] - 'a']--;
                if (K == ++check[W[i + slidesize] - 'a']) {
                    return slidesize;
                }
            }

            slidesize++;
        }

        return -1;
    }

    public static int largeSlide() {
        int[] firstCheck = new int[26];
        int[] check;
        int slidesize = W.length;

        for (int i = 0; i < W.length; i++) {
            firstCheck[W[i] - 'a']++;
        }

        while (slidesize > 0) {

            check = Arrays.copyOf(firstCheck, firstCheck.length);

            if (K == firstCheck[W[slidesize - 1] - 'a']--) {
                return slidesize;
            }

            for (int i = 0; i < W.length - slidesize; i++) {
                check[W[i] - 'a']--;
                if (K == ++check[W[i + slidesize] - 'a']) {
                    return slidesize;
                }
            }

            slidesize--;
        }

        return -1;
    }
}
