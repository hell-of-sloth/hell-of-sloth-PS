import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Main_1742_음식물피하기
 * 난이도 3/10
 * BFS
 * 228ms
 * BFS로 한 덩이가 가장 큰거를 찾는 문제
 * Set을 이용해서 방문처리를 해서 다음 방문할 곳을 처리하도록 했는데
 * 그냥 배열로 처리해서 처음부터 탐색하다 찾으면 BFS하고 처리하는게 더 빠른 듯 -> 144ms
 * (규리 코드를 참고)
 * 
 * 그렴 Set을 이용한 방문 처리는 항상 느린가????
 */
public class Main_1742_음식물피하기 {
    // 빠른 입출력 BufferedReader, StringBuilder
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // 변수 선언
    static int N, M, K;
    static int result = 0;
    static Set<List<Integer>> visitedSet = new HashSet<>();     // List는 안의 내용으로 중복 체크 가능!

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        K = parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            visitedSet.add(Arrays.asList(a, b)); // 방문하려고 하는 노드들 넣기
        }

        // BFS 실행
        while (bfs()) {
            // System.out.println("중간점검 : " + result);
        }

        // 결과 출력
        System.out.println(result);
    }
    
    /**
     * BFS
     * @return
     */
    public static boolean bfs() {
        Queue<List<Integer>> q = new ArrayDeque<>();            // BFS를 위한 Queue

        if (visitedSet.isEmpty())                               // 방문할 곳이 없으면 종료
            return false;

        List<Integer> start = visitedSet.iterator().next();     // 시작점, Set은 그냥은 요소를 못 꺼내서 iterator 사용
        q.offer(start);                                         // 시작점 넣기
        visitedSet.remove(start);                               // 방문 처리
        
        // 상하좌우 이동을 위한 배열
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        // 덩어리 크기
        int cnt = 0;

        // BFS
        while (!q.isEmpty()) {
            List<Integer> node = q.poll();                      // 큐에서 노드 꺼내기
            cnt++;                                              // 덩어리 크기 증가

            for (int i = 0; i < 4; i++) {
                int nx, ny;
                nx = node.get(0) + dx[i];
                ny = node.get(1) + dy[i];
                List<Integer> nextNd = Arrays.asList(nx, ny);   // 다음 노드를 List로 만들기

                // 다음 노드가 방문한 곳이고, 범위 안에 있으면 방문 처리하고 큐에 넣기
                if (isIn(nx, ny) && visitedSet.contains(nextNd)) {
                    visitedSet.remove(nextNd);
                    q.offer(nextNd);
                }
            }
        }

        // 결과 갱신
        result = Math.max(result, cnt);
        return true;
    }
    
    /**
     * 범위 체크
     * @param x 행
     * @param y 열 
     * @return  범위 안에 있으면 true
     */
    public static boolean isIn(int x, int y) {
        return x > 0 && x <= N && y > 0 && y <= M;
    }
}
