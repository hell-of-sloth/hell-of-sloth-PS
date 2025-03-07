import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;

/**
 * Main_2839_설탕배달
 * 난이도 2/10
 * DP
 * 104ms 14mb
 * 
 * 설탕을 배달하는데 3kg, 5kg 봉지가 있다.
 * 최소한의 봉지를 사용하여 Nkg을 배달하는 방법을 구하는 문제
 * 길이 N의 배열을 만들어서 3kg, 5kg 봉지를 사용하는 방법을 저장한다.
 * 현재 무게에서 3, 5를 뺀 값 중 둘 다 사용하는 경우 더 작은 값을 저장한다.
 * 0이면 사용할 수 없는 무게이므로 -1 출력
 */
public class Main_2839_설탕배달_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static int N;       // 입력 변수 선언
    static int[] dp;    // DP 배열 선언

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        dp = new int[N + 1];
        dp[3] = 1;
        if (N >= 5) { // 5kg 봉지를 사용할 수 있는 경우
            dp[5] = 1;
        }
        
        // 6kg부터 Nkg까지 DP로 계산
        for (int i = 6; i <= N; i++) {
            if (dp[i - 3] != 0 && dp[i - 5] != 0) { // 3kg, 5kg 둘 다 사용하는 경우
                dp[i] = (dp[i - 3] < dp[i - 5]) ? dp[i - 3] + 1 : dp[i - 5] + 1;
            } else if (dp[i - 3] != 0) { // 3kg만 사용하는 경우
                dp[i] = dp[i - 3] + 1;
            } else if (dp[i - 5] != 0) { // 5kg만 사용하는 경우
                dp[i] = dp[i - 5] + 1;
            }
            // 그 외는 0이므로 0으로 남아있음
        }

        // 출력, 0이면 사용할 수 없는 무게이므로 -1 출력
        System.out.println(dp[N] != 0 ? dp[N] : -1);
    }
}
