import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * Main_17281_야구_송준영
 * 난이도 7/10
 * 순열
 * 468ms 15,000KB
 * 
 * 순열을 이용하여 1번 선수부터 9번 선수까지 타순을 정하고
 * 각 타순에 대해 게임 시뮬레이션을 수행하여 최대 점수를 구함
 * 나는 각 이닝 마다 최적의 순서를 찾아서 하는 줄 알았다...
 * 이래서 문제를 잘 읽어야 한다.
 * 1번째 선수는 4번으로 고정되어 있어야 하므로 이것을 구현하는게 중요 할 것이다.
 */
public class Main_17281_야구_송준영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;                           // 이닝 수
    static int[][] players;                 // game[i][j] : i번째 이닝에서 j번 선수의 결과
    static int maxScore = 0;                // 최대 점수
    static int[] order = new int[9];        // 타순 (배열의 값은 선수 번호)
    static boolean[] used = new boolean[9]; // 순열 생성시 사용 여부 (player 0은 고정)

    public static void main(String[] args) throws Exception {
        // 입력 처리
        N = parseInt(br.readLine());
        players = new int[N][9];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                players[i][j] = parseInt(st.nextToken());
            }
        }

        // player 0을 4번 타자로 고정 (인덱스 3에 고정)
        order[3] = 0;
        used[0] = true;

        // 순열 생성
        permute(0);

        // 결과 출력
        System.out.println(maxScore);
    }

    /**
     * 순열 생성
     * @param depth 순열의 깊이
     */
    static void permute(int depth) {
        if (depth == 9) { // 타순 완성 → 게임 시뮬레이션
            int score = game();
            maxScore = Math.max(maxScore, score);
            return;
        }
        if (depth == 3) { // 4번 타자는 고정되어 있으므로 넘어감
            permute(depth + 1);
            return;
        }

        // 각 선수 순서 순열 생성
        for (int player = 1; player < 9; player++) {
            if (!used[player]) {
                used[player] = true;
                order[depth] = player;
                permute(depth + 1);
                used[player] = false;
            }
        }
    }

    /**
     * 게임 시뮬레이션
     * @return  게임 결과 점수
     */
    static int game() {
        int score = 0;
        int playerOrder = 0; // 다음 타자 인덱스 (0~8)

        // 각 이닝에 대해 게임 시뮬레이션
        for (int inning = 0; inning < N; inning++) {
            int outs = 0;
            int first = 0, second = 0, third = 0;

            // 3아웃까지 반복
            while (outs < 3) {
                int player = order[playerOrder];
                int result = players[inning][player];

                switch (result) {
                    case 0: // 아웃
                        outs++;
                        break;
                    case 1: // 안타
                        score += third;
                        third = second;
                        second = first;
                        first = 1;
                        break;
                    case 2: // 2루타
                        score += third + second;
                        third = first;
                        second = 1;
                        first = 0;
                        break;
                    case 3: // 3루타
                        score += third + second + first;
                        third = 1;
                        second = 0;
                        first = 0;
                        break;
                    case 4: // 홈런
                        score += third + second + first + 1;
                        third = 0;
                        second = 0;
                        first = 0;
                        break;
                    }
                // 다음 타자
                playerOrder++;
                playerOrder %= 9;
            }   
        }
        return score;
    }
}
