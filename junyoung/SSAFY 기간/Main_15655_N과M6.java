import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * Main_15655_N과M(6)
 * 난이도 2/10
 * 조합, 정렬?
 * N개의 자연수 중에서 M개를 고른 수열 + 오름차순
 * 조합의 경우 두개의 재귀를 하는 방법이 더 빠르다! (수를 넣냐 안 넣냐)
 * 빠른 출력을 위해서 StringBuilder를 사용했다
 * 오름차순으로 넣기 위해서 먼저 정렬을 수행하였음
 */
public class Main_15655_N과M6 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // static 변수 선언 -> 다른 메소드에서도 쉽게 접근
    static int N, M;
    static int[] arr, visited;
    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        arr = new int[N];
        visited = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        // 오름차순으로 넣어야 해서 먼저 정렬을 해주었음
        Arrays.sort(arr);

        // 조합을 위한 재귀함수 호출
        backtrack(0, 0, new StringBuilder());

        // 출력
        System.out.println(sb);
    }

    /**
     * 조합을 위한 재귀함수
     * @param check 현재까지 선택한 수의 개수
     * @param depth 현재까지 탐색한 깊이
     * @param temp  현재까지 선택한 수열
     */
    public static void backtrack(int check, int depth, StringBuilder temp) {
        // 종료조건
        if (M == check) {           // M개를 선택했으면 지금까지 값 sb에 넣기
            sb.append(temp.toString()).append("\n");
            return;
        } else if (N == depth) {    // 끝까지 탐색했는데 M개를 못 골랐으면 그냥 종료
            return;
        }

        // 현재 수를 넣기 전 길이 저장
        int len = temp.length();

        // 현재 수를 넣는 경우, 넣지 않는 경우
        temp.append(arr[depth]).append(" ");
        backtrack(check+1, depth+1, temp);      // 현재 수를 넣는 경우 -> check+1, depth+1
        temp.setLength(len);                    // 현재 수를 넣은 경우를 지우기 위해 길이를 저장해놨다가 다시 설정
        backtrack(check, depth+1, temp);        // 현재 수를 넣지 않는 경우 -> depth+1 만
    }
}
