import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * Main_14729_칠무해
 * 난이도 1/10
 * 정렬
 * 2000ms
 * 
 * double 배열의 정렬, 7개의 작은 수를 출력
 * 가장 작은 7개만 출력해서 heap을 사용해서 할 수도 있을 것 같다 -> 시간을 줄일 수 있을 듯
 */
public class Main_14729_칠무해 {
    // 빠른 입출력
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // 변수 선언
    static int N;
    static double[] scores;
    public static void main(String[] args) throws Exception {
        // 입력 및 초기화
        N = parseInt(br.readLine());
        scores = new double[N];

        // 점수 입력
        for (int i = 0; i < N; i++) {
            scores[i] = Double.parseDouble(br.readLine());
        }

        // 정렬
        Arrays.sort(scores);

        // sb에 출력 입력, %.3f는 소수점 3자리까지 출력
        for (int i = 0; i < 7; i++) {
            sb.append(String.format("%.3f\n", scores[i]));
        }

        // 출력
        System.out.println(sb);
    }
}
