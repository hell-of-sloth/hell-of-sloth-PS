
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_13251_조약돌꺼내기_개망버전 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int sumStone = 0;
        long sameTotal = 0;
        for (int i = 0; i < M; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            sameTotal += calJohap(tmp, K);
            sumStone += tmp;
        }
        long total = calJohap(sumStone, K);
        System.out.println(sumStone);
        System.out.println("sameTotal : " + sameTotal);
        System.out.println("total : " + total);
        System.out.println((double) sameTotal / total);
    }

    public static long calJohap(int N, int K) {
        if (N < K) {
            return 0;
        } else if (N == K) {
            return 1;
        }

        long bunja = 1;
        long bunmo = 1;

        for (int i = 0; i < K; i++) {
            bunja *= (N-i);
        }
        for (int i = 1; i <= K; i++) {
            bunmo *= i; 
        }

        System.out.println("분자 : " + bunja);
        System.out.println("분모 : " + bunmo);

        return bunja / bunmo;
    }
}
