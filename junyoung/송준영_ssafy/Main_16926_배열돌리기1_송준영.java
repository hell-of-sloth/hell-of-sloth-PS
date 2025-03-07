import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * Main_16926_배열돌리기1
 * 난이도 5/10
 * 구현, 시뮬레이션
 * 756ms 300mb => 580ms 30mb
 * 
 * 배열을 돌리는 문제
 * 달팽이와 비슷하게 풀면 된다
 * 나는 편차를 주고 그 편차가 1 올라가면 한 겹 안으로 들어가게끔 구현하였다.
 * while문으로 돌려 시작 위치가 끝 위치보다 커지면 종료하게 구현하였음
 * 
 * 첫 값을 temp로 저장하고 역방향으로 할당해주면 마지막에 temp만 넣어주면 arr를 새로 할당할 필요가 없다 -> 메모리 절약
 * 달팽이와 마찬가지로 dir의 부호를 반전시켜 방향을 정해주었다.
 */
public class Main_16926_배열돌리기1_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // 입력 변수 선언
    static int N, M, R; // 배열의 크기 (N, M), 회전 횟수 (R)
    static int[][] arr; // 배열

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        R = parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }

        // 회전 횟수만큼 돌리기
        for (int i = 0; i < R; i++) {
            turn();
        }

        // 결과 sb에 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        // 결과 출력
        System.out.println(sb);
    }
    
    /**
     * 배열을 돌리는 메서드
     */
    public static void turn() {
        int dir = 1;    // 방향 1 or -1
        int bias = 0;   // 편차 -> 0 : 제일 바깥, 1 : 한 칸 안쪽, 2 : 두 칸 안쪽, ...
        int minSide = Math.min(N, M); // 가로 세로 중 더 작은 값 -> 돌리는 횟수 계산을 위해서
        
        // 편차가 가로 세로 중 더 작은 값을 고르고
        // 편차가 (minSide - 1) - bias보다 작을 때까지 돌린다 -> 처음 시작이 끝 부분보다 작을 때 까지
        while (bias < (minSide - 1) - bias) {
            int start = bias, end = bias;   // 편차 값이 곧 시작 값이다 -> 어쩌다 보니 그럼 (좋다!)

            int temp = arr[start][end];     // 첫 값은 나중에 없어지니까 미리 남겨둠

            // 달팽이처럼 돌리기 x 2
            // 오른쪽, 아래, 왼쪽, 위로
            for (int k = 0; k < 2; k++) {
                // 가로
                for (int i = bias; i < (M - 1) - bias; i++) {
                    end += dir;     // 방향에 따라 증가
                    arr[start][end - dir] = arr[start][end];    // 이전 값에 할당
                }
                // 세로
                for (int i = bias; i < (N - 1) - bias; i++) {
                    start += dir;   // 방향에 따라 증가
                    if (start == bias && end == bias) { // 처음 값으로 돌아오면 temp를 넣어준다
                        arr[start - dir][end] = temp;
                        break;
                    }

                    arr[start - dir][end] = arr[start][end];    // 이전 값에 할당
                }

                dir *= -1;  // 방향 반전
            }

            bias++; // 편차 증가 -> 안 쪽으로~
        }
    }
}
