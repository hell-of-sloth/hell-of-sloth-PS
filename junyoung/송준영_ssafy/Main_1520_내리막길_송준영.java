import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Main_1520_내리막길
 * 난이도 8/10
 * DFS, DP
 * 
 * DFS로 풀어야 하는 문제 -> DP도 써야했다
 * DFS로 가면서 경로를 체크하는데 DP로 갔던 경로를 체크해 놓으면 시간을 줄여야 한다.
 * 간곳과 안 간곳 모두 체크해야한다 -> possible 배열을 만들어서 체크해주었다
 * 간 곳이면 해당 노드를 방문시 몇개의 가능한 경로가 있는지 저장해야한다
 * 이걸 저장하는 로직을 생각해 내는데 꽤 오래걸렸다
 * 처음에 return값으로 해결해보려함 -> 너무 복잡해짐..
 * 생각해보니 마지막에 저장하는 것이 아닌 처음에 다 저장되는 로직이었음...!
 * possible[0][0]이 답이 된다
 */
public class Main_1520_내리막길_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;            // 맵 크기
    static int[][] arr;         // 맵
    static boolean[][] visited; // 방문 배열
    static int[][] possible;    // 가능한 경로의 개수를 배열

    // 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];
        possible = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
            Arrays.fill(possible[i], -1);   // -1로 초기화
        }

        // DFS 시작
        dfs(0, 0, arr[0][0]);

        // 출력
        System.out.println(possible[0][0]);

        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < M; j++) {
        //         System.out.print(possible[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }
    
    /**
     * DFS + DP
     * @param x 행
     * @param y 열
     * @param val   현재 위치의 값
     */
    public static void dfs(int x, int y, int val) {
        // 도착지점이면 1로 초기화
        if (x == N - 1 && y == M - 1) {
            possible[x][y] = 1;
            // System.out.println("나는 왔다 : " + x + " " + y);
            return;
        }
        
        // System.out.println("나는 왔다 : " + x + " " + y);

        // 이놈이 갈수 있는 곳들의 경로 합
        int flag = 0;

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            // 범위 안에 있고, 현재 값보다 작고, 방문하지 않았다면
            if (isIn(nx, ny) && (arr[nx][ny] < val) && !visited[nx][ny]) {
                if (possible[nx][ny] == 0)      // 0이면 방문할 필요 없음 (방문했지만 도착지까지 못 간 곳)
                    continue;
                if (possible[nx][ny] > 0) {     // 이미 방문한 곳이면
                    flag += possible[nx][ny];   // 그 값만 더해주고 다시 돌아가기
                    continue;
                }

                // 방문 처리
                visited[nx][ny] = true;
                dfs(nx, ny, arr[nx][ny]);
                flag += possible[nx][ny];    // 돌아왔을 때 그 값 더해주기
                visited[nx][ny] = false;
            }
        }

        // 현재 노드에 가능한 경로의 개수 저장
        possible[x][y] = flag;
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
