import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA 6109 추억의 2048 게임
 * 난이도 3/10
 * 구현, 시뮬레이션, 배열
 * 143ms 39mb
 * 
 * 방향에 따라 채우는 로직은 switch문으로 분기를 나눠서 각자 따로 구현 했다 -> logic 메서드 안에 구현
 * 채워지는 인덱스 순서만 조작하면 됨
 * 한 줄씩 채우는 로직을 구현하고, 이를 N번 반복하면 된다. -> 아마 한꺼번에 채우는 메서드로 만들면 실행시간은 더 단축될 것으로 예상
 * 
 * 핵심은 채울때 한번 합쳐진 곳은 다시 합쳐지지 않도록 하는 것이다. -> logic 메서드에서 부가설명
 */
public class SWEA_6109_추억의2048게임_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;       // 배열 크기
    static String S;    // 방향
    static int[][] arr; // 배열

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 수 입력 및 메인 메서드 실행
        int T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d\n", t));
            solve();
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
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        S = st.nextToken();
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }

        // 방향에 따라 한 줄씩 채우는 로직
        for (int i = 0; i < N; i++) {
            logic(i);
        }

        // 결과 sb에 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.append('\n');
        }
    }

    /**
     * 2048 게임 규칙에 따라 한 줄 단위로 변형해주는 메서드
     * @param index
     */
    public static void logic(int index) {
        int[] temp = new int[N];    // 임시로 결과를 저장할 배열 (한 줄)
        int idx = 0;                // temp 배열의 인덱스

        switch (S) {    // 방향에 따라 채우는 로직이 다름
            case "up":
                for (int i = 0; i < N; i++) {
                    if (arr[i][index] == 0) continue;   // 0은 무시하고 넘어감

                    if (temp[idx] == 0) {                       // temp가 비어있으면 채워줌
                        temp[idx] = arr[i][index];
                    } else if (temp[idx] == arr[i][index]) {    // temp에 이미 값이 있고, 같은 값이면 합쳐줌 -> 그 후 temp 인덱스 넘기기
                        temp[idx++] += arr[i][index];
                    } else {                                    // temp에 이미 값이 있고, 다른 값이면 그 다음 인덱스에 채워줌
                        temp[++idx] += arr[i][index];
                    }
                }
                for (int i = 0; i < N; i++) {                   // temp에 저장된 값을 arr에 저장
                    arr[i][index] = temp[i];
                }
                break;
            case "down":
                for (int i = N - 1; i >= 0; i--) {
                    if (arr[i][index] == 0) continue;

                    if (temp[idx] == 0) {
                        temp[idx] = arr[i][index];
                    } else if (temp[idx] == arr[i][index]) {
                        temp[idx++] += arr[i][index];
                    } else {
                        temp[++idx] += arr[i][index];
                    }
                }
                for (int i = 0; i < N; i++) {
                    arr[N - i - 1][index] = temp[i];
                }
                break;
            case "left":
                for (int i = 0; i < N; i++) {
                    if (arr[index][i] == 0) continue;

                    if (temp[idx] == 0) {
                        temp[idx] = arr[index][i];
                    } else if (temp[idx] == arr[index][i]) {
                        temp[idx++] += arr[index][i];
                    } else {
                        temp[++idx] += arr[index][i];
                    }
                }
                arr[index] = temp;
                break;
            case "right":
                for (int i = N - 1; i >= 0; i--) {
                    if (arr[index][i] == 0) continue;

                    if (temp[idx] == 0) {
                        temp[idx] = arr[index][i];
                    } else if (temp[idx] == arr[index][i]) {
                        temp[idx++] += arr[index][i];
                    } else {
                        temp[++idx] += arr[index][i];
                    }
                }
                for (int i = 0; i < N; i++) {
                    arr[index][N - i - 1] = temp[i];
                }
                break;
        }
    }
}
