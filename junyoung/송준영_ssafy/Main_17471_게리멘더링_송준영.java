import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * Main_17471_게리멘더링
 * 난이도 5/10
 * 부분집합, BFS
 * 112ms 14mb
 * 
 * 부분집합과 BFS를 이용한 문제
 * 부분집합을 이용해서 지역구를 구하고 그 지역구가 유효한지 각 BFS를 통해서 확인한다
 * BFS를 통해서 각 지역구가 유효한지 확인하고 유효하다면 인구수 차이를 구한다
 * 이때 유효한지 확인하는 방법은 BFS를 통해서 연결된 지역구를 모두 방문했는지 확인하는 것
 * 
 * 지역구를 뽑는 것은 비트마스킹을 이용해보았다
 */
public class Main_17471_게리멘더링_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;               // 지역 수
    static int[] population;    // 인구 수
    static boolean[] visited;   // 방문 배열
    static List<List<Integer>> graph = new ArrayList<>();   // 인접리스트
    static int result = Integer.MAX_VALUE;                  // 결과값
    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        population = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = parseInt(st.nextToken());
        }
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                int node = parseInt(st.nextToken());
                graph.get(i).add(node);
            }
        }

        // 부분집합을 이용한 백트래킹, 지역구 선정, BFS가 안에 있음
        backtrack(1, 0);

        // 결과 출력
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    /**
     * 부분집합을 이용한 백트래킹
     * @param depth 지금 선택하고 있는 지역구
     * @param check 선택한 지역구
     */
    public static void backtrack(int depth, int check) {
        // 지역구를 다 뽑았다면 BFS를 통해서 유효한지 확인
        if (depth == N) {
            bfs(check);
            return;
        }

        // 지역구를 뽑는 경우와 뽑지 않는 경우
        backtrack(depth + 1, check | 1 << (depth-1));
        backtrack(depth + 1, check);
    }

    // BFS를 통해서 유효한지 확인
    public static void bfs(int check) {
        boolean[] visited = new boolean[N+1];   // 방문 배열
        Queue<Integer> q = new ArrayDeque<>();  // 큐

        // 1로 처리된 첫번째 지역구를 찾아서 큐에 넣어줌
        for (int i = 1; i <= N; i++) {
            if ((check & 1 << (i-1)) != 0) {
                q.offer(i);
                visited[i] = true;
                break;
            }
        }

        // BFS 시작 -> 1로 처리된 지역구만 방문
        while (!q.isEmpty()) {
            int temp = q.poll();

            for (int i = 0; i < graph.get(temp).size(); i++) {
                int next = graph.get(temp).get(i);
                if ((check & 1 << (next-1)) != 0 && !visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }

        // 0 으로 처리된 첫번째 지역구를 찾아서 큐에 넣어줌
        for (int i = 1; i <= N; i++) {
            if ((check & 1 << (i-1)) == 0) {
                q.offer(i);
                visited[i] = true;
                break;
            }
        }

        // BFS 시작 -> 0으로 처리된 지역구만 방문
        while (!q.isEmpty()) {
            int temp = q.poll();

            for (int i = 0; i < graph.get(temp).size(); i++) {
                int next = graph.get(temp).get(i);
                if ((check & 1 << (next-1)) == 0 && !visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }

        // 방문하지 않은 지역구가 있다면 차이 계산 하지 않고 리턴
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                return;
            }
        }

        // 방문한 지역구가 모두 유효하다면 인구수 차이 계산
        result = Math.min(result, populationDiffer(check));
    }

    /**
     * 인구수 차이 계산
     * @param check 지역구
     * @return  인구수 차이
     */
    public static int populationDiffer(int check) {
        int sum1 = 0;   // 1로 처리된 지역구 인구수
        int sum2 = 0;   // 0으로 처리된 지역구 인구수
        for (int i = 1; i <= N; i++) {
            if ((check & 1 << (i-1)) != 0) {    // 1로 처리된 지역구
                sum1 += population[i];
            } else {                            // 0으로 처리된 지역구
                sum2 += population[i];
            }
        }

        // 인구수 차이 반환
        return Math.abs(sum1 - sum2);
    }
}
