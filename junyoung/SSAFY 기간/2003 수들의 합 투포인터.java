// 체감 난이도 2/10 투포인터
// 투포인터 구현이 은근 헷갈림


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String args[]) throws Exception {
        int N, M;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;
        int sum = 0;
        int L = 0;
        for (int R = 0; R < N; R++) {
            sum += arr[R];
            while (sum > M) {
                sum -= arr[L];
                L++;
            }
            if (sum == M) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
