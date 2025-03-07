import java.io.*;
import java.util.*;

public class Main_11779_최소비용구하기2_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, m;            // 도시 개수, 버스(간선) 개수
    static List<List<Node>> graph;  // 인접 리스트
    static int[] dist;          // 최단 거리 배열
    static int[] route;         // 경로 복원 배열
    static int start, end;      // 시작 도시, 도착 도시

    public static void main(String[] args) throws IOException {
        
        // 1. 입력 받기
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        // 그래프 초기화 (1번 도시부터 n번 도시까지 사용)
        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 출발 도시
            int b = Integer.parseInt(st.nextToken()); // 도착 도시
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, cost));
        }

        // 시작 도시, 도착 도시
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 다익스트라 수행
        dijk();

        // 결과 출력
        sb.append(dist[end]).append("\n");      // 최소 비용

        Deque<Integer> stack = new ArrayDeque<>();  // 경로 역추적을 위한 스택, 경로 스택 채우기
        int city = end;
        while (city != 0) {
            stack.push(city);
            if (city == start) break; 
            city = route[city];
        }

        sb.append(stack.size()).append("\n");   // 경로 길이

        while (!stack.isEmpty()) {                  // 경로 출력
            sb.append(stack.pop()).append(" ");
        }

        // 모두 출력
        System.out.println(sb);
    }

    /**
     * 다익스트라 알고리즘
     */
    public static void dijk() {
        dist = new int[n + 1];
        route = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 시작 도시까지 비용은 0, 우선순위큐에 삽입
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int curIdx = node.city;
            int curCost = node.cost;

            // 이미 더 짧은 경로를 알고 있다면 스킵
            if (dist[curIdx] < curCost) continue;

            // 현재 도시와 인접한 도시 확인
            for (Node next : graph.get(curIdx)) {
                int nextidx = next.city;
                int nextCost = curCost + next.cost;

                // 더 짧은 경로 발견 시 dist 업데이트 & 경로 저장
                if (dist[nextidx] > nextCost) {
                    dist[nextidx] = nextCost;
                    route[nextidx] = curIdx;
                    pq.offer(new Node(nextidx, nextCost));
                }
            }
        }
    }

    /**
     * 노드 클래스
     * - city : 도시 번호
     * - cost : 비용
     * comparable 구현을 통해서 우선순위 큐에서 비용이 작은 순으로 정렬
     */
    static class Node implements Comparable<Node> {
        int city;
        int cost;

        public Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
