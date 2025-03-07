import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_27433_팩토리얼2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static long result = 1;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        
        for (int i = 1; i <= N; i++) {
            result *= i;
        }

        System.out.println(result);
    }
}
