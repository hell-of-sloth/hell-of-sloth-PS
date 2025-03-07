import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * Main_2458_키순서
 * 난이도 3/10
 * BFS
 * 680ms
 * 
 * 테스트케이스 별로 처리하는 것과 BFS를 통해 모든 노드를 방문하면서 BFS된 횟수를 구하면 그게 답이 된다.
 * BFS를 통해 모든 노드를 방문하면서 BFS된 횟수를 구하면 그게 답이 된다.
 */
public class Main_2458_키순서 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // 노드 수, 간선 수, 그래프, 방문 배열
    static int N, M;
    static List<List<Integer>> graph;
    static List<List<Integer>> checkList;

    public static void main(String[] args) throws Exception {
        System.out.println(solve());       
    }
    
    // 메인 로직 메서드
    public static int solve() throws Exception {
        int cnt = 0; // 결과값
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 방문 배열 초기화
        checkList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            checkList.add(new ArrayList<>());
        }

        // 그래프 입력
        for (int i = 0; i < M; i++) {
            int a, b;
            st = new StringTokenizer(br.readLine());
            a = parseInt(st.nextToken());
            b = parseInt(st.nextToken());
            graph.get(a).add(b);
        }

        // 모든 노드에 대해 BFS
        for (int i = 1; i <= N; i++) {
            BFS(i);
        }

        // 각 노드별로 BFS된 횟수를 체크하면서 N-1이면 카운트
        for (int i = 1; i <= N; i++) {
            if (checkList.get(i).size() == N - 1) {
                cnt++;
            }
        }

        return cnt;
    }
    
    /**
     * BFS 메서드
     * @param start 시작 노드
     */
    public static void BFS(int start) {
        Deque<Integer> q = new ArrayDeque<>();      // BFS를 위한 큐
        boolean[] visited = new boolean[N + 1];     // 방문 배열

        // 시작 노드 방문 처리
        q.offerLast(start);
        visited[start] = true;

        // BFS
        while (!q.isEmpty()) {
            int node = q.pollFirst();

            // 다음 노드 방문 처리
            for (int next : graph.get(node)) {
                if (!visited[next]) {
                    q.offerLast(next);
                    visited[next] = true;
                    checkList.get(start).add(next); // start 노드에서 next 노드로 갈 수 있음을 체크
                    checkList.get(next).add(start); // next 노드에서 start 노드로 갈 수 있음을 체크
                }
            }
        }
    }
}
