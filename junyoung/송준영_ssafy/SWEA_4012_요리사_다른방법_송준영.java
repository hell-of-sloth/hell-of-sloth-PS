import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA_4012_요리사
 * 난이도 4/10
 * 조합, NP
 * 
 * 이 코드는 부분집합을 이용한 풀이이며 272ms 27,700kb이다.
 */
public class SWEA_4012_요리사_다른방법_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] synergy;

    static int minVal;

    public static void main(String[] args) throws Exception {
        int T = parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, minVal));
        }
        
        System.out.println(sb);
    }
    
    public static void solve() throws Exception {
        N = parseInt(br.readLine());
        synergy = new int[N][N];

        minVal = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                synergy[i][j] = parseInt(st.nextToken());
            }
        }

        backtrack(0, 0, 0);
    }

    public static void backtrack(int depth, int select, int flag) {
        if (depth == N && select != N/2)
            return;

        if (select == N / 2) {
            // System.out.println("안들어옴");
            int firstVal = 0, secondVal = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j)
                        continue;

                    if ((flag & 1 << i) != 0 && (flag & 1 << j) != 0) {
                        firstVal += synergy[i][j];
                    } else if ((flag & 1 << i) == 0 && (flag & 1 << j) == 0) {
                        secondVal += synergy[i][j];
                    }
                }
            }

            // System.out.print(firstVal + " : ");
            // System.out.println(secondVal);
            minVal = Math.min(minVal, Math.abs(firstVal - secondVal));
            return;
        }
        
        backtrack(depth + 1, select + 1, (flag | 1 << depth));
        backtrack(depth + 1, select, flag);
    }
}
