import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA 1210 Ladder1
 * 난이도 4/10
 * BFS?
 * 186ms 38,000 kb
 * 
 * 사다리 타기이다.class 출발점을 다 찾아서 리스트에 넣은 다음 각 출발 점에서 출발해서 2를 만나면 true를 반환하고 출력한다.
 * 사다리는 내려가거나 오, 왼쪽만 가므로 3방향만 고려하면 된다.
 * 테스트케이스가 10개로 고정적이라는 것 주의
 */
public class SWEA_1210_Ladder1_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[][] ladder;          // 사다리 배열
    static int[] dx = { 0, 0, 1 };  // 오, 왼, 아래
    static int[] dy = { 1, -1, 0 }; // 오, 왼, 아래

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 10개
        for (int t = 1; t <= 10; t++) {
            solve();
        }
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     */
    public static void solve() throws Exception {
        // 테스트 케이스 번호입력
        int T = parseInt(br.readLine());

        ladder = new int[100][100]; // 사다리 배열 초기화
        List<int[]> startPoints = new ArrayList<>(); // 출발점 리스트 초기화

        // 사다리 입력
        for (int i = 0; i < 100; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 100; j++) {
                ladder[i][j] = parseInt(st.nextToken());
            }
        }

        // 사다리 출발점 찾기
        for (int i = 0; i < 100; i++) {
            if (ladder[0][i] == 1) {
                startPoints.add(new int[] { 0, i });
            }
        }

        // 출발점에서 사다리 타기, 만약 찾으면 해당 시작점의 열 좌표 입력
        for (int i = 0; i < startPoints.size(); i++) {
            if (laddering(startPoints.get(i))) {
                sb.append(String.format("#%d %d\n", T, startPoints.get(i)[1]));
                return;
            }
        }
    }

    /**
     * 사타리타기 메서드
     * @param startPoint 출발점
     * @return 만약 2를 만나면 true, 아니면 false
     */
    public static boolean laddering(int[] startPoint) {
        Queue<int[]> q = new ArrayDeque<>(); // BFS를 위한 큐
        boolean[][] visited = new boolean[100][100]; // 방문 처리배열 -> Set으로 될 지도? 더 빠를려나?

        // 시작점 방문처리 및 큐에 삽입
        q.offer(startPoint);
        visited[startPoint[0]][startPoint[1]] = true;

        // BFS 실행
        while (!q.isEmpty()) {
            int[] node = q.poll();

            // 만약 2를 만나면 true 반환
            if (ladder[node[0]][node[1]] == 2) {
                return true;
            }

            for (int i = 0; i < 3; i++) {
                int nx, ny;
                nx = node[0] + dx[i];
                ny = node[1] + dy[i];
                // 다음 좌표가 범위 내에 있고 방문하지 않았으며 사다리가 있으면 방문처리 후 큐에 삽입, 그후 나가야됨 -> 방향이 정해지면 거기서 끝
                if (isIn(nx, ny) && !visited[nx][ny] && ladder[nx][ny] != 0) {
                    visited[nx][ny] = true;
                    q.offer(new int[] { nx, ny });
                    break;
                }
            }
        }
        return false;
    }

    /**
     * 범위 체크 메서드
     * @param x x좌표
     * @param y y좌표
     * @return 범위 내에 있으면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < 100 && y >= 0 && y < 100;
    }
}
