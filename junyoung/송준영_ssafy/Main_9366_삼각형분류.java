import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;


public class Main_9366_삼각형분류 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;

    public static void main(String[] args) throws Exception {
        N = parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int a, b, c;
            a = parseInt(st.nextToken());
            b = parseInt(st.nextToken());
            c = parseInt(st.nextToken());

            sb.append(String.format("Case #%d: %s\n", i, isTri(a, b, c)));
        }
        System.out.println(sb);
    }
    
    public static String isTri(int a, int b, int c) {
        if (a >= b + c || b >= a + c || c >= a + b) {
            return "invalid!";
        }

        if (a == b && b == c) {
            return "equilateral";
        } else if (a == b || b == c || a == c) {
            return "isosceles";
        } else {
            return "scalene";
        }
    }
}
