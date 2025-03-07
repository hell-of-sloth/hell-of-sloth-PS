import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Main_2748_피보나치수2
 * 난이도 1/10
 * DP
 * 104ms 14,100kb
 * 
 * 피보나치 수열을 DP로 풀어보는 문제
 * 마지막 값(90번째) 까지 가면 int 범위를 넘어가기 때문에 long으로 선언해주어야 한다
 * 그것만 주의하면 풀리는 문제
 */
public class Main_2748_피보나치수2_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;       // 입력값
    static long[] pibo; // 피보나치 수열 배열
    public static void main(String[] args) throws Exception {
        // 입력 처리및 초기화
        n = Integer.parseInt(br.readLine());
        pibo = new long[n+1];
        pibo[0] = 0;
        pibo[1] = 1;

        // DP로 피보나치 수열 구하기
        for (int i = 2; i <= n; i++) {
            pibo[i] = pibo[i-1] + pibo[i-2];
        }

        // 출력
        System.out.println(pibo[n]);
    }
}
