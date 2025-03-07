import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_22862_가장긴짝수연속한부분수열 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static int[] arr;
    static int result;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0, r = 0, cnt = 0;
        while (r < N) {
            if (arr[r] % 2 != 0) {
                cnt++;
            }
            
            if (cnt > K) {
                while (cnt > K) {
                    if (arr[l++] % 2 != 0) {
                        cnt--;
                    }
                }
            }

            result = Math.max(result, (r - l + 1) - cnt);

            r++;
        }
        System.out.println(result);
    }
}
