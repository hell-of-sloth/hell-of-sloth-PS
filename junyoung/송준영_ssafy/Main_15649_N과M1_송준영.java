import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * 백준 15649 N과 M(1)
 * 난이도 2/10
 * 순열
 * 220ms 24,600 kb
 * 
 * 1부터 N까지의 수 중에서 중복 없이 M개를 고른 수열을 모두 구하는 문제
 * 백트래킹을 쓰면 된다!
 * 순열이므로 재귀함수 안에 for문이 들어간다.
 * 중복이 없으므로 visited 배열로 중복 처리를 해주어야 한다
 */
public class Main_15649_N과M1_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;            // N, M
    static boolean[] visited;   // 방문 여부

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        visited = new boolean[N + 1];

        // 순열 구하기 실행
        permutaion(0, new StringBuilder());

        // 출력
        System.out.println(sb);
    }

    /**
     * 순열 구하기
     * @param depth 현재 깊이
     * @param temp 현재까지의 순열
     */
    public static void permutaion(int depth, StringBuilder temp) {
        // 탈출 조건, M개 만큼 뽑으면 탈출
        if (depth == M) {
            sb.append(temp).append("\n");
            return;
        }
        
        // 임시 문자열 길이 저장, 다시 되돌리기 위해서
        int len = temp.length();

        // 1부터 N까지 순회
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp.append(i).append(" ");
                permutaion(depth + 1, temp);        // temp에 i를 추가하고 다음 depth로
                temp.setLength(len);                // 갔다왔으면 넣은거 빼기
                visited[i] = false;
            }
        }
    }
}
