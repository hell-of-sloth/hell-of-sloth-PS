import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * SWEA 6782 현주가 좋아하는 제곱근 놀이
 * 난이도 4/10
 * 그리디?
 * 201ms 47mb
 * 
 * 제곱근을 구하는 문제 
 * 제곱근을 구하면서 제곱근이 정수인지 확인하고 아니라면 다음 제곱근으로 넘어가는 방식 -> 좀 더 빠르게
 * 가장 가까운 제곱근에서 아래로 내려가야 가장 빨리 2에 도달할 수 있다 -> 그리디 인듯?
 * 이 방법을 생각하는게 오래걸렸다
 */
public class SWEA_6782_현주가좋아하는제곱근놀이_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long N;  // 입력값
    static int cnt; // 결과값
    public static void main(String[] args) throws Exception {
        // 테스트케이스 처리
        int T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, cnt));
        }

        // 결과 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        N = Long.parseLong(br.readLine());  // 입력값 초기화
        cnt = 0;    // 결과값 초기화
        
        // 제곱근을 구하면서 2에 도달할 때까지 반복
        while (N != 2) {
            long sqrt = (long) Math.sqrt(N);
            if (sqrt * sqrt == N) { // 제곱근이 정수인 경우 -> 아니면 루트가 안됨
                N = sqrt;           // 제곱근으로 이동
                cnt++;              // 결과값 증가
            } else {
                long next = (sqrt + 1) * (sqrt + 1);    // 다음 제곱근
                cnt += next - N;                        // 다음 제곱근까지 이동하는 횟수 넣어주기
                N = next;                               // 다음 제곱근으로 이동
            }
        }
    }

    

}
