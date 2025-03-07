import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
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
 * 
 * NP 시 시간 6배 정도로 늘어남 -> 835ms
 */
public class SWEA_5215_햄버거다이어트_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, L;        // 재료 수, 제한 칼로리
    static int[][] info;    // 재료 정보

    static int result; // 결과 값
    static int[] arr;

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

        // NP로 모든 조합 생성후 찾기
        for (int i = 1; i <= N; i++) {
            arr = new int[N];

            // 1 ~ N 개 골랐을 때 정하기
            for (int j = 0; j < i; j++) {
                arr[j] = 1;
            }

            // 정렬 필수
            Arrays.sort(arr);

            // 각 케이스별 값 합과 칼로리 합 구해서 result에 추가
            do {
                int tempV = 0;
                int tempL = 0;
                for (int k = 0; k < N; k++) {
                    if (arr[k] == 1) {
                        tempV += info[k][0];
                        tempL += info[k][1];
                    }
                }
                if (tempL <= L) {
                    result = Math.max(result, tempV);
                }
            } while (np()); // 다음 순열이 있으면 반복
        }
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

    /**
     * 다음 순열을 구하는 메서드
     * @return  다음 순열이 있으면 true, 없으면 false
     */
    public static boolean np() {

        // 1. 꼭대기 찾기
        int i = N - 1;
        while (i > 0 && arr[i - 1] >= arr[i])
            --i;

        // 마지막 순열이면 종료
        if (i == 0)
            return false;

        // 2. 교환할 값 찾기
        int j = N - 1;
        while (arr[i - 1] >= arr[j])
            --j;

        // 3. 교환
        swap(i - 1, j);

        // 4. 교환 후 다음 자리들 내림차순 정렬
        int k = N - 1;
        while (i < k)
            swap(i++, k--);

        return true;
    }

    /**
     * 두 인덱스의 값을 교환하는 메서드
     * @param x
     * @param y
     */
    public static void swap(int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
