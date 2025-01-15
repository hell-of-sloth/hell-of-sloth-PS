// 체감 난이도 5/10 dp
// DP 생각 안 나면 못 품 148ms

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int lastSqrt = (int)Math.sqrt(N);

        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= lastSqrt; i++) {
            dp[i * i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            if (dp[i] == 1) {
                continue;
            }

            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[j * j] + dp[i - j * j]);
            }
        }
        System.out.println(dp[N]);
    }
}
