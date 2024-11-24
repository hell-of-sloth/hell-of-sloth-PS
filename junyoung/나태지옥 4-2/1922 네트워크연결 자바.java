import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(reader.readLine().trim());
        int M = Integer.parseInt(reader.readLine().trim());
        
        // 이중 리스트를 이용한 인접 리스트 생성
        List<List<int[]>> connections = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            connections.add(new ArrayList<>());
        }
        
        for (int i = 0; i < M; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine().trim());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            connections.get(a).add(new int[]{b, c});
            connections.get(b).add(new int[]{a, c});
        }
        
        System.out.println(prim(N, connections));
    }
    
    public static int prim(int N, List<List<int[]>> connections) {
        int answer = 0;
        boolean[] visited = new boolean[N + 1];

        // 자바의 우선순위 큐, 배열 입력시 Comparator.comparingInt를 사용해서 기준을 알려줘야 함
        // 기본적으로 오름차순으로 정렬됨
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        pq.offer(new int[]{0, 1}); // (cost, node) 초기화
        int nodes = 0;
        
        while (!pq.isEmpty()) {
            if (nodes == N) { // 모든 노드를 방문했으면 종료
                break;
            }
            
            int[] current = pq.poll();
            int cost = current[0];
            int node = current[1];
            
            if (visited[node]) { // 방문 했으면 넘어가기
                continue;
            }
            
            visited[node] = true;
            answer += cost;
            nodes++;
            
            for (int[] next : connections.get(node)) {
                int nextNode = next[0];
                int nextCost = next[1];
                if (!visited[nextNode]) {
                    pq.offer(new int[]{nextCost, nextNode});
                }
            }
        }
        
        return answer;
    }
}