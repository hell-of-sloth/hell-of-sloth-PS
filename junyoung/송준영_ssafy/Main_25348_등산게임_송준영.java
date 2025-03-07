import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * 
 */
public class Main_25348_등산게임_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // 입력 변수
    // E: 에너지, H: 산 꼭대기 높이, N: 돌 개수
    // P: 돌 높이
    // E 범위 1000조
    // H 범위 1억
    static long E;
    static int H, N;
    static int[] P;

    static Node[][] dp;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        E = Long.parseLong(st.nextToken());
        H = parseInt(st.nextToken());
        N = parseInt(st.nextToken());

        // dp 배열 초기화
        // 
        dp = new Node[N][3]; 
        
        P = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            P[i] = parseInt(st.nextToken());
        }

        
    }

    public static class Node {
        int distance;
        int energy;
        
        public Node(int distance, int energy) {
            this.distance = distance;
            this.energy = energy;
        }
    }
}
