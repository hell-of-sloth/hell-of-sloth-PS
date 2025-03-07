import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_17472_다리만들기2
 * 난이도 8~9/10
 * BFS, MST, 배열탐색
 * 108ms 14mb
 * 
 * BFS, MST, 배열탐색이 같이 있는 그래프 계열 거의 끝판왕 문제
 * 각각의 메서드 구현은 생각보다 막 어렵지 않지만 저 위의 유형을 모두 알고 있어야 풀 수 있는 문제이다
 * 게다가 엣지케이스나 조건에 안 맞는 경우를 잘 생각하지 않으면 틀리기 쉽다 -> 본인은 여기서 힘들었음
 * 
 * 1. 섬을 구분하기 위해 BFS를 사용하여 각 섬에 번호를 붙여준다
 * 2. 각 섬에서 다른 섬으로 가는 최소 거리를 구해야 하는데 이 때 배열탐색을 사용하여 구해준다 (일직선 사방탐색)
 * 이 때 거리가 2인 것은 버리고 자기 자신이 닿는 경우도 버려준다
 * 3. MST를 구하기 위해 Prim 알고리즘을 사용한다
 * 
 * 이렇게 하면 구해지지만 여기서 틀렸습니다가 나와 문제 조건을 의심하였다 -> 여기서부터 꼬임
 * 문제가 모든 섬이 그냥 다리로 연결되면 되는 것인지? 아니면 한 섬에서 다른섬을 모두 갈 수 있어야 하는 것인지? 모호하여 일단 전자의 경우는 Prim으로 안 되는 경우가 있을 수 있다
 * 따라서 Kruskal도 해보았는데... 틀려서 아마 이게 문제가 아닌 것 을 파악하였다
 * 
 * => 결국 반례를 찾았는데 탐색 도중 다른 곳은 방문 해도 방문 못하는 곳이 있을 수 있는데 이 경우를 생각하지 않아서 틀린 것이었다
 * => 처리해주니까 바로 정답
 * 
 * 충분히 생각할 수 있는 반례 였는데 일부만 처리한 것을 전체다 처리했다고 생각해서 넘어갔었다...
 * 한 번 본 반례 사례도 다시 보자 
 * 
 * 골드 1문제인 만큼 코드가 길고 복잡하지만 이해하면 어렵지 않은 문제이다. 개인적으로 좋은 문제라고 생각한다
 * 
 * Kruskal구현 및 Union-Find 구현을 지우지 않고 주석으로 처리해 놓았으니 확인하고 싶은 사람은 코드를 보아도 좋다.
 */
public class Main_17472_다리만들기2_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M; // 맵 크기
    static int[][] map; // 맵
    static int[][] indexMap; // 섬 번호를 매긴 맵
    static int[][] graph; // 섬 간 거리를 저장할 인접배열 -> 배열 선택 이유는 거리 탐색하며 중복 값이 검출 될 수 잇는데 이런경우 리스트는 처리해주기 복잡하다고 생각하여 배열로 선택
    static int result = 0; // 결과값

    // 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    // static int[] parent;

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        map = new int[N][M];
        indexMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        int idx = 1; // 섬 번호

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && indexMap[i][j] == 0)
                    bfs(i, j, idx++); // BFS마다 섬번호 증가 + BFS 하면서 순회하는 노드 인덱스 붙여주기 -> indexMap 생성
            }
        }

        // 섬 간 거리를 저장할 인접배열 초기화, MAX_VALUE로 초기화 -> 최소값을 찾아야 하기 때문에
        graph = new int[idx][idx];
        for (int i = 0; i < idx; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }

        // 섬 간 거리를 구하기 위한 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (indexMap[i][j] != 0)
                    searchPath(i, j, indexMap[i][j]);
            }
        }

        // parent = new int[idx];
        // for (int i = 1; i < idx; i++) {
        //     parent[i] = i;
        // }

        Prim(); // 섬 사이 연결시 간선들 합의 최소 값을 구하기 위한 Prim 알고리즘

        // Kruskal();

        System.out.println(result == 0 ? -1 : result); // 결과 출력

        // System.out.println();

        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < M; j++) {
        //         System.out.print(indexMap[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        // System.out.println();

        // for (int i = 1; i < idx; i++) {
        //     for (int j = 1; j < idx; j++) {
        //         System.out.print((graph[i][j] == Integer.MAX_VALUE ? 0 : graph[i][j]) + " ");
        //     }
        //     System.out.println();
        // }
    }

    /**
     * BFS를 통해 섬을 구분하기 위한 메서드
     * indexMap 생성 메서드
     * @param x 시작 행
     * @param y 시작 열
     * @param idx   매겨질 섬 번호
     */
    public static void bfs(int x, int y, int idx) {
        Queue<int[]> q = new ArrayDeque<>(); // BFS를 위한 큐

        q.offer(new int[] { x, y });
        indexMap[x][y] = idx; // 섬 번호 매기기, visited 대신 사용

        while (!q.isEmpty()) {
            int[] temp = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = temp[0] + dx[i], ny = temp[1] + dy[i];

                // 범위 안에 있고, 섬이 있고, 방문하지 않았다면
                if (isIn(nx, ny) && map[nx][ny] == 1 && indexMap[nx][ny] == 0) {
                    indexMap[nx][ny] = idx;
                    q.offer(new int[] { nx, ny });
                }
            }
        }
    }

    /**
     * 섬 간 거리를 구하기 위한 메서드
     * @param x 시작 행
     * @param y 시작 열
     * @param startIdx  시작 섬 번호
     */
    public static void searchPath(int x, int y, int startIdx) {
        // 상하좌우 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            int cnt = 0; // 거리
            int endIdx = 0; // 도착 섬 번호

            // 범위 안에 있고, 시작 섬 번호와 다르다면
            while (isIn(nx, ny) && indexMap[nx][ny] != startIdx) {
                if (indexMap[nx][ny] != 0) { // 섬이 있다면 '도착 섬 번호' 저장 후 탈출
                    endIdx = indexMap[nx][ny];
                    break;
                }

                // 범위 안에 있고, 섬이 아니라면 계속 탐색 -> 인덱스 증가
                nx += dx[i];
                ny += dy[i];
                cnt++; // 거리 증가
            }

            if (endIdx == 0 || cnt < 2) // 도착 섬이 없거나 거리가 2 미만이라면 무시
                continue;

            graph[startIdx][endIdx] = Math.min(graph[startIdx][endIdx], cnt); // 최소값으로 갱신
        }
    }

    /**
     * Prim 알고리즘을 통해 MST를 구하는 메서드
     */
    public static void Prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 우선순위 큐
        boolean[] visited = new boolean[graph.length]; // 방문 체크

        // 첫 번째 노드 처리
        visited[1] = true; // 방문처리
        for (int i = 1; i < graph.length; i++) { // 1번 섬에서 갈 수 있는 섬들을 우선순위 큐에 넣어준다
            if (graph[1][i] != Integer.MAX_VALUE) {
                pq.offer(new Node(graph[1][i], i));
            }
        }

        while (!pq.isEmpty()) {
            Node temp = pq.poll();

            if (!visited[temp.idx]) { // 방문하지 않았다면 방문처리 후 그 섬에서 갈 수 있는 경로 다 pq에 넣어주기 (방문 안한 섬 경로만) -> 시간 살짝 줄이기
                result += temp.cost;
                visited[temp.idx] = true;
                for (int i = 1; i < graph.length; i++) {
                    if (graph[temp.idx][i] != Integer.MAX_VALUE && !visited[i]) {
                        pq.offer(new Node(graph[temp.idx][i], i));
                    }
                }
            }
        }

        // 만약 모든 섬을 방문하지 못했다면 -1 로 결과 설정
        for (int i = 1; i < graph.length; i++) {
            if (!visited[i]) {
                result = -1;
                break;
            }
        }
    }

    // public static void Kruskal() {
    //     PriorityQueue<Node> pq = new PriorityQueue<>();
    //     Set<Integer> visited = new HashSet<>();

    //     for (int i = 1; i < graph.length; i++) {
    //         for (int j = i; j < graph.length; j++) {
    //             if (graph[i][j] != Integer.MAX_VALUE) {
    //                 pq.offer(new Node(graph[i][j], i, j));
    //             }
    //         }
    //     }

    //     while (!pq.isEmpty()) {
    //         Node temp = pq.poll();

    //         if (find(temp.x) != find(temp.y)) {
    //             result += temp.cost;
    //             visited.add(temp.x);
    //             visited.add(temp.y);
    //             union(temp.x, temp.y);
    //         }

    //         if (visited.size() == graph.length - 1) {
    //             return;
    //         }
    //     }
    // }

    // public static void union(int x, int y) {
    //     int parentX = find(x);
    //     int parentY = find(y);

    //     if (parentX == parentY) return;

    //     if (parentX < parentY) {
    //         parent[parentY] = parentX;
    //     } else {
    //         parent[parentX] = parentY;
    //     }
    // }

    // public static int find(int x) {
    //     if (parent[x] == x)
    //         return x;

    //     return find(parent[x]);
    // }

    /**
     * 범위 체크 함수
     * @param x 행
     * @param y 열
     * @return  범위 안이면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    /**
     * 우선순위 큐에 넣기 위한 Node 클래스
     * cost : 거리, idx : 섬 번호
     * 거리를 기준으로 오름차순 정렬되도록 구현
     * Comparable 인터페이스 구현
     */
    public static class Node implements Comparable<Node> {
        int cost;
        int idx;

        public Node(int cost, int idx) {
            this.cost = cost;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    // public static class Node implements Comparable<Node> {
    //     int cost;
    //     int x;
    //     int y;

    //     public Node(int cost, int x, int y) {
    //         this.cost = cost;
    //         this.x = x;
    //         this.y = y;
    //     }

    //     @Override
    //     public int compareTo(Node o) {
    //         return this.cost - o.cost;
    //     }
    // }
}
