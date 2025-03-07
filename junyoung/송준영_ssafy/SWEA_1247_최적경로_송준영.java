import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA_1247_최적경로_송준영
 * 난이도 5/10
 * 순열
 * 2018ms 28MB
 * 
 * 순열을 이용한 문제풀이
 * 시간이 널널해서 순열을 이용한 문제풀이를 선택
 * 순열을 이용한 문제풀이는 순열을 구하는 함수와 순열을 이용한 계산 함수로 나누어서 구현
 * 순열을 구하는 함수에서 순열을 구하고 순열을 이용한 계산 함수에서 순열을 이용하여 계산
 * 
 * 순열을 구하면서 가지치기를 하면 시간이 더 빨라질 듯함 or NP 이용
 */
public class SWEA_1247_최적경로_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;               // 고객 수
    static int[][] vertex;      // 고객 좌표
    static int[] permu, home, company;  // 순열, 집, 회사
    static boolean[] visited;   // 방문 배열
    static int result;          // 결과

    public static void main(String[] args) throws Exception {
        // 테스트케이스 입력 및 반복
        int T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, result));
        }
        // 출력
        System.out.println(sb);
    }
    
    /**
     * 메인 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        company = new int[2];
        home = new int[2];
        vertex = new int[N][2];
        permu = new int[N];
        visited = new boolean[N];
        result = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        company[0] = parseInt(st.nextToken());
        company[1] = parseInt(st.nextToken());
        home[0] = parseInt(st.nextToken());
        home[1] = parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            vertex[i][0] = parseInt(st.nextToken());
            vertex[i][1] = parseInt(st.nextToken());
            // System.out.println("vertex : " + vertex[i][0] + " " + vertex[i][1]);
        }

        // 순열 구하기
        permutaion(0);
    }

    /**
     * 정석적인 순열 구하기 -> 시간 복잡도가 높음
     * @param depth 순열의 깊이
     */
    public static void permutaion(int depth) {
        if (depth == N) { // 순열이 완성되면 계산
            calResult();
            return;
        }

        // 순열 구하기
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permu[depth] = i;
                permutaion(depth + 1);
                visited[i] = false;
            }
        }
    }

    /**
     * 순열을 이용한 계산 함수
     * 순열을 이용하여 회사에서 출발하여 고객을 방문하고 집으로 돌아오는 경로 계산
     */
    public static void calResult() {
        int temp = 0;

        temp += calDist(company[0], company[1], vertex[permu[0]][0], vertex[permu[0]][1]);
        // System.out.print(temp + " ");
        for (int i = 1; i < N; i++) {
            temp += calDist(vertex[permu[i - 1]][0], vertex[permu[i - 1]][1], vertex[permu[i]][0], vertex[permu[i]][1]);
            // System.out.print(temp + " ");
        }
        temp += calDist(vertex[permu[N - 1]][0], vertex[permu[N - 1]][1], home[0], home[1]);
        // System.out.print(temp + "\n");

        result = Math.min(result, temp);
    }

    /**
     * 거리 계산 함수
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return  두 점 사이의 거리
     */
    public static int calDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
