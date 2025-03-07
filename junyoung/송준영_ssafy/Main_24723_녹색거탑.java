import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Main_24723_녹색거탑
 * 난이도 1/10
 * 수학
 * 쉬어가는 문제
 * 2의 층의 수 제곱 만큼이 답이 된다
 */
public class Main_24723_녹색거탑 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        double result = Math.pow(2, N);     // 2의 N승

        System.out.println((int)result);
    }
}