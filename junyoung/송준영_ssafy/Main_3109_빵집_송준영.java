import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * Main_3109_빵집
 * 난이도 9/10
 * 그리디, DFS, 백트래킹?
 * 392ms, 49mb
 * 
 * 처음 시도 for문 DFS -> 시간초과
 * 두번째 시도 재귀 DFS -> 시간초과
 * 세번째 시도 int 배열 제거 -> 각 변수로 대입 -> 시간 초과
 * 네번째 시도 도착하면 마지막에 visit 처리 -> 시간초과
 * 
 * 결국 다른 사람 코드 참고하여 visited를 안 없애는 것이 핵심이라는 것을 알게됨
 * 왜? 안 없애도 되냐? 그리디 측면에서 보았을때 이전 파이프가 못 간 곳은 다음 파이프가 어차피 못감
 * 전형적인 그래프가 아니라 한 방향으로 가능 그래프라 가능한 듯
 * visited가 적층적으로 쌓인다
 * 그 후 가능한 케이스를 발견하면 그 즉시 모든 재귀를 종료시키는게 답
 * 
 * 아직 나는 갈 길이 멀다
 */
public class Main_3109_빵집_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int R, C;            // 행, 열
    static char[][] arr;        // 빵집
    static boolean[][] visited; // 방문 체크
    static int cnt = 0;         // 파이프 개수
    
    // 오른쪽 위, 오른쪽, 오른쪽 아래
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        R = parseInt(st.nextToken());
        C = parseInt(st.nextToken());

        arr = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        // DFS 시작, 시작 부분이 .이면 DFS 시작
        for (int i = 0; i < R; i++) {
            if (arr[i][0] == '.') {
                visited[i][0] = true;
                DFS(i, 0);
            }
        }

        // 결과 출력
        System.out.println(cnt);
    }
    
    /**
     * DFS 메서드
     * @param x 현재 x좌표
     * @param y 현재 y좌표
     * @return  도착하면 true, 아니면 false
     */
    public static boolean DFS(int x, int y) {
        // 도착하면 카운트 증가 후 true 반환
        if (y == C - 1) {
            cnt++;
            return true;
        }

        // 오른쪽 위, 오른쪽, 오른쪽 아래로 이동
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 내에 있고 방문하지 않았고 .이면
            if (isIn(nx, ny) && !visited[nx][ny] && arr[nx][ny] == '.') {
                visited[nx][ny] = true;
                if (DFS(nx, ny)) { // 도착하면 true 반환해서 재귀 즉시 종료
                    return true;
                }
            }
        }

        // 못 가면 false 반환
        return false;
    }

    /**
     * 범위 내에 있는지 확인하는 메서드
     * @param x 행
     * @param y 열
     * @return  범위 내에 있으면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < R && y < C && y >= 0;
    }
}
