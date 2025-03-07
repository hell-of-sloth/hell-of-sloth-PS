import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA 6808. 규영이와 인영이의 카드게임
 * 난이도 3/10
 * 1733 ms 약 27,000 kb
 * 백트래킹, 순열?
 * 
 * 규영이의 카드 순서는 이미 정해져 있으므로 인영이의 카드의 순서를 다 조합해보면 된다 -> 순열
 * 각 카드를 정할 때 그 카드를 규영이와 비교해서 점수를 그때 그때 갱신해준다. 
 * -> 분기 마다 점수가 바뀌므로 매개변수로 넘겨주는 것이 편함(개인적으로는)
 * 그 후 모은 카드의 배열이 끝나면 그때 점수 총합을 비교하여 승리, 패배 횟수를 증가시킨다.
 */
public class SWEA_6808_규영이와인영이의카드게임_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] nums;          // 규영이가 가지고 있는 카드
    static int[] othernums;     // 인영이가 가지고 있는 카드
    static boolean[] visited;   // 방문 체크
    static int win;             // 규영이가 이긴 횟수
    static int lose;            // 규영이가 진 횟수

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 수 입력
        int T = parseInt(br.readLine());

        // 메인 로직 반복
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d %d\n", t, win, lose));
        }

        // 출력
        System.out.println(sb);
    }
    
    // 메인 메서드
    public static void solve() throws Exception {
        // static 변수 초기화
        // 초기화를 잘 해주어야 오류 안 남
        nums = new int[9];
        othernums = new int[9];
        visited = new boolean[9];
        win = 0;
        lose = 0;

        // 규영이가 뽑은 카드를 체크할 배열
        boolean[] numCheck = new boolean[19];

        // 규영이가 뽑은 카드 저장 및 체크
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 9; i++) {
            nums[i] = parseInt(st.nextToken());
            numCheck[nums[i]] = true;
        }

        // 인영이가 가지고 있는 카드 저장
        int othernumsIdx = 0; // 인영이 카드 인덱스
        for (int i = 1; i < 19; i++) {
            // 규영이가 가지고 있지 않은 카드를 저장
            if (!numCheck[i]) {
                othernums[othernumsIdx++] = i;
            }
        }

        // 백트래킹 실행
        backtrack(0, 0, 0);

        // System.out.println(Arrays.toString(nums));
        // System.out.println(Arrays.toString(othernums));
    }

    /**
     * 백트래킹 메서드
     * @param depth 현재 뽑은 카드 수
     * @param gyu   지금까지 뽑은 것 중 규영이 점수
     * @param inn   지금까지 뽑은 것 중 인영이 점수
     */
    public static void backtrack(int depth, int gyu, int inn) {
        // 종료 조건
        if (depth == 9) {
            // 규영이의 점수가 더 높으면
            if (gyu > inn) {
                win++; // 이긴 횟수 증가
            } else if (gyu < inn) { // 아니면
                lose++; // 진 횟수 증가
            }
            return;
        }

        // 9개의 카드 중 하나를 뽑음
        for (int i = 0; i < 9; i++) {
            // 방문하지 않은 카드라면
            if (!visited[i]) {
                visited[i] = true;
                if (nums[depth] > othernums[i]) {   // 규영이가 뽑은 카드가 더 크면, 규영이 점수 업
                    backtrack(depth + 1, gyu + nums[depth] + othernums[i], inn);
                } else {                            // 인영이가 뽑은 카드가 더 크면, 인영이 점수 업
                    backtrack(depth + 1, gyu, inn + nums[depth] + othernums[i]);
                }
                visited[i] = false;
            }
        }
    }    
}
