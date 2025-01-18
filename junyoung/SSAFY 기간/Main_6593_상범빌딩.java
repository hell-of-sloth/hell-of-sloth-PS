// 해당문제는 3차원으로 이루어진 미로를 탈출하는 문제
// 가장 빠른 시간으로 탈출하는 문제이므로 BFS를 사용하여 풀 수 있음
// 3차원 배열을 사용하여 각 층의 행, 열, 높이를 저장하고 시작점과 도착점을 찾아내어 BFS를 실행

import java.io.*;
import java.util.*;

public class Main_6593_상범빌딩 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    // 입력을 받기 위한 BufferedReader, 입력 시간을 줄여줌
    static StringTokenizer st;                                                          // 한 줄 입력을 받아서 공백으로 나눠줌
    public static void main(String[] args) throws Exception {
        int L, R, C;                                            // 각 층의 행, 열, 높이
        String str;                                             // 임시 문자열 변수

        while(true) {

            // 첫 줄 입력 받기
            st = new StringTokenizer(br.readLine());            
            L = Integer.parseInt(st.nextToken());               
            R = Integer.parseInt(st.nextToken());               
            C = Integer.parseInt(st.nextToken());               

            if (L == 0 && R == 0 && C == 0) break;              // 0 0 0 입력 시 종료

            char[][][] building = new char[L][R][C];            // 건물 정보 저장 배열
            int[] start = new int[3];                           // 시작점 좌표
            int[] end = new int[3];                             // 도착점 좌표

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    str = br.readLine();                        // 한 줄 입력 받기
                    for (int k = 0; k < C; k++) {
                        if (str.contains("S")) {              // 시작점 찾기, S가 있는 위치 저장
                            start[0] = i;
                            start[1] = j;
                            start[2] = str.indexOf("S");
                        }
                        if (str.contains("E")) {              // 도착점 찾기, E가 있는 위치 저장
                            end[0] = i;
                            end[1] = j;
                            end[2] = str.indexOf("E");
                        }
                        building[i][j][k] = str.charAt(k);      // 건물 정보 저장
                    }
                }
                br.readLine();
            }

            int result = bfs(L, R, C, building, start, end);    // bfs 탐색 실행

            if (result == -1) {
                System.out.println("Trapped!");                             // 도착점에 도달하지 못하면 "Trapped!" 출력
            } else {
                System.out.println("Escaped in " + result + " minute(s).");   // 도착점에 도달하면 걸린 시간 출력
            }
        }
    }

    public static int bfs(int L, int R, int C, char[][][] building, int[] start, int[] end) {
        boolean[][][] visited = new boolean[L][R][C];               // 이미 방문했는지 여부
        int[][][] distance = new int[L][R][C];                      // 각 위치까지의 이동 거리
    
        // 6가지 방향 벡터 (x, y, z)
        int[] dx = { 1, -1, 0, 0, 0, 0 };
        int[] dy = { 0, 0, 1, -1, 0, 0 };
        int[] dz = { 0, 0, 0, 0, 1, -1 };
    
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { start[0], start[1], start[2] });    // 시작점 큐에 삽입
        visited[start[0]][start[1]][start[2]] = true;               // 시작점 방문 처리
        distance[start[0]][start[1]][start[2]] = 0;                 // 시작점 거리 0
    
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();                               // 큐에서 하나 꺼내기
            int x = cur[0];
            int y = cur[1];
            int z = cur[2];
    
            // 도착점에 도달했으면 해당 좌표까지의 거리 반환
            if (x == end[0] && y == end[1] && z == end[2]) {
                return distance[x][y][z];
            }
    
            // 6가지 방향으로 이동
            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];
    
                // 건물 범위를 벗어나지 X + 벽 X + 방문하지 않은 경우만 이동
                if (nx >= 0 && nx < L && ny >= 0 && ny < R && nz >= 0 && nz < C) {
                    if (!visited[nx][ny][nz] && building[nx][ny][nz] != '#') {
                        visited[nx][ny][nz] = true;                     // 방문 처리
                        distance[nx][ny][nz] = distance[x][y][z] + 1;   // 거리 갱신
                        queue.offer(new int[] { nx, ny, nz });          // 큐에 삽입
                    }
                }
            }
        }
    
        return -1;  // 도착점을 찾지 못하면 -1 을 반환
    }
}