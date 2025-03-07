
import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Main_2606_바이러스
 * 난이도 2/10
 * 그래프, BFS
 * 104ms
 * 전형적인 BFS문제이다 BFS를 어떻게 푸는지 익숙하다면 진짜 쉽게 풀 수 있다
 */
public class Main_2606_바이러스 {
    // 빠른 입력을 위한 변수
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    // N, M, 그래프를 저장할 변수, 인접리스트로 선언
    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 입력 처리
        N = parseInt(br.readLine());
        M = parseInt(br.readLine());

        // 그래프 초기화
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken()) - 1;
            int b = parseInt(st.nextToken()) - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // BFS메서드 실행 및 출력
        System.out.println(BFS(0));
    }

    /**
     * BFS 메서드
     * @param start 시작 노드
     * @return  1번 컴퓨터를 통해 감염된 컴퓨터 수
     */
    public static int BFS(int start) {
        Queue<Integer> q = new ArrayDeque<>();  // BFS는 Queue를 쓴다!! 꼭 기억할 것
        boolean[] visited = new boolean[N];     // 방문 체크를 위한 배열    
        int cnt = 0; // 감염된 컴퓨터 수

        // 시작 노드 방문 처리
        q.offer(start);
        visited[start] = true;

        // BFS 처리, q가 빌 때까지
        while (!q.isEmpty()) {
            int node = q.poll();

            // 현재 노드와 연결된 노드들을 처리
            for (int nextNd : graph.get(node)) {
                if (!visited[nextNd]) {
                    visited[nextNd] = true;
                    q.offer(nextNd);
                    cnt++;
                }
            }
        }

        // 결과 반환
        return cnt;
    }
}
