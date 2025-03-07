import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_2491_수열
 * 난이도 3/10
 * DP
 * 수열이 주어지고 연속된 수열의 길이가 최대인 것을 찾는 문제
 * 커지는거, 작아지는거 각각 저장
 * 그리고 최대값을 찾는다
 * 한번만 반복하면서 탐색 가능
 * 다른 방법은 없을까?
 */
public class Main_2491_수열 {
    // 빠른 입력과 토큰분할
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        // 입력 처리
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 커지는 수열 길이 저장, 작아지는 수열 길이 저장
        int dpBig = 1;
        int dpSmall = 1;

        int max = 1;    // 최대값, 결과

        // 한번만 반복하면서 탐색
        for (int i = 1; i < N; i++) {
            // 커지는 수열 길이 저장
            if (arr[i] >= arr[i - 1]) {
                dpBig++;
            } else {    // 커지는게 끊기면 다시 1로 초기화 + 최대값 갱신
                max = Math.max(max, dpBig);
                dpBig = 1;
            }

            // 작아지는 수열 길이 저장
            if (arr[i] <= arr[i - 1]) {
                dpSmall++;
            } else {    // 작아지는게 끊기면 다시 1로 초기화 + 최대값 갱신
                max = Math.max(max, dpSmall);
                dpSmall = 1;
            }
        }

        // 마지막에 최대값 갱신
        max = Math.max(max, dpBig);
        max = Math.max(max, dpSmall);

        // 결과 출력
        System.out.println(max);
    }
}
