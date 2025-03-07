import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA_1861_정사각형방
 * 난이도 4/10
 * BFS
 * 231ms 42mb
 * 
 * 정사각형 방에서 이동할 수 있는 방의 개수와 시작 방의 번호를 출력하는 문제
 * 작은 수부터 검사해서 이동 거리의 최대를 갱신해준다
 * 작은 수부터 가서 visited를 처리해 주므로 거기에 이어진 수들은 안 봐도 된다. -> 총 시간  N * N
 */
public class SWEA_1861_정사각형방_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;                       // 방의 크기
    static int[][] arr;                 // 방의 정보
    static Map<Integer, int[]> points;  // 방의 인덱스를 저장할 맵
    static boolean[][] visited;         // 방문 여부
    static int resultIdx;               // 결과 방의 번호
    static int result;                  // 결과 이동 거리

    // 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 수 입력
        int T = parseInt(br.readLine());

        // 테스트 케이스 수만큼 반복
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d %d\n", t, resultIdx, result));
        }

        // 결과 출력
        System.out.println(sb);
    }
    
    /**
     * 메인 로직 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        arr = new int[N][N];
        points = new HashMap<>();
        visited = new boolean[N][N];
        resultIdx = 0;
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = parseInt(st.nextToken());
                points.put(arr[i][j], new int[] { i, j });
            }
        }

        // BFS 탐색
        BFS();
    }

    /**
     * BFS 탐색 메서드, 1 ~ N * N 까지 방을 탐색하면서 이동 거리를 계산한다
     */
    public static void BFS() {
        // 1 ~ N * N 까지 방을 탐색, 이미 본 방이면 넘어감
        for (int i = 1; i <= N * N; i++) {
            int[] start = points.get(i); // 시작 방의 좌표

            // System.out.println("start! " + i);

            if (visited[start[0]][start[1]] == true) // 이미 방문한 방이면 넘어감
                continue;

            // System.out.println("get! " + i);

            int moves = 0; // 이동 거리 초기화
            Queue<int[]> q = new ArrayDeque<>(); // BFS 큐

            // 처음 노드 처리
            q.offer(start);
            visited[start[0]][start[1]] = true;
            moves++;

            // BFS 탐색
            while (!q.isEmpty()) {
                int[] temp = q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = temp[0] + dx[j], ny = temp[1] + dy[j];

                    // 범위 밖에 안 나가고, 방문하지 않았으며, 지금보다 1 크면 -> 이동가능
                    if (isIn(nx, ny) && !visited[nx][ny] && (arr[nx][ny] == arr[temp[0]][temp[1]] + 1)) {
                        visited[nx][ny] = true;
                        moves++;
                        q.offer(new int[] { nx, ny });
                    }
                }
            }

            // 이동 거리가 더 크면 갱신
            if (result < moves) {
                resultIdx = i;
                result = moves;
            }
        }
    }

    /**
     * 범위 안에 있는지 확인하는 메서드
     * @param x 행
     * @param y 열
     * @return  범위 안에 있으면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
