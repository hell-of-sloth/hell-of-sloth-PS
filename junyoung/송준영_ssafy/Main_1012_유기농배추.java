import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_1012_유기농배추
 * 난이도 3/10
 * BFS
 * 136ms
 * 
 * 테스트케이스 별로 처리하는 것과 맵을 다 돌면서 BFS를 하며 BFS 된 횟수를 구하면 그게 답이 된다
 * 테스트케이스별 문제풀이 메서드를 따로 분리해 주고 BFS메서드도 분리해 주면서 모듈화를 적용해 주었다 -> 유지보수(?) 편리
 * visited 처리와 범위, 배추 처리만 잘 해주면 성공이다
 */
public class Main_1012_유기농배추 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // 테케 수, 방향 벡터
    static int T;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    // 맵 크기, 배추 수, 맵, 방문 배열 -> 초기화 유의
    static int M, N, K;
    static boolean[][] farm;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        // 테케 수 입력
        T = parseInt(br.readLine());

        // 테케 수 만큼 반복
        for (int i = 0; i < T; i++) {
            solve();
        }

        // 출력
        System.out.println(sb);
    }
    
    /**
     * 테스트케이스 별로 처리하는 함수
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        M = parseInt(st.nextToken());
        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());

        // 초기화!
        farm = new boolean[N][M];
        visited = new boolean[N][M];

        // 뭉탱이 배추 세는 변수
        int cnt = 0;

        // 배추 위치 입력
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r, c;
            c = parseInt(st.nextToken());
            r = parseInt(st.nextToken());
            farm[r][c] = true;
        }

        // 맵을 돌면서 BFS, 만약 배추가 있어도 visted 되었다면 무시하고 진행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // System.out.print(visited[i][j] + " ");
                if (farm[i][j] && !visited[i][j]) {
                    BFS(i, j);
                    cnt++;
                }
            }
            // System.out.println();
        }

        // sb에 뭉탱이 배추 수 넣기
        sb.append(cnt).append("\n");
    }

    /**
     * BFS 함수
     * @param x 시작 행
     * @param y 시작 열
     */
    public static void BFS(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>(); // 순회를 위한 queue

        // 시작점 방문 처리
        q.add(new int[] { x, y });
        visited[x][y] = true;

        // BFS 시작
        while (!q.isEmpty()) {
            int[] node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx, ny;
                nx = node[0] + dx[i];
                ny = node[1] + dy[i];

                // 범위 밖이거나 이미 방문했거나 배추가 없으면 무시
                if (!isIn(nx, ny) || visited[nx][ny] || farm[nx][ny] != true)
                    continue;

                visited[nx][ny] = true;
                q.add(new int[] { nx, ny });
            }
        }
    }
    
    /**
     * 범위 체크 함수
     * @param x 행
     * @param y 열
     * @return  범위 안이면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
