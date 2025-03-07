import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main_18352_특정거리의도시찾기
 * 난이도 3/10
 * 1056ms
 * 다익스트라
 * 
 * 최단거리라 다익스트라로 접근했다
 * 노드는 따로 클래스를 만들어서 접근
 */
public class Main_18352_특정거리의도시찾기 {
    // 빠른 입출력을 위한 BufferedReader, StringBuilder
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // N: 도시의 개수, M: 도로의 개수, K: 거리 정보, X: 출발 도시
    static int N, M, K, X;
    static List<List<Integer>> graph = new ArrayList<>();   // 그래프

    // 거리 정보를 저장할 배열
    static int[] dist;

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        X = parseInt(st.nextToken());

        // 그래프 초기화
        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 정보 입력
        for (int i = 0; i < M; i++) {
            int s, e;
            st = new StringTokenizer(br.readLine());
            s = parseInt(st.nextToken());
            e = parseInt(st.nextToken());
            graph.get(s - 1).add(e - 1);
        }

        // 다익스트라 실행
        djik(X - 1);
        
        // 출력, 값이 없으면 flag를 이용해 -1 출력
        boolean flag = false;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == K) {
                sb.append(i + 1).append("\n");
                flag = true;
            }
        }
        if (!flag) {
            System.out.println(-1);
        } else {
            System.out.println(sb);
        }
    }

    /**
     * 다익스트라 알고리즘
     * @param start     시작 노드
     */
    public static void djik(int start) {
        // 우선순위 큐를 이용한 다익스트라
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> {
            return n1.cost - n2.cost;
        });

        // 시작 노드 처리
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        // 우선순위 큐가 빌 때까지 반복
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            // 이미 처리된 노드라면 패스
            if (dist[node.index] != node.cost) {
                continue;
            }
            
            // 최단거리를 발견하면 갱신
            for (int i : graph.get(node.index)) {
                if (dist[i] > dist[node.index] + 1) {
                    dist[i] = dist[node.index] + 1;
                    pq.offer(new Node(i, dist[node.index] + 1));
                }
            }
        }
    }
}

class Node {
    int index, cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}
