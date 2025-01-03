import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution {
    static int N;
    static char[][] map;
    static int[][] nearMines;
    static boolean[][] visited;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}; // 8방향 이동
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스 수
        sc.nextLine(); // 개행 문자 처리

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            sc.nextLine(); // 개행 문자 처리
            map = new char[N][N];
            nearMines = new int[N][N];
            visited = new boolean[N][N];

            // 입력 받기
            for (int i = 0; i < N; i++) {
                map[i] = sc.nextLine().toCharArray();
            }

            // 지뢰 인접 숫자 계산
            calnearMines();

            // 최소 클릭 수 계산
            int clicks = calMinClicks();

            // 출력
            System.out.println("#" + t + " " + clicks);
        }
    }

    // 인접한 지뢰 수 계산
    static void calnearMines() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '*') {
                    nearMines[i][j] = -1; // 지뢰 위치는 -1
                } else {
                    int count = 0;
                    for (int k = 0; k < 8; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (isBound(nx, ny) && map[nx][ny] == '*') {
                            count++;
                        }
                    }
                    nearMines[i][j] = count;
                }
            }
        }
    }

    // 최소 클릭 수 계산
    static int calMinClicks() {
        int clicks = 0;

        // 1. 인접 지뢰가 없는 칸(숫자 0)을 먼저 클릭
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (nearMines[i][j] == 0 && !visited[i][j]) {
                    bfs(i, j);
                    clicks++;
                }
            }
        }

        // 2. 나머지 클릭하지 않은 칸을 클릭
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == '.') {
                    clicks++;
                }
            }
        }

        return clicks;
    }

    // BFS로 0인 칸과 인접한 칸 탐색
    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int k = 0; k < 8; k++) {
                int nx = cx + dx[k];
                int ny = cy + dy[k];
                if (isBound(nx, ny) && !visited[nx][ny] && map[nx][ny] == '.') {
                    visited[nx][ny] = true;
                    if (nearMines[nx][ny] == 0) {
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    // 좌표가 유효한지 확인
    static boolean isBound(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
