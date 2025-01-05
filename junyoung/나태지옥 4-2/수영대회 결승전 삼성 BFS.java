// 체감 난이도 3/10 생각보다 방법이 빨리 생각남
// BFS인데 조건이 붙은 BFS인데
// 시간을 추가하여 해당 노드의 시간에 따라서 소용돌이 이동이 결정되는 BFS

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            System.out.println("#" + t + " " + solve());
        }
    }

    public static int solve() throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int[] startPoint = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        st = new StringTokenizer(br.readLine());
        int[] endPoint = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        // BFS
        int answer = bfs(N, map, startPoint, endPoint);

        return answer;        
    }

    public static int bfs(int N, int[][] map, int[] startPoint, int[] endPoint) {
        boolean visited[][] = new boolean[N][N];
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> q = new LinkedList<>(); // 자바에서 큐는 LinkedList로 구현

        int[] start = new int[] {startPoint[0], startPoint[1], 0}; // 시작점, 시간

        // 시작점이 도착점과 같은 경우
        if (start[0] == endPoint[0] && start[1] == endPoint[1]) {
            return 0;
        }

        // 보통의 경우
        q.offer(start);
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int cur[] = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] == 1) {
                    continue;
                }

                if (nx == endPoint[0] && ny == endPoint[1]) {
                    return cur[2] + 1;
                }

                // 소용돌이인 경우
                if (map[nx][ny] == 2) {
                    if ((cur[2] + 1) % 3 == 0) {    // 이동 가능 경우
                        q.offer(new int[] {nx, ny, cur[2] + 1});
                        visited[nx][ny] = true;
                    } else {                        // 이동 불가능 경우
                        q.offer(new int[] {cur[0], cur[1], cur[2] + 1});
                    }
                    
                } else {
                    q.offer(new int[] {nx, ny, cur[2] + 1});
                    visited[nx][ny] = true;
                }
            }
        }

        return -1; 
    }
}
