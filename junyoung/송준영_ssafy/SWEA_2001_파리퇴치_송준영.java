import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA 2001 파리퇴치
 * 난이도 3/10
 * 누적합
 * 100ms 26,000 kb
 * 
 * N x N 배열에서 M x M 크기의 파리채를 움직여서 잡을 수 있는 파리의 최대 수를 구하는 문제
 * 누적합을 이용해서 풀 수 있다.
 * 각 인덱스는 그렇게 되면 0 ~i, 0 ~ j까지의 합을 가지고 있다.
 * 그러면 해당 인덱스에서 -M, -M을 해주면 i-M, j-M까지의 합을 구할 수 있고 그것을 빼준후 겹치는 부분을 다시 더해주면 된다
 * 
 */
public class SWEA_2001_파리퇴치_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;        // N, M
    static int[][] arr;     // 배열, 누적합 저장
    static int result;      // 결과 값

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 수 입력
        int T = parseInt(br.readLine());

        // 테스트 케이스 수만큼 반복
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, result));
        }

        // 출력
        System.out.println(sb);
    }

    /**
     * 메인 로직 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        result = 0;
        arr = new int[N][N];

        // 배열 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }

        // 누적합 계산
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                arr[i][j] += arr[i][j - 1];
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] += arr[i - 1][j];
            }
        }

        // for (int[] i : arr) {
        //     System.out.println(Arrays.toString(i));
        // }

        // 최대값 구하기
        for (int i = M - 1; i < N; i++) {
            for (int j = M - 1; j < N; j++) {
                int temp;
                if (i - M >= 0 && j - M >= 0) {
                    temp = arr[i][j] - arr[i - M][j] - arr[i][j - M] + arr[i - M][j - M];
                } else if (i - M >= 0) {
                    temp = arr[i][j] - arr[i - M][j];
                } else if (j - M >= 0) {
                    temp = arr[i][j] - arr[i][j - M];
                } else {
                    temp = arr[i][j];
                }
                result = Math.max(result, temp);    // 최대값 갱신
            }
        }
    }
}
