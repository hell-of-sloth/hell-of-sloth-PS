import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <풀기전 예측>
 * 이미 벽에 닿은 프로세서는 포함 안하기
 * 처음 프로세서 부터 백트래킹
 * 각 백트래킹 프로세스는 4방향 탐색 적용 -> visited 배열 이용
 * 4방향 다 안되는 상황 발생하면 뒤로 가기 == return
 * 여러 방법 경우 최소의 경우니까 min 스태틱 설정해서 될때마다 갱신
 * 
 * Solution_1767_프로세서연결하기
 * 난이도 28/10
 * 백트래킹
 * 
 * 푸는데 걸린시간 한 2 ~ 2시간 반? 정도
 * 첫 시도 -> 반례 발견 -> 해결위해 순열로 반복하려고 해봄
 * 두 번째 시도 -> 답은 맞지만 시간초과 -> 중복이 너무많음 -> 다시 조합으로 + 가지치기 + 안가는 경우도 추가
 * 세 번째 시도 ->반례 다 되는 데 왜 안되지? -> minWire, maxProc 테케마다 초기화 안 해줌... 이거 찾느라 고생함
 * -> 결국 통과
 * 
 * 풀이 방법
 * 1. 프로세서 리스트에 가장자리에 있는 프로세서는 추가하지 않고, 나머지 프로세서는 추가
 * 2. 백트래킹을 통해 각 프로세서를 선택하거나 ☆선택하지 않는 경우☆를 모두 탐색
 * 3. 선택한 프로세서는 4방향으로 전선을 놓아보고, 전선을 놓을 수 있는 경우에만 전선을 놓고 다음 프로세서로 넘어감
 * 4. 전선을 놓을 수 없는 경우에는 다음 프로세서로 넘어감
 * 5. 프로세서를 가장 많이 넣는 경우만 최소 전선의 길이를 갱신, 만약 같다면 적은 길이로 변경
 * 6. ☆☆☆ 시간을 줄이기 위해 앞으로 모든 프로세서를 넣어도 maxProc보다 작으면 취소하고 가지치기
 */
public class Solution_1767_프로세서연결하기 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // static 변수 선언
    static int N;
    static int minWire, maxProc;

    static int[][] map;
    static boolean[][] visited;
    static List<int[]> processors;

    // 방향 벡터 설정
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 입력
        int T = parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            solve();                                                   // 테스트 케이스마다 풀이
            sb.append(String.format("#%d %d\n", t, minWire));   // 빠른 출력을 위해 sb에 저장
        }
        System.out.println(sb); // 출력
    }

    /**
     * 테스트 케이스 풀이 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        N = parseInt(br.readLine().trim()); // 입력

        map = new int[N][N];                // 맵
        visited = new boolean[N][N];        // 와이어 or 프로세서 있는지 확인
        processors = new ArrayList<>();     // 프로세서 리스트 (가장자리 제외)
        minWire = Integer.MAX_VALUE;        // 최소 전선 길이 == 답
        maxProc = 0;                        // 놓았을 때 최대 프로세서 개수

        // 맵 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        // 프로세서 리스트에 추가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    if (!isSide(N, i, j))                   // 가장자리에 있는 프로세서는 추가하지 않음
                        processors.add(new int[] { i, j }); // 가장자리에 있으면 방문만, 없으면 프로세서 리스트에 추가
                    visited[i][j] = true;
                }
            }
        }

        // 백트래킹
        backtrack(0, 0, 0);
    }

    /**
     * 백트래킹 메서드
     * 
     * @param depth  현재 프로세서 인덱스
     * @param select 선택한 프로세서 개수
     * @param cable  전선 길이
     */
    public static void backtrack(int depth, int select, int cable) {
        // 종료 조건
        if (depth == processors.size()) {       // 모든 프로세서를 다 돌았을 때
            if (select > maxProc) {             // 선택한 프로세서가 maxProc보다 많으면 갱신
                maxProc = select;
                minWire = cable;
            } else if (select == maxProc) {     // 같으면 최소 전선 길이로 갱신
                minWire = Math.min(minWire, cable);
            }
            return;
        }

        // ☆☆남은 프로세서를 다 더해도 maxProc보다 작으면 취소 -> 시간을 줄이기 위해서☆☆
        if (select + processors.size() - depth < maxProc) {
            return;
        }

        // 현재 프로세서 좌표
        int x = processors.get(depth)[0];
        int y = processors.get(depth)[1];

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx, ny;
            int tempCable = 1; // 전선 길이

            // 전선 놓기
            while (true) {
                nx = x + dx[i] * tempCable;
                ny = y + dy[i] * tempCable;

                if (!isIn(N, nx, ny)) {                 // 범위를 벗어나면 => 전선을 놓을 수 있는 경우
                    backtrack(depth + 1, select + 1, cable + tempCable - 1);
                    break;
                } else if (visited[nx][ny]) {           // 이미 전선이나 프로세서가 있으면 => 전선을 놓을 수 없는 경우
                    break;
                }
                visited[nx][ny] = true;                 // 전선 놓기
                tempCable++;                            // 전선 길이 증가
            }

            // 체크한 전선 지우기
            for (int j = 1; j < tempCable; j++) {
                nx = x + dx[i] * j;
                ny = y + dy[i] * j;
                visited[nx][ny] = false;
            }
        }

        // 해당 프로세서를 선택하지 않는 경우도 탐색 -> depth 증가, select, cable 그대로
        backtrack(depth + 1, select, cable);
    }

    /**
     * 범위 체크 메서드
     * 
     * @param N 맵의 크기
     * @param x x좌표
     * @param y y좌표
     * @return 범위 안이면 true
     */
    public static boolean isIn(int N, int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    /**
     * 가장자리에 있는지 확인
     * 
     * @param N 맵의 크기
     * @param x x좌표
     * @param y y좌표
     * @return 있으면 true
     */
    public static boolean isSide(int N, int x, int y) {
        int nx, ny;

        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (!isIn(N, nx, ny))
                return true;
        }
        return false;
    }
}
