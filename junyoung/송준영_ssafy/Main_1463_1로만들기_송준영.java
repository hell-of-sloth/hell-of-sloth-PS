import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

public class Main_1463_1로만들기_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        N = parseInt(br.readLine());
        dp = new int[N+1];

        for (int i = 0; i < N+1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[1] = 0;

        for (int i = 1; i <= N; i++) {
            if (i * 3 <= N) {
                dp[i * 3] = Math.min(dp[i] + 1, dp[i * 3]);
            }
            if (i * 2 <= N) {
                dp[i * 2] = Math.min(dp[i] + 1, dp[i * 2]);
            }
            if (i + 1 <= N) {
                dp[i+1] = Math.min(dp[i] + 1, dp[i+1]);
            }
        }

        System.out.println(dp[N]);
    }
}
