import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_1600_말이되고픈원숭이 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int K, W, H;

    // 방향 벡터 -> 4(일반 이동) / 12(말 점프 포함)
    static int[] dx = { -1, 0, 1, 0, -2, -1, 1, 2, 2, 1, -1, -2 };
    static int[] dy = { 0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1 };

    static int[][] map;
    static int[][][] visited; // [H][W][K+1]: 점프 사용 횟수 별 방문 거리 저장

    public static void main(String[] args) throws Exception {
        K = parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = parseInt(st.nextToken());
        H = parseInt(st.nextToken());
        map = new int[H][W];
        visited = new int[H][W][K+1]; // K+1 크기로 확장

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = parseInt(st.nextToken());
                Arrays.fill(visited[i][j], Integer.MAX_VALUE); // 최댓값으로 초기화
            }
        }

        // 시작점이 도착점이면 0 출력
        if (H == 1 && W == 1) {
            System.out.println(0);
        } else {
            System.out.println(BFS());
        }
    }

    public static int BFS() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0, 0, 0, 0}); // {x, y, jump, count}
        visited[0][0][0] = 0;

        while (!q.isEmpty()) {
            int[] node = q.poll();
            int x = node[0], y = node[1], jump = node[2], count = node[3];

            // jump 수에 따라 갈수 있는 방향 한정
            int move;
            if (jump < K) {
                move = 12;
            } else {
                move = 4;
            }

            // 4 or 12방향 탐색
            for (int i = 0; i < move; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                int newJump;
                if (i < 4) {
                    newJump = jump;
                } else {
                    newJump = jump + 1;
                }

                if (nx == H - 1 && ny == W - 1) {
                    return count + 1; // 목표 지점 도달 시 즉시 반환
                }

                // 범위 밖이거나 벽이면 스킵
                if (!isIn(nx, ny) || map[nx][ny] == 1) continue;

                // 새로운 위치의 점프 상태에서 더 적은 횟수로 도착할 수 있을 때만 갱신
                if (visited[nx][ny][newJump] > count + 1) {
                    visited[nx][ny][newJump] = count + 1;
                    q.offer(new int[] {nx, ny, newJump, count + 1});
                }
            }
        }
        
        return -1;
    }

    public static boolean isIn(int x, int y) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }
}