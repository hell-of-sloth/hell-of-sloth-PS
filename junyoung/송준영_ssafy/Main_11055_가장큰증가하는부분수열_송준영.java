import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
// import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Main_11055_가장큰증가하는부분수열
 * 난이도 4/10
 * DP
 * 
 * DP는 여전히 어려운 것 같다
 * 이 문제는 그래도 상대적으로 쉬운 DP로 입력값과 제한시간이 2중 for문으로 충분히 풀 수 있었다
 * 현재 값이랑 이전값들을 비교하면서 현재 값보다 이전 값이 작으면 그 값의 DP를 보아 현재 DP와 이전 DP에 현재 값을 더한 값을 비교하여 DP를 채우면 된다.
 */
public class Main_11055_가장큰증가하는부분수열_송준영 {
    // 빠른 입출력을 위한 BufferedReader, StringTokenizer, StringBuilder
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;           // 수열의 길이
    static int[] arr;       // 수열
    static int[] dp;        // DP 배열
    static int result = 0;  // 결과값

    public static void main(String[] args) throws Exception {
        // 입력 및 초기화 처리
        N = parseInt(br.readLine());
        arr = new int[N+1];
        dp = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        // DP 로직
        dp[0] = arr[0];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {  // 현재 값이 이전 값보다 크면
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);    // 현재 DP와 이전 DP에 현재 값을 더한 값 중 큰 값을 DP에 넣는다
                }
            }
            // 결과값 갱신
            result = Math.max(result, dp[i]);
        }

        // System.out.println(Arrays.toString(dp));
        // 출력
        System.out.println(result);
    }
}
