import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Main_2667_단지번호붙이기
 * 난이도 3/10
 * BFS
 * 
 * BFS로 단지를 찾아내는 문제
 * for 문으로 맵 전체를 돌면서 빌딩이 있고 방문하지 않았으면 BFS 돌려 방문처리를 시킨다
 * 이렇게 한번 돌면 돌면서 거쳐간 빌딩 수를 리스트에 더하고 넘어간다
 * 총 단지의 수는 리스트의 요소 개수와 같으며 정렬을 해주어서 오름차순으로 출력 되게끔 해준다.
 */
public class Main_2667_단지번호붙이기 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    
    // 맵 크기, 맵, 방문 배열
    static int N;
    static int[][] map;
    static boolean[][] visited;

    // 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    // 단지 빌딩 개수 리스트
    static List<Integer> numList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 입력
        N = parseInt(br.readLine());

        // 맵 초기화
        map = new int[N][N];
        visited = new boolean[N][N];

        // 맵 입력, 입력이 char 기때문에 - '0' 으로 숫자로 변환해준다!
        for (int i = 0; i < N; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp[j] - '0';
            }
        }

        // BFS, 맵 전체를 돌면서 빌딩이 있고 방문하지 않았으면 BFS를 돌린다
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    BFS(i, j);
                }
            }
        }

        // 정렬 + lambda 함수
        numList.sort((i, j) -> {
            return i - j;
        });

        // 리스트 크기 +  리스트의 요소를 다 sb에 삽입
        sb.append(numList.size() + "\n");
        for (int i : numList) {
            sb.append(i + "\n");
        }

        // 출력
        System.out.println(sb);
    }

    /**
     * BFS
     * @param x 행
     * @param y 열
     */
    public static void BFS(int x, int y) {
        // BFS를 위한 Queue
        Queue<int[]> q = new ArrayDeque<>();

        // 시작점 처리
        q.offer(new int[] { x, y });
        visited[x][y] = true;

        int cnt = 1; // 빌딩 개수

        //  BFS
        while (!q.isEmpty()) {
            int[] node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx, ny;
                nx = node[0] + dx[i];
                ny = node[1] + dy[i];

                // 범위 밖이거나 이미 방문했거나 빌딩이 없으면 무시
                if (!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] != 1)
                    continue;

                visited[nx][ny] = true;
                q.offer(new int[] { nx, ny });
                cnt++;
            }
        }

        // 리스트에 요소 추가해주기
        numList.add(cnt);
    }

    /**
     * 범위 체크 메서드
     * @param x 행
     * @param y 열
     * @return  범위 안이면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
