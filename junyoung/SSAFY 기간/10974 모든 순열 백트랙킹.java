// 체감 난이도 3/10 백트랙킹
// 처음 1960ms -> BufferedWriter 사용 612ms
// -> StringBuilder 추가 216ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        boolean[] selected = new boolean[N+1];
        backtracking(N, arr, selected, 0);
        bw.write(sb.toString());
        bw.flush(); // 남아있는 데이터를 모두 출력시킴
    }

    static void backtracking(int N, int[] arr, boolean[] selected, int idx) throws Exception {

        if (idx == N) {
            for (int i = 0; i < N; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i < N+1; i++) {
            if (selected[i]) continue;
            selected[i] = true;
            arr[idx] = i;
            backtracking(N, arr, selected, idx + 1);
            selected[i] = false;
        }
    }
}
