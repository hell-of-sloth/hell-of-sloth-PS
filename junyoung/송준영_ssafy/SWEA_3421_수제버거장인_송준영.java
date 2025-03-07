import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA 3421 수제버거장인
 * 난이도 4/10
 * 비트마스킹, 부분집합
 * 208ms -> 153ms 27,000 kb
 * 
 * N개의 재료로 만들 수 있는 버거의 개수를 구하는 문제
 * 재료들의 조합을 구하면 되는데 재료들 중에 서로 맞지 않는 것이 있으면 안된다.
 * 그래서 부분집합을 구하면서 맞지 않는 것이 있으면 넘어가는 방식으로 풀 수 있다.
 * 
 * 처음에는 Set을 이용해서 안 맞는 재료 확인 자료 구조를 만들었음 -> 수정후 int 배열로 비트마스킹으로 확인
 * 
 * 재귀함수에서 자기가 선택한 재료 확인시 배열이 아닌 int 값을 넘겨주고 비트마스킹으로 확인하며 진행 -> int 값을 넘겨주면 자동 복사가 되니 편하다, 배열은 참조형이라 깊은 복사가 안됨
 */
public class SWEA_3421_수제버거장인_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;                        // 재료의 수, 안 맞는 재료의 수
    // static List<Set<Integer>> unmatch;   // 안 맞는 재료 (Set으로 구현)
    static int[] unmatchBit;                // 안 맞는 재료 (int 배열, 비트마스킹으로 구현)

    static int result;                      // 결과 값

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 수 입력
        int T = parseInt(br.readLine());

        // 메인 로직 반복 및 각 테스트 케이스 결과 출력
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, result));
        }

        // 총 결과 출력
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
        M = parseInt(st.nextToken());
        result = 0;

        // unmatch = new ArrayList<>();         // 안 맞는 재료 리스트 초기화
        unmatchBit = new int[N + 1]; // 안 맞는 재료 비트마스킹 배열 초기화
        // for (int i = 0; i <= N; i++) {       // 안 맞는 재료 리스트 초기화
        //     unmatch.add(new HashSet<>());
        // }

        // 안 맞는 재료 입력
        for (int i = 0; i < M; i++) {
            int one, two;
            st = new StringTokenizer(br.readLine());
            one = parseInt(st.nextToken());
            two = parseInt(st.nextToken());

            // unmatch.get(one).add(two);
            // unmatch.get(two).add(one);
            unmatchBit[one] = unmatchBit[one] | 1 << two; // 비트마스킹으로 안 맞는 재료 저장
            unmatchBit[two] = unmatchBit[two] | 1 << one;
        }

        // 재료 선택 및 결과 계산
        backtrack(1, 0);
    }

    /**
     * 조건에 맞는 재료 조합을 찾는 메서드
     * @param depth     현재 depth : 재료의 번호
     * @param select    지금까지 선택된 재료 (비트마스킹 용)
     */
    public static void backtrack(int depth, int select) {
        // 재료를 다 탐색했을 때
        if (depth == N + 1) {
            result++;
            return;
        }

        boolean flag = false;   // 안 맞는 재료가 있는지 확인하는 플래그

        // for (int i = 1; i < depth; i++) {
        //     if ((select & 1 << i) != 0 && unmatch.get(depth).contains(i)) {
        //         flag = true;
        //         break;
        //     }
        // }

        // 안 맞는 재료 확인
        for (int i = 1; i < depth; i++) {
            // 전에 뽑았고 depth가 안 맞는 얘일때
            if ((select & 1 << i) != 0 && (unmatchBit[depth] & 1 << i) != 0) {
                flag = true;    // true 설정 후 break
                break;
            }
        }

        if (!flag) { // 안 맞는 재료가 있으면 못 넣어줌
            backtrack(depth + 1, select | 1 << depth);  // 재료를 넣어줌 -> 비트마스킹으로 표시
        }
        backtrack(depth + 1, select);   // 안 넣는 경우도 확인
    }
}
