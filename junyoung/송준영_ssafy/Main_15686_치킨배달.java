import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Main_15686_치킨배달
 * 난이도 5/10
 * 백트래킹, 조합, 구현?
 * 치킨거리 = 집과 가장 가까운 치킨집 사이의 거리인데
 * 이 치킨거리의 합이 최소가 되는 경우를 찾아야함
 * 근데 M개의 치킨집만 남기고 나머지는 다 지운다고 하는데 -> 그러면 이 남아있는 치킨집의 경우의 수를 알아야함
 * 이 때 조합을 이용함
 * 치킨집의 경우의 수를 구하고 각 집에서 치킨집까지의 거리를 구해서 최소값을 구함
 * 조금 복잡한 백트래킹 느낌
 * 뽑은 치킨집을 어떻게 자료구조에 저장할 것이고 최소거리를 갱신하는 로직을 구현해야함
 */
public class Main_15686_치킨배달 {
    // 빠른 입력과 토큰화를 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // N, M을 저장할 변수 + 결과값을 저장할 변수
    // 다른 메서드에서 바로 접근 할 수 있게 static으로 선언
    static int N, M;
    static int result = Integer.MAX_VALUE;

    // 집과 치킨집을 저장할 리스트
    static List<int[]> homes = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = parseInt(st.nextToken());
                if(temp == 1) {                         // 1 이면 집, 배열로 좌표 저장
                    homes.add(new int[] { i , j });
                } else if (temp == 2) {                 // 2 이면 치킨집, 배열로 좌표 저장
                    chickens.add(new int[] { i, j });
                }
            }
        }

        // 백트래킹으로 치킨집 M개를 뽑아서 최소 치킨거리를 구함
        backtrack(M, 0, -1, new boolean[chickens.size()]);
        
        // 결과 출력
        System.out.println(result);
    }

    // 집, 치킨집 위치 구하기 N * N = 2500
    // 치킨집 M개의 조합으로 각 집 거리 최소값을 다 더하기 -> 도시의 치킨거리
    // 가장 작은얘로 설정

    /**
     * 백트래킹으로 치킨집 M개를 뽑아서(조합) 최소 치킨거리를 구함
     * 조합을 구현하려고 변수 하나를 더 추가 했는데 다른 방법이 있을까...?
     * @param M     뽑아야되는 치킨집의 개수
     * @param depth 현재까지 뽑은 치킨집의 개수
     * @param step  현재까지 뽑은 치킨집의 인덱스 -> 이 인덱스 이후만 뽑을 수 있음
     * @param check 치킨집을 뽑았는지 확인하는 배열
     */
    public static void backtrack(int M, int depth, int step, boolean[] check) {
        // 종료조건
        if (M == depth) {
            int min_sum = 0;                // 최소 치킨거리를 저장할 변수
            for (int[] home : homes) {      // 각 집에서 치킨집까지의 거리를 구해서 최소값을 구함
                int temp_min = Integer.MAX_VALUE;
                for (int i = 0; i < check.length; i++) {
                    if (check[i]) {         // 뽑은 치킨집만 거리를 구함
                        temp_min = Math.min(temp_min, calChickenDist(home, chickens.get(i)));
                    }
                }
                min_sum += temp_min;        // 최소값을 더함
            }

            // 결과값이랑 비교해서 더 작은거로 갱신
            result = Math.min(result, min_sum);
            return;
        }

        // 치킨집 조합 구하기, 백트래킹
        for (int i = 0; i < chickens.size(); i++) {
            if (!check[i] && step < i) {    // 뽑지 않은 치킨집이고 이전에 뽑은 치킨집보다 뒤에 있는 치킨집만 뽑음
                check[i] = true;
                backtrack(M, depth + 1, i, check);
                check[i] = false;
            }
        }
    }

    /**
     * 집과 치킨집 사이의 거리를 구하는 메서드
     * @param home      집 좌표 배열
     * @param chicken   치킨집 좌표 배열
     * @return          집과 치킨집 사이의 거리
     */
    public static int calChickenDist(int[] home, int[] chicken) {
        return Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
    }
    
}
