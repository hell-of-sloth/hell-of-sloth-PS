import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Main_15664_N과M10
 * 난이도 2/10
 * 조합, 백트래킹
 * 104ms
 * N개의 자연수 중 M개를 고른 수열
 * 오름차순으로 고르는데 입력으로 중복된게 올 수 있음
 * 중복은 Set으로 처리
 */
public class Main_15664_N과M10 {
    // 빠른 입출력 BufferedReader, StringBuilder
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // 변수 선언
    static int N, M;
    static int[] arr;
    static Set<String> duple = new HashSet<>();                 // 중복 처리를 위한 Set, Set 요소 접근시 O(1)

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        // 오름차순 처리를 위한 정렬
        Arrays.sort(arr);

        // 백트래킹 실행
        backtrack(0, 0, new StringBuilder());

        // 결과 출력
        System.out.println(sb);
    }
    
    /**
     * 백트래킹
     * @param depth 방문한 숫자의 수
     * @param check 넣은 숫자의 수
     * @param temp 임시 StringBuilder
     */
    static public void backtrack(int depth, int check, StringBuilder temp) {
        // 종료 조건
        if (M == check) {                               // M개를 다 뽑았을 때
            if (!duple.contains(temp.toString())) {     // 중복이 아닐 때
                sb.append(temp).append("\n");       // 결과 저장
                duple.add(temp.toString());             // 중복 처리
            }
            return;
        } else if (N == depth) {                        // 끝까지 갔을 때
            return;
        }

        int len = temp.length();                        // 현재 길이 저장, 복원을 위해서~

        temp.append(arr[depth]).append(" ");        // 현재 숫자를 넣음
        backtrack(depth + 1, check + 1, temp);          // 현재 숫자를 넣는 경우
        temp.setLength(len);                            // 복원
        backtrack(depth + 1, check, temp);              // 현재 숫자를 넣지 않는 경우
    }
}
