import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;   // import static

/**
 * Main_10163_색종이
 * 난이도 3/10
 * 배열, 브루트포스, 구현
 * 이 문제는 최대 배열 크기가 1001 * 1001 이고 각 색종이 최대 크기가 이와 같아서
 * 100번 반복하면 1억이라 간당간당 할 것같아서 고민했는데 248ms로 통과하였다
 * 2차원 배열로 매트릭스를 초기화 하고 색종이를 덮는 부분을 각 순서 번호로 덮어 씌운다
 * 마지막에 매트릭스를 모두 순회하며 색종이 번호를 세어 출력한다
 */
public class Main_10163_색종이 {
    // 빠른 입출력과 문자 나누기
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        // 입력 처리
        int N = parseInt(br.readLine());
        int[][] mat = new int[1001][1001];  // 색종이를 덮을 수 있는 매트릭스
        int[] papers = new int[N+1];        // 색종이 번호별로 몇 칸을 덮었는지 저장할 배열
                                            // 인덱스 1은 1번째 색종이 면적

        // 색종이를 덮는 부분
        for (int i = 1; i < N+1; i++) {
            int x, y, w, h;                             // 색종이의 시작 좌표(x, y) 및 너비, 높이
            
            // 입력 처리
            st = new StringTokenizer(br.readLine());
            x = parseInt(st.nextToken());
            y = parseInt(st.nextToken());
            w = parseInt(st.nextToken());
            h = parseInt(st.nextToken());

            // 매트릭스에 색종이 번호로 덮어 씌우기
            for (int j = x; j < x + w; j++) {
                for (int k = y; k < y + h; k++) {
                    mat[j][k] = i;
                }
            }
        }

        // 색종이 번호별로 몇 칸을 덮었는지 세기 (브루트포스)
        for (int i = 0; i < 1001; i++) {
            for (int j = 0; j < 1001; j++) {
                papers[mat[i][j]]++;
            }
        }

        // 출력을 위해 StringBuilder에 저장
        for (int i = 1; i < N+1; i++) {
            sb.append(papers[i] + "\n");
        }

        // 출력
        System.out.println(sb.toString());
    }
}
