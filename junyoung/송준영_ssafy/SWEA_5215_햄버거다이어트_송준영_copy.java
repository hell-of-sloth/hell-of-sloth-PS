import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA 5215 햄버거 다이어트
 * 난이도 1/10
 * 조합
 * 146ms 26,600kb
 * 
 * 조건 있는 조합이다
 * 조합 하다가 L 넘으면 가지치기 해버리면 된다. -> 매우 심플
 * 조합하는 법만 알면 쉬운 문제
 */
public class SWEA_5215_햄버거다이어트_송준영_copy {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, L;        // 재료 수, 제한 칼로리
    static int[][] info;    // 재료 정보

    static int result;      // 결과 값

    public static void main(String[] args) throws Exception {
        int T = parseInt(br.readLine()); // 테스트 케이스 수 입력

        // 테스트 케이스 수만큼 반복
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, result));
        }

        // 출력
        System.out.println(sb);
    }
    
    /**
     * 메인 로직 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        L = parseInt(st.nextToken());

        info = new int[N][2]; // 재료 정보 배열
        result = 0; // 결과 값 초기화

        // 재료 정보 입력
        for (int i = 0; i < N; i++) {
            int val, cal;
            st = new StringTokenizer(br.readLine());
            val = parseInt(st.nextToken());
            cal = parseInt(st.nextToken());

            info[i][0] = val;
            info[i][1] = cal;
        }

        // 조합 실행
        comb(0, 0, 0);
    }

    /**
     * 조합 메서드
     * @param depth 조합 깊이
     * @param val   현재 맛 점수
     * @param cal   현재 칼로리
     */
    public static void comb(int depth, int val, int cal) {
        // 종료 조건
        if (depth == N) {
            result = Math.max(result, val);
            return;
        }

        // 분기 나누기 + 가지치기
        if (cal + info[depth][1] <= L) {
            comb(depth + 1, val + info[depth][0], cal + info[depth][1]);
        }
        comb(depth + 1, val, cal);
    }
}
