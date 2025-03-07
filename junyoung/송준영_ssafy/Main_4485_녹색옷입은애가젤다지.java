import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * Main_4485_녹색옷입은애가젤다지
 * 난이도 7/10
 * 다익스트라, BFS
 * 204ms
 * 최소 비용인데 각 노드마다 가중치가 있다? -> 다익스트라일 확률 높음
 * BFS 형식으로 주변 노드를 다 탐색하면서 최소 거리를 갱신한다
 * 간선이 4방향이므로 dx, dy를 사용해서 탐색
 * Node 클래스를 만들어서 x, y, cost를 저장하고 cost를 기준으로 우선순위 큐(PriorityQueue)를 사용
 * (첨 알았음)PriorityQueue의 정렬 기준은 Lambda 함수로 구현이 된다! -> comparable, comparator 구현 필요 x
 * isIn 메서드로 범위 체크를 해주었다
 */
public class Main_4485_녹색옷입은애가젤다지 {
    // 빠른 입출력을 위한 변수
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int t = 0;          // 테스트 케이스 번호
        while (true) {      // 0이 나올 때까지 반복
            int num = parseInt(br.readLine());      // 입력

            if (num == 0)                           // 0이면 종료
                break;
            
            // 빠른 출력을 위해 sb에 결과 저장
            sb.append(String.format("Problem %d: %d\n", ++t, djikstra(num)));
        }

        // 출력
        System.out.println(sb);
    }

    /**
     * 다익스트라 알고리즘
     * @param N 맵의 크기
     * @return  최소 비용
     * @throws Exception
     */
    public static int djikstra(int N) throws Exception {
        // 입력 처리
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost); // 우선순위 큐, cost 기준으로 오름차순 정렬
        int[][] map = new int[N][N];    // 맵 (N * N)

        // 맵 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        // 비용 배열
        int[][] cost = new int[N][N];

        // 비용 배열 초기화(무한은 아니지만 무한으로)
        for (int i = 0; i < N; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }

        // 시작점 초기화 및 우선순위 큐에 넣기
        cost[0][0] = map[0][0];
        pq.offer(new Node(0, 0, map[0][0]));

        // 방향벡터 설정
        int[] dx = { 0, 0, -1, 1 };
        int[] dy = { -1, 1, 0, 0 };

        // 다익스트라 알고리즘, 노드 순회 및 비용 갱신
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            // 이미 갱신된 노드는 패스 (노드 가중치보다 작은 값이 이미 들어가 있으면 이미 들린거라 패스)
            // 이 방법을 사용하면 visited배열을 안써도 되서 편하고 빨라짐
            if (node.cost > cost[node.x][node.y])
                continue;

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                // 범위 밖이면 패스
                if (!isIn(nx, ny, N))
                    continue;

                // 비용 갱신, 지금 노드의 비용 + 다음 노드의 가중치가 더 작으면 갱신 + 큐에 넣기
                if (cost[nx][ny] > cost[node.x][node.y] + map[nx][ny]) {
                    cost[nx][ny] = cost[node.x][node.y] + map[nx][ny];
                    pq.offer(new Node(nx, ny, cost[nx][ny]));
                }
            }
        }

        // 도착지([N-1][N-1])의 비용 반환
        return cost[N - 1][N - 1];
    }

    /**
     * 범위 체크 메서드
     * @param x     x좌표(행)
     * @param y     y좌표(열)
     * @param N     맵의 크기
     * @return
     */
    public static boolean isIn(int x, int y, int N) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    /**
     * 노드 클래스
     * x, y, cost를 저장
     */
    public static class Node {
        int x, y, cost;

        // 생성자
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
