import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_2578_빙고
 * 난이도 2/10
 * 완전탐색? 구현
 * 104ms
 * 빙고가 가능한 경우를 모두 체크하면서 빙고가 3개 이상인 경우가 되면 출력
 */
public class Main_2578_빙고 {
    // 빠른 입력을 위한 BufferedReader + StringTokenizer
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // 빙고판과 정답을 저장할 배열
    static int[][] bingo = new int[5][5];
    static int[] answers = new int[25];
    public static void main(String[] args) throws Exception {
        // 입력 처리
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                bingo[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                answers[i * 5 + j] = Integer.parseInt(st.nextToken());
            }
        }

        // 빙고를 체크하면서 3개 이상이 되면 출력
        for (int i = 0; i < 25; i++) {
            find(answers[i]);
            if (check() >= 3) {
                System.out.println(i+1);
                break;
            }
        }
    }

    // 빙고판에서 해당 숫자를 찾아서 0으로 바꿈 -> 완탐
    public static void find(int num) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (bingo[i][j] == num) {
                    bingo[i][j] = 0;
                    return;
                }
            }
        }
    }

    // 빙고가 몇 개인지 체크
    public static int check() {
        int cnt = 0;    // 빙고 개수

        // 가로 빙고
        for (int i = 0; i < 5; i++) {
            if (bingo[i][0] == 0 && bingo[i][1] == 0 && bingo[i][2] == 0 && bingo[i][3] == 0 && bingo[i][4] == 0) {
                cnt++;
            }
        }
        // 세로 빙고
        for (int i = 0; i < 5; i++) {
            if (bingo[0][i] == 0 && bingo[1][i] == 0 && bingo[2][i] == 0 && bingo[3][i] == 0 && bingo[4][i] == 0) {
                cnt++;
            }
        }

        // 대각선 빙고
        if (bingo[0][0] == 0 && bingo[1][1] == 0 && bingo[2][2] == 0 && bingo[3][3] == 0 && bingo[4][4] == 0) {
            cnt++;
        }

        if (bingo[0][4] == 0 && bingo[1][3] == 0 && bingo[2][2] == 0 && bingo[3][1] == 0 && bingo[4][0] == 0) {
            cnt++;
        }

        return cnt;
    }
}
