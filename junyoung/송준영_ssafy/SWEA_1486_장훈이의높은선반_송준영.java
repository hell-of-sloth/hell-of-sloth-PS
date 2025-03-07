import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA_1486_장훈이의높은선반
 * 난이도 3/10
 * 부분집합
 * 87ms 26,000kb
 * 
 * 처음에 그리디인줄 알았다가 계산 해보니까 어차피 다 조사해야할 것 같아서 부분집합으로 풀었음
 * N 이 20이라서 가능한 것 같다. (처음에 N숫자를 잘 못 보고 그리디로 접근) -> 문제 좀 잘 읽자....
 * 조건을 넘길때마다 result를 갱신해주는 방법을 사용했다
 */
public class SWEA_1486_장훈이의높은선반_송준영 {
    
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, B;            // N: 점원 수, B: 선반 높이
    static Integer[] heights;   // 점원 키 배열
    static int result;          // 결과값

    public static void main(String[] args) throws Exception {
        int T = parseInt(br.readLine());    // 테스트 케이스 수

        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, result - B));    // 선반 높이를 뺀 값 출력
        }

        System.out.println(sb);             // 결과 출력
    }

    /**
     * 메인 로직 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        B = parseInt(st.nextToken());

        // 배열 입력 및 초기화
        heights = new Integer[N];
        result = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = (Integer) parseInt(st.nextToken());
        }

        // Arrays.sort(heights, Collections.reverseOrder());

        // 부분집합 실행
        backtrack(0, 0);
    }

    /**
     * 부분집합을 이용한 백트래킹
     * @param depth 현재 조사하고 있는 직원 인덱스
     * @param sum   현재까지의 키 합
     */
    public static void backtrack(int depth, int sum) {
        // 끝까지 가면 탈출
        if (depth == N) {
            return;
        }

        // 현재 직원을 포함한 경우
        int nextSum = sum + heights[depth];

        if (nextSum < B) {  // 선반 높이를 넘지 않으면 다음 직원으로
            backtrack(depth + 1, nextSum);
        } else {            // 선반 높이를 넘으면 결과값 갱신
            result = Math.min(result, nextSum);
        }
        
        // 현재 직원을 포함하지 않은 경우
        backtrack(depth + 1, sum);
    }
}
