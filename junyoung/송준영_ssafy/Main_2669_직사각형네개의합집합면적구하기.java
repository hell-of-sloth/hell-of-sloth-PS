import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 처음에는 겁나 어려운 문제인줄 알았다
 * (사각형 좌표 구해서 겹치는 부위 빼고... 다시더하고..)
 * 그런데 생각해보니 좌표 배열 만들어서 각 사각형의 좌표에 1을 넣어주면
 * 좌표의 1만 세면 그 넓이를 구할 수 있다!
 * 좌표 크기도 100 * 100 에다가 직사각형도 4개 뿐이라 이건 확실히 완전탐색이었다
 * 104ms
 */
public class Main_2669_직사각형네개의합집합면적구하기 {
    // 입력 처리
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        int[][] rectangles = new int[4][4];                                     // 4개의 직사각형 좌표
        int[][] map = new int[101][101];                                        // 좌표 배열

        // 직사각형 좌표 입력
        for (int i = 0; i <4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                rectangles[i][j] = Integer.parseInt(st.nextToken());
            }    
        }

        // 좌표 배열에 직사각형 1로 표시 -> 겹쳐도 상관 없이 1로됨
        for (int i = 0; i < 4; i++) {
            for (int j = rectangles[i][0]; j < rectangles[i][2]; j++) {
                for (int k = rectangles[i][1]; k < rectangles[i][3]; k++) {
                    map[j][k] = 1;
                }
            }
        }

        // 직사각형 넓이 cnt
        int cnt = 0;

        // 완전탐색으로 1인 좌표만 세기 -> 100*100 -> 10000정도 -> 1초 안에 가능 (약 1억연산 == 1초)
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (map[i][j] == 1) {
                    cnt++;
                }
            }
        }

        // 답안 출력
        System.out.println(cnt);
    }
}


