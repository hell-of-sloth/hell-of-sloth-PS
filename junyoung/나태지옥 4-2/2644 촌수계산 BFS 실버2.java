// 자바 테스트
// 자바의 코테에서 입출력을 경험 해봤음
// 자바 ArrayList와 배열의 명령어를 외워야겠다
// Queue 도
// 일단 성공 100ms

import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p1 = Integer.parseInt(st.nextToken());
        int p2 = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        boolean[] visited = new boolean[n + 1];

        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{p1, 0});
        visited[p1] = true;

        while (!q.isEmpty()) {
            Integer[] cur = q.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            if (curNode == p2) {
                System.out.println(curDist);
                return;
            }

            for (int next : adj.get(curNode)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(new Integer[]{next, curDist + 1});
                }
            }
        }

        System.out.println(-1);
    }
}