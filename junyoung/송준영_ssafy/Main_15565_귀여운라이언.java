import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * Main_15565_귀여운라이언
 * 난이도 5/10
 * 투포인터
 * 376ms
 * 
 * 같은 위치에서 시작하는 투 포인터로 접근하여 풀었다
 * 로직은 r이 이동하면서 K개 이상 라이언이 있는지 확인하고, K개 이상이면 l을 이동시키면서 최소 길이를 찾는 방식
 * K개를 찾으면 l이 다음 1이 나올때까지 이동하면서 최소 길이를 찾는다
 * 
 * 이 로직을 생각했는데 생각보다 구현이 햇갈려서 시간이 좀 걸렸다
 * l을 0부터 해야하는지 -1 부터 해야하는지도 고려해야 하고
 * l을 ++l 로 해야하는지 l++로 해야하는지도 고려해야 했었다
 * 이것을 잘 못 하면 특정 케이스를 빼먹을 수 있기 때문에 테케 검사를 철저히 해야한다.
 * 
 * K가 1일때는 어차피 1이라 1이 나오면 바로 종료하게 해주었다
 */
public class Main_15565_귀여운라이언 {
    // 빠른 입출력
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // 변수 선언
    static int N, K;                        // 인형의 개수, 라이언의 개수
    static int[] dolls;                     // 인형 배열
    static int result = Integer.MAX_VALUE;  // 결과값

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        dolls = new int[N];

        // 인형 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dolls[i] = parseInt(st.nextToken());
        }

        int lionCnt = 0;    // 찾은 라이언 개수
        int l = -1;         // l 포인터, -1 부터 시작하는 이유는 ++l 로 시작하기 때문에 0번째 인덱스 검사를 하기 위해서

        // -1 부터 시작하면 ++l 해야 0인덱스 검사 가능하고 1찾으면 1이 있는 인덱스에서 멈춤
        // 0 부터 시작하면 l++ 해야 0인덱스 검사 가능하고 1찾으면 1이 있는 인덱스 다음 인덱스에서 멈춤
        // 그래서 각 방식에 따라 계산 방식이 달라짐

        // 투포인터
        // r을 오른쪽 포인터로 for문으로 r을 이동시켰다
        for (int r = 0; r < N; r++) {   
            // 라이언 인형 발견시 
            if (dolls[r] == 1) {
                lionCnt++;      //  라이언 개수 증가
                if (K == 1) {   // K가 1이면 바로 종료
                    result = 1; // 결과는 1로
                    break;
                }
            }

            // 라이언 개수가 K개 면
            if (lionCnt == K) {
                // l을 이동시키면서 최소 길이 찾기
                while (true) {
                    // l증가 시킨 후 1찾기
                    if (dolls[++l] == 1) {
                        lionCnt--;      // 찾으면 라이언 개수 감소
                        break;
                    }
                }

                // 최소 길이 찾기
                result = Math.min(result, r - l + 1);
            }
        }

        // 못 찾으면 -1 출력
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
}
