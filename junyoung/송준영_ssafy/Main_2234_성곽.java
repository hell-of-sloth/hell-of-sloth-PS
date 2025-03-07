import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Main_2234_성곽
 * 난이도 7/10
 * BFS, 비트마스킹
 * 144ms -> 132ms
 * 
 * 이 문제는 크게 3가지로 나눌수 있다
 * 1. 방의 개수 찾기
 * 2. 가장 큰 방 찾기
 * 3. 한 개 벽을 부수었을 때 가장 큰 방 찾기
 * 
 * 1, 2 번은 쉽다 그런데 3번이 문제이다
 * 3번을 어떻게 찾을까 고민하다가 방의 개수를 찾을 때 인접한 방을 찾아서 저장해두면
 * 나중에 한꺼번에 계산할 수 있음
 * 인접한 방은 중복을 제거하기 위해 Set을 사용
 * 1번 문제는 다 탐색하면서 BFS 방문안했으면 돌리기~
 * 2번 문제는 BFS탐색할때 크기 저장하기~
 * 
 * StringBuilder 미사용시 sysout이 3개 밖에 없는데도 시간 차이가 꽤 남
 * StringBuilder 사용하자 : 144ms -> 132ms
 * 비트연산해도 되는데 딱히 해도 시간 변화는 없었음 -> 굳이 쓸필요 없는데
 */
public class Main_2234_성곽 {
    // 빠른 입출력을 위한 BufferedReader, StringTokenizer, StringBuilder
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // 변수 선언
    static int N, M;                                // 맵 크기
    static int[][] map;                             // 벽 위치 저장 맵
    static int[][] visited;                         // 방 방문 여부, 각 성의 인덱스로 표현
    
    // 방향 벡터
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { -1, 0, 1, 0 };

    static List<Integer> roomSize = new ArrayList<>();          // 방 크기 저장 리스트
    static List<Set<Integer>> roomLink = new ArrayList<>();     // 방 인접 리스트 - Set로 중복 방지
    static int totalRooms;                          // 방 총 개수
    static int maxRoom = Integer.MIN_VALUE;         // 가장 큰 방
    static int maxWallRoom = Integer.MIN_VALUE;     // 한 개 연결 했을 때 가장 큰 방

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        map = new int[M][N]; // 배열 초기화
        visited = new int[M][N]; // 배열 초기화
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        // 인덱스 1부터 찾을거라 0 채워주기
        roomSize.add(0);
        roomLink.add(new HashSet<>());

        // BFS 순회 및 방 개수 찾기
        int order = 1;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == 0) {
                    roomLink.add(new HashSet<>()); // 방 인접 리스트 초기화
                    BFS(i, j, order++);
                    totalRooms++; // 방 개수 증가
                }
            }
        }

        // 방 크기, 가장 큰 방 찾기
        for (int size : roomSize) {
            maxRoom = Math.max(maxRoom, size);
        }

        // 방 인접 리스트를 돌면서 가장 큰 방 찾기
        for (int i = 1; i < roomLink.size(); i++) {
            for (int link : roomLink.get(i)) {
                maxWallRoom = Math.max(maxWallRoom, roomSize.get(i) + roomSize.get(link));
            }
        }

        // 결과 출력 -> 빠른 출력을 위해서 StringBuilder 사용
        sb.append(totalRooms).append("\n");
        sb.append(maxRoom).append("\n");
        sb.append(maxWallRoom).append("\n");
        System.out.println(sb);
    }
    
    /**
     * BFS 탐색
     * @param x             시작 x
     * @param y             시작 y
     * @param castleIdx     성의 인덱스
     */
    public static void BFS(int x, int y, int castleIdx) {
        Queue<int[]> q = new ArrayDeque<>();
        int cnt = 1; // 방의 크기

        // 시작점 방문
        q.offer(new int[] { x, y });
        visited[x][y] = castleIdx;

        // 큐가 빌때까지 참색
        while (!q.isEmpty()) {
            int[] node = q.poll(); // 큐에서 노드 꺼내기
            
            // 비트 연산으로 벽이있는 방향 저장 - 1이면 벽, 0이면 벽 없음
            int[] dir = new int[4];
            for (int i = 0; i < 4; i++) {
                // 2 나누기 연산으로 처리
                dir[i] = map[node[0]][node[1]] % 2;
                map[node[0]][node[1]] = map[node[0]][node[1]] / 2;
                // dir[i] = (map[node[0]][node[1]] >> i) & 1;           // 비트연산으로도 가능 -> 시간차이는 없었음(?)
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx, ny;
                nx = node[0] + dx[i]; // 다음 x
                ny = node[1] + dy[i]; // 다음 y

                // 범위 밖이면 나락
                if (!isIn(nx, ny))
                    continue;

                // 벽이면 인접 성 잇기
                if (dir[i] == 1) {
                    // 방문한 방이거나 같은 방이면 탈출!
                    if (visited[nx][ny] == 0 || visited[nx][ny] == castleIdx)
                        continue;
                    
                    // 현재 성 인덱스에 인접 성 인덱스 추가
                    roomLink.get(castleIdx).add(visited[nx][ny]);
                    continue;
                }

                // 벽이 아니면
                if (visited[nx][ny] == 0) {
                    visited[nx][ny] = castleIdx;    // 방문 처리, 현재 성 인덱스로 지정
                    q.offer(new int[] { nx, ny });
                    cnt++; // 방의 크기 증가
                }
            }
        }

        // 방의 크기 저장
        roomSize.add(cnt);
    }

    /**
     * 범위 체크
     * @param x 행
     * @param y 열
     * @return  범위 안이면 true
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }
}
