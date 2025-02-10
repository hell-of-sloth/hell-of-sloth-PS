import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Main_15656_N과M7
 * 난이도 3/10
 * 조합, 백트랙킹
 * 중복을 허용하는 조합을 구해야 하는데 오름차순으로 뽑아야 한다
 * 백트래킹에 for문을 넣어주고 넣었는지 안 넣었는지를 확인할 필요 없어서 방문체크는 안 해주었다
 */
public class Main_15656_N과M7 {
    // 빠른 입력과 출력 위한 변수
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // N과 M을 입력받을 변수와 배열
    static int N, M;
    static int[] arr;

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

        // 오름차순 대로 뽑기 위해 정렬 먼저 처리
        Arrays.sort(arr);

        // 백트래킹 시작
        backtrack(0, 0, new StringBuilder());

        // 출력
        System.out.println(sb);
    }
    
    /**
     * 백트래킹 함수
     * @param depth 탐색한 개수
     * @param check 뽑은 개수
     * @param temp  임시 저장할 StringBuilder
     */
    public static void backtrack(int depth, int check, StringBuilder temp) {
        // 종료 조건
        if (check == M) {                       // 뽑은 개수가 M개가 되면
            sb.append(temp).append("\n");
            return;
        } else if (depth == N) { // 탐색한 개수가 N개가 되면
            return;
        }

        // 기존 StringBuilder의 길이 저장 -> 복원을 위해서
        int len = temp.length();

        // 모든 글자 탐색하고 넣기
        for (int i = 0; i < N; i++) {
            temp.append(arr[i]).append(" ");
            backtrack(depth + 1, check + 1, temp);  // 다음 탐색
            temp.setLength(len);
        }
    }
}